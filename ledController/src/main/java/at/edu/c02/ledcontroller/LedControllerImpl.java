package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public void setLight() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        //apiService.setLight("#f00",true, 56);

        //54 - 61

        for (int i = 54; i <= 61; i=i+2) {
            apiService.setLight("#f00",true, i);
        }
    }



    @Override
    public JSONArray getGroupLeds() throws IOException {

        JSONObject response = apiService.getLights();
        JSONArray allLeds = response.getJSONArray("lights");
        JSONArray groupLeds = new JSONArray();

        for (int i = 0; i < allLeds.length(); i++) {
            JSONObject led = allLeds.getJSONObject(i);
            if (led.has("groupByGroup") && !led.isNull("groupByGroup")) {
                groupLeds.put(led);
            }
        }
        return groupLeds;

    }
}
