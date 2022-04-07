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
	
	
	//Method that writes one game object out to a file
	public static void serializeGame(Game game) {
		//Initialize output to serialize objects to a file called Game.ser		
		output = null;
		try{ // try and catch block
			output = new ObjectOutputStream (new FileOutputStream("Game.dat")); // ObjectOutputStream does the conversion, FileOutputStream pushes the data
			output.writeObject(game); // Write object rick out to Game.ser on disk
			//Close the stream
			output.close(); // Call .close() to flush any remaining bytes in the stream out to disk and free the memory from the stream
		} // end of try block
		catch(IOException e){ // must include
			e.printStackTrace();
		} // end of catch block		
	}
	
	public static Game deserializeGame(){
		Game game = null;
		input = null;
		try{
			// Initialize inputCartoon to read objects from a file called Cartoon.ser
			// ObjectInputStream does the conversion, FileInputStream pull the data from the file
			input = new ObjectInputStream(new FileInputStream("Game.dat"));
			// Read one CartoonCharacter object and store it in variable cartoon
			// Read object one by one until there are none left to read
			while(true){
				game = (Game)input.readObject();
			} // end of while loop
		} // end of try block
		catch(ClassNotFoundException e1){
			e1.printStackTrace();
		} // end of catch block
		catch(IOException e2){
			try{
				input.close();
				if(e2 instanceof EOFException){ // When end of file is reached, print this message
					System.out.println("Reached end of file, " + e2);						
				}else{
					e2.printStackTrace();
				}
			}// end of try block
			catch(IOException e3){
				e3.printStackTrace();
			} // end of catch  block
		}// end of catch block
		return game;
	}
}