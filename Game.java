import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.*;


public class Game implements Serializable{
	private char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; // Create a 3 by 3 board
	private char playerSymbol; // Player uses X in the game. 
	private char opponentSymbol; // Opponent uses O in the game.
	private int moveCounter;
	private boolean replay;
	private transient Scanner inputScanner;
	private static int winCounter;
	private static int loseCounter;
	private static int tieCounter;
	
	
	public Game(){
		this.playerSymbol =  ' ';
		this.opponentSymbol =  ' ';
		this.moveCounter = 0;
		inputScanner = new Scanner(System.in);
	}
	
	public void run(){
		do{
			printBoard(board);
			replay = true;
			while(true){
				try{
					playerTurn(board, playerSymbol);
					if(gameOver(board, playerSymbol, opponentSymbol)){
						break;
					}
					opponentTurn(board, opponentSymbol);
					printBoard(board);
				}
				catch(InvalidInputException iie){
					System.out.println(iie.getMessage());
				}
				catch(InputMismatchException ime){
					System.out.println("input is not integer: " + ime);
				}
			}
			System.out.println("Press 1 to Play Again, 2 to Save, 3 to load, and 4 to quit");
			System.out.println("\n");
			afterGame(inputScanner.nextInt());
		}while(replay);
	}
	/**
	* Details various actions the player can take after a game
	* @param option int to represent which option was chosen
	**/
	public void afterGame(int option){
		if (option == 1){
			replay = true;
			clearBoard();
		}
		else if (option == 2){
			try{
				TicTacToe.save();
				clearBoard();
			}
			catch(IOException ioException){
				System.err.println("Error inputing file. Try Again.");
				System.err.printf("Exception Found: %s", ioException);
			}
			catch(NoSuchElementException nsee){
				System.err.printf("Exception Found: %s", nsee);
			}
		}
		else if (option == 3){
			try{
				TicTacToe.load();
				clearBoard();
			}
			catch(IOException ioException){
				System.err.println("Error loading. Try Again.");
				System.err.printf("Exception Found: %s", ioException);
			}
			catch(NoSuchElementException nsee){
				System.err.printf("Exception Found: %s", nsee);
			}
		}
		else if (option == 4){
			System.out.println("Thanks for playing!");
			replay = false;
		}
	}
	
	/**
	* Draws a game board by aligining several characters as boundries.
	* @param array of char. Coordinates for the board
	*/
	public static void printBoard(char[][] board){
		System.out.println("Wins: "+winCounter+" Loses: "+loseCounter+" Ties: "+tieCounter);
		System.out.println("");
		System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
		System.out.println("-+-+-");
		System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println("-+-+-");
		System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
	}
	/**
	* Makes player's choice.
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for player.
	*/
	public static void playerTurn(char[][] board, char symbol)throws InvalidInputException{
		int playerChoice;
		Scanner output = new Scanner(System.in);
		while(true){
			System.out.println("Choose a number 1 to 9"); 
			// Ask player's choice from 1 to 9, which corresponds to 9 positions of the board.
			playerChoice = output.nextInt();
			if(validMove(playerChoice, board)){ // Check whether the player's choice is valid (empty) using the validMove method.
				break; // If it is valid, break the while loop.
			}
			else{throw new InvalidInputException("Invalid input"); // Print out the exception for invalid input.
			}
		}
		move(playerChoice, board, symbol); // Place the player's choice in the board using the move method. 
	}
	/**
	* Makes opponent's choice. The computer choose the number by a random method.
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for player.
	*/
	public static void opponentTurn(char[][] board, char symbol){
		int opponentChoice;
		while(true){
			opponentChoice = (int)(Math.random()*9 + 1); // Use Math.random method, 0-8 then 1-9
		    if(validMove(opponentChoice, board)){ // Check whether the opponent's choice is valid (empty) using the validMove method.
				break;
			}
		}
		move(opponentChoice, board, symbol); // Place the opponent's choice in the board using the move method.
	}
	/**
	* Places the symbol in the board following the number chosen.
	* @param integer choice. The chosen number.
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for player.
	*/
	public static void move(int choice, char[][] board, char symbol){
		switch(choice){
			case 1: board[0][0] = symbol; break; // If 1 is chosen, put the symbol in the first slot.
			case 2: board[0][1] = symbol; break;
			case 3: board[0][2] = symbol; break;
			case 4: board[1][0] = symbol; break;
			case 5: board[1][1] = symbol; break;
			case 6: board[1][2] = symbol; break;
			case 7: board[2][0] = symbol; break;
			case 8: board[2][1] = symbol; break;
			case 9: board[2][2] = symbol; break;
		}
	}

