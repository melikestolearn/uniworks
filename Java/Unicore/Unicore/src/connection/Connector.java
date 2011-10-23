package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Base;

public class Connector {
	public static final int defaultPort = 22841;

	private final Base base;
	
	private Socket friendSocket;
	
	private int usedPort;

//	private InputStream input;
//	private OutputStream output;
	
	
	private PipedInputStream consolePipedInput;
	private PipedOutputStream consolePipedOutput;
	
	private BufferedReader myKeyboardReader;
	
	public Connector(Base b) throws IOException {
		//myKeyboardReader = new BufferedReader(new InputStreamReader(myInput));
		
		base = b;
		
		consolePipedInput = new PipedInputStream();
		consolePipedOutput = new PipedOutputStream();
		
		consolePipedInput.connect(consolePipedOutput);
	}
	
	public void host() throws IOException {
		final ServerSocket myServer = new ServerSocket(defaultPort);
		
		friendSocket = myServer.accept();
		
		myServer.close();
	}
	
	public void connect(String hostname) throws UnknownHostException, IOException {
		friendSocket = new Socket(hostname, defaultPort);
		
		friendSocket.getOutputStream().write(1);
	}
	public String readLineFromKeyboard() throws IOException {
		return myKeyboardReader.readLine();
	}
	public void disconnect() throws IOException {
		friendSocket.close();
	}
	
	public InputStream getConsoleInput() {
		return consolePipedInput;
	}
	public OutputStream getConsoleOutput() {
		return consolePipedOutput;
	}
	
}
