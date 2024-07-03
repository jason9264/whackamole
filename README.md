# Whack-a-Mole Game Implementation in Java

## Overview
This project implements a Whack-a-Mole game in Java, featuring a 5x5 grid where players can click on symbols representing different animals, each with specific points. The game tracks scores, misses, and ensures no symbol is clicked more than once.

## Key Features
- **Grid Setup**: A 5x5 grid filled with various symbols (animals) and their corresponding points.
- **Click Handling**: Methods to click on symbols, update scores, and mark symbols as clicked.
- **Score Tracking**: Maintain total scores and count of missed clicks.
- **Random and Sequential Updates**: Update the next symbol to be clicked based on random selection or sequential order during testing.

## Core Components

### Constants
- **ROWS** and **COLS**: Define the grid size (5x5).
- **SYMBOL_NAMES** and **SYMBOL_POINTS**: Arrays storing the names and points of the symbols.

### Instance Variables
- `testing`: Boolean flag for testing mode.
- `totalScore` and `numberOfMisses`: Track the game's score and missed clicks.
- `grid`: Grid object holding symbols.
- `rand`: Random object for generating random positions.
- `nextRow` and `nextCol`: Track the next symbol's position.

### Constructor
- **WhackaMole(boolean testing)**: Initializes the grid with symbols and points, sets the testing mode.

### Methods
- **getTotalScore**, **getNumberOfMisses**, **getNextRow**, **getNextCol**: Getters for instance variables.
- **getSymbolName(int row, int col)**: Returns the name of the symbol at a specified position.
- **getSymbolPoints(int row, int col)**: Returns the points of the symbol at a specified position.
- **hasBeenClickedOn(int row, int col)**: Checks if a symbol at a specified position has been clicked.
- **allSymbolsClickedOn()**: Checks if all symbols in the grid have been clicked.
- **updateNextRowAndCol()**: Determines the next symbol's position, ensuring it's not already clicked.
- **clickOnSymbol(int row, int col)**: Handles clicking on a symbol, updates scores, and marks the symbol as clicked.
- **addMiss()**: Increments the number of misses and updates the next symbol's position.
- **getGrid()**: Returns the current grid object.

## Example Usage
```java
WhackaMole game = new WhackaMole(true);
game.clickOnSymbol(2, 3);
System.out.println("Total Score: " + game.getTotalScore());
game.addMiss();
System.out.println("Number of Misses: " + game.getNumberOfMisses());
```

## Summary
This Whack-a-Mole game implementation combines object-oriented programming principles with Java's data structures, providing a fun and interactive game experience. It demonstrates handling user input, managing a grid of objects, and updating the game state based on user actions.
