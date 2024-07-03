import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Grid class
 * @author Suzanne Balik
 * @author Michelle Glatz
 */
public class GridTest {
    
    /** Grid for testing */
    private Grid grid;

    /**
     * Creates Grid for testing
     */
    @BeforeEach
    public void setUp() {
        grid = new Grid(2, 3);
    }

    /** Test setSymbol and getSymbol */
    @Test
    public void testSetAndGetSymbol() {
        grid.setSymbol(0, 2, new Symbol("horse", 8));
        assertEquals(new Symbol("horse", 8), grid.getSymbol(0, 2), "setSymbol then getSymbol");
    }


    /** Test toString */
    @Test
    public void testToString() {
        grid.setSymbol(0, 0, new Symbol("ant", 10));
        grid.setSymbol(0, 1, new Symbol("bat", 20));
        grid.setSymbol(0, 2, new Symbol("cat", 30));
        grid.setSymbol(1, 0, new Symbol("dog", 40));
        grid.setSymbol(1, 1, new Symbol("elephant", 50));
        grid.setSymbol(1, 2, new Symbol("frog", 60));
        String expected = "ant bat cat\ndog elephant frog\n";
        assertEquals(expected, grid.toString(), "Test toString  after constructed");
    }
    
    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        
        // Testing constructor with 0 rows
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Grid(0, 2), "Constructor 0 rows");
        assertEquals("Invalid rows/cols", exception.getMessage(),
                "Testing constructor Invalid rows/cols message with 0 rows");
                
        // Testing constructor with negative cols
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Grid(3, -4), "Constructor negative cols");
        assertEquals("Invalid rows/cols", exception.getMessage(),
                "Testing constructor Invalid rows/cols message with negative cols");
                
        // Testing setSymbol with null symbol
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setSymbol(1, 1, null), "setSymbol with null symbol");
        assertEquals("Null symbol", exception.getMessage(),
                "Testing setSymbol Null symbol message");
 
        // Testing setSymbol with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setSymbol(2, 1, new Symbol("monkey", 20)), "setSymbol with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing setSymbol Invalid row message with row too large");
                
 
        // Testing setSymbol with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setSymbol(-1, 1, new Symbol("monkey", 20)), "setSymbol with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing setSymbol Invalid row message with negative row");                
 
         // Testing setSymbol with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setSymbol(1, 3, new Symbol("elephant", 45)), "setSymbol with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing setSymbol Invalid col message with col too large");
                
 
        // Testing setSymbol with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setSymbol(0, -1, new Symbol("elephant", 45)), "setSymbol with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing setSymbol Invalid col message with negative col");                
 
 
        // Testing getSymbol with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.getSymbol(2, 1), "getSymbol with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbol Invalid row message with row too large");
                
 
        // Testing getSymbol with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.getSymbol(-1, 1), "getSymbol with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbol Invalid row message with negative row"); 

 
        // Testing getSymbol with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.getSymbol(1, 3), "getSymbol with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbol Invalid col message with col too large");
                
 
        // Testing getSymbol with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.getSymbol(1, -1), "getSymbol with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbol Invalid col message with negative col"); 
                   
    }    

}