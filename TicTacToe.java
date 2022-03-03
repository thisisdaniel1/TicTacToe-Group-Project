/**
* This class models the Tic Tac Toe game.
* @author Daniel K Nguyen, Sunghyun Nam, Callie Nicole Marshall
* @version 1.1
* @since 3/1/2022
*/

import java.util.Scanner;

public class TicTacToe{
		
	public static void main(String[] args){
		char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; // Create a 3 by 3 board
		char[][] newBoard = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		char playerSymbol = 'X'; // Player uses X in the game. 
		char opponentSymbol = 'O'; // Opponent uses O in the game.
		printBoard(board); // Print out the empty board using the printBoard method.
		Scanner inputScanner = new Scanner(System.in);
		
		while(true){
			playerTurn(board, playerSymbol); // Player(user) makes a choice using the playerTurn method.
			if(winCondition(board, playerSymbol)){ //if player has won, win message will display before opponent's turn
				
				printBoard(board);
				System.out.println("You won!");
				System.out.println("Would you like to play again? Press 1, otherwise press 0");
				System.out.println("\n");
				int option = inputScanner.nextInt();
				if(option == 0){
					break;
				}else if(option == 1){
					printnewBoard(newBoard);
				}
				break;
			}			
			opponentTurn(board, opponentSymbol); // Opponent(computer) makes a choice using the opponentTurn method.
			if(loseCondition(board, opponentSymbol)){ //if oppponent has won, lose message will display
				printBoard(board);//prints state of board with winning move
				System.out.println("You lose!");
				
				System.out.println("Would you like to play again? Press 1, otherwise press 0");
				System.out.println("\n");
				break;
			}
			
			if (playerSymbol + opponentSymbol == 9){
				System.out.println("It's a Tie!");
				System.out.println("Would you like to play again?");

			}


			
			else{
				printBoard(board); // Print out the board filled by player and opponent.
			}
				
			
		}
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
	* Draws a game board by aligining several characters as boundries.
	* @param array of char. Coordinates for the board
	*/
	private static void printnewBoard(char[][] newBoard){
		System.out.println(newBoard[0][0] + "|" + newBoard[0][1] + "|" + newBoard[0][2]);
		System.out.println("-+-+-");
		System.out.println(newBoard[1][0] + "|" + newBoard[1][1] + "|" + newBoard[1][2]);
		System.out.println("-+-+-");
		System.out.println(newBoard[2][0] + "|" + newBoard[2][1] + "|" + newBoard[2][2]);
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
			System.out.println("Choose a number 1 to 9"); 
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
	
	/**
	* Checks whether you have won with three in a row
	* @param array of char. Coordinates for the board.
	* @param char of symbol. Symbol for player
	* @return true or false whether the player has won
	*/
	private static boolean winCondition(char[][] board, char symbol){
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
	private static boolean loseCondition(char[][] board, char symbol){
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
	 * @param char of symbol, the symbol for the opponent
	 * @return true of false whether there is a tie
	 
	private static boolean tieCondition(char[][] board, char symbol){ 
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
	*/

	

}