package ui;

import model.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PrintTableUI {
    private static String[] column = {"EXPENSE", "CATEGORY", "AMOUNT"};
    private static String[][] data;
    private JFrame frame;
    private static JTable expenseTable;
    private static DefaultTableModel defaultTableModel;

    public PrintTableUI(List<Expense> expenses) {
        frame = new JFrame("Expense Table");
        copyListToArray(expenses);
        defaultTableModel = new DefaultTableModel(data, column);
        expenseTable = new JTable(defaultTableModel);
        expenseTable.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(expenseTable);
        frame.add(sp);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    public static void copyListToArray(List<Expense> expenses) {
        data = new String[expenses.size()][column.length];
        for (int j = 0; j < expenses.size(); j++) {
            data[j][0] = expenses.get(j).getName();
        }

        for (int j = 0; j < expenses.size(); j++) {
            data[j][1] = expenses.get(j).getCategory().toString();
        }

        for (int j = 0; j < expenses.size(); j++) {
            data[j][2] = String.valueOf(expenses.get(j).getAmount());
        }

    }

    public static JTable getExpenseTable() {
        return expenseTable;
    }

    public static void addExpenseToTable(Expense e) {
        String[] expense = new String[3];
        expense[0] = e.getName();
        expense[1] = e.getCategory().toString();
        expense[2] = String.valueOf(e.getAmount());
        defaultTableModel.addRow(expense);
    }
}
