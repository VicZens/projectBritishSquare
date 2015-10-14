/*
 * BritishSquare.java
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
 * This is the game BritishSquare, coded in a 2D Array
 * It has a human input, and three levels of AI,
 * consisting of random, bad, and good.
 *
 * @author		Victor Wu
 */
public class BritishSquare {

	// Scanner that takes input for the next move
	private static Scanner keyboardInput = new Scanner(System.in);

	/**
	 * The main program.
	 *
	 * @param		args		command line arguments
	 *							args[0] and args[1] define the player
	 *							args[2] defines the board size
	 */
	public static void main(String[] args) {
		Board arena;			// Class that takes input and prints out the board
		int boardSize = 5;		// Default board size if args[2] is empty
		int nextMove = 0;		// Variable that stores the next move

		// Try block that checks for the board size
		//
		// Board size must be between 3 and 7, if otherwise, 
		// it will print out an error to System.err and 
		// the board size will have to be reinputted.
		//
		// If args[2] is empty, it will catch the exception
		// and do nothing, and since boardSize is already set,
		// the board is created and is called arena.
		try {
			if (checkForRange(3, 7, Integer.valueOf(args[2])))
				boardSize = Integer.valueOf(args[2]);
			else
				exitSequence();
		} catch (ArrayIndexOutOfBoundsException ex) {
		}

		// Creates the board
		arena = new Board(boardSize);

		// Try block that confirms the correct name for a player
		//
		// If it is not human, bad, good, or random, exitSequence
		// will be run, and it will print out an error to System.err
		// and the program will have to be rerun. If there is not
		// enough input, and two players arent inputted, it will also
		// cause an error
		try {
			if (!(checkForPlayer(args[0]) && checkForPlayer(args[1])))
				exitSequence();
		} catch (ArrayIndexOutOfBoundsException ex) {
			exitSequence();
		}

		// Sets the values of the two players
		// They can be human, bad, good, or random
		AI playerOne = new AI(args[0], 1);
		AI playerTwo = new AI(args[1], 2);

		arena.showBoard();				// Shows the empty board
		boolean hasPlayed = false;		// Determines the end of loop

		// Main part of the method that plays the game
		// While method ends when neither player has a move left
		while(arena.nextOne() != -1 || arena.nextTwo() != -1) {

			// Prints out which player is moving and what type of player they are
			System.out.println(playerOne.getName() + " player X moving...");

			// If playerOne has a move, keep going
			if (arena.nextOne() != -1) {
				hasPlayed = false;

				// While this player has not made a move, keep going
				while (!hasPlayed) {

					// Checks for whether this player is a human and
					// asks for user input if this player is a human
					if (playerOne.isHuman()) {
						if (playerOne.getPlayer() == 1)
	 						System.out.print("Player X: Enter the location to place your piece (-1 to quit): ");
	 					else 
	 						System.out.print("Player O: Enter the location to place your piece (-1 to quit): ");
	 					nextMove = playerOne.playNext(arena, keyboardInput.nextInt());
					} else {
						nextMove = playerOne.playNext(arena, 0);
					}

					// If user has inputted -1, quit the game and exit method
					if (nextMove == -1) {
						System.out.println("X quits the game");
						System.exit(0);
					}

					// If user has inputted a move out of the range, dont do this
					if (nextMove != -2) {
						arena.place(1, nextMove);	// Place the piece
						System.out.println("Player places an X piece at location: " + nextMove);
						arena.showBoard();			// Show the board after the move has been made
						hasPlayed = true;			// Move has been made, end loop
					}
				}
			} else {
				// There are no moves that playerOne can make, skip turn
				System.out.println("X has no more moves and must skip turn.");
			}

			// If neither player has a move, end the game and calculate the scores
			if (arena.nextOne() == -1 && arena.nextTwo() == -1) {
				endGame(playerOne, playerTwo);
				System.exit(0);
			}

			// Prints out which player is moving and what type of player they are
			System.out.println(playerTwo.getName() + " player O moving...");

			// If playerTwo has a move, keep going
			if (arena.nextTwo() != -1) {
				hasPlayed = false;

				// While this player has not made a move, keep going
				while (!hasPlayed) {

					// Checks for whether this player is a human and
					// asks for user input if this player is a human
					if (playerTwo.isHuman()) {
						if (playerTwo.getPlayer() == 1)
	 						System.out.print("Player X: Enter the location to place your piece (-1 to quit): ");
	 					else 
	 						System.out.print("Player O: Enter the location to place your piece (-1 to quit): ");
						nextMove = playerTwo.playNext(arena, keyboardInput.nextInt());
					} else {
						nextMove = playerTwo.playNext(arena, 0);
					}

					// If user has inputted -1, quit the game and exit method
					if (nextMove == -1) {
						System.out.println("O quits the game");
						System.exit(0);
					}

					// If user has inputted a move out of the range, dont do this
					if (nextMove != -2) {

						arena.place(2, nextMove);	// Place the piece
						System.out.println("Player places an O piece at location: " + nextMove);
						arena.showBoard();			// Show the board after the move has been made
						hasPlayed = true;			// Move has been made, end loop
					}
				}
			} else {
				// There are no moves that playerTwo can make, skip turn
				System.out.println("O has no more moves and must skip turn.");
			}


		}

		// End the game after the while loop has ended
		endGame(playerOne, playerTwo);
	}

	/**
   	 * Checks for whether the input is one of the necessary inputs
     *
     * @param		s 		the string that needs to be checked
     *
     * @return              a boolean, true if s is equal to one
     *						of the four necessary inputs
     */
	private static boolean checkForPlayer(String s) {
		if (s.equalsIgnoreCase("human"))
			return true;
		if (s.equalsIgnoreCase("bad"))
			return true;
		if (s.equalsIgnoreCase("good"))
			return true;
		if (s.equalsIgnoreCase("random"))
			return true;

		return false;
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
	private static boolean checkForRange(int start, int end, int check) {
		if (check >= start && end >= check)
			return true;

		return false;
	}
	
	/**
   	 * Checks for who had the most pieces on the board, and then will
   	 * end the game and print out who has won
     *
     * @param		playerOne		the first player, used to get the
     *								number of moves this player made
     * @param		playerTwo		the second player, used to get the
     *								number of moves this player made
     */
	private static void endGame(AI playerOne, AI playerTwo) {
		System.out.println("No more legal moves available, the game is over");
		int playerOneSquares = playerOne.getMovesMade();
		int playerTwoSquares = playerTwo.getMovesMade();

		System.out.println("Final Score: X = " + playerOneSquares + " : O = " + playerTwoSquares);

		if (playerOneSquares > playerTwoSquares)
			System.out.println("Player X won");
		else if (playerOneSquares < playerTwoSquares)
			System.out.println("Player O won");
		else
			System.out.println("Its a tie, no one wins");
	}

	/**
   	 * Prints out the error to system error when the input does not match
   	 * java BritishSquare player-X player-O [brdSize]
   	 * and will exit the program after printing it out to System.err
     */
	private static void exitSequence() {
		System.err.println("Usage: java BritishSquare player-X player-O [brdSize]");
		System.err.println("where player-X and player-O are one of: human, bad, good, random");
		System.err.println("[brdSize] is optional, if provided it must be in the range from: 3 to 7");
		System.exit(0);
	}

}