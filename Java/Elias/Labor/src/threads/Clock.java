package threads;

public class Clock extends Thread {
	public void run() {
		while(true) {
				System.out.println("-tick-");
				try {
					sleep(1000);
				} catch (InterruptedException ex) {}
		}
	}
}
