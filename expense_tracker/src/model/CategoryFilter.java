package model;

import java.util.List;
import java.util.stream.Collectors;
import controller.InputValidation;

public class CategoryFilter implements TransactionFilter {
    private String category;
    
    public CategoryFilter(String category) {
        this.category = category;
    }
    
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        if (!InputValidation.isValidCategory(category)) {
            return transactions; // Return all transactions if category is invalid
        }
        
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}