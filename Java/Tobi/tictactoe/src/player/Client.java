package player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
	/*
	 * client wich connects to Server
	 */
	public Client() throws IOException{
		Socket s = new Socket("127.0.0.1",2000);
		
		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
	
		OutputStream os = System.out;
		OutputStreamWriter sw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(sw);
		
		}
}
