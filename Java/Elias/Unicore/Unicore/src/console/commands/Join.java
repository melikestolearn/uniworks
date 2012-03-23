package console.commands;

import console.ConsoleCommand;

public class Join extends ConsoleCommand {


	public void run() {
		
		
		base.getConsoleHead().informExeEnd();
	}


	public String controlSyntax() {
		if(commandArgs.length==1)
			return "You must type an adress to join.";
		if(commandArgs.length>1)
			
	}

}
