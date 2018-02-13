import com.example.Room;
import com.example.GameWorld;
import com.example.JsonFileLoader;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RoomTester {
    private static GameWorld gameWorld;
    private static Room siebel1112;

    @Before
    public void setUp() {
        gameWorld = JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL);
        siebel1112 = gameWorld.getRooms()[4];
    }

    @Test
    public void getNameTest() {
        assertEquals("Siebel1112", siebel1112.getName());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("You are in Siebel 1112.  There is space for two code reviews in this room.",
                siebel1112.getDescription());
    }

    @Test
    public void getItemsTest() {
        assertTrue(Arrays.asList("USB-C connector", "grading rubric").equals(siebel1112.getItems()));
    }

    @Test
    public void getDirectionTest() {
        assertTrue(siebel1112.getDirections()[0].getDirectionName().equals("West"));
        assertTrue(siebel1112.getDirections()[0].getRoom().equals("SiebelNorthHallway"));
    }
}
