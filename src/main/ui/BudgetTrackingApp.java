package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import model.*;
import persistence.*;

//budget tracking application
public class BudgetTrackingApp {
    private static final String JSON_STORE = "./data/BudgetPlanner.json";
    private Category category;
    private BudgetPlanner expenses;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private final Scanner in;

    public BudgetTrackingApp() throws FileNotFoundException {
        in = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        newExpensesList();
    }

    //MODIFIES: this
    /*EFFECTS: create a new expenses list, prompt user to enter their expenses,
     *print out number of expenses, remaining, and sum
     *prompt user if they want to see how many expenses in a particular category of their choice
     */
    private void newExpensesList() {
        System.out.print("Enter a monthly budget: ");
        int budget = in.nextInt();
        expenses = new BudgetPlanner(budget);
        boolean keepGoing = true;
        String choice;
        while (keepGoing) {
            seeChoices();
            choice = in.next();
            processChoices(choice);
            System.out.println();
            if (choice.equals("q")) {
                keepGoing = false;
            }
        }
    }

    //EFFECTS: print out a number of choices for user to choose
    public void seeChoices() {
        System.out.println("Choose from one of the following choices");
        System.out.println("a - Add expense");
        System.out.println("e - See all expenses");
        System.out.println("c - See expenses in category");
        System.out.println("s - Save expense");
        System.out.println("l - Load previous expenses");
        System.out.println("q - Quit application ");
    }

    //EFFECTS: process choices based on user's input
    public void processChoices(String choice) {
        switch (choice) {
            case "a":
                addExpense();
                break;
            case "e":
                seeExpenses();
                break;
            case "s":
                saveExpensesList();
                break;
            case "l":
                loadExpensesList();
                break;
            case "c":
                seeNumOfCategory();
                break;
            default:
                System.out.print("Selection not valid. Please try again.");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: add expense of user's choice into the list of expenses
    public void addExpense() {
        System.out.print("Insert new expense: ");
        String name = in.next();
        System.out.print("Insert category, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        String category = in.next();
        this.category = Category.valueOf(category);
        System.out.print("Insert amount: ");
        int amount = in.nextInt();
        Expense expense = new Expense(name, this.category, amount);
        expenses.addExpense(expense);
    }

    //EFFECTS: print out list of expenses, number of expenses, remaining, and sum
    public void seeExpenses() {
        System.out.println("Expenses\n" + "--------");
        System.out.print(expenses.getListOfExpenses());
        System.out.println("Number of expenses: " + expenses.getNumOfExpenses());
        System.out.println("Total: " + expenses.getSumOfExpenses());
        System.out.println("Remaining: " + expenses.getRemainingOfBudget());
    }

    //MODIFIES: this
    /*EFFECTS: prompt user to enter a category of their choice and will print out
    *how many expenses in that category
     */
    public void seeNumOfCategory() {
        System.out.print("Insert category you want to see, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        String category = in.next();
        this.category = Category.valueOf(category);
        System.out.println(expenses.getNumOfCategory(this.category));
    }

    //MODIFIES: this
    //EFFECTS: save user's expense list to file
    //citation: class WorkroomApp of project JsonSerializationDemo
    private void saveExpensesList() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenses);
            jsonWriter.close();
            System.out.println("Saved my expenses list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: load user's expense list from file
    //citation: class WorkroomApp of project JsonSerializationDemo
    private void loadExpensesList() {
        try {
            expenses = jsonReader.read();
            System.out.println("Loaded my expenses list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
