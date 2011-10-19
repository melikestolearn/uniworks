package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Instruction;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;
import corewar.pipes.eventlog.Event;
import corewar.pipes.eventlog.PrintEvent;

/** Contains the Data code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class DataCode implements CoreCode {

	/** Executes a Data command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress){

		if(progr.queueIsEmpty()) {				//Sollte dies der letzte Task des Programmes sein, wird das Programm geloescht
			progr.setDead(write, currentTask);
		}
		else {						//Ansonsten wird nur der laufende Task geloescht
			progr.killTask();
			final Event kill = Event.newKill(progr.getProgramIndex(), currentTask.getIndex());
			write.println(PrintEvent.printEvent(kill));
		}

	}
}
