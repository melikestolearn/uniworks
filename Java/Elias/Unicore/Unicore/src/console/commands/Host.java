package console.commands;

import java.io.IOException;

import main.Base;

import console.ConsoleCommand;

public class Host extends ConsoleCommand {

	public Host(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {
		try {
			if(commandArgs.length>1)
				base.getConnector().host(Integer.parseInt(commandArgs[1]));
			else
				base.getConnector().host();
		} catch (IOException e) {
			base.printAtConsole("An error has occured. Could not connect.");
			base.printAtConsole("An error has occured. We could not connect to host."); //!!!!!!!!!!!!
		}
		
		base.getConsoleHead().informExeEnd();
		
	}

	@Override
	public String controlSyntax() {
		final int p;
		if(commandArgs.length>1) {
			try {
				p = Integer.parseInt(commandArgs[1]);
				if(p>65536)
					return "Port number must be under 65536";
			}
			catch(NumberFormatException ex) {
				return "Port must be a number";
			}
		}
		return null;
	}

}
