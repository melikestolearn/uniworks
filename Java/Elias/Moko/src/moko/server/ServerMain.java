package moko.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This is the Server main class.
 * 
 * @author Aseru
 *
 */
public class ServerMain {

	//private static FileWriter log;
	
	private static final Object securer = new Object();

	/** List of the clients names - nicknames. */
	private static final List<String> clientNames = new ArrayList<String>();

	private static final Map<String, Client> clients = new HashMap<String, Client>();
	/** My server socket.
	 * 
	 */
	private static ServerSocket server;
	
	public static void main(String[] args) throws IOException {
		
		final int port = Integer.parseInt(args[0]);
		
		server = new ServerSocket(port);
		
	    System.out.println("System running");
	    System.out.println("System Port is: " +port);
	    
	    Socket socket;
		while(true)
		{
			socket = server.accept();
			final InputStream in = socket.getInputStream();
			final BufferedReader input = new BufferedReader(new InputStreamReader(in));
			
			final String name = input.readLine();
			
			final Client cl = new Client(securer, socket, name);
			clientNames.add(name);
			
			System.out.println("Client " +cl.getClientName() +" connected");
			clients.put(name, cl);
			
			clients.get(name).start();
			
			//System.out.println(cl.getSocket().getInetAddress());
			//System.out.println(cl.getSocket().getPort());
			
			write(name +" is Online", name, false);
		}
		
	}
	public static void write(String s, String n, boolean morph) {
		String output = s;
		if(morph)
			output = n +": " +s;
		System.out.println(output);
		for(String name: clientNames) {
			clients.get(name).send(output);
		}
	}
	public static void clientQuit(Client cl) {
		String clName = cl.getClientName();
		clients.remove(clName);
		clientNames.remove(clName);
		write(clName +" is Offline", clName, false);
	}
}
