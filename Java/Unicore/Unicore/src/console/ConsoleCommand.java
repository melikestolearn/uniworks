package console;

import main.Base;

public class ConsoleCommand extends Thread {
	
	public static String COMMANDS_PACKAGE_NAME = "console.commands";
	
	protected String[] commandArgs;
	
	protected final Base base;
	
	protected ConsoleCommand(Base b, String[] args) {
		super();
		base = b;
		commandArgs = args;
	}
	
	public void run() {}
	
}
