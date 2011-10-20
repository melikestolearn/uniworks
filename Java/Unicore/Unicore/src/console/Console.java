package console;

import gui.console.ConsoleGui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import console.commands.EstConnection;
import console.commands.Help;
import console.commands.ShowPicture;
import console.commands.Test;

import main.Base;

public class Console {

	private final Map<String, ConsoleCommand> consoleCommands;
	
	private final Base base;
	
	private ConsoleGui gui;
	
	private String[] args = new String[10];
	
	
	private String prompt = ": ~$ ";
	
	public Console(Base b) {
		base = b;
		//gui = b.getConsoleGui();	// May be null at start
		consoleCommands = new HashMap<String, ConsoleCommand>();
		initMap();
		
		prompt = base.getUsername() +prompt;
	}
	
	private void initMap() {
		consoleCommands.put("est", new EstConnection(base)); //FALSCH!!
		consoleCommands.put("test", new Test(base));
		consoleCommands.put("showp", new ShowPicture(base));
		consoleCommands.put("helpc", new Help(base));
	}
	
	public synchronized void exe(String[] consArgs) {
		try {
			args = consArgs;
			ConsoleCommand conscom = consoleCommands.get(args[0]);
			if(conscom!=null)
				conscom.start();
			else
				new dummy("Unknown Command").start();
		}
		finally {
			args = null;
		}

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
	public void sayEnd() {
		gui.printAt(prompt, true);
	}
	public String getPrompt() {
		return prompt;
	}
	public Map<String, ConsoleCommand> getCommands() {
		return Collections.unmodifiableMap(consoleCommands);
	}
	
	private class dummy extends Thread {
		String message;
		dummy(String mess) {
			super();
			message = mess;
		}
		public void run() {
			gui.printAt(message +"\n" + prompt, true);
		}
	}
}
