package flik;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void test () {
        assertTrue(Flik.isSameNumber(1, 1));
        assertFalse(Flik.isSameNumber(1, 2));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
