import com.example.Adventure;
import com.example.GameWorld;
import com.example.JsonFileLoader;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AdventureTester {

    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";

    Adventure adventure;

    @Before
    public void setUp() {
        adventure = new Adventure();
    }

    @Test
    public void createGameWorldFromURLTest() {
        adventure.createGameWorld(JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL));
        assertTrue(adventure.getStartingRoomName().equals("MatthewsStreet")
                && adventure.getEndingRoomName().equals("Siebel1314"));
    }

    @Test
    public void takeItemTest() {
        adventure.usersNextMove("Take coin");
        assertTrue(adventure.getCurrentRoom().getItems().size() == 0);
    }

    @Test
    public void takeInvalidItem() {
        assertEquals("You can't take phone", adventure.takeValidItem("phone"));
    }

    @Test
    public void dropItemTest() {
        adventure.usersNextMove("Drop coin");
        assertTrue(adventure.getCurrentRoom().getItems().contains("coin"));
    }

    @Test
    public void dropInvalidItemTest() {
        assertEquals("You can't drop phone", adventure.dropValidItem("phone"));
    }

    @Test
    public void moveInADirectionTest() {

    }

    @Test
    public void moveInAInvalidDirectionTest() {

    }

    @Test
    public void getItemInventory() {

    }
}
