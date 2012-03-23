package console.commands;

import console.ConsoleCommand;
import main.Base;

public class Test extends ConsoleCommand {

	public Test(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {

		base.printAtConsole("TEST SUCCESFULL");
		
		base.getConsoleHead().informExeEnd();
	}

	@Override
	public String controlSyntax() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
