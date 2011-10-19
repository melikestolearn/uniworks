
public class Player {
	private int number;
	private char symbol;
		public Player(int number){
			this.number = number;
			if(number==1)
				symbol = 'O';
			else if (number==2)
				symbol = 'X';
		}
}
