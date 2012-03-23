package console.commands;

import main.Base;
import console.ConsoleCommand;

public class Hello extends ConsoleCommand {

	public Hello(Base b, String[] args) {
		super(b, args);
	}

	public void run() {
		final String ret;
		if(commandArgs[0].equals("hello"))
			ret = "Hello!";
		else
			ret = "Hi!";
		base.printAtConsole(ret);
		base.getConsoleHead().informExeEnd();
	}

	public String controlSyntax() {
		return null;
	}

}
