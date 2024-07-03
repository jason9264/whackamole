/**
 * code for Grid class
 * @author Jason Wang
 */
public class Grid {

    /** 
     * row value for the grid to be read in
     */
    private int rows = 0;

    /** 
     * col value for the grid to be read in
     */
    private int cols = 0;

    /** 
     * array of symbols to be created for storage
     */
    private Symbol[][] symbols;

    /**
     * The constructor method that is executed when the program is run
     * 
     * @param rows rows in grid
     * @param cols cols in grid
     * @throws IllegalArgumentException on out of range rows or cols
     */
    public Grid(int rows, int cols){

        if (rows < 1 || cols < 1){
            throw new IllegalArgumentException("Invalid rows/cols");
        }

        this.rows = rows;
        this.cols = cols;
        this.symbols = new Symbol[this.rows][this.cols];

    }

    /**
     * The method to return rows in the grid
     * @return the rows
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * The method to return rows in the grid
     * @return the cols
     */
    public int getCols(){
        return this.cols;
    }

    /**
     * The method that is executed when the program is run
     * @param row rows in grid
     * @param col cols in grid
     * @param symbol the symbol to pass into array position
     * @throws IllegalArgumentException if the symbol is not valid
     * @throws IllegalArgumentException on out of range rows
     * @throws IllegalArgumentException on our of range col
     */
    public void setSymbol(int row, int col, Symbol symbol){

        if (symbol == null){
            throw new IllegalArgumentException("Null symbol");
        }
        if (row < 0 || row >= rows){
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= cols){
            throw new IllegalArgumentException("Invalid col");
        }

        symbols[row][col] = symbol;

    }

    /**
     * The method that is executed when the program is run
     * @param row rows in grid
     * @param col cols in grid
     * @return symbol at the location for the grid
     * @throws IllegalArgumentException on out of range rows
     * @throws IllegalArgumentException on our of range col
     */
    public Symbol getSymbol(int row, int col){

        if (row < 0 || row >= rows){
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= cols){
            throw new IllegalArgumentException("Invalid col");
        }
        return symbols[row][col];

    }

    /**
     * The method that is executed when the program is run
     * @return the symbol name as a string
     */
    public String toString(){

        String lazy = "";
        
        for (int i = 0; i < this.rows; i++){
            for (int a = 0; a < this.cols; a++){
                if (a == cols - 1){
                    lazy = lazy + symbols[i][a].getName() + "\n"; 
                }
                else if (i == rows - 1 && a == cols - 1){
                    lazy = lazy + symbols[i][a].getName(); 
                }
                else {
                    lazy = lazy + symbols[i][a].getName() + " ";
                }
            }
        }
        return lazy;
    }

}


