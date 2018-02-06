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
    
}
