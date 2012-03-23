package console.commands;

import main.Base;
import console.ConsoleCommand;
import functions.defaultCanvas.EasyFrame;
import functions.defaultCanvas.Playground;

public class ShowPicture extends ConsoleCommand {
	public ShowPicture(Base b, String[] args) {
		super(b, args);
	}
	
	public void run() {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EasyFrame(new Playground(), false);
            }
        });
		
		base.getConsoleHead().informExeEnd();
	}
}
