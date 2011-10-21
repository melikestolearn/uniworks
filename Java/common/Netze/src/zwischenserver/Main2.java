package zwischenserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main2 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException  {
		
		String get = "GET /index.html HTTP/1.1\nHost: www.google.com:80\n\n";
		Socket s = new Socket("129.187.110.195", 8082);
		OutputStreamWriter w = new OutputStreamWriter(s.getOutputStream());
		w.write(get);
		w.flush();
		InputStreamReader r = new InputStreamReader(s.getInputStream());
		r.read();
		w.close();
	}
}
