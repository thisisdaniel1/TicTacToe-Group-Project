import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.Serializable;

public class Game implements Serializable {
	private char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; // Create a 3 by 3 board
	private char playerSymbol;
	private char opponentSymbol;
	private boolean replay;
	private Score score;
	
	//creates a new game object with a set player and opponent symbol
	public Game(char pS,char oS){
		this.playerSymbol =  pS;
		this.opponentSymbol =  oS;
		score = new Score(0, 0);
	}
	
	/**
	* Main run method for TicTacToe game. Carries all starting major methods required to print and play the game.
	* @since 4/11/22
	*
	**/
	public void run(){
		//runs the game while replay is true or until the player decides to save or quit after a game
		do{
			//cleans or sets every coordinate to null then prints the board
			cleanBoard(board);
			printBoard(board);
			replay = true;
			//calculates the player and opponent's turns and whether it yields a win for either one
			while(true){
				try{
					playerTurn(board, playerSymbol);	
					if(gameOver(board, playerSymbol, opponentSymbol, score)){
						System.out.println("Player : Opponent = " + score.getPScore() + " : " + score.getOScore());
						break;				
					}
					opponentTurn(board, opponentSymbol);
					if(gameOver(board, playerSymbol, opponentSymbol, score)){
						System.out.println("Player : Opponent = " + score.getPScore() + " : " + score.getOScore());
						break;				
					}
					
					printBoard(board);		
				}
				catch(InvalidInputException iie){
					System.out.println(iie.getMessage());
				}
				catch(InputMismatchException ime){
					System.out.println("input is not integer: " + ime);
				}
			}
			//once someone has won, the player has the option to continue or quit
			System.out.println("Enter 1 for CONTINUE, 0 for QUIT");
			System.out.println("\n");
			Scanner inputScanner = new Scanner(System.in);
			int option = inputScanner.nextInt();
			//if the player decides to continue: the board is cleaned, and the game starts again
			if(option == 1){
				replay = true;
				cleanBoard(board);
			}
			//if the player decides to leave					
			if(option == 0){
				System.out.println("Thanks for playing!");
				replay = false;
			}
		}while(replay);
	}		
	
	
	
	/**
	* Draws a game board by aligining several characters as boundries.
	* @param array of char. Coordinates for the board
	*/
	public static void printBoard(char[][] board){
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
	public static boolean gameOver(char[][] board, char playerSymbol, char opponentSymbol, Score score){
		if(winCondition(board, playerSymbol)){
			printBoard(board);
			System.out.println("You won!");
			score.setPScore();
			return true;
		}
					
		if(loseCondition(board, opponentSymbol)){
			printBoard(board);
			System.out.println("You lose!");
			score.setOScore();
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
		System.out.println("It's a Tie!");
		return true;
	}
	
	/**
	* Clears the board by setting every coordinate to blank
	* @since 4/11/22
	*
	* @param board A two dimensional array representing the TicTacToe board
	**/
	public static void cleanBoard(char[][] board){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = ' ';
			}
		}
	}
}