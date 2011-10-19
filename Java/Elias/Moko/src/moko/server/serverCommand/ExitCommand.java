package moko.server.serverCommand;

import javax.swing.JOptionPane;
import moko.server.Server;

public class ExitCommand extends ServerCommand {

        public ExitCommand(String com, Server svr) {
            super(com, svr);
        }
        
	public void run(String com, Server srv) {
            int answer = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION);
            if(answer==JOptionPane.OK_OPTION) {
                    srv.disconnect();
                    System.exit(0);
            }
        }

}
