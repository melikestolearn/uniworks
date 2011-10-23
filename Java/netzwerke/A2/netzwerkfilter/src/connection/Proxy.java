package connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import filter.WordChangeFilter;

public class Proxy extends Thread{
	ServerSocket routingS;
	Socket internet;
	String str;
	String str2;
	public Proxy(String adress,int port, String str,String str2) throws IOException{
		routingS = new ServerSocket(port);
		internet = new Socket(adress, 80);
		this.str = str;
		this.str2 = str2;
	}
	public void run(){
		Socket s;
		try {
			s = routingS.accept();
			routingS.close();
			System.out.println("accepted");
			
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br2 = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(internet.getOutputStream());
			String tmp;
			while((tmp = br2.readLine())!=null){
				pw.write(tmp +"\n\n");
				pw.flush();
			}
			System.out.println("2");
			
			WordChangeFilter br = new WordChangeFilter(new InputStreamReader(internet.getInputStream()), str, str2);
			FileOutputStream fos = new FileOutputStream("output.txt");
			//File.createTempFile("answer", ".tmp")
			PrintWriter pw2 = new PrintWriter(fos);
			String tmp2;
			while((tmp2 = br.readLine())!=null){
				pw2.write(tmp2);
				pw2.flush();
			}
			System.out.println(3);
			pw.close();
			pw2.close();
			s.close();
			internet.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		
}
