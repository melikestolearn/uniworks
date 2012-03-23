package console;

import main.Base;

/** A console command.
 *  Its a command, which is simple and doesn't require a lot of time.
 */
public abstract class ConsoleCommand extends Thread {
	
	public static String COMMANDS_PACKAGE_NAME = "console.commands";
	
	protected String[] commandArgs;
	
	protected final Base base;
	
	protected ConsoleCommand(Base b, String[] args) {
		super();
		base = b;
		commandArgs = args;
	}
	
	public abstract void run();
	
	/** Controls the syntax of a command use.
	 * Schould return a message to help the user if an incorrect syntax was used.
	 * @return Null if syntax is correct, or a message if the syntax is incorrect.
	 */
	public abstract String controlSyntax();
	
}
