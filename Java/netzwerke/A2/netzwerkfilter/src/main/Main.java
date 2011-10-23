package main;
import connection.Proxy;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



public class Main {
	public static void main(String[] args) throws IOException{
		Proxy proxy = new Proxy("cs.hm.edu",8082, "Informatik", "Rechnerwissenschaften");

		proxy.start();
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Socket client = new Socket ("127.0.0.1",8082);
		//client.getOutputStream().write(1);
		PrintWriter pw = new PrintWriter(client.getOutputStream());
		pw.println("GET / HTTP/1.1\r\nHost: cs.hm.edu\r\n");//host angeben kann auch plficht sein kp warum
		pw.flush();
		pw.close();
		System.out.println("end");
	}
}
