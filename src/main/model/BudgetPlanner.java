package model;

import java.util.*;

public class BudgetPlanner {
    private final int budget;
    private List<Expense> expenses;

    public BudgetPlanner(int budget) {
        this.budget = budget;
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public int getBudget() {
        return budget;
    }

    public String getListOfExpenses() {
        StringBuilder expenses = new StringBuilder("Expenses\n" + "--------\n");
        for (Expense e: this.expenses) {
            expenses.append(String.format("%s%10s%6s\n", e.getName(), e.getCategory(), e.getAmount()));
        }
        return expenses.toString();
    }

    public int  getNumOfExpenses() {
        return expenses.size();
    }

    public int getNumOfCategory(Category category) {
        int count = 0;
        for (Expense e: this.expenses) {
            if (e.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }

    public int getSumOfExpenses() {
        int sum = 0;
        for (Expense e: this.expenses) {
            sum += e.getAmount();
        }
        return sum;
    }

    public int getRemainingOfBudget() {
        return getBudget() - getSumOfExpenses();
    }
}
