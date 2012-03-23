package main;

import gui.console.Console;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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

	private File log;
	
	public Base() {
		try {
			connector = new Connector(this);
			
			final String[] d = new Date(System.currentTimeMillis()).toString().split("\\s+");
			log = new File("Log"+d[2]+d[1]+(d[3].replace(":", ".")).substring(0,5));
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
	
	/** Sets the user name.
	 * @param newName The new user name.
	 */
	public void setUsername(String newName) {
		username = newName;
		consoleHead.changeUserPrompt(null);
	}
	public void log(String logMessage) {
		
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
	public File getLog() {
		return log;
	}
}
