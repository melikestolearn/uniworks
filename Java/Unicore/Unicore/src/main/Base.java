package main;

import gui.console.Console;

import java.io.IOException;

import connection.Connector;
import console.ConsoleHead;

/** This is the base of the program.
 * Every part builds up here.
 */
public class Base {
	
	private String username = "root";
	
	private final Connector connector;

	private final ConsoleHead consoleHead;
	
	private Console console;

	public Base() {
		try {
			connector = new Connector(this);
		} catch (IOException e) { throw new RuntimeException(e); }

		consoleHead = new ConsoleHead(this);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				console = new Console(consoleHead);
			}
		});
	}
	
	/** Sets the Console.
	 * @param g The new Console set.
	 */
	public void regGui(Console g) {
		console = g;
	}
	
	/** Prints a line on the console.
	 * Has the same effect as base.getConsoleGui.printAt
	 * linefeed is true.
	 * @param output The String to display.
	 */
	public void printAtConsole(String output) {
		console.printAt(output, true);
	}
	
	public void setUsername(String newName) {
		username = newName;
		consoleHead.changeUserPrompt(null);
	}
	
	public ConsoleHead getConsoleHead() {
		return consoleHead;
	}
	public Connector getConnector() {
		return connector;
	}
	public Console getConsole() {
		return console;
	}
	public String getUsername() {
		return username;
	}
}
