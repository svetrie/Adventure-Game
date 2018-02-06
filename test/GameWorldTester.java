import com.example.GameWorld;
import com.example.JsonFileLoader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameWorldTester {

    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
    private static GameWorld gameWorld;

    @Before
    public void setUp() {
        gameWorld = JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL);
    }

    @Test
    public void getStartingRoomTest() {
        assertEquals("MatthewsStreet", gameWorld.getStartingRoom());
    }

    @Test
    public void getEndingRoom() {
        assertEquals("Siebel1314", gameWorld.getEndingRoom());
    }

    @Test
    public void getRoomByNameTest() {
        assertEquals("You are in the ACM office.  There are lots of friendly ACM people.",
                gameWorld.getRoomByName("AcmOffice").getDescription());
    }
}
