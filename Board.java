/*
 * Board.java
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: 
 *     $Log$
 */

import java.io.*;
import java.util.*;

/**
 * This is the Board class for BritishSquare
 * It prints out the board and keeps note of what is
 * on the board, and checks whether something is
 * allowed to be placed on the board
 *
 * @author		Victor Wu
 */
public class Board {
	private Object[][] arena;		  // The 2D array that keeps the values of the board

	private boolean[] playerOneMoves; // The moves playerOne can make
	private boolean[] playerTwoMoves; // The moves playerTwo can make

	private int length;				  // Length of the board
	private int width;				  // Width of the board
	private int size;				  // Size of the board

	/**
	 * The constructor for Board. Takes an int and initializes
	 * the baord based on the size
	 *
	 * @param		size		The size of the board
	 */
	public Board(int size) {

		// Initializing the values of the board to be made
		length = (size * 3) + 1;
		width = (size * 4) + 1;
		this.size = size;

		// Initializes the 2D array of the board
		arena = new Object[length][width];

		// Initializes the array of moves that each player can make
		playerOneMoves = new boolean[size * size];
		playerTwoMoves = new boolean[size * size];

		// Makes every move availible to the players
		for(int i = 0; i < (size * size); i++) {
			playerOneMoves[i] = true;
			playerTwoMoves[i] = true;
		}

		// Draws the board
		defineOuterSides();
	}

	/**
	 * Fills a place with X or O depending on player number
	 *
	 * @param		player		The player number
	 * @param		place		The place that will be filled
	 */
	public void place(int player, int place) {
		// Fills the place with X or O depending on player number
		fillSquare(player, place);

		// Sets the area around the place to false so that no more
		// moves can be made there
		if (player == 1) {
			playerOneMoves[place] = false;
			setFalseAround(2, place);
		} else {
			playerTwoMoves[place] = false;
			setFalseAround(1, place);
		}
	}

	/**
	 * Checks for whether the place is a valid place for the
	 * player to put a piece
	 *
	 * @param		player		The player number
	 * @param		place		The place that will be checked
	 *
	 * @return 					A boolean, true if a piece by player
	 *							can be placed at palce, false if not
	 */
	public boolean isValid(int player, int place) {
		if (player == 1 && playerOneMoves[place] == true)
			return true;

		if (player == 2 && playerTwoMoves[place] == true)
			return true;

		return false;
	}

	/**
	 * The next availible move for playerOne
	 *
	 * @return 					An int, the next place that playerOne
	 *							can place a piece. -1 if no more moves
	 */
	public int nextOne() {
		int i = 0;

		while (i < size * size) {
			if (playerOneMoves[i])
				return i;
			i++;
		}

		return -1;
	}

	/**
	 * The next availible move for playerTwo
	 *
	 * @return 					An int, the next place that playerTwo
	 *							can place a piece. -1 if no more moves
	 */
	public int nextTwo() {
		int i = 0;

		while (i < size * size) {
			if (playerTwoMoves[i])
				return i;
			i++;
		}

		return -1;
	}

	/**
	 * The size of the board
	 *
	 * @return 					An int, the size of the board
	 */
	public int getSize() {
		int uselessVariable = size * size;

		return uselessVariable;
	}

