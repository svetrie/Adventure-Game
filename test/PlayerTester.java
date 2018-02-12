import com.example.GameWorld;
import com.example.JsonFileLoader;
import com.example.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerTester {
    private static final String JSON_FILE = "AlternateAdventure.json";
    private static GameWorld gameWorld;
    private static Player player;

    @Before
    public void setUp() {
        gameWorld = JsonFileLoader.parseJsonFileUsingFilePath(JSON_FILE);
        player = gameWorld.getPlayer();
        player.initializeCurrentHealth();
    }

    @Test
    public void getPlayerAttackTest() {
        assertEquals(15, player.getAttack(), 0.0001);
    }

    @Test
    public void getPlayerDefenseTest() {
        assertEquals(10, player.getDefense(), 0.0001);
    }

    @Test
    public void getPlayerMaxHealthTest() {
        assertEquals(50, player.getMaxHealth(), 0.0001);
    }

    @Test
    public void getPlayerLevelTest() {
        assertEquals(0, player.getLevel());
    }

    @Test
    public void reducePlayerHealthTest() {
        player.reduceCurrentHealth(10);
        assertEquals(40, player.getCurrentHealth(), 0.0001);
    }

    @Test
    public void getPlayerInfoTest() {
        assertEquals("Level: 0, Attack: 15.0, Defense: 10.0, Health: 50.0/50.0", player.getPlayerInfo());
    }

    @Test
    public void gainExperienceTest() {
        player.gainExperience(gameWorld.getMonsterByName("Justin Bieber"));
        assertEquals(420.0, player.getTotalExp(), 0.0001);
    }

    @Test
    public void getExpForNextLevelTest() {
        player.gainExperience(gameWorld.getMonsterByName("Justin Bieber"));
        assertEquals(436.5, player.getExpForNextLevel(), 0.09);
    }

    @Test
    public void levelUpTest() {
        player.gainExperience(gameWorld.getMonsterByName("Justin Bieber"));

        assertEquals(5, player.getLevel());
        assertEquals(114, player.getAttack(), 1);
        assertEquals(76, player.getDefense(), 1);
        assertEquals(185, player.getMaxHealth(), 1);
    }
}
