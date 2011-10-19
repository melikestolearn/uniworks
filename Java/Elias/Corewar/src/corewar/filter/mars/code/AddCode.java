package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.FindResults;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;
import corewar.pipes.eventlog.Event;
import corewar.pipes.eventlog.PrintEvent;

/** Contains the Addition code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class AddCode implements CoreCode {

	/** Executes an Add command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress) {
		final Argument sourceArg = inst.getArgument(0);
		final Argument destinationArg = inst.getArgument(1);
		final Value valueAdress = FindResults.getDestinationAdress(core, sourceArg, currentAdress);		//Adresse holen
		final int value = FindResults.correctImmediate(core, sourceArg, valueAdress);				//Korrigiertes Argument holen

		final Value destinationAdress = FindResults.getDestinationAdress(core, destinationArg, currentAdress);		//Adresse wo hingeschrieben werden soll


		final Instruction destInstr = core.get(destinationAdress);
		if(destInstr.getCommand()==Command.KLL)					// Test ob an der Zieladresse der Befehl "Kill" steht
			return;											// Ein addieren auf "Kill", das keine Argumente hat, zeigt keine Wirkung

		core.update(destinationAdress,new Value(value));

		final Event setMem = Event.newSetMem(destinationAdress, core.get(destinationAdress));
		write.println(PrintEvent.printEvent(setMem));
	}
}
