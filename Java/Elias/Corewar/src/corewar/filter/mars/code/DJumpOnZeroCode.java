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

/** Contains the Decrement jump on zero code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class DJumpOnZeroCode implements CoreCode {

	/** Executes a DJZ command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress){
		final Argument flagArgument = inst.getArgument(1);		//Wert holen
		final Value flagAdress = FindResults.getDestinationAdress(core, flagArgument, currentAdress);	//Adresse aus Wert berechnen

		final Instruction testInst = core.get(flagAdress);
		if(testInst.getCommand()==Command.KLL)				// Test ob an der Zieladresse der Befehl "Kill" steht
			return;										// Der Versuch, ein Argument von "Kill" zu veraendern, zeigt keine Wirkung

		core.update(flagAdress, new Value(-1));		//Um 1 verringern

		final Value flagValue = core.get(flagAdress).getArgument(1).getValueObject();	//Verringerten Wert holen
		final Argument flag = new Argument(Mode.immediate, flagValue);	//Wert als argument "tarnen"
		final Instruction jumpOnZeroInstruction = new Instruction(Command.JMZ, inst.getArgument(0), flag);	//JumpOnZero Instruktion bauen
		(new JumpOnZeroCode()).execute(core, write, progr, currentTask, jumpOnZeroInstruction, currentAdress);	//Instruktion ausfuehren

		final Event setMem = Event.newSetMem(flagAdress, core.get(flagAdress)); 	//Event schreiben
		write.println(PrintEvent.printEvent(setMem));
	}
}
