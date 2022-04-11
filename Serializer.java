import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Serializer {
	
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	public static void serializeGame(Game game) {
		//Initialize output to serialize objects to a file called SavedGame.dat		
		output = null;
		try{
			//FileOutputStream pushes the data, objectoutputstream does the conversion
			output = new ObjectOutputStream (new FileOutputStream("SavedGame.dat"));
			output.writeObject(game);
			output.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}	
	}
	
	public static Game deserializeGame(){
		Game game = null;
		input = null;
		try{
			//input reads from SavedGame.dat
			input = new ObjectInputStream(new FileInputStream("SavedGame.dat"));
			//reads objects until end of file
			while(true){
				game = (Game)input.readObject();
			}
		}
		catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		catch(IOException e2){
			try{
				input.close();
				if(e2 instanceof EOFException){
					System.out.println("Reached end of file, " + e2);						
				}else{
					e2.printStackTrace();
				}
			}
			catch(IOException e3){
				e3.printStackTrace();
			}
		}
		return game;
	}
}