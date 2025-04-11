package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseTrackerModel {

  // Change to private for encapsulation
  private List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  // Return an unmodifiable copy of the list
  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }
}