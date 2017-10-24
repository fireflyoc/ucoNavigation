package Se2ServerTester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Decker on 10/12/2017. responsible for parsing and storing the JSON
 * data
 */
public class JSONReader {

    private String dataString;
    private HashMap<String, String> jsonMap;

    //constructor - takes a raw JSON data string
    public JSONReader(String dataString) {
        jsonMap = new HashMap<>();
        System.out.println("JSONreader Recieved: " + dataString);
        this.dataString = dataString;

        parseUserInput();

    }//end constructor

    private void parseUserInput() {

        try {
           
//recieved string must be in this format: 
//{
//      "route":[
//         {  
//            "name":  "start",
//             "lat": "double",
//             "lon": "double"
//          },
//         {  
//            "name":  "end",
//             "lat": "double",
//             "lon": "double"
//          }
//         ]
//    
//      "ada":"true"
//}            

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

    } //end parse Data

    public HashMap<String, String> getDataMap() {
        return jsonMap;
    }

} //end Class
