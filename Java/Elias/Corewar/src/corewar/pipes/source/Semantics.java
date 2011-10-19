package corewar.pipes.source;

import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Mode;
import corewar.filter.compiler.exceptions.IllegalAdressModeException;

/** This class is checking semantics on a command.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class Semantics {
	/** Private custom Constructor.
	 * No constructor for the class Semantics,
	 * as it contains only static methods and there should be no objects
	 */
	private Semantics() {}
	/** Method that checks if the adressing mode is allowed.
	 * Its separated by four parts. First part is for checking if the command is "DAT",
	 * if so it checks for direct and indirect and throws an exception
	 * Second part checks if the command is "KLL". In this case, no testing is needed.
	 * Third part checks the source argument for illegal modes
	 * Fourth part checks the destination argument for illegal modes
	 * @param instr An instruction
	 * @throws IllegalAdressModeException Wherever there is an illegal adressing mode
	 */
	public static void checkSem(final Instruction instr) throws IllegalAdressModeException {
		final Command instrCommand = instr.getCommand();
		if(instrCommand==Command.KLL)
			return;
		if(instrCommand==Command.DAT) {			//Nur DAT ueberpruefen
			final Mode mode = instr.getArgument(1).getModeObject();
			if(mode==Mode.direct || mode==Mode.indirect)
					throw new IllegalAdressModeException();
			return;
		}
		//Alle anderen befehle auf immediate ueberpruefen
		if(!(instr.getArgument(0)==null) && (instr.getArgument(0).getModeObject()==Mode.immediate))
				checkSourceForImm(instrCommand);
		if(instr.getArgument(1).getModeObject()==Mode.immediate)
			checkDestinationForImm(instrCommand);
	}
	/** This is the method that checks the source mode.
	 * @param com The command of the Instruction object
	 * @throws IllegalAdressModeException Wherever there is an illegal adressing mode
	 */
	private static void checkSourceForImm(final Command com) throws IllegalAdressModeException {
		if(com==Command.JMZ || com==Command.DJZ)
			throw new IllegalAdressModeException();
	}
	/** This is the method that checks the destination mode.
	 * @param com The command of the Instruction object
	 * @throws IllegalAdressModeException Wherever there is an illegal adressing mode
	 */
	private static void checkDestinationForImm(final Command com) throws IllegalAdressModeException {
		if(!(com==Command.JMZ || com==Command.CMP))
			throw new IllegalAdressModeException();
	}

}
