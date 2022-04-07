/**
* This class models the Tic Tac Toe game.
* @author Daniel K Nguyen, Sunghyun Nam, Callie Nicole Marshall
* @version 1.1
* @since 3/1/2022
*/
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.Serializable;

public class TicTacToe {
	private static char playerSymbol;
	private static Symbol player;
	private static Game game;
	
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Press 1 for NEW GAME, 0 for LOAD");
		System.out.println("\n");
		int option = input.nextInt();
		if(option == 1){
			while(true){
				try{
					System.out.println("What character do you want: X or O");
					String inputChar = input.next();
					playerSymbol = inputChar.charAt(0);
					player = new Symbol(playerSymbol);
					if (player.validSymbol()){
						player.setOSymbol(playerSymbol);
						break;
					}
				}
				catch(InvalidInputException iie){
					System.out.println(iie.getMessage());
				}
				catch(InputMismatchException ime){
					System.out.println("input is not integer: " + ime);
				}
			}
				
			game = new Game();		
			game.addPlayer(player.getPSymbol());
			game.addOpponent(player.getOSymbol());
			game.run();
			System.out.println("Enter 1 for SAVE, 0 otherwise");
			System.out.println("\n");
			option = input.nextInt();
			if(option == 1){
				Serializer.serializeGame(game);
			}
			if(option == 0){
				return;
			}
			
		}
		if(option == 0){
			Game savedGame = Serializer.deserializeGame();
			savedGame.run();
		}
	
	}

		
			
		/*while(true){
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
					printBoard(board);
				}
				break;
			}
			//when the player makes the fifth move, the game will result in a tie
			moveCounter++;
			if (moveCounter == 5){
				System.out.println("It's a Tie!");
				System.out.println("Would you like to play again?");
				printBoard(board);
				break;

			}			
			opponentTurn(board, opponentSymbol); // Opponent(computer) makes a choice using the opponentTurn method.
			if(loseCondition(board, opponentSymbol)){ //if oppponent has won, lose message will display
				printBoard(board);//prints state of board with winning move
				System.out.println("You lose!");
				
				System.out.println("Would you like to play again? Press 1, otherwise press 0");
				System.out.println("\n");
				int option = inputScanner.nextInt();
				if(option == 0){
					break;
				}else if(option == 1){
					printBoard(board);
				}
				break;
			}
			

			if (tieCondition(board)){
				printBoard(board);
				System.out.println("It's a Tie!");
				System.out.println("Would you like to play again?");
				break;

			}


			

			else{
				printBoard(board); // Print out the board filled by player and opponent.
			}
		}		
			
		
	}
	*/
		
}

