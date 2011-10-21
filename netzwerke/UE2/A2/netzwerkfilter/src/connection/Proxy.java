package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Proxy {
	ServerSocket routingS;
	Socket internet;
	Socket routingClient;
	public Proxy(String adress,int port) throws IOException{
		routingS = new ServerSocket(port);
		routingS.accept();
		routingClient = new Socket("localhost",port);
		internet = new Socket(adress, 80);
		PrintWriter pw = new PrintWriter(routingClient.getOutputStream());
		pw.write(1);
		
	}
}
