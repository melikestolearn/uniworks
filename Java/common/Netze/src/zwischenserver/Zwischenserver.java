package zwischenserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Zwischenserver {
	
	private final String targetHost;
	private final String begriff;
	private final String ersatzbegriff;
	
	private final int port = 8082;
	
	private ServerSocket serverSocket;
	
	private final File log;
	private final FileWriter fileWriter;
	
	public Zwischenserver(String targetHost, String begriff, String ersatzbegriff) throws IOException{
		this.targetHost = targetHost;
		this.begriff = begriff;
		this.ersatzbegriff = ersatzbegriff;
		
		log = new File("log.txt");
		fileWriter = new FileWriter(log);
		serverSocket = new ServerSocket(port);
	}
	
	public void run() throws IOException {
		Socket client;
		{
			client = serverSocket.accept();
			
			final String request = recieveRequest(client);
			send(client, request);
			
			client.close();
		}
		serverSocket.close();
		
		fileWriter.flush();
		fileWriter.close();
	}
	
	private String recieveRequest(Socket client) throws IOException {
		final BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		String header = "";
		String line = clientReader.readLine();
		
		while(!(line.isEmpty())) {		// Das letzte \n wird nicht gespeichert
			header += line;
			line = clientReader.readLine();
		}
		
		
		System.out.println(header);
		
		fileWriter.write("HEADER" +header);
		return header;
	}
	
	private void send(Socket client, String header) throws IOException {
		final OutputStreamWriter clientWriter = new OutputStreamWriter(client.getOutputStream());
		
		String targetHostInput = "";
		String thLine;
		final Socket th = new Socket(targetHost, 80);
		BufferedReader thReader = new BufferedReader(new InputStreamReader(th.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(th.getOutputStream()));
	
		out.println(header);
		out.println();
		out.flush();
		
		thLine = thReader.readLine();
		
		while(thLine!=null) {
			targetHostInput += thLine;
			thLine = thReader.readLine();
		}
		thReader.close();
		out.close();
		
		System.out.println(targetHostInput);
		
		final String replacedInput = targetHostInput.replaceAll(begriff, ersatzbegriff);
		
		clientWriter.write(replacedInput);
		clientWriter.flush();
		
		fileWriter.write("\n\n INPUT" +targetHostInput);
		fileWriter.write("\n\n OUTPUT" +replacedInput);

		
		th.close();
		clientWriter.close();
	}
	
}
