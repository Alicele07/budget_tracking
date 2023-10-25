package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*Represent a planner that user can see all of their expenses,
*calculate the sum amd remaining of all expenses
*see the number of certain categories in their expenses
 */
public class BudgetPlanner implements Writable {
    private final int budget;
    private final List<Expense> expenses;

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
        StringBuilder expenses = new StringBuilder();
        for (Expense e: this.expenses) {
            expenses.append(String.format("%s%10s%6s\n", e.getName(), e.getCategory(), e.getAmount()));
        }
        return expenses.toString();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    //EFFECTS: get the number of expenses
    public int  getNumOfExpenses() {
        return expenses.size();
    }

    //REQUIRES: category cannot be different from what defined in Category enum
    public String getNumOfCategory(Category category) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Expense e: this.expenses) {
            if (e.getCategory().equals(category)) {
                count++;
                sb.append(String.format("%s%10s%6s\n", e.getName(), e.getCategory(), e.getAmount()));
            }
        }
        return "You have " + count + " expenses in " + category + " category.\n" + sb;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("budget", budget);
        json.put("expenses", expensesToJson());
        return json;
    }

    private JSONArray expensesToJson() {
        JSONArray jsonExpenses = new JSONArray();

        for (Expense e : expenses) {
            jsonExpenses.put(e.toJson());
        }
        return jsonExpenses;
    }
}
