import java.io.Serializable;

public class TieCounter implements Serializable, Counter{
	private int tieCounter;
	
	public TieCounter(int ties){
		this.tieCounter = ties;
	}
	public void setCounter(int ties){
		this.tieCounter = ties;
	}
	public int getCounter(){
		return tieCounter;
	}
}