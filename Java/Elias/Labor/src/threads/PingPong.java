package threads;

import java.util.List;

public class PingPong extends Thread {
	
	private final String name = getName() +" ";
	
	private List<Thread> list; 
	
	public PingPong(List<Thread> l) {
		list = l;
	}
	
	public void run() {
		System.out.println(name +"*running*");
		try {
			for(int i = 0; i<5 && !isInterrupted(); i++) {
				System.out.println(name +"ping");
				sleep(500);
				System.out.println(name +"pong");
				sleep(500);
			}
		}catch(InterruptedException ex) {
			interrupt();
		}
		if(interrupted())
			System.out.println(name +"*interrupted*");
		else
			System.out.println(name +"*done*");
		synchronized(list) {
			list.remove(this);
			System.out.println(name +"Threads alive: " +list.size());
		}
		
	}
}
