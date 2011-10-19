package corewar.pipes.source;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Mode;
import corewar.common.instructions.Value;
import corewar.filter.compiler.exceptions.BadSyntaxException;
import corewar.filter.compiler.exceptions.FalseArgumentNumberException;
import corewar.filter.compiler.exceptions.UnknownCommandException;

/** This class is for checking the syntax of a command.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class Syntax {
	/** The constructor for Syntax is private.
	 *  It is not allowed to be used as the class contains only static methods
	 */
	private Syntax() {}
	/** Method that takes a text line and converts it into an instruction object.
	 * @param line A line with a Redcode command
	 * @return A new instruction, or null if the line is empty
	 * @throws BadSyntaxException If the method builtInstruction() failes
	 * @throws FalseArgumentNumberException If the method builtInstruction() failes
	 */
	public static Instruction parseInstruction(final String line) throws BadSyntaxException {
		final String textLine = removeComment(line);
		if(isEmpty(textLine)){
			return null;
		}
		final String[] args = splitTokens(textLine.trim());
		return builtInstruction(args);
	}

	/** Checks if line is empty.
	 * @param line The text line
	 * @return true if line is empty, else false
	 */
	private static boolean isEmpty(final String line) {
		for(int count = 0; count<line.length(); count++) {
			if(line.charAt(count)!=' ')
				return false;
		}
		return true;
	}

	/** Removes the comment, if exists.
	 * @param line The text line
	 * @return the line without comment
	 */
	private static String removeComment(final String line) {
		final int index = line.indexOf(';');
		if (index==-1) {
			return line;
		}
		else
			return line.substring(0, index);
	}

	/** Method to split the Tokens of a Redcode command.
	 * @param line The text line
	 * @return An array of strings that contains the command and the argument(s)
	 */
	private static String[] splitTokens(final String line) {
		return line.split("\\s+");
	}
	/** Method that builts an instruction.
	 *  This method takes an array which contains the command and argument(s)
	 *  It checks if the argument number is right, depending on the command
	 *  And it returns a new instruction
	 * @param param The array whith the tokens of the Redcode command
	 * @return A new instruction
	 * @throws BadSyntaxException If the given command does not match with the existing
	 * @throws FalseArgumentNumberException If there are is a false number of arguments
	 */
	private static Instruction builtInstruction(final String[] param) throws BadSyntaxException {
		try {
			param[0] = param[0].toUpperCase();		//Umwandeln in Grossbuchstaben
			final Command com = Command.valueOf(param[0]);	//Als Enum deklarieren
			if(com==Command.NOP || com==Command.REV)		// Test, ob es sich um einen nicht unterstuetzten befehl handelt
				throw new UnknownCommandException();
			final Argument arg1;
			final Argument arg2;
			if(com.getArguments()!=param.length-1) {			//Wenn falsche anzahl argumente wirft Exception
				throw new FalseArgumentNumberException();
			}
			if (com.getArguments()==1) {	// Vergleicht gespeicherte nummer an argumenten
				arg1 = null;
				arg2 = setArg(param[1]);
			}
			else if(com.getArguments()==0) {
				arg1 = null;
				arg2 = null;
			}
			else {
				arg1 = setArg(param[1]);
				arg2 = setArg(param[2]);
			}
			return new Instruction(com, arg1, arg2);
		}
		catch(IllegalArgumentException ex) {
			throw new UnknownCommandException();
		}
	}
	/** Method to split mode and value and make new argument.
	 * @param arg The argument from the array
	 * @return A new argument
	 * @throws IllegalArgumentException If there is a char that is not a number (Except adress mode)
	 */
	private static Argument setArg(final String arg) {
		final char first = arg.charAt(0);	//Erstes zeichen von arg. holen
		final Value val;
		final Mode mode;
		try {
			mode = Mode.getMode(first);
			String tmpToParse = arg;
			if(first=='#' || first=='@') {
				tmpToParse = tmpToParse.substring(1, arg.length());		//Adressen-vorzeichen entfernen
			}
			val = new Value(Integer.parseInt(tmpToParse));			//Von String in zahl umwandeln, test ob keine Zahl, kann Exception werfen
			return new Argument(mode, val);
		}
		catch(NumberFormatException ex) {
			throw new IllegalArgumentException();
		}
	}
}
