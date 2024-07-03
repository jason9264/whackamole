import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Symbol class
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Jason Wang
 */
public class SymbolTest { 

    /** Symbol giraffe for testing */
    private Symbol giraffe;

    /** Symbol giraffe2 for testing */
    private Symbol giraffe2;

    /**
     * Create Symbols for testing
     */
    @BeforeEach
    public void setUp() {
        giraffe = new Symbol("giraffe", 25);

    }

    /**
     * Create Symbols for testing
     */
    @BeforeEach
    public void setUp2() {
        giraffe2 = new Symbol("giraffe2", 25);
    }

    /**
     * test name
     */
    @Test
    public void testGetNameGiraffe() {
        assertEquals("giraffe", giraffe.getName(), "giraffe name");
    }    

    /**
     * test name2
     */
    @Test
    public void testGetNameGiraffe2() {
        assertEquals("giraffe2", giraffe2.getName(), "giraffe2 name");
    }    

    /**
     * test points
     */
    @Test
    public void testGetPointsGiraffe() {
        assertEquals(25, giraffe.getPoints(), "giraffe points");
    }     

    /**
     * test points 2
     */
    @Test
    public void testGetPointsGiraffe2() {
        assertEquals(25, giraffe2.getPoints(), "giraffe2 points");
    }     

    /**
     * test string
     */
    @Test
    public void testToStringGiraffe() {
        assertEquals("giraffe 25 false", giraffe.toString(), "giraffe toString");        
    }

    /**
     * test string 2
     */
    @Test
    public void testToStringGiraffe2() {
        assertEquals("giraffe2 25 false", giraffe2.toString(), "giraffe2 toString");        
    }

    /**
     * test clickedon
     */
    @Test
    public void testHasBeenClickedOnGiraffe() {
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe hasBeenClickedOn");
    }

    /**
     * test clickedon2
     */
    @Test
    public void testHasBeenClickedOnGiraffe2() {
        assertFalse(giraffe2.hasBeenClickedOn(), "giraffe2 hasBeenClickedOn");
    }

    /**
     * test setClickedOn
     */
    @Test
    public void testSetHasBeenClickedOnGiraffe() {
        giraffe.setHasBeenClickedOn(true);
        assertTrue(giraffe.hasBeenClickedOn(), "giraffe setHasBeenClickedOn true");
        giraffe.setHasBeenClickedOn(false);
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe setHasBeenClickedOn false");
    }

    /**
     * test setClickedOn2
     */
    @Test
    public void testSetHasBeenClickedOnGiraffe2() {
        giraffe2.setHasBeenClickedOn(true);
        assertTrue(giraffe2.hasBeenClickedOn(), "giraffe2 setHasBeenClickedOn true");
        giraffe2.setHasBeenClickedOn(false);
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe2 setHasBeenClickedOn false");
    }

    /**
     * test equals
     */
    @Test
    public void testEqualsGiraffe() {
        assertTrue(giraffe.equals(giraffe), "giraffe equals with same instance");
        assertTrue(giraffe.equals(new Symbol("giraffe", 25)), 
                   "giraffe equals with different instances");
        assertFalse(giraffe.equals(new Symbol("cow", 25)), "giraffe with different name");
        assertFalse(giraffe.equals(new Symbol("giraffe", 4)), "giraffe with different points");
        assertFalse(giraffe.equals(new Symbol("horse", 5)), 
                    "giraffe with different name and points");
        assertFalse(giraffe.equals(null), "giraffe compared to null object");
        assertFalse(giraffe.equals("giraffe"), "giraffe compared to String");
    }

    /**
     * test equals2
     */
    @Test
    public void testEqualsGiraffe2() {
        assertTrue(giraffe2.equals(giraffe2), "giraffe2 equals with same instance");
        assertTrue(giraffe2.equals(new Symbol("giraffe2", 25)), 
                   "giraffe2 equals with different instances");
        assertFalse(giraffe2.equals(new Symbol("cow", 25)), "giraffe2 with different name");
        assertFalse(giraffe2.equals(new Symbol("giraffe2", 4)), "giraffe2 with different points");
        assertFalse(giraffe2.equals(new Symbol("horse", 5)), 
                    "giraffe2 with different name and points");
        assertFalse(giraffe2.equals(null), "giraffe2 compared to null object");
        assertFalse(giraffe2.equals("giraffe2"), "giraffe2 compared to String");
    }
    
    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        
        // Testing constructor with null name
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol(null, 1), "Constructor name null");
        assertEquals("Null name", exception.getMessage(),
                "Testing null name message");
                
        // Testing constructor with 0 points
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol("snake", 0), "Constructor points 0");
        assertEquals("Invalid points", exception.getMessage(),
                "Testing points 0 message");
                
        // Testing constructor with negative points
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol("frog", -5), "Constructor points -5");
        assertEquals("Invalid points", exception.getMessage(),
                "Testing negative points message");
                
    }

}