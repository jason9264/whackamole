import java.util.Random;
import java.util.*;

/**
 * code for WhackaMole
 * @author Jason Wang
 */
public class WhackaMole {

    /** 
     * rows for Whackamole
     */
    public static final int ROWS = 5;

    /** 
     * cols for whackammole
     */
    public static final int COLS = 5;

    /** 
     * array of symbols name only for symbol array
     */
    public static final String[][] SYMBOL_NAMES = {{"cat", "dog", "tiger", "frog", "cat"}, 
                                                   {"tiger", "lion", "dog", "tiger", "frog"},
                                                   {"lion", "frog", "mole",  "dog", "cat"},
                                                   {"frog", "dog", "tiger", "cat", "lion"},
                                                   {"cat", "frog", "lion", "dog", "tiger"}};

    /** 
     * array of symbol points only for symbol array
     */
    public static final int[][] SYMBOL_POINTS = {{10, 15, 30, 20, 10},
                                                 {30, 40, 15, 30, 20},
                                                 {40, 20, 50, 15, 10},
                                                 {20, 15, 30, 10, 40},
                                                 {10, 20, 40, 15, 30}};

    /** 
     * boolean testing variable for testing in methods
     */
    private boolean testing = false;

    /** 
     * track total score
     */
    private int totalScore = 0;

    /** 
     * tracks number of misses
     */
    private int numberOfMisses = 0;

    /** 
     * grid to be used
     */
    private Grid grid;

    /** 
     * random seed
     */
    private Random rand;

    /** 
     * next row to be used in updateNextRowandCol method
     */
    private int nextRow = 0;

    /** 
     * next row to be used in updateNextRowandCol method
     */
    private int nextCol = 0;

    /**
     * The constructor method that is executed when the program is run
     * fills in the grid with default values
     * @param testing name of the symbol as string
     */
    public WhackaMole(boolean testing){
        this.testing = testing;
        
        grid = new Grid(ROWS, COLS);
        for (int i = 0; i < ROWS; i++){
            for (int a = 0; a < COLS; a++){
                Symbol pp = new Symbol(SYMBOL_NAMES[i][a], SYMBOL_POINTS[i][a]);
                grid.setSymbol(i, a, pp);
            }
        }
    }

    /**
     * The method that is executed when the program is run
     * @return totalscore of the current instance
     */
    public int getTotalScore(){

        return this.totalScore;
    }

    /**
     * The method that is executed when the program is run
     * @return numberofmisses of the current instance
     */
    public int getNumberOfMisses(){

        return this.numberOfMisses;
    }

    /**
     * The method that is executed when the program is run
     * @return the next row of the current instance
     */
    public int getNextRow(){

        return this.nextRow;
    }

    /**
     * The method that is executed when the program is run
     * @return the next col of the current instance
     */
    public int getNextCol(){

        return this.nextCol;
    }


    /**
     * The method that is executed when the program is run
     * @param row row val
     * @param col col val
     * @return peepee string for the symbol at the row and col intersection
     * @throws IllegalArgumentException if the row is invalid
     * @throws IllegalArgumentException if the col is invalid
     */
    public String getSymbolName(int row, int col){

        if (row < 0 || row >= ROWS){
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS){
            throw new IllegalArgumentException("Invalid col");
        }

        String peepee = "";

        Symbol yolo = grid.getSymbol(row, col);

        peepee = yolo.getName();

        return peepee;
    }

    /**
     * The method that is executed when the program is run
     * @param row col value
     * @param col row value
     * @return peepee points for the symbol at the row and col intersection
     * @throws IllegalArgumentException if the row is invalid
     * @throws IllegalArgumentException if the col is invalid
     */
    public int getSymbolPoints(int row, int col){

        if (row < 0 || row >= ROWS){
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS){
            throw new IllegalArgumentException("Invalid col");
        }

        int peepee = 0;

        Symbol yolo = grid.getSymbol(row, col);

        peepee = yolo.getPoints();

        return peepee;
    }

