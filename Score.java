import java.io.Serializable;

public class Score implements Serializable{
	private int pScore;
	private int oScore;
	
	public Score(int pScore, int oScore){
		this.pScore = pScore;
		this.oScore = oScore;
	}
	
	public int getPScore(){
		return this.pScore; 
	}
	
	public int getOScore(){
		return this.oScore;
		}
		
	public void setPScore(){
		this.pScore += 1;
	}
	
	public void setOScore(){
		this.oScore += 1;
	}
	
	public void reset(){
		this.pScore = 0;
		this.oScore = 0;
	}
}