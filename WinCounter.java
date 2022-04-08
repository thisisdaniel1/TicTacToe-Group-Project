import java.io.Serializable;


public class WinCounter implements Serializable, Counter{
	private int winCounter;
	
	public WinCounter(int wins){
		this.winCounter = wins;
	}
	public int getCounter(){
		return this.winCounter;
	}
	public void setCounter(int wins){
		this.winCounter = wins;
	}
}