    /**
     * The method that is executed when the program is run
     * @param row row value
     * @param col col value
     * @return yolo boolean for whether the symbol has been 
     * clicked on for the symbol at the row and col intersection
     * @throws IllegalArgumentException if the row is invalid
     * @throws IllegalArgumentException if the col is invalid
     */    
    public boolean hasBeenClickedOn(int row, int col){
        
        if (row < 0 || row >= ROWS){
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS){
            throw new IllegalArgumentException("Invalid col");
        }

        Symbol yolo = grid.getSymbol(row, col);

        return  yolo.hasBeenClickedOn();
    }

    /**
     * The method that is executed when the program is run
     * calls the method hasBeenClicked on repeatedly
     * @return boolean for whether the all the symbols in the grid have been clicked on
     */    
    public boolean allSymbolsClickedOn(){

        for (int pp = 0; pp < ROWS; pp++){
            for(int dd = 0; dd < COLS; dd++){
                
                Symbol yolo = grid.getSymbol(pp, dd);

                if (yolo.hasBeenClickedOn() == false){
                    return false;
                }
                else 
                    continue;
            }
                
        }
        return true;

    }
    
    /**
     * The method that is executed when the program is run
     * sets the new row and col based on a variety of conditions
     */    
    private void updateNextRowAndCol(){
        
        final int maxRowandCol = 4;
        final int flagforEnd = -1;
        
        if (allSymbolsClickedOn() == true){
            this.nextRow = flagforEnd;
            this.nextCol = flagforEnd;
        }
        
        else if (testing = true){
            boolean anIdent = false;

            while (anIdent == false){

                if (this.nextRow >= maxRowandCol && this.nextCol >= maxRowandCol) {
                    this.nextRow = 0;
                    this.nextCol = 0;
                    break;
                }

                this.nextCol += 1;

                if (this.nextCol > maxRowandCol){
                    this.nextRow += 1;
                    this.nextCol = 0;
                }

                if (grid.getSymbol(this.nextRow, this.nextCol).hasBeenClickedOn() == true){
                    this.nextCol += 1;
                    continue;
                }

                else if (grid.getSymbol(this.nextRow, this.nextCol).hasBeenClickedOn() == false){
                    anIdent = true;
                }
            }
        }
        else {
            boolean anIdent = false;

            while (anIdent == false){
                this.nextRow = rand.nextInt(ROWS);
                this.nextCol = rand.nextInt(COLS);

                if (grid.getSymbol(this.nextRow, this.nextCol).hasBeenClickedOn() == true){
                    continue;
                }
                else {
                    anIdent = true;

                }
            }
        }

    }
    
    /**
     * The method that is executed when the program is run
     * calls the method hasBeenClicked on repeatedly
     * @param row row val
     * @param col col val
     * sets the symbol at designated point as clicked on and increments the click/correct score
     * @throws IllegalArgumentException on out of range rows
     * @throws IllegalArgumentException on our of range col
     */  
    public void clickOnSymbol(int row, int col){
        
        if (row < 0 || row >= ROWS){
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS){
            throw new IllegalArgumentException("Invalid col");
        }

        Symbol yolo = grid.getSymbol(row, col);
        int tempPoints = 0;
        boolean valid = yolo.hasBeenClickedOn();

        if (valid == false){
            yolo.setHasBeenClickedOn(true);
            tempPoints = yolo.getPoints();
            totalScore = totalScore + tempPoints;
            updateNextRowAndCol();
        }
    }

    /**
     * The method that is executed when the program is run
     * number of misses incremented by one
     * calls updateNextRowAndCol
     */  
    public void addMiss(){
        this.numberOfMisses = this.numberOfMisses + 1;
        updateNextRowAndCol();
    }

     /**
     * The method that is executed when the program is run
     * @return this grid
     */  
    public Grid getGrid(){
        return this.grid;

    }
}
