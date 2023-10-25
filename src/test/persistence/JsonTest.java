package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(String name, Category category, int amount, Expense expense) {
        assertEquals(name, expense.getName());
        assertEquals(category, expense.getCategory());
        assertEquals(amount, expense.getAmount());
    }
}
