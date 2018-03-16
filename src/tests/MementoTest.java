package tests;

import edu.sdsu.observer.Memento;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class MementoTest {
    @Test
    public void testSetFilePath() {
        try {
            new Memento(null);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }

        try {
            new Memento("");
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }

        try {
            new Memento("Path");
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }
    }
}