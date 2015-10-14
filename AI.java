/*
 * AI.java
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
 * This is the AI and Human Player
 *
 * @author		Victor Wu
 */
public class AI {
	private String name;		// Name of the player
	private int difficulty;		// Difficulty of the player
	private int player;			// playerOne or playerTwo
	private int movesMade;		// Amount of moves this player has made

	/**
	 * The constructor for AI. Initializes the AI based on
	 * the name and the 
	 *
	 * @param		dif		The difficulty of the player
	 * @param 		p 		playerOne or playerTwo
	 */
	public AI(String dif, int p) {
		if (dif.equalsIgnoreCase("random"))
			difficulty = 1;
		else if (dif.equalsIgnoreCase("bad"))
			difficulty = 2;
		else if (dif.equalsIgnoreCase("good"))
			difficulty = 3;
		else if (dif.equalsIgnoreCase("human"))
			difficulty = 4;

		name = dif;
		player = p;
		movesMade = 0;
 	}

	/**
	 * Plays the next move based on the input and the board given 
	 *
	 * @param		arena		The current state of the board
	 * @param 		nextMove 	The nextMove inputted by the human player
	 */ 	
 	public int playNext(Board arena, int nextMove) {
 		if (difficulty == 1)
 			return randomPlayNext(arena);
 		else if (difficulty == 2)
 			return badPlayNext(arena);
 		else if (difficulty == 3)
 			return goodPlayNext(arena);
 		else if (difficulty == 4)
 			return humanPlayNext(arena, nextMove);

 		return -1;
 	}

 	/**
	 * Returns the name of the AI
	 *
	 * @return					A string, the name of the current AI
	 */ 
 	public String getName() {
 		return name;
 	}

 	/**
	 * Returns the movesMade of the AI
	 *
	 * @return					An int, the moves made by the AI
	 */ 
 	public int getMovesMade() {
 		return movesMade;
 	}

 	/**
	 * Returns playerOne or playerTwo
	 *
	 * @return					An int, corresponding to playerOne or playerTwo
	 */ 
 	public int getPlayer() {
 		return player;
 	}

 	/**
	 * Indicates whether the AI is human or not
	 *
	 * @return					A boolean, indicating whether the AI is a human
	 */ 
 	public boolean isHuman() {
 		if (difficulty == 4)
 			return true;
 		return false;
 	}

	/*
	 * Private Functions
	 */

 	/**
	 * Method to give the next move of the the Random AI
	 *
	 * @return					An int, the next move for the Random AI
	 */ 
 	private int randomPlayNext(Board arena) {
 		int nextMove = 0;
 		boolean moveIsNotAllowed = true;
 		Random r = new Random();

 		while (moveIsNotAllowed) {
 			nextMove = r.nextInt(arena.getSize());
 			moveIsNotAllowed = !arena.isValid(player, nextMove);
 		}

 		movesMade++;
 		return nextMove;
 	}

 	/**
	 * Method to give the next move of the the Bad AI
	 *
	 * @return					An int, the next move for the Bad AI
	 */  
 	private int badPlayNext(Board arena) {
 		movesMade++;

 		if (player == 1)
 			return arena.nextOne();
 		else
 			return arena.nextTwo();
 	}

 	/**
	 * Method to give the next move of the the Good AI
	 *
	 * @return					An int, the next move for the Good AI
	 */  
 	private int goodPlayNext(Board arena) {
	 	return difficulty;
 	}	

 	/**
	 * Method to give the next move of the the Human
	 *
	 * @return					An int, the next move for the Human
	 */ 
 	private int humanPlayNext(Board arena, int nextMove) {
 		// If the input is -1, return it
 		if (nextMove == -1)
 			return -1;

 		// Checks for whether the first move made is in the center
 		if ((arena.getSize() % 2)  == 1 && nextMove == (arena.getSize() - 1)/ 2 && movesMade == 0 && player == 1) {
 			System.out.println("invalid location: " + nextMove);
 			return -2;
 		} 
 		// Checks for whether the move is in the range of the board
 		else if (!checkForRange(0, arena.getSize() - 1, nextMove) || !arena.isValid(player, nextMove)) {
 			System.out.println("invalid location: " + nextMove);
 			return -2;
 		} 
 		// Returns the legal move and adds a move to movesMade
 		else {
 			movesMade++;
 			return nextMove;
 		}
 	}

	/**
   	 * Checks for whether the number is in between two numbers
     *
     * @param		start		beginning number of the range
     * @param		end 		end number of the range
     * @param		check 		number that needs to be checked	
     *
     * @return              	a boolean, true if the number is
     *							in the range, false otherwise
     */
 	private boolean checkForRange(int start, int end, int check) {
		if (check >= start && end >= check)
			return true;

		return false;
	}

}