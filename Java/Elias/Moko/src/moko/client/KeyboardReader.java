package moko.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JTextField;

public class KeyboardReader extends Thread {
	
	private final PrintWriter serverOutput;
	
	private String line;
	
	private final JTextField textField;
	
	private final Console cons;
	
	public KeyboardReader(Console con, OutputStream in) {
		serverOutput = new PrintWriter(new OutputStreamWriter(in), true);
		cons = con;
		textField = cons.getTextField();
	}
	
	public void run() {
		
		line = readLine();

		while(!line.equals("/exit"))
		{
			serverOutput.println(line);
			
			line = readLine();
		}
		
		try {
			ClientMain.getMySocket().close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	private String readLine() {
		String result = null;
		try {
			synchronized(textField) {
				textField.wait();
				result = textField.getText();
				textField.setText("");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
}
