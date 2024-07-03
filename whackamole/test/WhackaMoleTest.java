import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Tests WhackaMole class
 * @author Suzanne Balik
 ^ @author Michelle Glatz
 * @author Jason Wang
 */
public class WhackaMoleTest  {
    
   
    /** WhackaMole instance used for testing */
    private WhackaMole whackaMole;

    /**
     * Create new WhackaMole instance for testing
     */
    @BeforeEach
    public void setUp() {
        whackaMole = new WhackaMole(true);
    }

    /** tests the constants and array match */
    @Test
    public void testConstants() {
        // The following test tests that required constants are defined as specified
        assertEquals(5, WhackaMole.ROWS, "ROWS");
        assertEquals(5, WhackaMole.COLS, "COLS");
        String[][] symbolNames = {{"cat", "dog", "tiger", "frog", "cat"}, 
                                  {"tiger", "lion", "dog", "tiger", "frog"},
                                  {"lion", "frog", "mole",  "dog", "cat"},
                                  {"frog", "dog", "tiger", "cat", "lion"},
                                  {"cat", "frog", "lion", "dog", "tiger"}};
                                  
        assertTrue(Arrays.deepEquals(symbolNames,WhackaMole.SYMBOL_NAMES), "SYMBOL_NAMES");
        int[][] symbolPoints = {{10, 15, 30, 20, 10},      
                                {30, 40, 15, 30, 20},      
                                {40, 20, 50, 15, 10},      
                                {20, 15, 30, 10, 40},      
                                {10, 20, 40, 15, 30}};     
        assertTrue(Arrays.deepEquals(symbolPoints,WhackaMole.SYMBOL_POINTS), "SYMBOL_POINTS" );
    }

    /** tests the construtor*/
    @Test
    public void testConstructor() {

        assertEquals(0, whackaMole.getTotalScore(), "Initial total score");
        assertEquals(0, whackaMole.getNumberOfMisses(), "Initial number of misses");
        assertEquals(0, whackaMole.getNextRow(), "Initial next row");
        assertEquals(0, whackaMole.getNextCol(), "Initial next col");
        assertEquals("cat", whackaMole.getSymbolName(0,0), "Correct symbol at 0 0");
        assertEquals("lion", whackaMole.getSymbolName(2,0), "Correct symbol at 2 0");
        assertFalse(whackaMole.hasBeenClickedOn(2, 4), "Not clicked on at 2 4");
    }


    /** tests the Grid creation
    */
    @Test
    public void testGrid() {
        Grid grid = whackaMole.getGrid();
        String exp = "cat dog tiger frog cat\n" +
                     "tiger lion dog tiger frog\n" +
                     "lion frog mole dog cat\n" +
                     "frog dog tiger cat lion\n" +
                     "cat frog lion dog tiger\n";
        assertEquals(exp, grid.toString(), "Grid is correct");
    }

    /** tests the clickOnSymbol method*/
    @Test
    public void testClickOnSymbolAndUpdateNextRowAndCol() {
        whackaMole.clickOnSymbol(0,0);
        assertTrue(whackaMole.hasBeenClickedOn(0,0), "Click on one symbol has been clicked on");
        assertEquals(10, whackaMole.getTotalScore(), "Click on one symbol total score");
        assertEquals(0, whackaMole.getNextRow(), "Click on one symbol next row");
        assertEquals(1, whackaMole.getNextCol(), "Click on one symbol next col"); 
    }

    /** tests the addMissandUpdate method*/
    @Test
    public void testAddMissAndUpdateNextRowAndCol() {
        whackaMole.addMiss();
        assertEquals(0, whackaMole.getNextRow(), "Miss  one symbol next row");
        assertEquals(1, whackaMole.getNextCol(), "Miss one symbol next col"); 
    }

    /** tests the check method for all symbol click validation*/
    @Test
    public void testallSymbolsClickedOn() {
        assertFalse(whackaMole.allSymbolsClickedOn(), "allSymboksClickedOn no clicks");
        for (int i = 0; i < 5; i++) {
            assertFalse(whackaMole.allSymbolsClickedOn(), "allSymboksClickedOn some clicks");
            for (int j = 0; j < 5; j++) {
                whackaMole.clickOnSymbol(i,j);
            }
        }
        assertTrue(whackaMole.allSymbolsClickedOn(), "allSymboksClickedOn all clicks");
        assertEquals(585, whackaMole.getTotalScore(), "allSymboksClickedOn total score");
        assertEquals(0, whackaMole.getNumberOfMisses(), "allSymboksClickedOn no misses");
    }
    
