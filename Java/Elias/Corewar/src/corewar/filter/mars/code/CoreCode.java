package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Instruction;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;

/** Represents the code for varius commands.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public interface CoreCode {
	/** Executes a Redcode command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress);
}
