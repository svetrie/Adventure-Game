import com.example.Adventure;
import com.example.GameWorld;
import com.example.Item;
import com.example.Monster;
import com.example.JsonFileLoader;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AdventureTester {
    private static final String JSON_FILE = "AlternateAdventure.json";
    private static Adventure adventure;

    @Before
    public void setUp() {
        adventure = new Adventure(JsonFileLoader.parseJsonFileUsingFilePath(JSON_FILE));
    }

    @Test
    public void takeItemTest() {
        adventure.usersNextMove("Take textbook");

        assertTrue(adventure.getCurrentRoom().getItems().size() < 1);
        assertTrue(adventure.getPlayer().getItemByName("textbook") != null);
    }

    @Test
    public void takeInvalidItemTest() {
        assertEquals("You can't take phone", adventure.takeValidItem(new String[] {"take", "phone"}));
    }

    @Test
    public void dropItemTest() {
        adventure.usersNextMove("Take textbook");
        adventure.usersNextMove("Drop textbook");

        assertTrue(adventure.getCurrentRoom().getItemByName("textbook") != null);
        assertTrue(adventure.getPlayer().getItemInventory().size() < 1);
    }

    @Test
    public void dropInvalidItemTest() {
        assertEquals("You can't drop phone", adventure.dropValidItem(new String[] {"drop", "phone"}));
    }

    @Test
    public void moveInADirectionTest() {
        adventure.usersNextMove("Go East");
        assertEquals("HenryAdministrationBuilding", adventure.getCurrentRoom().getName());
    }

    @Test
    public void moveInAInvalidDirectionTest() {
        assertEquals("You can't go west", adventure.changeRooms("west"));
    }

    @Test
    public void changeRoomsWithoutDefeatingMonstersTest() {
        adventure.usersNextMove("Go East");
        adventure.usersNextMove("Go West");

        assertEquals("HenryAdministrationBuilding", adventure.getCurrentRoom().getName());
    }

    @Test
    public void attackMonsterTest() {
        Monster justinBieber = adventure.getGameWorld().getMonsterByName("justin bieber");
        justinBieber.initializeHealth();

        adventure.usersNextMove("Go east");
        adventure.attack(justinBieber, 15);

        assertEquals( 5, justinBieber.getCurrentHealth(), 0.0001);
    }

    @Test
    public void attackMonsterWithItemTest() {
        Monster justinBieber = adventure.getGameWorld().getMonsterByName("justin bieber");
        justinBieber.initializeHealth();

        adventure.usersNextMove("Go east");
        adventure.usersNextMove("take calculator");
        adventure.attackWithItem(justinBieber, "calculator");

        assertEquals( 2.5, justinBieber.getCurrentHealth(), 0.0001);
    }

    /*
    @Test
    public void getItemInventory() {
        adventure.usersNextMove("take textbook");
        adventure.usersNextMove("go east");
        adventure.usersNextMove("take pencil");
        adventure.usersNextMove("take calculator");
        adventure.usersNextMove("list");

        String[] expectedItemInventory = {"textbook", "pencil", "calculator"};

        for (String itemName : expectedItemInventory) {
            assertTrue(adventure.getPlayer().getItemByName(itemName) != null);
        }
    }*/

    @Test
    public void acceptUserInputInAllCapsTest() {
        adventure.usersNextMove("GO EAST");
        assertEquals("HenryAdministrationBuilding", adventure.getCurrentRoom().getName());

        adventure.usersNextMove("TAKE PENCIL");
        assertTrue(adventure.getPlayer().getItemByName("pencil") != null);
    }

    @Test
    public void acceptUserInputWithLargeSpaces() {
        adventure.usersNextMove("   Go  East  ");
        assertEquals("HenryAdministrationBuilding", adventure.getCurrentRoom().getName());

        adventure.usersNextMove(" take   pencil   ");
        assertTrue(adventure.getPlayer().getItemByName("pencil") != null);
    }

}
