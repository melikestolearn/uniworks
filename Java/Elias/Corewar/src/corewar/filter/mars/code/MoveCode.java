package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Mode;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.FindResults;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;
import corewar.pipes.eventlog.Event;
import corewar.pipes.eventlog.PrintEvent;

/** Contains the Move code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class MoveCode implements CoreCode {

	/** Executes a Move command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core, final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress) {
		final Argument sourceArg = inst.getArgument(0);
		Instruction instrToMove;
		if(sourceArg.getModeObject()==Mode.immediate) {		//Bei Mode immediate wird ein neuer DAT-Befehl generiert
			instrToMove = new Instruction(Command.DAT, new Argument(Mode.immediate, new Value(0)), new Argument(Mode.immediate, sourceArg.getValueObject()));
		}
		else {
			final Value sourceAdress;
			sourceAdress = FindResults.getDestinationAdress(core, sourceArg, currentAdress);
			instrToMove = core.get(sourceAdress);
		}
		final Value destinationAdress;		//Zieladresse
		final Argument destinationArg = inst.getArgument(1);
		destinationAdress = FindResults.getDestinationAdress(core, destinationArg, currentAdress);
		core.set(destinationAdress, instrToMove);

		final Event setMem = Event.newSetMem(destinationAdress, instrToMove);
		write.println(PrintEvent.printEvent(setMem));
	}
}
