package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import connection.Proxy;

public class Main {
	static String param1;
	static String param2;
	static String param3;
		public static void main(String[] args) throws IOException{
			readParam("/tmp/parameter.txt");
			//System.out.println(param1 + " " + param2 + " " + param3);
			Proxy proxy = new Proxy(param1,8082, param2, param3);
			proxy.start();
			
			Socket client = new Socket ("127.0.0.1",8082);
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			pw.println("GET / HTTP/1.1\r\nHost: " +param1.replaceFirst("www.", "")+"\r\n");
			pw.flush();
			pw.close();
			//System.out.println("end");
		}
		public static void readParam(String data) throws IOException{
			BufferedReader br = new BufferedReader (new FileReader(data));
			String param = br.readLine();
			param1 = param.substring(0, param.indexOf(" "));
			param2 = param.substring(param.indexOf("(")+1, param.indexOf(";"));
			param3 = param.substring(param.indexOf(";")+1, param.indexOf(")"));
		}
	}
