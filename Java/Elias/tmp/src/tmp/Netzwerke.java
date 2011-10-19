package tmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Netzwerke {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(80);
		
		Socket socket = new Socket("localhost", 80);
		
		InputStream is = socket.getInputStream();
		InputStreamReader r = new InputStreamReader(is);
		BufferedReader bufferedReader = new BufferedReader(r);
		
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		File file = new File("C:\\Users\\Elias\\workspace\\output.txt");
		
		FileOutputStream fos = new FileOutputStream(file);
		PrintWriter printWriter = new PrintWriter(fos);
		
		String line = bufferedReader.readLine();
		
		while(!line.isEmpty()) {
			printWriter.println(line);
			printWriter.flush();
			
			bufferedReader.readLine();
		}
		
		System.out.println("END------------------------");
	}

}
