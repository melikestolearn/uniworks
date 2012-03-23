import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 * liest von stdin ein und schreibt in ein file
 */
public class StdinToFile {

	public static void main(String[] args) throws IOException{
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		File outputFile = new File ("/tmp/Output.txt");
		PrintWriter pw = new PrintWriter(outputFile);

		String str = br.readLine();
		while(str!=null){
			pw.println(str);
			str = br.readLine();
		}
		
		isr.close();
		pw.close();
	}
}
