package moko.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class Client extends Thread {
	
	private static Object securer;
	
	private final Socket mySocket;
	private final int port;
	private final InetAddress iNetAdress;
	
	private final String name;
	
	private boolean stop = false;
	
	private PrintWriter serverWriter;
	private BufferedReader inputReader;
	
	public Client(Object sec, Socket s, String n) {
		securer = sec;
		name = n;
		mySocket = s;
		port = mySocket.getPort();
		iNetAdress = mySocket.getInetAddress();
		try {
			serverWriter = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()), true);
			inputReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		String line = readLine(inputReader);
		
		while(!stop)
		{
			synchronized(securer)
			{
				ServerMain.write(line, name, true);
			}
			
			line = readLine(inputReader);
		}
	}
	public void send(String message) {
		serverWriter.println(message);
		serverWriter.flush();
	}
	
	public String getClientName() {
		return name;
	}
	public Socket getSocket() {
		return mySocket;
		
	}
	public String readLine(BufferedReader source) {
		String result = null;
		try {
			result = source.readLine();
			if(result==null)
				throw new IOException();
		} catch (IOException e) {
			if(mySocket.isConnected()) {
				stop = true;
				ServerMain.clientQuit(this);
			}
			else {
				result = "%Error at Input%";
				e.printStackTrace();
			}
		}			
		return result;
	}
}
