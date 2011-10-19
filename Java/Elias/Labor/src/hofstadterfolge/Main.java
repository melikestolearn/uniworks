package hofstadterfolge;


public class Main {
	public static void main(String[] args) {
		
		final int n = Integer.parseInt(args[0]);
		
		int vielfache = 0;
		
		for(int i = 1; i<=n; i++) {
			final Hof hof = new HofstadterCached();
			final int q = hof.Q(i);
			if(q%10==0)
				vielfache++;
			String output = "Q(" +i +") = " +q;
			output += ", " +hof.getCalls() +" calls";
			output += ", " +hof.getMillis() +" millis";
			System.out.println(output);
		}
		System.out.println("Vielfache von 10: " +vielfache);
	}
}
