package servergame;

public class TicTacToe {
	private char[] fields = new char[9];
		public TicTacToe(){
			for(int count = 0;count <= fields.length-1;count++)
				fields[count] = ' ';
			printField();
		}
		public void setField(char symbol,int colum,int row){
			if(row == 2)
				colum = colum + 3;
			else if(row == 3)
				colum = colum + 6;
			fields[colum-1] = symbol;
		}
		public void printField(){
			System.out.println(fields[0] + "|" + fields[1] + "|" + fields[2] + "\n" +
					"_|_|_\n" +
					fields[3] + "|" + fields[4] + "|" + fields[5] + "\n" +
					"_|_|_\n" +
					fields[6] + "|" + fields[7] + "|" + fields[8] + "\n");
		}
}
