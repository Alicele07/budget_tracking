package persistence;

import org.json.JSONObject;

//return objects as JSON object
public interface Writable {
    JSONObject toJson();
}
