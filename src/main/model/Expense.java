package model;

public class Expense {
    private int amount;
    private Category category;
    private String name;

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
}
