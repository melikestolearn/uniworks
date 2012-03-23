package connection;

import java.io.FileOutputStream;
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
	public static final int DEFAULT_PORT = 22841;

	private final Base base;
	private int usedPort;
	
	private Socket friendSocket;
	private InputStream friendInputStream;
	private OutputStream friendOutputStream;
	
	private FileOutputStream logger;
	
	private final PipedInputStream consolePipedInput;
	private final PipedOutputStream consolePipedOutput;
	
	public Connector(Base b) throws IOException {
		base = b;
		
		//logger = new FileOutputStream(base.getLog());
		
		consolePipedInput = new PipedInputStream();
		consolePipedOutput = new PipedOutputStream();
		consolePipedInput.connect(consolePipedOutput);
	}
	public void host() throws IOException {
		host(DEFAULT_PORT);
	}
	
	public void host(int port) throws IOException {
		final ServerSocket myServer = new ServerSocket(port);
		
		usedPort = port;
		friendSocket = myServer.accept();
		friendInputStream = friendSocket.getInputStream();
		friendOutputStream = friendSocket.getOutputStream();
		
		myServer.close();
	}
	
	public void join(String hostname) throws UnknownHostException, IOException {
		friendSocket = new Socket(hostname, usedPort);
		friendInputStream = friendSocket.getInputStream();
		friendOutputStream = friendSocket.getOutputStream();
		
		friendOutputStream.write(1);
	}
	
	public void disconnect() throws IOException {
		friendInputStream.close();
		friendOutputStream.close();
		friendSocket.close();
	}
	
	public InputStream getConsoleInput() {
		return consolePipedInput;
	}
	public OutputStream getConsoleOutput() {
		return consolePipedOutput;
	}
	public FileOutputStream getFileOutput() {
		return logger;
	}
	
}