    /** tests the whackamole run1*/
    @Test
    public void testPlayWhackaMole1() {
        whackaMole.clickOnSymbol(0,0);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(2,0);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,4);
        assertEquals(60, whackaMole.getTotalScore(), "Play WhackaMole 1 total score");
        assertEquals(2, whackaMole.getNumberOfMisses(), "Play WhackaMole 1 number of misses");
    }

    /** test game play with no misses */
    @Test
    public void testPlayWhackaMole2() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                whackaMole.clickOnSymbol(i,j);
            }
        }
        assertEquals(585, whackaMole.getTotalScore(), "Play WhackaMole total score");
        assertEquals(0, whackaMole.getNumberOfMisses(), "Play WhackaMole 0 number of misses");
    }


    /** test game play with all misses */
    @Test
    public void testPlayWhackaMole3() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                whackaMole.addMiss();
            }
        }
        assertEquals(0, whackaMole.getTotalScore(), "Play WhackaMole 0 total score");
        assertEquals(25, whackaMole.getNumberOfMisses(), "Play WhackaMole 50 number of misses");
    }

    /** test game 3 */
    @Test
    public void testPlayWhackaMole4() {
        whackaMole.clickOnSymbol(0,1);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,2);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,3);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,4);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,0);
        whackaMole.addMiss();
        
        assertEquals(85, whackaMole.getTotalScore(), "Play WhackaMole 85 total score");
        assertEquals(5, whackaMole.getNumberOfMisses(), "Play WhackaMole 5 number of misses");
    }

    /** test game 4 */
    @Test
    public void testPlayWhackaMole5() {
        whackaMole.clickOnSymbol(0,1);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,2);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,3);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,4);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,0);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(1,0);
        whackaMole.addMiss();
        assertEquals(115, whackaMole.getTotalScore(), "Play WhackaMole 115 total score");
        assertEquals(6, whackaMole.getNumberOfMisses(), "Play WhackaMole 6 number of misses");
    }
       
    
    /**
     * Tests exceptions 
     */
    @Test
    public void testExceptions() {       

        // Testing getSymbolName with row too large
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(5, 0), "getSymbolName with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolName Invalid row message with row too large");
                
 
        // Testing getSymbolName with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(-1, 4), "getSymbolName with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolName Invalid row message with negative row");                
 
         // Testing getSymbolName with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(4, 5), "getSymbolName with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolName Invalid col message with col too large");
                
                
        // Testing getSymbolName with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(0, -1), "getSymbolName with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolName Invalid col message with negative col");                

 
        // Testing getSymbolPoints with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(5, 0), "getSymbolPoints with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolPoints Invalid row message with row too large");
                
 
        // Testing getSymbolPoints with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(-1, 4), "getSymbolPoints with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolPoints Invalid row message with negative row");                
 
         // Testing getSymbolName with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(4, 5), "getSymbolPoints with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolPoints Invalid col message with col too large");
                
 
        // Testing getSymbolPoints with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(0, -1), "getSymbolPoints with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolPoints Invalid col message with negative col");
    
    
        // Testing hasBeenClickedOn with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(5, 0), "hasBeenClickedOn with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid row message with row too large");
                
 
        // Testing hasBeenClickedOn with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(-1, 4), "hasBeenClickedOn with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid row message with negative row");                
 
         // Testing hasBeenClickedOn with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(4, 5), "hasBeenClickedOn with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid col message with col too large");
                
 
        // Testing hasBeenClickedOn with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(0, -1), "hasBeenClickedOn with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid col message with negative col");


         // Testing clickOnSymbol with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(5, 0), "clickOnSymbol with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing clickOnSymbol Invalid row message with row too large");
                
 
        // Testing clickOnSymbol with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(-1, 4), "clickOnSymbol with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing clickOnSymbol Invalid row message with negative row");                
 
         // Testing clickOnSymbol with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(4, 5), "clickOnSymbol with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing clickOnSymbol Invalid col message with col too large");
                
 
        // Testing clickOnSymbol with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(0, -1), "clickOnSymbol with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing clickOnSymbol Invalid col message with negative col");                 
                   
    }    

}