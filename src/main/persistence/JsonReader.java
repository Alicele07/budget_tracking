package persistence;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.BudgetPlanner;

import model.Category;
import model.Expense;
import org.json.*;

//represent a reader and read expenses of BudgetPlanner from JSON data stored in file
//citation: class JsonReader of project JsonSerializationDemo
public class JsonReader {
    private final String source;

    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: read BudgetPlanner from file and return it
    public BudgetPlanner read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpensesList(jsonObject);
    }

    //EFFECTS: read source file as String and return it
    public String readFile(String source) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(sb::append);
        }
        return sb.toString();
    }

    //EFFECTS: parse BudgetPlanner from JSON object and return it
    public BudgetPlanner parseExpensesList(JSONObject jsonObject) {
        int budget = jsonObject.getInt("budget");
        BudgetPlanner planner = new BudgetPlanner(budget);
        addExpenses(planner, jsonObject);
        return planner;
    }

    //EFFECTS: parse expenses from JSON object and add them to BudgetPlanner
    private void addExpenses(BudgetPlanner planner, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject expense = (JSONObject) json;
            addExpense(planner, expense);
        }
    }

    //EFFECTS: parse expense from JSON object and add it to BudgetPlanner
    private void addExpense(BudgetPlanner planner, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category category = Category.valueOf(jsonObject.getString("category"));
        int amount = jsonObject.getInt("amount");
        Expense expense = new Expense(name, category, amount);
        planner.addExpense(expense);
    }
}
