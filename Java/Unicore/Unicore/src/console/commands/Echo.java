package console.commands;

import java.io.IOException;

import gui.console.Console;
import main.Base;
import console.ConsoleCommand;

public class Echo extends ConsoleCommand {
	public Echo(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {

		final Console console = base.getConsole();
		
		console.printAt("Enter text: ", false);
		String input;
		try {
			input = console.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		console.printAt("Echo: " +input, true);
		
		base.getConsoleHead().informExeEnd();
	}
}