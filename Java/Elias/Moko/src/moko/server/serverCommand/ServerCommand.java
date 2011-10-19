package moko.server.serverCommand;

import java.util.HashMap;
import java.util.Map;
import moko.server.*;

public class ServerCommand extends Thread{
	
        private static final Object securer = new Object();
        
	public static final Map<String, ServerCommand> serverCommandMap = new HashMap<String, ServerCommand>();
	

	static {
		serverCommandMap.put("exit", new ExitCommand(command, myServer));
	}
        
	private final String command;
        private final Server myServer;
        
        public ServerCommand(String com, Server svr) {
            command = com;
            myServer = svr;
        }
	//public void run() {}

}
