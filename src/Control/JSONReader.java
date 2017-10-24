package Control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Decker on 10/12/2017. responsible for parsing and storing the JSON
 * data
 */
public class JSONReader {

    public HashMap<String, String> parseUserInput(String dataString) {

        HashMap<String, String> jsonMap = new HashMap<>();
        try {

            JSONObject jsonObject = new JSONObject(dataString);
            JSONArray routeArray = jsonObject.getJSONArray("route");
            JSONObject obj = routeArray.getJSONObject(0);

            if (obj != null) {
                jsonMap.put("startNode", obj.getString("name"));
                jsonMap.put("startLat", obj.getString("lat"));
                jsonMap.put("startLon", obj.getString("lon"));
            } else {
                System.err.println("could not read route array 0");
            }

            obj = routeArray.getJSONObject(1);

            if (obj != null) {
                jsonMap.put("endNode", obj.getString("name"));
                jsonMap.put("endLat", obj.getString("lat"));
                jsonMap.put("endLon", obj.getString("lon"));
            } else {
                System.err.println("could not read route array 1");
            }

            jsonMap.put("ada", jsonObject.getString("ada"));

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return jsonMap;
    } //end parse Data

} //end Class
