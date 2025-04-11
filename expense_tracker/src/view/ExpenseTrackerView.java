package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import model.AmountFilter;
import model.CategoryFilter;
import model.TransactionFilter;

import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  
  // Filter components
  private JButton filterByAmountBtn;
  private JButton filterByCategoryBtn;
  private JButton clearFilterBtn;
  private JFormattedTextField filterAmountField;
  private JTextField filterCategoryField;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    
    // Create filter panel
    JPanel filterPanel = new JPanel();
    filterPanel.setBorder(BorderFactory.createTitledBorder("Filter Transactions"));
    
    JLabel filterAmountLabel = new JLabel("Amount:");
    filterAmountField = new JFormattedTextField(format);
    filterAmountField.setColumns(10);
    
    JLabel filterCategoryLabel = new JLabel("Category:");
    filterCategoryField = new JTextField(10);
    
    filterByAmountBtn = new JButton("Filter by Amount");
    filterByCategoryBtn = new JButton("Filter by Category");
    clearFilterBtn = new JButton("Clear Filter");
    
    filterPanel.add(filterAmountLabel);
    filterPanel.add(filterAmountField);
    filterPanel.add(filterByAmountBtn);
    filterPanel.add(filterCategoryLabel);
    filterPanel.add(filterCategoryField);
    filterPanel.add(filterByCategoryBtn);
    filterPanel.add(clearFilterBtn);
  
    // Add panels to frame
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(inputPanel, BorderLayout.NORTH);
    topPanel.add(filterPanel, BorderLayout.SOUTH);
    
    add(topPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
  
    // Set frame properties
    setSize(800, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void refreshTable(List<Transaction> transactions) {
    // Clear existing rows
    model.setRowCount(0);
    // Get row count
    int rowNum = 0;
    double totalCost = 0;
    
    // Calculate total cost
    for(Transaction t : transactions) {
      totalCost += t.getAmount();
    }
    
    // Add rows from transactions list
    for(Transaction t : transactions) {
      model.addRow(new Object[]{++rowNum, t.getAmount(), t.getCategory(), t.getTimestamp()}); 
    }
    
    // Add total row
    Object[] totalRow = {"Total", null, null, totalCost};
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();
  }
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  
  public JButton getFilterByAmountBtn() {
    return filterByAmountBtn;
  }
  
  public JButton getFilterByCategoryBtn() {
    return filterByCategoryBtn;
  }
  
  public JButton getClearFilterBtn() {
    return clearFilterBtn;
  }
  
  public double getFilterAmountField() {
    if(filterAmountField.getText().isEmpty()) {
      return 0;
    } else {
      try {
        return Double.parseDouble(filterAmountField.getText().replace(",", ""));
      } catch (NumberFormatException e) {
        return 0;
      }
    }
  }
  
  public String getFilterCategoryField() {
    return filterCategoryField.getText();
  }
  
  public DefaultTableModel getTableModel() {
    return model;
  }
  
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    } else {
      try {
        return Double.parseDouble(amountField.getText().replace(",", ""));
      } catch (NumberFormatException e) {
        return 0;
      }
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }
  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}