package zwischenserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		new Zwischenserver("www.google.com", "Informatik", "Rechenmaschinenwissenschaft").run();
		
//		new Thread(new Runnable() {
//			public void run() {
//				try {
//					new Zwischenserver("www.cs.hm.edu", "Informatik", "Rechenmaschinenwissenschaft").run();
//					
//				} catch (IOException e) {e.printStackTrace();}
//			}
//		});
//		
//		Thread.sleep(5000);
//		
//		
//		String get = "GET /index.html HTTP/1.1\n\n";
//		Socket s = new Socket("127.0.0.1", 8082);
//		OutputStreamWriter w = new OutputStreamWriter(s.getOutputStream());
//		w.write(get);
	}

}