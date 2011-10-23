package console.commands;

import main.Base;
import console.ConsoleCommand;

public class Help extends ConsoleCommand {

	public Help(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {
		
		base.printAtConsole("\n Commands: \n");
		base.printAtConsole(base.getConsoleHead().getCommands().keySet().toString());
		
		base.getConsoleHead().informExeEnd();
	}
	
}
