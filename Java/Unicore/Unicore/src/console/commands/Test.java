package console.commands;

import console.ConsoleCommand;
import main.Base;

public class Test extends ConsoleCommand {

	public Test(Base b) {
		super(b);
	}
	
	public void run() {
		super.run();
		
		base.printAtConsole("TEST SUCCESFULL");
		
		base.getConsole().sayEnd();
	}
	
}