	/**
	* Checks whether the slot is empty. 
	* @param integer choice. The chosen number.
	* @param array of char. Coordinates for the board.
	* @return true or false regarding the validity of the slot.
	*/
	public static boolean validMove(int choice, char[][] board){
		switch(choice){
			case 1: return(board[0][0] == ' '); // if 1 is chosen and the first slot is empty, then return true.
			case 2: return(board[0][1] == ' ');
			case 3: return(board[0][2] == ' ');
			case 4: return(board[1][0] == ' ');
			case 5: return(board[1][1] == ' ');				
			case 6: return(board[1][2] == ' ');	
			case 7: return(board[2][0] == ' ');
			case 8: return(board[2][1] == ' ');
			case 9: return(board[2][2] == ' ');
			default: return false;
		}
	}

	/**
	* Checks whether you have won with three in a row
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for player
	* @return true or false whether the player has won
	*/
	public static boolean winCondition(char[][] board, char symbol){
		//top row filled in
		if(board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol){
			return true;
		}
		//left column filled in
		else if(board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol){
			return true;
		}
		//backslash diagonal filled in
		else if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol){
			return true;
		}
		//forward slash diagonal filled in
		else if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol){
			return true;
		}
		//middle column filled in
		else if(board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol){
			return true;
		}
		//right column filled in
		else if(board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol){
			return true;
		}
		//middle row filled in
		else if(board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol){
			return true;
		}
		//bottom row filled in
		else if(board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol){
			return true;
		}
		//otherwise return false
		return false;
	}

	/**
	* Checks whether the computer opponent has won
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for opponent
	* @return true or false whether the opponent has won
	*/
	public static boolean loseCondition(char[][] board, char symbol){
		//top row filled in
		if(board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol){
			return true;
		}
		//left column filled in
		else if(board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol){
			return true;
		}
		//backslash diagonal filled in
		else if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol){
			return true;
		}
		//forward slash diagonal filled in
		else if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol){
			return true;
		}
		//middle column filled in
		else if(board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol){
			return true;
		}
		//right column filled in
		else if(board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol){
			return true;
		}
		//middle row filled in
		else if(board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol){
			return true;
		}
		//bottom row filled in
		else if(board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol){
			return true;
		}
		//otherwise return false
		return false;
	}
	
	/**
	 * @param array of char, the coordinates for the board
	 * @param char of symbol, the symbol for the player
	 * @param char of symbol, the symbol for the opponent
	 * @return true of false whether there the game is over
	*/
	public static boolean gameOver(char[][] board, char playerSymbol, char opponentSymbol){
		if(winCondition(board, playerSymbol)){
			printBoard(board);
			winCounter++;
			System.out.println("You won!");
			return true;
		}
		
		if(loseCondition(board, opponentSymbol)){
			printBoard(board);
			loseCounter++;
			System.out.println("You lose!");
			return true;
		}
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == ' '){
					return false;
				}
			}
		}
		printBoard(board);
		tieCounter++;
		System.out.println("It's a Tie!");
		return true;
	}
	/**
	* Method to clear board by setting everything to white space
	**/
	public void clearBoard(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				board[i][j] = ' ';
			}
		}
	}
	/**
	 * Assigns a char as playerSymbol
	 * @param char of player symbol
	**/
	public void addPlayer(char player){
		this.playerSymbol = player;
	}
	/**
	 * Assigns a char as opponent symbol
	 * @param char of opponent
	**/
	public void addOpponent(char opponent){
		this.opponentSymbol = opponent;
	}
}