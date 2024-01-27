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
    public JSONArray getGroupLeds() throws IOException {
        // Rufen Sie die Liste aller LEDs auf
        JSONObject response = apiService.getLights();
        JSONArray allLeds = response.getJSONArray("lights");

        // Erstellen Sie ein neues JSON-Array für die Gruppen-LEDs
        JSONArray groupLeds = new JSONArray();

        // Durchlaufen Sie alle LEDs und filtern Sie die Gruppen-LEDs heraus
        for (int i = 0; i < allLeds.length(); i++) {
            JSONObject led = allLeds.getJSONObject(i);
            if (led.optBoolean("isGroupLed")) { // Hier wird geprüft, ob die LED zur Gruppe gehört
                groupLeds.put(led);
            }
        }
        return groupLeds;
    }
}
