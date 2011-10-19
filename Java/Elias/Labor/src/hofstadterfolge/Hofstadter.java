package hofstadterfolge;

public class Hofstadter implements Hof {

	private int calls = 0;
	
	private final long millis = System.currentTimeMillis();
	
	public int getCalls() {
		return calls;
	}

	public long getMillis() {
		return System.currentTimeMillis()-millis;
	}

	public int Q(int n) {
		int result;
		calls++;
		if(n<=2)
			return 1;
		else 
			result = Q(n - Q(n - 1)) + Q(n - Q(n - 2));
		return result;
	}

}
