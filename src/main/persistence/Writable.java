package persistence;

import org.json.JSONObject;

//return objects as JSON object
//citation: interface Writable of project JsonSerializationDemo
public interface Writable {
    JSONObject toJson();
}
