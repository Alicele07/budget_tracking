package ui;

import java.util.*;
import model.BudgetPlanner;
import model.Category;
import model.Expense;

public class BudgetTrackingApp {
    private Category category;
    private BudgetPlanner expenses;

    public BudgetTrackingApp() {
        newExpensesList();
    }

    private void newExpensesList() {
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
                System.out.print("Number of expenses: " + expenses.getNumOfExpenses());
                System.out.println("Remaining: " + expenses.getRemainingOfBudget());
            }
        }
        System.out.print("Want to see the number of certain categories? Y/N ");
        String choose = in.next();
        if (choose.equals("Y")) {
            seeNumOfCategory();
        }
        System.out.println("Total: " + expenses.getSumOfExpenses());
    }

    public void addExpense() {
        Scanner in = new Scanner(System.in);
        System.out.print("Insert new expense: ");
        String name = in.nextLine();
        System.out.print("Insert category, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        String category = in.next();
        this.category = Category.valueOf(category);
        System.out.print("Insert amount: ");
        int amount = in.nextInt();
        Expense expense = new Expense(name, this.category, amount);
        expenses.addExpense(expense);
    }

    public void seeNumOfCategory() {
        Scanner in = new Scanner(System.in);
        System.out.print("Insert category you want to see, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        String category = in.next();
        this.category = Category.valueOf(category);
        System.out.print(expenses.getNumOfCategory(this.category));
    }

}
