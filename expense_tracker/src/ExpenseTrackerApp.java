import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import model.AmountFilter;
import model.CategoryFilter;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });
    
    // Handle filter by amount button clicks
    view.getFilterByAmountBtn().addActionListener(e -> {
      double filterAmount = view.getFilterAmountField();
      
      if (!InputValidation.isValidAmount(filterAmount)) {
        JOptionPane.showMessageDialog(view, "Invalid amount for filtering");
        view.toFront();
        return;
      }
      
      AmountFilter filter = new AmountFilter(filterAmount);
      controller.applyFilter(filter);
    });
    
    // Handle filter by category button clicks
    view.getFilterByCategoryBtn().addActionListener(e -> {
      String filterCategory = view.getFilterCategoryField();
      
      if (!InputValidation.isValidCategory(filterCategory)) {
        JOptionPane.showMessageDialog(view, "Invalid category for filtering");
        view.toFront();
        return;
      }
      
      CategoryFilter filter = new CategoryFilter(filterCategory);
      controller.applyFilter(filter);
    });
    
    // Handle clear filter button clicks
    view.getClearFilterBtn().addActionListener(e -> {
      controller.clearFilter();
    });
  }
}