package console.commands;

import main.Base;
import console.ConsoleCommand;
import functions.defaultCanvas.EasyFrame;
import functions.defaultCanvas.Playground;

public class ShowPicture extends ConsoleCommand {
	public ShowPicture(Base b) {
		super(b);
	}
	
	public void run() {
		super.run();
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EasyFrame(new Playground(), false);
            }
        });
		
		base.getConsole().sayEnd();
	}
}
