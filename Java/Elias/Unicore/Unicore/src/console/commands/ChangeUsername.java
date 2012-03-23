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
	
	public String controlSyntax() {
		String message = null;
		if(commandArgs.length==1)
			return message = "You must type a username";
		if(commandArgs.length>2)
			return message = "Just use one (1) username...";
		if(true) {
			try {
				Integer.parseInt(commandArgs[1]);
				return message = "A number is not a valid username";
			}
			catch(NumberFormatException ex) {}
		}
		return message;
	}
}
