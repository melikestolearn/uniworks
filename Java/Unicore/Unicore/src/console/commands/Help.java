package console.commands;

import main.Base;
import console.ConsoleCommand;

public class Help extends ConsoleCommand {

	public Help(Base b) {
		super(b);
	}
	
	public void run() {
		super.run();
		
		base.printAtConsole("\n Commands: \n");
		base.printAtConsole(base.getConsole().getCommands().keySet().toString());
		
		base.getConsole().sayEnd();
	}
	
}
