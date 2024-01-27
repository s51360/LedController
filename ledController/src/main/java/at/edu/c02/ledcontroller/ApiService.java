package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;
    JSONObject getLight(int id) throws IOException;
    JSONObject setLight(String color, boolean status, int id) throws IOException;
}
