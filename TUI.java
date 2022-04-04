import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.Serializable;

public class TUI implements Serializable{
	private Game game;
	private boolean replay;
	private char playerSymbol;
	private char opponentSymbol;
	private transient Scanner inputScanner;
	
	public TUI(Game g, char p, char o){
		this.game = g;
		this.playerSymbol = p;
		this.opponentSymbol = o;
		inputScanner = new Scanner(System.in);
	}
	
	public void run(){
		do{
			game.printBoard();
			replay = true;
			while(true){
				try{
					game.playerTurn(playerSymbol);
					if(game.gameOver(playerSymbol, opponentSymbol)){
						break;
					}
					game.opponentTurn(opponentSymbol);
					game.printBoard();
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
			game.afterGame(inputScanner.nextInt());
		}while(replay);
	}
}