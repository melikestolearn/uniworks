package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.FindResults;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;

/** Contains the Compare code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class CompareCode implements CoreCode {

	/** Executes a Compare command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress){
		final Argument firstArg = inst.getArgument(0);
		final Argument secondArg = inst.getArgument(1);
		final Value firstAdress = FindResults.getDestinationAdress(core, firstArg, currentAdress);
		final Value secondAdress = FindResults.getDestinationAdress(core, secondArg, currentAdress);

		final int firstValue = FindResults.correctImmediate(core, firstArg, firstAdress);
		final int secondValue = FindResults.correctImmediate(core, secondArg, secondAdress);

		if(firstValue!=secondValue) {
			currentTask.setAdress(currentAdress+1);
		}
	}
}
