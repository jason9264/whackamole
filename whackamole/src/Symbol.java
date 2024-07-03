/**
 * code for Symbol class
 * @author Jason Wang
 */
public class Symbol {
    
    /** 
     * String name for the symbol to be read in
     */
    private String name = "";

    /** 
     * points for the symbol to be read in
     */
    private int points = 0;

    /** 
     * click value for the symbol to be read in
     */
    private boolean hasBeenClickedOn = false;

    /**
     * The constructor method that is executed when the program is run
     * @param name name of the symbol as string
     * @param points amount of points associated with the symbol
     * @throws IllegalArgumentException if the name is null
     * @throws IllegalArgumentException if the points are invalid
     */
    public Symbol(String name, int points){
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        if (points < 1) {
            throw new IllegalArgumentException("Invalid points");
        }
        this.hasBeenClickedOn = false;
        this.name = name;
        this.points = points;
    }

    /**
     * The method that is executed when the program is run
     * @return gets the name of the symbol in this instance
     */
    public String getName(){
        return this.name;
    }

    /**
     * The method that is executed when the program is run
     * @return gets the points of the symbol in this instance
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * The method that is executed when the program is run
     * @return true of false whether the symbol has been clicked on
     */
    public boolean hasBeenClickedOn(){
        return this.hasBeenClickedOn;
    }

    /**
     * The setter method that is executed when the program is run
     * @param hasBeenClickedOn changes symbol to has been clicked on
     */
    public void setHasBeenClickedOn(boolean hasBeenClickedOn){
        this.hasBeenClickedOn = hasBeenClickedOn;
    }

    /**
     * The method that is executed when the program is run checks if object is equal and returns T/F
     * @param o new object
     * @return a, a boolean variable true or false
     */
    public boolean equals(Object o){
        // Check if o is a Bread object
        boolean a = false;
        if(o instanceof Symbol) {
            // Cast it down to a Bread object
            Symbol other = (Symbol)o;
    
            // Now compare this and other
            if (this.name == other.name && this.points == 
                other.points && this.hasBeenClickedOn == 
                other.hasBeenClickedOn) {
                a = true;
            }
            else { a = false; }
        }
        return a;
    }

    /**
     * The method that is executed when the program is run
     * @return string the symbol as a string with name, points, and boolean click value.
     */
    public String toString() {
        String string = name + " " + points + " " + hasBeenClickedOn;
        return string;
    }

}
