package ui;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            new BudgetTrackingAppUI();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
}
