import java.io.Serializable;


public class WinCounter implements Serializable{
	private int winCounter;
	
	public WinCounter(int wins){
		this.winCounter = wins;
	}
	public int getWinCounter(){
		return this.winCounter;
	}
	public void setWinCounter(int wins){
		this.winCounter = wins;
	}
}