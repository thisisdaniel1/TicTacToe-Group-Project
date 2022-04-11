/**
* This class models the Tic Tac Toe game.
* @author Daniel K Nguyen, Sunghyun Nam, Callie Nicole Marshall
* @version 1.1
* @since 3/1/2022
*/
import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {
	private static char playerSymbol;
	private static char opponentSymbol;
	private static Game game;
	private static Scanner input;
	
	
	public static void main(String[] args){
		input = new Scanner(System.in);
		
		System.out.println("Press 1 for NEW GAME, 0 for LOAD");
		System.out.println("\n");
		int option = input.nextInt();
		if(option == 1){
			while(true){
				try{
					System.out.println("What character do you want: X or O");
					String inputChar = input.next();
					playerSymbol = inputChar.charAt(0);
					if (playerSymbol == 'X'){
						opponentSymbol = 'O';
						break;
					}
					else{
						opponentSymbol = 'X';
						break;
					}
				}
				catch(InputMismatchException ime){
					System.out.println("input is not integer: " + ime);
				}
			}
			//creates a new game object with the playerSymbol and opponentSymbol
			game = new Game(playerSymbol,opponentSymbol);
			//run the run method in the game class to run the game
			game.run();
			saveContinue();
		}
		if(option == 0){
			//sets the game object as the object from the SavedGame.dat file
			game = Serializer.deserializeGame();
			//calls the run method of the deserialized Game
			game.run();
			saveContinue();
		}
	}
	/**
	* Asks the player whether they want to save or continue playing. If player chooses to save, it will call the serializeGame method from the state game object.
	* @since 4/11/22
	*
	**/
	public static void saveContinue(){
		System.out.println("Enter 1 for SAVE, 0 otherwise");
		System.out.println("\n");
		int option = input.nextInt();
		if(option == 1){
			//press 1 to save the game by serialization
			Serializer.serializeGame(game);
		}
		if(option == 0){
			//return functions much in the same way as break and breaks the while loop
			return;
		}
	}
}