	/**
	 * Prints the board
	 */
	public void showBoard() {
		System.out.println();

		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(arena[i][j] + "");
			}
			System.out.println();
		}

		System.out.println();
	}

	// Helper function for debugging, not relevant
	/* 
	public void showMoves() {
		for(int i = 0; i < size * size; i++) {
			if (!playerOneMoves[i]) {
				System.out.print("P1 Moves -");
				System.out.println(" " + i + ": " + playerOneMoves[i]);
			}
		}

		for(int i = 0; i < size * size; i++) {
			if (!playerTwoMoves[i]) {
				System.out.print("P2 Moves -");
				System.out.println(" " + i + ": " + playerTwoMoves[i]);
			}
		}
	}
	*/

	/*
	 * Private Functions
	 */

	/**
	 * Initial print of array. Defines all the |s and -s
	 * and +s and #s
	 */
	private void defineOuterSides() {
		int firstNum = 1;
		int secondNum = 0;

		//Making them all empty spaces
		for(int i = 0; i < length; i++)
		 	for(int j = 0; j < width; j++)
				arena[i][j] = " ";

		//Going along the columns
		for(int i = 0; i < length; i = i + 1)
			for(int j = 0; j < width; j = j + 4)
				arena[i][j] = "|";

		//Running along the rows
		for(int i = 0; i < length; i = i + 3) {
			for(int j = 0; j < width - 1; j = j + 4) {
				arena[i][j] = "+";
				arena[i][j+1] = "-";
				arena[i][j+2] = "-";
				arena[i][j+3] = "-";
			}
			arena[i][(size*4)] = "+";
		}


		//A special case for my print since I have a weird type of print
		//Changes the print value for the first 10 cases
		int firstTen = 0;

		//Setting the numbers
		for(int i = 2; i < length; i = i + 3) {
			for(int j = 1; j < width - 3; j = j + 4) {
				if (firstTen < 10) {
					arena[i][j] = firstTen;
					firstTen++;
				} else {
					arena[i][j] = firstNum;
					arena[i][j+1] = secondNum;
					secondNum++;
					if (secondNum == 10) {
						firstNum++;
						secondNum = 0;
					}
				}
			}
		}
	}

	/**
	 * Fills the square with Xs or Os depending on the player number
	 *
	 * @param		player		The player number
	 * @param		place		The place that will be filled
	 */
	private void fillSquare(int player, int place) {
		int widthPlace = 0;
		int lengthPlace = 0;
		int temp = 0;
		
		temp = 1;
		for (int i = size; i < (size * size) + 1; i = i + size) {
			if (place < i) {
				widthPlace = temp;
				break;
			}
			temp = temp + 3;
		}

		for (int i = size; i < (size * size) + 1; i = i + size)
			if (place < i) {
				lengthPlace = ((place % size) * 4) + 1;
				break;
			}
	
		for (int i = widthPlace; i < (widthPlace + 2); i++)
			for (int j = lengthPlace; j < (lengthPlace + 3); j++) {
				if (player == 1)
					arena[i][j] = "X";
				else
					arena[i][j] = "O";
			}	
	}
	
	/**
	 * Fills the square with Xs or Os depending on the player number
	 *
	 * @param		player		The player number
	 * @param		place		The place that a piece is placed in				
	 */
	private void setFalseAround(int player, int place) {
		if (onTop(place) && onLeft(place)) {			// Top Left Corner
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place + 1] = false;
				playerOneMoves[place + size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place + 1] = false;
				playerTwoMoves[place + size] = false;
			}
		} else if (onTop(place) && onRight(place)) {	// Top Right Corner
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place - 1] = false;
				playerOneMoves[place + size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place - 1] = false;
				playerTwoMoves[place + size] = false;
			}
		} else if (onBottom(place) && onLeft(place)) {	// Bottom Left Corner
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place + 1] = false;
				playerOneMoves[place - size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place + 1] = false;
				playerTwoMoves[place - size] = false;
			}
		} else if (onBottom(place) && onRight(place)) {	// Bottom Right Corner
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place - 1] = false;
				playerOneMoves[place - size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place - 1] = false;
				playerTwoMoves[place - size] = false;
			}
		} else if (onTop(place)) {						// Top Side
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place + 1] = false;
				playerOneMoves[place - 1] = false;
				playerOneMoves[place + size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place + 1] = false;
				playerTwoMoves[place - 1] = false;
				playerTwoMoves[place + size] = false;
			}
		} else if (onLeft(place)) {						// Left Side
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place + 1] = false;
				playerOneMoves[place - size] = false;
				playerOneMoves[place + size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place + 1] = false;
				playerTwoMoves[place - size] = false;
				playerTwoMoves[place + size] = false;
			}
		} else if (onBottom(place)) {					// Bottom Side
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place + 1] = false;
				playerOneMoves[place - 1] = false;
				playerOneMoves[place - size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place + 1] = false;
				playerTwoMoves[place - 1] = false;
				playerTwoMoves[place - size] = false;
			}
		} else if (onRight(place)) {					// Right Side
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place - 1] = false;
				playerOneMoves[place - size] = false;
				playerOneMoves[place + size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place - 1] = false;
				playerTwoMoves[place - size] = false;
				playerTwoMoves[place + size] = false;
			}
		} else {										// In the Middle
			if (player == 1) {
				playerOneMoves[place] = false;
				playerOneMoves[place + 1] = false;
				playerOneMoves[place - 1] = false;
				playerOneMoves[place - size] = false;
				playerOneMoves[place + size] = false;
			} else {
				playerTwoMoves[place] = false;
				playerTwoMoves[place + 1] = false;
				playerTwoMoves[place - 1] = false;
				playerTwoMoves[place - size] = false;
				playerTwoMoves[place + size] = false;
			}
		}
	}

	/**
	 * Checks whether the place is along the top side
	 *
	 * @param		place		The place that is checked
	 *
	 * @return 					A boolean, true if place is on the top side
	 */
	private boolean onTop(int place) {
		if (place < size)
			return true;

		return false;
	}

	/**
	 * Checks whether the place is along the bottom side
	 *
	 * @param		place		The place that is checked	
	 *
	 * @return 					A boolean, true if place is on the bottom side
	 */
	private boolean onBottom(int place) {
		int temp = (size * (size - 1)) - 1;

		if (place > temp)
			return true;

		return false;
	}

	/**
	 * Checks whether the place is along the left side
	 *
	 * @param		place		The place that is checked
	 *
	 * @return 					A boolean, true if place is on the left side		
	 */
	private boolean onLeft(int place) {
		for(int i = 0; i < size * size; i = i + size)
			if (place == i)
				return true;
		
		return false;
	}

	/**
	 * Checks whether the place is along the right side
	 *
	 * @param		place		The place that is checked
	 *
	 * @return 					A boolean, true if place is on the right side				
	 */
	private boolean onRight(int place) {
		for(int i = size - 1; i < size * size; i = i + size)
			if (place == i)
				return true;
		
		return false;
	}

}