package corewar.common.instructions;

/** The class instruction represents a Redcode Command.
 * 	It is set from one command and one/two arguments
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Instruction {
	/** This is the first argument, the source adress. */
	private final Argument sourceAdress;
	/** This is the second argument, the destination adress. */
	private final Argument destinationAdress;
	/** This is the Redcode command. */
	private final Command redCom;
	/** Custom constructor.
	 * @param command The command
	 * @param argument1 First argument
	 * @param argument2 Second argument
	 */
	public Instruction(final Command command,final Argument argument1,final Argument argument2) {
		redCom = command;
		sourceAdress = argument1;
		destinationAdress = argument2;
	}
	/** Getter for the Argument.
	 * To call with one parameter, which should be {0,1}
	 * This is for choosing the first argument or the second
	 * @param argumentIndex Set 0 to get the first argument, set 1 to get the second
	 * @return The source adress or the destination adress
	 * @throws IllegalArgumentException if index < 0 or index > 1
	 */
	public Argument getArgument(final int argumentIndex) {
		if(argumentIndex==0)
			return sourceAdress;
		else if(argumentIndex==1)
			return destinationAdress;
		else
			throw new IllegalArgumentException();
	}
	/** Getter for the command.
	 * @return The type of command
	 */
	public Command getCommand() {
		return redCom;
	}
}
