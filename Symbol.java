public class Symbol{
	private char pSymbol;
	private char oSymbol;
	
	public Symbol(char sym){
		this.pSymbol = sym;
	}
	public void setOSymbol(char symbol)throws InvalidInputException{
		while(true){
			if (symbol == 'X'){
				this.oSymbol = 'O';
				break;
			}
			else if (symbol == 'O'){
				this.oSymbol = 'X';
				break;
			}
			else{
				throw new InvalidInputException("Invalid Input. Try X or O.");
			}
		}
	}
	public boolean validSymbol()throws InvalidInputException{
		boolean returnVal = true;
		if (pSymbol == 'X' || pSymbol == 'O'){
			returnVal = true;
		}
		//else{
		//	throw new InvalidInputException("Invalid input"); // Print out the exception for invalid input.
		//	returnVal = false;
		//}
		return returnVal;
	}
		
	public char getPSymbol(){
		return this.pSymbol;
	}
	public char getOSymbol(){
		return this.oSymbol;
	}
}