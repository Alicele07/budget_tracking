package model;

import java.util.*;

public class BudgetPlanner {
    private int budget;
    private List<Expense> expenses;
    private List<Expense> categories;

    public BudgetPlanner(int budget) {
        this.budget = budget;
        expenses = new ArrayList<Expense>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public String getListOfExpenses() {
        String expenses = "Expenses\n" + "--------\n";
        for (int i = 0; i < this.expenses.size(); i++) {
            expenses += String.format("%s%10s%6s\n", this.expenses.get(i).getName(),
                    this.expenses.get(i).getCategory(), this.expenses.get(i).getAmount());
        }
        return expenses;
    }

    public String getNumOfCategories(Category category) {
        categories = new ArrayList<Expense>();
        String categories = "";
        int count = 0;
        for (int i = 0; i < this.expenses.size(); i++) {
            if (this.expenses.get(i).getCategory().equals(category)) {
                this.categories.add(this.expenses.get(i));
                count++;
            }
        }
        for (int i = 0; i < this.categories.size(); i++) {
            categories += String.format("%s%10s%6s\n", this.categories.get(i).getName(),
                    this.categories.get(i).getCategory(), this.categories.get(i).getAmount());
        }
        return categories + "You have " + count + " expenses that are in " + category + ".";
    }

    public int getSumOfExpenses() {
        int sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum += expenses.get(i).getAmount();
        }
        return sum;
    }

    public int getRemainingOfBudget() {
        int remaining = budget - getSumOfExpenses();
        return remaining;
    }
}
