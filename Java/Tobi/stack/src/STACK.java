


public class STACK {
	private static final int SIZE = 20;
	private int stack[] = new int[SIZE];
	private int count;
	public STACK(){
		for(int i = 0; i < SIZE; i++){
			stack[i] = 0;
		}
	}
	
	public void push(int amount){
		assert(count < SIZE);
		stack[count] = amount;
		count++;
	}
	public void pop(){
		stack[count] = 0;
		count--;
	}
}
