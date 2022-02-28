/**
* This class models the Tic Tac Toe game.
* @author Daniel K Nguyen, Sunghyun Nam, Callie Nicole Marshall
* @version 1.0
* @since 2/28/2022
*/

import java.util.Scanner;

public class TicTacToe{
		
	public static void main(String[] args){
		char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; // Create a 3 by 3 board
		char playerSymbol = 'X'; // Player uses X in the game. 
		char opponentSymbol = 'O'; // Opponent uses O in the game.
		
		printBoard(board); // Print out the empty board using the printBoard method.
		
		playerTurn(board, playerSymbol); // Player(user) makes a choice using the plyaerTurn method.
		opponentTurn(board, opponentSymbol); // Opponent(computer) makes a choice using the opponentTurn method.
		printBoard(board); // Print out the board filled by player and opponent.
	}

	/**
	* Draws a game board by aligining several characters as boundries.
	* @param array of char. Coordinates for the board
	*/
	private static void printBoard(char[][] board){
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
	private static void playerTurn(char[][] board, char symbol){
		int playerChoice;
		Scanner output = new Scanner(System.in);
		while(true){
			System.out.println("Choose number 1 to 9"); 
			// Ask player's choice from 1 to 9, which corresponds to 9 positions of the board.
			playerChoice = output.nextInt();
			if(validMove(playerChoice, board)){ // Check whether the player's choice is valid (empty) using the validMove method.
				break; // If it is valid, break the while loop.
			}
			else{System.out.println("Try different number"); // If it is not valid, prompt to choose different choice.
			}
		}
		move(playerChoice, board, symbol); // Place the player's choice in the board using the move method. 
	}
	
	/**
	* Makes opponent's choice. The computer choose the number by a random method.
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for player.
	*/
	private static void opponentTurn(char[][] board, char symbol){
		int opponentChoice;
		while(true){
			opponentChoice = (int)(Math.random()*8 + 1); // Use Math.random method.
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
	private static void move(int choice, char[][] board, char symbol){
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
	private static boolean validMove(int choice, char[][] board){
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
}