package ui;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            new BudgetTrackingApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
