package corewar.filter.mars.code;

import java.io.PrintWriter;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.FindResults;
import corewar.filter.mars.program.Program;
import corewar.filter.mars.program.Task;

/** Contains the Jump code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class JumpCode implements CoreCode {

	/** Executes a Jump command.
	 * @param core The Marscore.
	 * @param write The output Writer.
	 * @param progr The Program whose Task runs at the moment.
	 * @param currentTask The Task executing the Instruction.
	 * @param inst The Instruction to execute.
	 * @param currentAdress The adress of the Instruction.
	 */
	public void execute(final Core core,final PrintWriter write, final Program progr, final Task currentTask, final Instruction inst, final int currentAdress){
		final Argument arg = inst.getArgument(1);		//Wert von jump holen
		final Value adressValue = FindResults.getDestinationAdress(core, arg, currentAdress);		//Sprungadresse holen
		final int jumpAdress = adressValue.getValue();

		//Sprungadresse setzen auf die zu springende Adresse minus eins, weil die Adresse in der Schleife hochgezaehlt wird
		currentTask.setAdress(jumpAdress-1);
	}
}
