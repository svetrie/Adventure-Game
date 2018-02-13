import com.example.Room;
import com.example.GameWorld;
import com.example.JsonFileLoader;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RoomTester {
    private static GameWorld gameWorld;
    private static Room noyesLab;

    @Before
    public void setUp() {
        gameWorld = JsonFileLoader.parseJsonFileUsingFilePath("AlternateAdventure.json");
        noyesLab = gameWorld.getRooms()[4];
    }

    @Test
    public void getNameTest() {
        assertEquals("NoyesLaboratory", noyesLab.getName());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("You are in Noyes Laboratory. A mob of students exit Noyes 100 as their lecture ends",
                noyesLab.getDescription());
    }

    @Test
    public void getItemsTest() {
        String[] expectedItemNames = {"lab coat", "beaker", "boxing gloves"};

        for (int i = 0; i < noyesLab.getItems().size(); i++) {
            assertEquals(expectedItemNames[i], noyesLab.getItems().get(i).getName());
        }
    }

    @Test
    public void getItemByNameTest() {
        assertEquals(250, noyesLab.getItemByName("lab coat").getDamage(), 0.0001);
    }

    @Test
    public void getMonstersInRoomTest() {
        assertEquals(Arrays.asList("Connor McGregor", "Floyd Mayweather"), noyesLab.getMonstersInRoom());
    }

    @Test
    public void removeMonsterTest() {
        noyesLab.removeMonster("Connor McGregor");
        noyesLab.removeMonster("Floyd Mayweather");

        assertTrue(noyesLab.getMonstersInRoom().size() == 0);
    }

    @Test
    public void areAllMonstersDefeatedTest() {
        assertTrue(!noyesLab.areAllMonstersDefeated());

        noyesLab.removeMonster("Connor McGregor");
        noyesLab.removeMonster("Floyd Mayweather");

        assertTrue(noyesLab.areAllMonstersDefeated());
    }

    @Test
    public void getValidDirectionTest() {
        assertEquals("IlliniUnion", noyesLab.getValidDirection("Northwest").getRoom());
    }
}
