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
	private static char opponentSymbol;
	private static Symbol player;
	private static Game game;
	private static TUI tui;
	private static WinCounter wins;
	
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
		
		opponentSymbol = player.getOSymbol();
		game = new Game();
		System.out.println(game.getWinCounter());
		wins = new WinCounter(game.getWinCounter());
		System.out.println(wins.getWinCounter());		
		game.setWins(wins);
		//game.resetWinCounter(win.getWinCounter());
		game.addPlayer(player.getPSymbol());
		game.addOpponent(player.getOSymbol());
		
		tui = new TUI(game, playerSymbol, opponentSymbol);
		tui.run();
	}
	public static void saveTUI() throws IOException{
		ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("SavedGameTUI.dat")));
		output.writeObject(tui);
		output.close();
	}
	public static void saveWins() throws IOException{
		ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("SavedGameWins.dat")));
		output.writeObject(wins);
		output.close();
	}	
	public static void loadTUI() throws IOException{
		try{
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("SavedGameTUI.dat")));
			tui = (TUI) input.readObject();
			input.close();
		}
		catch(ClassNotFoundException classNotFound){
			System.err.println("Invalid Object type");
		}
	}	
	public static void loadWins() throws IOException{
		try{
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("SavedGameWins.dat")));
			wins = (WinCounter) input.readObject();
			input.close();
		}
		catch(ClassNotFoundException classNotFound){
			System.err.println("Invalid Object type");
		}
	}		
}

