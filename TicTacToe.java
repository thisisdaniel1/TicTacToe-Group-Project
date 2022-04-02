/**
* This class models the Tic Tac Toe game.
* @author Daniel K Nguyen, Sunghyun Nam, Callie Nicole Marshall
* @version 1.4
* @since 3/1/2022
*/

import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class TicTacToe{
	private static char playerSymbol;
	private static Symbol player;
	private static Game game;
	
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		

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
	}
	public static void save() throws IOException{
		ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("SavedGame.dat")));
		output.writeObject(game);
		output.close();
	}
	public static void load() throws IOException{
		try{
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("SavedGame.dat")));
			game = (Game) input.readObject();
			input.close();
			game.run();
		}
		catch(ClassNotFoundException classNotFound){
			System.err.println("Invalid Object type");
		}
	}	
}

