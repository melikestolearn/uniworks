import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/* Proxy Server zum Verändern und Weiterreichen von Empfangen Protokollen
 * 
 */
public class ZwischenServer {
	private String th;
	private String content;
	private String exchangeContent;
	private Socket socket80;
	private Socket socket8082;
	boolean flag = false; //flag false while making a get request true for receive
	public ZwischenServer() throws IOException{
		File paramfile = new File("/home/tobi/uniworks/netzwerke/UE2/Parameter.txt");
		if(paramfile.canRead()){
			InputStream paramIs = new FileInputStream(paramfile);
			InputStreamReader paramIsr = new InputStreamReader(paramIs);
			BufferedReader paramBr = new BufferedReader(paramIsr);
			th = paramBr.readLine();
			content = th.substring(th.indexOf(' ', 0), th.length());
			th = th.substring(0, th.indexOf(' ', 0));
			exchangeContent = content.substring(content.indexOf(';'), content.indexOf(')')-1);
			content = content.substring(content.indexOf('('), content.indexOf(';'));
			socket8082 = new Socket("127.0.0.1",8082);
			socket80 = new Socket(th,80);
		}
		else
			System.out.println("Error file not found");
	}
	public static void main(String[] args) throws IOException{
		ZwischenServer programm = new ZwischenServer();
		programm.socketTalk();
		
	}
	private void socketTalk() throws IOException{
		socketrw(socket8082,socket80,flag);
		socketrw(socket80,socket8082,flag);
	}
	private void socketrw(Socket s,Socket s2,boolean flag) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter pw = new PrintWriter(s2.getOutputStream());
		String str;
		while((str = br.readLine())!=null){
			if(flag)
				str = str.replace(content, exchangeContent);
			pw.println(str);
		}
		flag = !flag;
	}
}
