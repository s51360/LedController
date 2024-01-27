package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void testGetGroupLeds() throws IOException {
        ApiService mockedApiService = Mockito.mock(ApiService.class);
        JSONObject mockResponse = new JSONObject();
        Mockito.when(mockedApiService.getLights()).thenReturn(mockResponse);

        LedController controller = new LedControllerImpl(mockedApiService);
        JSONArray result = controller.getGroupLeds();


    }

}



