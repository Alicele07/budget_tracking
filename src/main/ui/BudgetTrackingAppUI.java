package ui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.*;
import persistence.*;
import static ui.PrintTableUI.*;

import javax.swing.*;

//budget tracking application
public class BudgetTrackingAppUI {
    private static final String JSON_STORE = "./data/BudgetPlanner.json";
    private Category category;
    private BudgetPlanner expenses;
    private final JFrame frame;
    private JButton add;
    private JButton print;
    private JButton save;
    private JButton load;
    private JButton seeCategory;
    private JButton quit;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    public BudgetTrackingAppUI() throws FileNotFoundException {
        frame = new JFrame("CPSC 210: BUDGET TRACKING APP");
        ImageIcon image1 = new ImageIcon("valentine.jpeg");
        frame.add(new JLabel(image1));
        frame.pack();
        frame.setSize(736, 517);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
        int budget = Integer.parseInt(JOptionPane.showInputDialog("Enter a monthly budget: "));
        expenses = new BudgetPlanner(budget);
        seeChoices();
        processChoices();
        quit.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
    }

    //EFFECTS: print out a number of choices for user to choose
    public void seeChoices() {
        JPanel panel = new JPanel();
        JLabel promptUser = new JLabel("Choose from one of the following choices", JLabel.CENTER);
        promptUser.setSize(panel.WIDTH, panel.HEIGHT / 3);
        panel.add(promptUser);

        add = new JButton("Add expense");
        print = new JButton("See all expenses");
        save = new JButton("Save expense");
        load = new JButton("Load previous expenses");
        seeCategory = new JButton("See expenses in category");
        quit = new JButton("Quit application");


        panel.setLayout(new FlowLayout());
        panel.add(add);
        panel.add(print);
        panel.add(save);
        panel.add(load);
        panel.add(seeCategory);
        panel.add(quit);
        panel.setSize(panel.WIDTH, panel.HEIGHT / 6);
        frame.add(panel);
        frame.setVisible(true);
    }

    //EFFECTS: process choices based on user's input
    public void processChoices() {
        add.addActionListener(e -> addExpense());

        print.addActionListener(e -> seeExpenses());

        save.addActionListener(e -> saveExpensesList());

        load.addActionListener(e -> loadExpensesList());

        seeCategory.addActionListener(e -> seeNumOfCategory());
    }

    //MODIFIES: this
    //EFFECTS: add expense of user's choice into the list of expenses
    public void addExpense() {
        String name = JOptionPane.showInputDialog("Insert new expenses: ");
        String category = JOptionPane.showInputDialog("Insert category, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        this.category = Category.valueOf(category);
        int amount = Integer.parseInt(JOptionPane.showInputDialog("Insert amount: "));
        Expense expense = new Expense(name, this.category, amount);
        expenses.addExpense(expense);
    }

    //EFFECTS: print out list of expenses, number of expenses, remaining, and sum
    public void seeExpenses() {
        new PrintTableUI(expenses.getExpenses());
        JOptionPane.showMessageDialog(getFrame(), "Number of expenses: " + expenses.getNumOfExpenses()
                    + "\nTotal: " + expenses.getSumOfExpenses()
                    + "\nRemaining: " + expenses.getRemainingOfBudget());
    }

    //MODIFIES: this
    /*EFFECTS: prompt user to enter a category of their choice and will print out
     *how many expenses in that category
     */
    public void seeNumOfCategory() {
        String category = JOptionPane.showInputDialog("Insert category, choose from HOUSING, SUPPLIES, UTILITIES, "
                + "HEALTHCARE, PERSONAL, PETS, ENTERTAINMENT: ");
        this.category = Category.valueOf(category);
        sortByCategory(this.category);
    }

    //MODIFIES: this
    //EFFECTS: save user's expense list to file
    //citation: class WorkroomApp of project JsonSerializationDemo
    private void saveExpensesList() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenses);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame,"Saved my expenses list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame,"Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: load user's expense list from file
    //citation: class WorkroomApp of project JsonSerializationDemo
    private void loadExpensesList() {
        try {
            expenses = jsonReader.read();
            JOptionPane.showMessageDialog(frame, "Loaded my expenses list from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"Unable to read from file: " + JSON_STORE);
        }
    }
}