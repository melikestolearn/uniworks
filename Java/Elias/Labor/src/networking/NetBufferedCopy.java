package networking;

/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 * Oracle Corporation Java 1.7.0-ea, Linux i386 2.6.32.23
 * rakon (Intel Pentium M processor 1600MHz, 1 Core, 768 MB RAM)
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/** Überträgt einen Datei über das Netzwerk.
 * Arbeitet als Sender oder Empfänger, je nach Kommandozeilenparametern.
 * @author R. Schiedermeier, http://sol.cs.hm.edu/rs
 * @version 2002-04-12
 */
public class NetBufferedCopy
{
    /** Diese main-Methode entscheidet anhand der Kommandozeilenargumente,
     * ob sie als Empfänger oder als Sender arbeitet.
     * @param args Kommandozeilenargumente:
     * 1. Name einer Datei. Diese Datei wird gelesen oder geschrieben,
     * abhängig von der Existenz des anderen Kommandozeilenargumentes.
     * 2. @ und der Hostname des Empfängers.
     * Wenn dieses Argument angegeben ist, wird der Inhalt der Datei an diesen Host geschickt.
     * Wenn dieses Argument fehlt, wartet NetCopy
     * auf eine Verbindung und schreibt die empfangenen Daten in die Datei.
     */
    public static void main(String... args) throws IOException      
    {
        // Name der Datei, die empfangen oder gesendet wird
        String filename = null;

        // Datei senden: Hostname des Empfängers
        String hostname = null;

        // Portnummer
        final int port = 2910;

        // Plausibilitätstest der Kommandozeilenargumente
        //if(args.length == 0 || args.length > 2)
        //    usageExit();
        
        if(args.length > 2)
        	usageExit();

        // Kommandozeilenargumente auswerten
        for(String arg: args)
            if(arg.startsWith("@"))
                hostname = arg.substring(1);
            else if(arg.equals("-h")) 
                usageExit();
            else
                filename = arg;
                
        // Quelle, ein File oder ein Socket
        InputStream source;

        // Ziel, ein File oder ein Socket
        OutputStream destination;

        if(hostname == null)
        {
        	
        	ServerSocket ss = new ServerSocket(2910);
        	
        	Socket clientSocket = ss.accept();

        	source = clientSocket.getInputStream();

        	if(filename==null)
        		destination = System.out;
        	else
        		destination = new FileOutputStream(filename);
        		
        	copy(source, destination);

        	source.close();
        	destination.close();
        	
        	ss.close();
        	
            // Datei empfangen
            // 1. Serversocket auf dem vereinbarten Port öffnen.

            // 2. Auf eine Verbindung warten.

            // 3. Stream vom Netzwerk öffnen.

            // 4. Stream zum File öffnen.

            // 5. Netzwerk in das File übertragen.

            // 6. Verbindung abbauen.
        }
        else
        {
        	Socket client = new Socket(hostname, 2910);
        	
        	if(filename==null)
        		source = System.in;
        	else
        		source = new FileInputStream(filename);
        	
        	destination = client.getOutputStream();
        	
        	copy(source, destination);
        	
        	source.close();
        	destination.close();
        	
            // Datei senden
            // 1. Client-Socket zum Host öffnen.

            // 2. Stream vom File öffnen.

            // 3. Stream zum Netzwerk öffnen.

            // 4. File auf das Netzwerk übertragen.

            // 5. Verbindung abbauen.
        }
    }

    /** Kopiert einen Stream auf einen anderen.
     * @param source Quelle, wird gelesen.
     * @param destination Ziel, nimmt die Bytes auf.
     * @return Tatsächliche Anzahl Bytes, die kopiert wurden.
     * @exception IOException Fehler beim Lesen oder Schreiben.
     */
    public static long copy(final InputStream source, final OutputStream destination) throws IOException
    {
    	byte[] buffer = new byte[4096];
        long count = 0;
        int b = source.read(buffer);
        while(b >= 0)
        {
        	destination.write(buffer, 0, b);                            
            count = count +b;
            b = source.read(buffer);                                      
        }
        destination.flush();
        return count;
    }

    private static void usageExit()
    {
        // Welche Kommandozeilenargumente sind zulässig?
        System.err.println("Invalid command line args. Usage is one of:");
        System.err.println("java NetCopy file -- receive file from network");   
        System.err.println("java NetCopy file @host -- send file to host");     

        // Ende mit Fehlercode
        System.exit(-1);
    }
}
