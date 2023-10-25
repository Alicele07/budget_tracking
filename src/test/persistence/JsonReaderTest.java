package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//inherits JsonTest, test constructors and methods in JsonReader class
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BudgetPlanner planner = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            BudgetPlanner planner = reader.read();
            assertEquals(2100, planner.getBudget());
            assertEquals(0, planner.getNumOfExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseList.json");
        try {
            BudgetPlanner planner = reader.read();
            assertEquals(2100, planner.getBudget());
            List<Expense> expenses = planner.getExpenses();
            assertEquals(2, expenses.size());
            checkExpense("grocery", Category.SUPPLIES, 400, new Expense("grocery", Category.SUPPLIES, 400));
            checkExpense("rent", Category.HOUSING, 500, new Expense("rent", Category.HOUSING, 500));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
