import com.example.JsonFileLoader;
import com.example.GameWorld;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonFileLoaderTest {

    private static final String DEFAULT_JSON_FILE_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
    GameWorld gameWorld = new GameWorld();

    @Test
    public void parseJsonFileUsingURLTest() {
        gameWorld = JsonFileLoader.parseJsonFileUsingUrl(DEFAULT_JSON_FILE_URL);
        assertEquals("MatthewsStreet", gameWorld.getStartingRoom());
        assertEquals("Siebel1314", gameWorld.getEndingRoom());
    }

    @Test
    public void parseJsonFileUsingFilePath() {
        gameWorld = JsonFileLoader.parseJsonFileUsingFilePath("AlternateAdventure.json");
        assertEquals("IlliniUnionBookstore", gameWorld.getStartingRoom());
        assertEquals("Foellinger", gameWorld.getEndingRoom());
    }

    @Test
    public void parseJsonFileUsingInvalidURLTest() {
        assertEquals(null, JsonFileLoader.parseJsonFileUsingUrl("askljaskcom"));
    }

    @Test
    public void parseJsonFileUsingInvalidFilePathTest() {
        assertEquals(null, JsonFileLoader.parseJsonFileUsingFilePath("cs126IsAwesome.json"));
    }
}
