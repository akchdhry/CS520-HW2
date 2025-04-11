# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Features

### Add Transactions
- Users can input a transaction amount and category.
- Input validation ensures the amount is numeric and the category is non-empty.

### Filter Transactions
- Users can filter transactions by **amount** or **category**.
- Filtering is implemented using the **Strategy Design Pattern**, allowing easy extension of filter criteria.
- Input validation is reused from Homework 1 to ensure correct filter input.

### Undo Feature (Planned)
- Users will be able to undo their last transaction removal.
- Design documented in `undo.txt`.
