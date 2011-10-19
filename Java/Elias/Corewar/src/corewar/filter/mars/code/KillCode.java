package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.CodeMap;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;

/** Contains the Kill code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class KillCode implements CoreCode {

	/** Executes an Kill command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core, final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress) {
		progr.killAll();
		CodeMap.getMap().get(Command.DAT).execute(core, write, progr, currentTask, inst, currentAdress);
	}
}
