package ui;

import java.util.*;
import model.BudgetPlanner;
import model.Category;
import model.Expense;

public class BudgetTrackingApp {
    private int amount;
    private Category category;
    private String name;
    private BudgetPlanner expenses;

    public BudgetTrackingApp() {
        newExpensesList();
    }

    public void newExpensesList() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a monthly budget: ");
        int budget = in.nextInt();
        expenses = new BudgetPlanner(budget);
        String choice = "Y";
        while (choice.equals("Y")) {
            System.out.print("Any expense to add? Y/N ");
            choice = in.next();
            if (choice.equals("Y")) {
                addExpense();
            } else {
                System.out.print(expenses.getListOfExpenses());
                System.out.println("Remaining: " + expenses.getRemainingOfBudget());
            }
        }
        System.out.print("Want to see the number of certain categories? Y/N ");
        String choose = in.next();
        if (choose.equals("Y")) {
            seeNumOfCertainCategory();
        }
        System.out.println("Total: " + expenses.getSumOfExpenses());
    }

    public void addExpense() {
        Scanner in = new Scanner(System.in);
        System.out.print("Insert new expense: ");
        name = in.nextLine();
        System.out.print("Insert category, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        String category = in.next();
        this.category = Category.valueOf(category);
        System.out.print("Insert amount: ");
        amount = in.nextInt();
        Expense expense = new Expense(name, this.category, amount);
        expenses.addExpense(expense);
    }

    public void seeNumOfCertainCategory() {
        Scanner in = new Scanner(System.in);
        System.out.print("Insert category, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        String category = in.next();
        this.category = Category.valueOf(category);
        System.out.print(expenses.getNumOfCategories(this.category));
    }

}
