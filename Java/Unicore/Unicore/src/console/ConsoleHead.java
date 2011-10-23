package console;

import gui.console.Console;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import main.Base;

public class ConsoleHead {

	public static final Map<String, String> CONSOLE_COMMANDS = new TreeMap<String, String>();;
	
	static {
		CONSOLE_COMMANDS.put("test", "Test");
		CONSOLE_COMMANDS.put("showp", "ShowPicture");
		CONSOLE_COMMANDS.put("helpc", "Help");
		CONSOLE_COMMANDS.put("setu", "ChangeUsername");
		CONSOLE_COMMANDS.put("echo", "Echo");
	} 
	
	private final Base base;
	
	private Console console;
	
	private char[] prompt = {' ','~','$',' '};
	
	private String userPrompt;
	
	public ConsoleHead(Base b) {
		base = b;
		
		//console = b.getConsoleGui();	// May be null at start
		userPrompt = base.getUsername() +prompt[0]+prompt[1]+prompt[2]+prompt[3];
	}

	public synchronized void exe(String[] consArgs) {
		try {
			String com = CONSOLE_COMMANDS.get(consArgs[0]);
			
			Class<?> exeClass = Class.forName(ConsoleCommand.COMMANDS_PACKAGE_NAME +"." +com);
			ConsoleCommand command = (ConsoleCommand) exeClass.getConstructor(base.getClass(), consArgs.getClass()).newInstance(base, consArgs);
			command.start();
			
			//Class.forName("Ljava.lang.String")consArgs.getClass()
			
		} catch (ClassNotFoundException e) 
		{
			new MessageDummy("Unknown Command").start();
		}
		catch (SecurityException e) { throw new RuntimeException(e);}
		catch (IllegalArgumentException e) { throw new RuntimeException(e);}
		catch (IllegalAccessException e) { throw new RuntimeException(e);}
		catch (InvocationTargetException e) { throw new RuntimeException(e);}
		catch (NoSuchMethodException e) { assert false: "Must not happen"; throw new RuntimeException(e);}
		catch (InstantiationException e) { assert false: "Must not happen";	throw new RuntimeException(e);}
	}
	
	public void informExeEnd() {
		console.printAt(userPrompt, false);
	}
	public void changeUserPrompt(String newPrompt) {
		if(newPrompt==null)
			userPrompt = base.getUsername() +userPrompt;
		else
			userPrompt = base.getUsername() +newPrompt;
	}

	public Base getBase() {
		return base;
	}
	public void regGui(Console g) {
		console = g;
	}
	public String getUserPrompt() {
		return userPrompt;
	}
	public Map<String, String> getCommands() {
		return Collections.unmodifiableMap(CONSOLE_COMMANDS);
	}
	
	private class MessageDummy extends Thread {
		String message;
		MessageDummy(String mess) {
			super();
			message = mess;
		}
		public void run() {
			console.printAt(message +"\n" + userPrompt, false);
		}
	}
}
