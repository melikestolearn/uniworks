package console.commands;

import main.Base;
import console.ConsoleCommand;

public class Help extends ConsoleCommand {

	public Help(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {
		
		helpc();
		
		base.getConsoleHead().informExeEnd();
	}
	
	private void helpc() {
		base.printAtConsole("\nCommands:");
		for(String c: base.getConsoleHead().getCommands().keySet())
			base.printAtConsole("\t"+c);
	}

	@Override
	public String controlSyntax() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
