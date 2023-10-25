package persistence;

import model.BudgetPlanner;
import model.Category;
import model.Expense;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//inherits JsonTest, test constructors and methods in JsonWriter class
//citation: class JsonWriterTest of project JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            BudgetPlanner planner = new BudgetPlanner(2100);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            BudgetPlanner planner = new BudgetPlanner(2100);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(planner);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            planner = reader.read();
            assertEquals(2100, planner.getBudget());
            assertEquals(0, planner.getNumOfExpenses());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BudgetPlanner planner = new BudgetPlanner(2100);
            planner.addExpense(new Expense("grocery", Category.SUPPLIES, 400));
            planner.addExpense(new Expense("rent", Category.HOUSING, 500));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseList.json");
            writer.open();
            writer.write(planner);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseList.json");
            planner = reader.read();
            assertEquals(2100, planner.getBudget());
            List<Expense> expenses = planner.getExpenses();
            assertEquals(2, expenses.size());
            checkExpense("grocery", Category.SUPPLIES, 400, expenses.get(0));
            checkExpense("rent", Category.HOUSING, 500, expenses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
