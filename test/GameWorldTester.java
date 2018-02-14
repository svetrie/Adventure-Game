import com.example.GameWorld;
import com.example.JsonFileLoader;
import com.example.Monster;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameWorldTester {
    private static final String JSON_FILE = "AlternateAdventure.json";
    private static GameWorld gameWorld;

    @Before
    public void setUp() {
        gameWorld = JsonFileLoader.parseJsonFileUsingFilePath(JSON_FILE);
        gameWorld.initializeRoomMap();
    }

    @Test
    public void getStartingRoomTest() {
        assertEquals("IlliniUnionBookstore", gameWorld.getStartingRoom());
    }

    @Test
    public void getEndingRoomTest() {
        assertEquals("Foellinger", gameWorld.getEndingRoom());
    }

    @Test
    public void getRoomsTest() {
        String[] expectedRoomNames = {"IlliniUnionBookstore", "HenryAdministrationBuilding",
                "IlliniUnion", "Altgeld", "NoyesLaboratory", "DavenportHall", "EnglishBuilding",
                "ForeignLanguagesBuilding", "Foellinger"};

        for (int i = 0; i < gameWorld.getRooms().length; i++) {
            assertEquals(expectedRoomNames[i], gameWorld.getRooms()[i].getName());
        }

    }

    @Test
    public void getRoomByNameTest() {
        assertEquals("You are in Noyes Laboratory. A mob of students exit Noyes 100 as their lecture ends",
                gameWorld.getRoomByName("NoyesLaboratory").getDescription());
    }

    @Test
    public void getMonsterByNameTest() {
        Monster monster = gameWorld.getMonsterByName("Justin Bieber");

        assertEquals(12.5, monster.getAttack(), 0.0001);
        assertEquals(12.5, monster.getDefense(), 0.0001);
        assertEquals(10.0, monster.getMaxHealth(), 0.0001);
    }

}
