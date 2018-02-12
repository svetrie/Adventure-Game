import com.example.GameWorld;
import com.example.JsonFileLoader;
import com.example.Monster;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MonsterTester {
    private static final String JSON_FILE = "AlternateAdventure.json";
    private static GameWorld gameWorld;
    private static Monster taylorSwift;

    @Before
    public void setUp() {
        gameWorld = JsonFileLoader.parseJsonFileUsingFilePath(JSON_FILE);
        taylorSwift = gameWorld.getMonsterByName("Taylor Swift");
        taylorSwift.initializeHealth();
    }

    @Test
    public void getMonsterNameTest() {
        assertEquals("Taylor Swift", taylorSwift.getName());
    }

    @Test
    public void getMonsterAttackTest() {
        assertEquals(15, taylorSwift.getAttack(), 0.0001);
    }

    @Test
    public void getMonsterDefenseTest() {
        assertEquals(12, taylorSwift.getDefense(), 0.0001);
    }

    @Test
    public void getMonsterMaxHealthTest() {
        assertEquals(10, taylorSwift.getMaxHealth(), 0.0001);
    }

    @Test
    public void reduceMonsterHealthTest() {
        taylorSwift.reduceCurrentHealth(6);
        assertEquals(4, taylorSwift.getCurrentHealth(), 0.0001);
    }
}
