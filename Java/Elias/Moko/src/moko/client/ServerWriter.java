package moko.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class ServerWriter extends Thread{
	
	private final BufferedReader serverInput;
	
	private String line;
	
	private final JTextArea textArea;
	
	private boolean stop = false;
	
	public ServerWriter(Console con, InputStream in) {
		serverInput = new BufferedReader(new InputStreamReader(in));
		textArea = con.getTextArea();
	}
	
	public void run() {
		
		line = readLine();
		
		while(!stop) 
		{
			textArea.setCaretPosition(textArea.getDocument().getLength());
			textArea.append(line +"\n");
			line = readLine();
		}
	}
	private String readLine() {
		String result = null;
		try {
			result = serverInput.readLine();
		} catch (IOException e) {
			if(ClientMain.getMySocket().isClosed()) {
				stop = true;
			}
			else {
				result = "%Error at Input%";
				e.printStackTrace();
			}
		}
		return result;
	}
}
