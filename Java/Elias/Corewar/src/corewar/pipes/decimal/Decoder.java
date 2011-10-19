package corewar.pipes.decimal;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Mode;
import corewar.common.instructions.Value;

/** This is for converting decimal code into Instruction Objects.
 * @author @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class Decoder {

	/** This are the Commands saved in an Array.
	 * The Array is used to get the Commands by an index.
	 */
	private static final Command[] COMMANDS = Command.values();		//Commands in Array speichern

	/** This are the Modes saved in an Array.
	 * The Array is used to get the Modes by an index.
	 */
	private static final Mode[] MODES = Mode.values();				//Modes in Array speichern

	/** Constant where an Command ends in dezimal-Code. */
	private static final int END_COM = 2;
	/** Constant where the first Argument begins (dezimal-Code). */
	private static final int START_FIRST_ARG = 3;
	/** Constant where the first Argument ends (dezimal-Code). */
	private static final int END_FIRST_ARG = 7;
	/** Constant where the second Argument begins (dezimal-Code). */
	private static final int START_SECOND_ARG = 8;

	/** Hidden constructor. */
	private Decoder() {}

	/** Method for converting.
	 * @param decLine The decimal code.
	 * @return An Instruction Object made of the decimal code.
	 */
	public static Instruction decode(final String decLine) {

		//Befehl ausschneiden und in zahl umwandeln
		final String com = decLine.substring(0, END_COM);
		final int comIndex = Integer.parseInt(com);

		//Variablen deklarieren
		final Argument argument1;
		final Argument argument2;
		String modStr;
		String valStr;
		int modInt;
		int valInt;

		//Mode und Value fuer Argument 1
		modStr = decLine.substring(END_COM, START_FIRST_ARG);
		modInt = Integer.parseInt(modStr);
		valStr = decLine.substring(START_FIRST_ARG, END_FIRST_ARG);
		valInt = Integer.parseInt(valStr);

		argument1 = new Argument(MODES[modInt],new Value(valInt));

		//Mode und Value fuer Argument 2
		modStr = decLine.substring(END_FIRST_ARG, START_SECOND_ARG);
		modInt = Integer.parseInt(modStr);
		valStr = decLine.substring(START_SECOND_ARG);
		valInt = Integer.parseInt(valStr);

		argument2 =  new Argument(MODES[modInt],new Value(valInt));

		return new Instruction(COMMANDS[comIndex],argument1,argument2);
	}

}
