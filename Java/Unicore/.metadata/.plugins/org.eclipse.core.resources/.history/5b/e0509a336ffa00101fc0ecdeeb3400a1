package main;

import connection.Connector;
import console.Console;

/** This is the base of the program.
 * Every part build up here.
 */
public class Base {
	private Connector connector;

	private Console console;

	public Base() {
		connector = new Connector(this, System.in);
		console = new Console(this);
	}
	
	public Console getConsole() {
		return console;
	}
	public Connector getConnector() {
		return connector;
	}
}
