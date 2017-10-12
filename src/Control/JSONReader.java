package Control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Decker on 10/12/2017. repsonsible for parsing and storing the JSON
 * data
 */
public class JSONReader {

    private String dataString;
    private HashMap<String, String> jsonMap;

    //constructor - takes a raw JSON data string
    public JSONReader(String dataString) {
        jsonMap = new HashMap<>();
        this.dataString = dataString;

        parseData();

    }//end constructor

    private void parseData() {

        try {
            JSONObject jsonObject = new JSONObject(dataString);
            
//            
//            {
//      "marker": [
//         {  
//            "name":  "start",
//             "lat": double,
//             "lon": double
//          },
//         {  
//            "name":  "end",
//             "lat": double,
//             "lon": double
//          }
//         ]
//}
            
            
           String marker = jsonObject.getString("marker");
//
//            if (cod != null) {
//
//                jsonMap.put("cod", cod);
//                //get sys
//                JSONObject sys = jsonObject.getJSONObject("sys");
//                if (sys != null) {
//                    jsonMap.put("sys", sys.getString("country"));
//
//                }
//                //get coord
//                JSONObject coord = jsonObject.getJSONObject("coord");
//                if (coord != null) {
//                    jsonMap.put("lat", coord.getString("lat"));
//                    jsonMap.put("lon", coord.getString("lon"));
//                }
//                //get weather array
//                JSONArray weather = jsonObject.getJSONArray("weather");
//                if (weather != null) {
//                    for (int i = 0; i < weather.length(); i++) {
//                        JSONObject thisWeather = weather.getJSONObject(i);
//                        jsonMap.put("main", thisWeather.getString("main"));
//                        jsonMap.put("description", thisWeather.getString("description"));
//                    }
//                }
//                //getTemp
//                JSONObject main = jsonObject.getJSONObject("main");
//                if (main != null) {
//                    jsonMap.put("temp", main.getString("temp"));
//                }
//
//                //getWind inf
//                JSONObject wind = jsonObject.getJSONObject("wind");
//                if (wind != null) {
//                    jsonMap.put("windSpeed", wind.getString("speed"));
//                    jsonMap.put("windDeg", wind.getString("deg"));
//                }
//
//            }



        } catch (JSONException e) {
            e.printStackTrace();

        }

    } //end parse Data

    public HashMap<String, String> getDataMap() {
        return jsonMap;
    }

} //end Class
