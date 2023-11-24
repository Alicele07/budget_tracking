package ui;

import model.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PrintTableUI extends JTable {
    private static final String[] column = {"EXPENSE", "CATEGORY", "AMOUNT"};
    private static JFrame frame;
    private static JTable expenseTable;
    private static DefaultTableModel defaultTableModel;

    public PrintTableUI(List<Expense> expenses) {
        frame = new JFrame("Expense Table");
        copyListToArray(expenses);
        expenseTable = new JTable(defaultTableModel);
        expenseTable.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(expenseTable);
        frame.add(sp);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    public static void copyListToArray(List<Expense> expenses) {
        String[][] data = new String[expenses.size()][column.length];
        for (int j = 0; j < expenses.size(); j++) {
            data[j][0] = expenses.get(j).getName();
        }

        for (int j = 0; j < expenses.size(); j++) {
            data[j][1] = expenses.get(j).getCategory().toString();
        }

        for (int j = 0; j < expenses.size(); j++) {
            data[j][2] = String.valueOf(expenses.get(j).getAmount());
        }

        defaultTableModel = new DefaultTableModel(data, column);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void sortByCategory(Category c) {
        JFrame jframe = new JFrame("Category Table");
        List<Expense> category = new ArrayList<>();
        for (int i = 0; i < expenseTable.getRowCount(); i++) {
            if (expenseTable.getValueAt(i, 1).equals(c.toString())) {
                Expense e = new Expense((String) expenseTable.getValueAt(i, 0),
                        Category.valueOf((String) expenseTable.getValueAt(i, 1)),
                        Integer.parseInt((String) expenseTable.getValueAt(i, 2)));
                category.add(e);
            }
        }
        copyListToArray(category);
        JTable tableCategory = new JTable(defaultTableModel);
        JScrollPane sp = new JScrollPane(tableCategory);
        jframe.add(sp);
        jframe.setSize(500, 200);
        JOptionPane.showMessageDialog(frame, "You have " + tableCategory.getColumnCount()
                + " expenses in " + c + " category.");
        jframe.setVisible(true);
    }
}
