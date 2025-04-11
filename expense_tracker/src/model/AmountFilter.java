package model;

import java.util.List;
import java.util.stream.Collectors;
import controller.InputValidation;

public class AmountFilter implements TransactionFilter {
    private double amount;
    
    public AmountFilter(double amount) {
        this.amount = amount;
    }
    
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        if (!InputValidation.isValidAmount(amount)) {
            return transactions; // Return all transactions if amount is invalid
        }
        
        return transactions.stream()
                .filter(t -> t.getAmount() == amount)
                .collect(Collectors.toList());
    }
}