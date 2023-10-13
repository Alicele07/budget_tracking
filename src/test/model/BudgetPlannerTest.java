package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//test constructors and methods in class BudgetPlanner
class BudgetPlannerTest {
    private BudgetPlanner testBudgetPlanner;

    @BeforeEach
    void runBefore() {
        testBudgetPlanner = new BudgetPlanner(2100);
    }

    @Test
    void testConstructors() {
        assertEquals(2100, testBudgetPlanner.getBudget());
    }

    @Test
    void testGetListOfExpensesOneExpense() {
        Expense e = new Expense("grocery", Category.SUPPLIES, 400);
        testBudgetPlanner.addExpense(e);
        assertEquals(String.format("%s%10s%6s\n", "grocery", Category.SUPPLIES, 400),
                testBudgetPlanner.getListOfExpenses());
        assertEquals(1, testBudgetPlanner.getNumOfExpenses());
        assertEquals(400, testBudgetPlanner.getSumOfExpenses());
        assertEquals(1700, testBudgetPlanner.getRemainingOfBudget());
    }

    @Test
    void testGetListOfExpensesMultipleExpense() {
        Expense grocery = new Expense("grocery", Category.SUPPLIES, 400);
        Expense rent = new Expense("rent", Category.HOUSING, 1000);
        testBudgetPlanner.addExpense(grocery);
        testBudgetPlanner.addExpense(rent);
        assertEquals(2, testBudgetPlanner.getNumOfExpenses());
        assertEquals(1400, testBudgetPlanner.getSumOfExpenses());
        assertEquals(700, testBudgetPlanner.getRemainingOfBudget());
    }

    @Test
    void testGetNumOfCategory() {
        Expense grocery = new Expense("grocery", Category.SUPPLIES, 100);
        Expense club = new Expense ("club", Category.ENTERTAINMENT, 50);
        Expense arcade = new Expense ("arcade", Category.ENTERTAINMENT, 100);
        testBudgetPlanner.addExpense(grocery);
        testBudgetPlanner.addExpense(club);
        testBudgetPlanner.addExpense(arcade);
        assertEquals(3, testBudgetPlanner.getNumOfExpenses());
        assertEquals(250, testBudgetPlanner.getSumOfExpenses());
        assertEquals(1850, testBudgetPlanner.getRemainingOfBudget());
        assertEquals(1, testBudgetPlanner.getNumOfCategory(Category.SUPPLIES));
        assertEquals(2, testBudgetPlanner.getNumOfCategory(Category.ENTERTAINMENT));
    }


}