package model;

import org.json.JSONObject;
import persistence.Writable;

//create an Expense object with name, category, and amount to add into your expense list
public class Expense implements Writable {
    private final int amount;
    private final Category category;
    private final String name;

    //REQUIRES: amount > 0, name != null, category != null
    //EFFECT: add an expense with name, category, and amount into the list of expenses
    public Expense(String name, Category category, int amount) {
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    //MODIFIES: this
    //EFFECTS: override method toJson() of interface Writable, return this as JSON objects
    //citation: class Thingy of project JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        json.put("amount", amount);
        return json;
    }
}
