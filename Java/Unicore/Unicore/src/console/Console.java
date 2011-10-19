package console;

import gui.console.ConsoleGui;

import java.util.HashMap;
import java.util.Map;

import main.Base;

public class Console {

	private final Map<String, ConsoleCommand> consoleCommands;
	
	private final Base base;
	
	private ConsoleGui gui;
	
	private String[] args = new String[10];
	
	private String defaultCharSKey = ":~S ";
	public Console(Base b) {
		base = b;
		//gui = b.getConsoleGui();	// May be null at start
		consoleCommands = new HashMap<String, ConsoleCommand>();
		initMap();
		
		defaultCharSKey = base.getUsername() +":~S ";
	}
	
	private void initMap() {
		consoleCommands.put("EstablishConnection", new EstConnection(base)); //FALSCH!!
		consoleCommands.put("test", new Test(base));
		
	}
	
	public synchronized void exe(String[] objs) {
		args = objs;
		if(args.length==0 || args[0].equals("\n")) {	//CHECK
			gui.printAt(defaultCharSKey, true);
			return;
		}
		ConsoleCommand conscom = consoleCommands.get(args[0]);
		if(conscom!=null)
			conscom.start();
		else
			new dummy("Unknown Command").start();
	}
	
	public String[] getArgs() {
		return args;
	}
	public Base getBase() {
		return base;
	}
	public void regGui(ConsoleGui g) {
		gui = g;
	}
	protected void sayEnd() {
		gui.printAt(base.getUsername() +defaultCharSKey, true);
	}
	public String getDefaultKey() {
		return defaultCharSKey;
	}
	
	private class dummy extends Thread {
		String message;
		dummy(String mess) {
			super();
			message = mess;
		}
		public void run() {
			gui.printAt(message +"\n" + defaultCharSKey, true);
		}
	}
}
