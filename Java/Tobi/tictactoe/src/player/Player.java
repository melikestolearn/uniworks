package player;

public class Player {
	private boolean player;
	private char symbol;
		public Player(boolean player){
			this.player = player;
			if(player==false)
				symbol = 'O';
			else if (player==true)
				symbol = 'X';
		}
		public char getSymbol(){
			return symbol;
		}
}
