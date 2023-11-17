package ui;

import model.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static model.Category.HOUSING;

public class Main {
    public static void main(String[] args) {
        List<Expense> e = new ArrayList<>();
        e.add(new Expense("a", HOUSING, 400));
        //try {
        PrintTableUI t = new PrintTableUI(e);
        t.addExpenseToTable(new Expense("b", HOUSING, 400));
        //} catch (FileNotFoundException ex) {
            //System.out.println("File not found");
        //}
    }
}
