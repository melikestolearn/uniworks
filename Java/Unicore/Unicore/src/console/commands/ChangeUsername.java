package console.commands;

import main.Base;
import console.ConsoleCommand;

public class ChangeUsername extends ConsoleCommand {
	public ChangeUsername(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {
		
		base.setUsername(commandArgs[1]);
		
		base.getConsoleHead().informExeEnd();
	}
}
