package corewar.pipes.decimal;

import corewar.common.Constants;
import corewar.common.instructions.Argument;
import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Mode;
import corewar.common.instructions.Value;
import corewar.filter.compiler.exceptions.BadCoresizeException;

/**Dezimal is used for coding Redcode into Dezimal-Code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class Dezimal {
	/** Private custom Constructor. */
	private Dezimal(){}
	/** Method for compiling an Redcode instruction into dezimal Code.
	 * @param instr Resembles an Redcode instruction
	 * @return A String with dezimal code
	 * @throws BadCoresizeException If the coresize is not in the supported interval [1-10000]
	 */
	public static String encode(final Instruction instr) throws BadCoresizeException{
		if(Constants.CORE_SIZE<=0 || Constants.CORE_SIZE>Constants.MAX_CORE_SIZE)		// Die coresize ueberpruefen
			throw new BadCoresizeException("False Coresize");
		final Command com = instr.getCommand();
		final Argument arg1 = instr.getArgument(0);
		final Argument arg2 = instr.getArgument(1);

		final String line;		// Das Ergebnis

		line = String.format("%02d", com.ordinal())
		+ argEncode(arg1) + argEncode(arg2);

		return line;
	}
	/** Encodes an Argument into a String.
	 * @param arg The Argument to encode.
	 * @return The String representation of the Argument.
	 */
	public static String argEncode(final Argument arg) {
		if(arg==null)
			return "00000";
		else {
			final Value val = arg.getValueObject();
			final Mode mod = arg.getModeObject();
			return mod.ordinal() + String.format("%04d", val.getValue());
		}
	}

}
