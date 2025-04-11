package controller;

import view.ExpenseTrackerView;
import java.util.List;
import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;

public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private TransactionFilter currentFilter;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;
    this.currentFilter = null;
    // Set up view event handlers
  }

  public void refresh() {
    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();
    
    // Apply filter if one is set
    if (currentFilter != null) {
      transactions = currentFilter.filter(transactions);
    }

    // Pass to view
    view.refreshTable(transactions);
  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    refresh(); // Use refresh to update the table with filtered results if applicable
    return true;
  }
  
  public void applyFilter(TransactionFilter filter) {
    this.currentFilter = filter;
    refresh();
  }
  
  public void clearFilter() {
    this.currentFilter = null;
    refresh();
  }
}