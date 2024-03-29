package persistence;

import model.*;
import org.json.JSONObject;
import java.io.*;

//represent a writer and write BudgetPlanner from user input to JSON data and store them in file
//citation: class JsonWriter of project JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    /*EFFECTS: opens writer; throws FileNotFoundException if destination file
    cannot be opened for writing
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of BudgetPlanner to file
    public void write(BudgetPlanner planner) {
        JSONObject json = planner.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
