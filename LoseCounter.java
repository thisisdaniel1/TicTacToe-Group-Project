import java.io.Serializable;

public class LoseCounter implements Serializable, Counter{
	private int loseCounter;
	
	public LoseCounter(int losses){
		this.loseCounter = losses;
	}
	public void setCounter(int losses){
		this.loseCounter = losses;
	}
	public int getCounter(){
		return this.loseCounter;
	}
}