package com.ritesh.expensetracker;

public class Transaction {
    String description;
    double amount;
    boolean isExpense;
    String dateTime;

    public Transaction(String description, double amount, boolean isExpense, String dateTime) {
        this.description = description;
        this.amount = amount;
        this.isExpense = isExpense;
        this.dateTime = dateTime;
    }
}
