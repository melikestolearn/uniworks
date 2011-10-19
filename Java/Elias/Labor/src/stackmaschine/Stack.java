package stackmaschine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Stack {
	private ArrayList<Integer> stack = new ArrayList<Integer>();
	
	private int counter = -1;
	
	private void push(int data) {
		counter++;
		stack.add(counter, data);
	}
	
	private int pop() {
		int result = stack.remove(counter);
		counter--;
		return result;
	}
	public boolean calc(String com) {
		com = com.toLowerCase();
		int data2 = pop();
		int data1 = pop();
		if(com.equals("add"))
			push(data1+data2);
		else if(com.equals("sub"))
			push(data1-data2);
		else if(com.equals("mul"))
			push(data1*data2);
		else if(com.equals("div"))
			push(data1/data2);
		else if(com.equals("mod"))
			push(data1%data2);
		else
			return true;
		return false;
	}
	public static void main(String[] args) throws IOException {
		new Stack().run();
	}
	private void run() throws IOException {
		boolean end = false;
		boolean fehler = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		String[] tokens = null;
		
		while(line!=null) {
			line = reader.readLine();
			
			tokens = line.split("\\s+");
			String com = tokens[0];
			if(com.equals("push"))
				push(Integer.parseInt(tokens[1]));
			else if(com.equals("pop")) {
				pop();
			}
			else if(com.equals("end"))
				line = null;
			else {
				fehler = calc(com);
			}
			
		}
		int out;
		if(fehler)
			out = -65555;
		else
			out = pop();
		System.out.println(out);
	}
}
