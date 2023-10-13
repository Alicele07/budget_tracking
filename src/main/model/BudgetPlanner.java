package model;

import java.util.*;

/*Represent a planner that user can see all of their expenses,
*calculate the sum amd remaining of all expenses
*see the number of certain categories in their expenses
 */
public class BudgetPlanner {
    private final int budget;
    private List<Expense> expenses;

    //REQUIRES: budget > 0
    //EFFECTS: create a BudgetPlanner with a specified amount of budget and instantiate a new expense list
    public BudgetPlanner(int budget) {
        this.budget = budget;
        expenses = new ArrayList<>();
    }

    //REQUIRES: expense != null
    //MODIFIES: this
    //EFFECTS: add new expense to ArrayList expenses
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public int getBudget() {
        return budget;
    }

    //MODIFIES: this
    public String getListOfExpenses() {
        StringBuilder expenses = new StringBuilder("Expenses\n" + "--------\n");
        for (Expense e: this.expenses) {
            expenses.append(String.format("%s%10s%6s\n", e.getName(), e.getCategory(), e.getAmount()));
        }
        return expenses.toString();
    }

    //EFFECTS: get the number of expenses
    public int  getNumOfExpenses() {
        return expenses.size();
    }

    //REQUIRES: category cannot be different from what defined in Category enum
    public int getNumOfCategory(Category category) {
        int count = 0;
        for (Expense e: this.expenses) {
            if (e.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }

    //MODIFIES: sum
    //EFFECTS: calculate the sum of all expenses in the expenses list
    public int getSumOfExpenses() {
        int sum = 0;
        for (Expense e: this.expenses) {
            sum += e.getAmount();
        }
        return sum;
    }

    //EFFECTS: get the remaining of budget after user enter in their expenses
    public int getRemainingOfBudget() {
        return getBudget() - getSumOfExpenses();
    }
}
