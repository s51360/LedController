package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashSet;

public class DemoApp {
    public static void main(String[] args) throws IOException {
        ApiService apiService = new ApiServiceImpl();
        LedController ledController = new LedControllerImpl(apiService);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while (!input.equalsIgnoreCase("exit")) {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'status', 'groupstatus', 'demo', or 'exit'");
            input = reader.readLine();

            if (input.equalsIgnoreCase("status")) {
                System.out.println("Please specify LED ID:");
                int id = Integer.parseInt(reader.readLine());
                JSONObject light = apiService.getLight(id);
                printLightStatus(light);
            } else if (input.equalsIgnoreCase("groupstatus")) {
                JSONArray groupLeds = ledController.getGroupLeds();
                for (int i = 0; i < groupLeds.length(); i++) {
                    JSONObject light = groupLeds.getJSONObject(i);
                    printLightStatus(light);
                }
            } else if (input.equalsIgnoreCase("demo")) {
                // Hier können Sie die Demo-Logik implementieren, falls gewünscht.
                System.out.println("Demo not implemented yet.");
            }
        }
    }

    private static void printLightStatus(JSONObject light) {

        JSONArray lights = light.getJSONArray("lights");
        JSONObject firstLight = lights.getJSONObject(0);

        int id = firstLight.getInt("id");
        String status = firstLight.getBoolean("on") ? "on" : "off";
        String color = firstLight.getString("color");
        System.out.println("LED " + id + " is currently " + status + ". Color: " + color + ".");
    }
}