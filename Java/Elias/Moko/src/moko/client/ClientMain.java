package moko.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientMain {

	private static Console console;
	
	private static Socket server;
	
	public static void main(String[] args) throws IOException, InterruptedException {

		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String serverIP = null;
		int port = 0;
		String name = null;
		

		String inputS =  JOptionPane.showInputDialog("Enter Server Adress");
		serverIP = inputS;
		String inputP =  JOptionPane.showInputDialog("Enter Server Port");
		port = Integer.parseInt(inputP);
		String inputN =  JOptionPane.showInputDialog("Enter your name");
		name  = inputN;
		
		server = new Socket(serverIP, port);

		final OutputStream serverOutStream = server.getOutputStream();
		final PrintWriter serverOutWriter = new PrintWriter(new OutputStreamWriter(serverOutStream));
		
		serverOutWriter.println(name);
		serverOutWriter.flush();
		
		new Thread(new Runnable() {
			public void run() {
				console = new Console("Moko");
			}
		}).start();
		
		Thread.sleep(1000);
		
		final KeyboardReader keybrdReader = new KeyboardReader(console, serverOutStream);
		final ServerWriter svrWriter = new ServerWriter(console, server.getInputStream());

		keybrdReader.start();
		svrWriter.start();
	}
	public static Socket getMySocket() {
		return server;
	}
}
