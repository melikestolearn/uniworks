package corewar.common.instructions;

/** Represents the commands of Redcode.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public enum Command {
	/** List of Commands.
	 * Warning: NOP and REV are not supported yet.
	 */
	DAT (1), MOV (2), ADD (2), JMP (1), JMZ (2), DJZ (2), CMP (2), SPL (1), NOP (0), REV (0), KLL (0);
	/** this is the number of arguments. */
	private final int arguments;
	/** Constructor for enum.
	 * @param arg is the number of arguments
	 */
	Command(final int arg) {
		arguments = arg;
	}
	/** Getter for the number of arguments.
	 * @return  1 or 2
	 */
	public int getArguments() {
		return arguments;
	}

}
