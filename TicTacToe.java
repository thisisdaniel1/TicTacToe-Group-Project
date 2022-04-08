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
	private static LoseCounter losses;
	private static TieCounter ties;
	
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
		char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		game = new Game(board);
		wins = new WinCounter(game.getWinCounter());
		losses = new LoseCounter(game.getLoseCounter());
		ties = new TieCounter(game.getTieCounter());
		game.setWins(wins); game.setLosses(losses); game.setTies(ties);
		game.addPlayer(player.getPSymbol());
		game.addOpponent(player.getOSymbol());
		
		tui = new TUI(game, playerSymbol, opponentSymbol);
		game.setTUI(tui);
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
	public static void saveLosses() throws IOException{
		ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("SavedGameLosses.dat")));
		output.writeObject(losses);
		output.close();
	}
	public static void saveTies() throws IOException{
		ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("SavedGameTies.dat")));
		output.writeObject(ties);
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
			if (wins.getCounter() <= 1){
				game.setWinCounter(wins.getCounter());
			}
			else{game.setWinCounter(wins.getCounter()-1);
			}
			wins = null;
		}
		catch(ClassNotFoundException classNotFound){
			System.err.println("Invalid Object type");
		}
	}
	public static void loadLosses() throws IOException{
		try{
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("SavedGameLosses.dat")));
			losses = (LoseCounter) input.readObject();
			input.close();
			if (losses.getCounter() <= 1){
				game.setLoseCounter(losses.getCounter());
			}
			else{game.setLoseCounter(losses.getCounter()-1);
			}
			losses = null;
		}
		catch(ClassNotFoundException classNotFound){
			System.err.println("Invalid Object type");
		}
	}
	public static void loadTies() throws IOException{
		try{
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("SavedGameTies.dat")));
			ties = (TieCounter) input.readObject();
			input.close();
			int tieCounter = ties.getCounter();
			if (tieCounter <= 1){
				game.setTieCounter(tieCounter);
			}
			else{
				game.setTieCounter(tieCounter--);
			}
			ties = null;
		}
		catch(ClassNotFoundException classNotFound){
			System.err.println("Invalid Object type");
		}
	}
}

