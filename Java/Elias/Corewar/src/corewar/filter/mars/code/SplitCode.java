package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.Constants;
import corewar.common.instructions.Argument;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.FindResults;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;

/** Contains the Split code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class SplitCode implements CoreCode{

	/** Executes a Split command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress){
		if(progr.getTaskIndexCounter()>Constants.CORE_SIZE-1) {		//Testen ob die zugelassene anzahl an Tasks ueberschritten wurde
			progr.setDead(write, currentTask);
			return;
		}
		final Argument arg = inst.getArgument(1);
		final Value adress = FindResults.getDestinationAdress(core, arg, currentAdress);

		progr.newTask(adress);

	}
}
