import com.example.Adventure;
import com.example.GameWorld;
import com.example.JsonFileLoader;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AdventureTester {
    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
    private static Adventure adventure;

    @Before
    public void setUp() {
        adventure = new Adventure(JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL));
    }

    @Test
    public void takeItemTest() {
        adventure.usersNextMove("Take coin");
        assertTrue(adventure.getCurrentRoom().getItems().size() < 1);
        assertTrue(adventure.getPlayer().getItemInventory().contains("coin"));
    }

    @Test
    public void takeInvalidItemTest() {
        assertEquals("You can't take phone", adventure.takeValidItem(new String[] {"take", "phone"}));
    }

    @Test
    public void dropItemTest() {
        adventure.usersNextMove("Take coin");
        adventure.usersNextMove("Drop coin");
        assertTrue(adventure.getCurrentRoom().getItems().contains("coin"));
        assertTrue(adventure.getPlayer().getItemInventory().size() < 1);
    }

    @Test
    public void dropInvalidItemTest() {
        assertEquals("You can't drop phone", adventure.dropValidItem(new String[] {"take", "phone"}));
    }

    @Test
    public void moveInADirectionTest() {
        adventure.usersNextMove("Go East");
        assertEquals("SiebelEntry", adventure.getCurrentRoom().getName());
    }

    @Test
    public void moveInAInvalidDirectionTest() {
        assertEquals("You can't go west", adventure.changeRooms("west"));
    }

    @Test
    public void getItemInventory() {
        adventure.usersNextMove("go east");
        adventure.usersNextMove("take key");
        adventure.usersNextMove("take sweatshirt");

        String[] expectedItemInventory = {"key", "sweatshirt"};

        assertEquals(Arrays.asList(expectedItemInventory), adventure.getPlayer().getItemInventory());
    }

    @Test
    public void acceptUserInputInAllCapsTest() {
        adventure.usersNextMove("GO EAST");
        assertEquals("SiebelEntry", adventure.getCurrentRoom().getName());
    }

    @Test
    public void acceptUserInputWithLargeSpaces() {
        adventure.usersNextMove("   Go  East  ");
        assertEquals("SiebelEntry", adventure.getCurrentRoom().getName());
    }

}
