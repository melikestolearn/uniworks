package console.commands;

import main.Base;
import console.ConsoleCommand;

public class EstConnection extends ConsoleCommand {

	public EstConnection(Base b, String[] args) {
		super(b, args);
	}

	public void run() {
		
		try {

			boolean gotInput = false;
			while(!gotInput) {
				String com = commandArgs[0].toLowerCase();

				if(com.equals("connect")) {
					if(commandArgs.length<2)
						throw new Exception("Illegal command.");

					base.getConnector().connect(commandArgs[1]);
					gotInput = true;
				}
				else if(com.equals("host")) {
					base.getConnector().host();
					gotInput = true;
				}
			}
			base.getConsoleHead().informExeEnd();
		}
		catch(Exception ex) {
			throw new RuntimeException();
		}
	}


}
