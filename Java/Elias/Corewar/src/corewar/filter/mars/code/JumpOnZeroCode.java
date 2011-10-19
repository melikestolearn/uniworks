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

/** Contains the Jump on zero code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class JumpOnZeroCode implements CoreCode {

	/** Executes a JMZ command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress){
		final Argument flagArgument = inst.getArgument(1);		//Wert holen
		final Value flagValueAdress = FindResults.getDestinationAdress(core, flagArgument, currentAdress);	//Adresse aus wert berechnen
		final int flagValue = FindResults.correctImmediate(core, flagArgument, flagValueAdress);		//Value aus Adresse holen
		if(flagValue==0) {		//Test ob value gleich 0
			final Instruction jumpInstruction = new Instruction(Command.JMP, null, inst.getArgument(0));	//Jump Instruktion bauen
			(new JumpCode()).execute(core, write, progr, currentTask, jumpInstruction, currentAdress);		//Als Jump Befehl ausfuehren
		}
	}
}
