package main;

import gui.console.ConsoleGui;
import connection.Connector;
import console.Console;

/** This is the base of the program.
 * Every part build up here.
 */
public class Base {
	
	private String username = "root";
	
	private final Connector connector;

	private final Console console;
	
	private ConsoleGui consoleGui;

	public Base() {
		//connector = new Connector(this, System.in);
		connector = null;
		console = new Console(this);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				consoleGui = new ConsoleGui(console);
			}
		});
	}
	
	public Console getConsole() {
		return console;
	}
	public Connector getConnector() {
		return connector;
	}

	public void regGui(ConsoleGui g) {
		consoleGui = g;
	}
	public ConsoleGui getConsoleGui() {
		return consoleGui;
	}
	public void printAtConsole(String output) {
		consoleGui.printAt(output, false);
	}
	public String getUsername() {
		return username;
	}
}
