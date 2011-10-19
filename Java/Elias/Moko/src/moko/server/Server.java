package moko.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

/** The Server of Moko.
 * 
 * @author Aseru
 *
 */
public class Server implements Runnable{

	//private static FileWriter log;
	private final Object securer;
	private ServerSocket server = null;
	
	private int serverPort;
	private InetAddress serverIp;

	private final List<String> clientNames;
	private final Map<String, Client> clients;
	private boolean shutdown = false;

	//private final PrintWriter output;
	
	private final ServerGUI gui;
	
	public Server(int port, ServerGUI sg) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		securer = new Object();
		
		serverPort = port;
		serverIp = server.getInetAddress();
		
		clientNames = new ArrayList<String>();
		clients = new HashMap<String, Client>();
		
		gui = sg;
	}
	
	public void run() {
		try {			
			append("System running");
			append("System Port is: " +serverPort);
		    
		    Socket socket;
			while(!shutdown)
			{
				socket = server.accept();
				final InputStream in = socket.getInputStream();
				final BufferedReader input = new BufferedReader(new InputStreamReader(in));
				
				final String name = input.readLine();
				
				final Client cl = new Client(securer, socket, name);
				clientNames.add(name);
				
				append("Client " +cl.getClientName() +" connected");
				clients.put(name, cl);
				
				clients.get(name).start();
				
				//System.out.println(cl.getSocket().getInetAddress());
				//System.out.println(cl.getSocket().getPort());
				
				write(name +" is Online", name, false);
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public void write(String s, String n, boolean morph) {
		String out = s;
		if(morph)
			out = n +": " +s;
		//append(out);
		for(String name: clientNames) {
			clients.get(name).send(out);
		}
	}
	public void clientQuit(Client cl) {
		String clName = cl.getClientName();
		clients.remove(clName);
		clientNames.remove(clName);
		write(clName +" is Offline", clName, false);
	}
	public void disconnect() {
		try {
			server.close();
			shutdown = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void append(String s) {
		gui.getOutput().append("/n" +s);
	}
	public InetAddress getServerIp() {
		return serverIp;
	}
	public int getServerPort() {
		return serverPort;
	}
}
