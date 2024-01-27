package at.edu.c02.ledcontroller;

import org.json.JSONArray;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    void setLight() throws IOException;
    JSONArray getGroupLeds() throws IOException;

}
