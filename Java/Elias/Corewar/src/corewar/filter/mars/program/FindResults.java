package corewar.filter.mars.program;

import corewar.common.instructions.Argument;
import corewar.common.instructions.Mode;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
/** This class contains methods which are necessary to find the real adresse at all Modes.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class FindResults {

	/** Constructor is hidden. */
	private FindResults(){}

	/** This method returns the final adress.
	 * It is importand to understand that the returned result is not the final value,
	 * but the core adress where the value is written.
	 * For Mode "immediate", it returns the current adress which is given as a parameter.
	 * @param core The Mars core.
	 * @param arg The Argument from which the adress is been calculated.
	 * @param curAdr The current adress in the Core.
	 * @return A Value object, which is the adress (like above explained).
	 */
	public static Value getDestinationAdress(final Core core, final Argument arg, final int curAdr) {
		final Mode mod = arg.getModeObject();						//Mode
		final int startValue = arg.getValueObject().getValue();		//Und Value
		final int result;
		if(mod==Mode.immediate)
			result = startValue;
		else if(mod==Mode.direct)
			result = startValue+curAdr;			//Neue adresse = Instruktions adresse + wert
		else {
			final Argument secondArg = core.get(new Value(curAdr+startValue)).getArgument(1);	//Instruktion von Neue Adresse holen
			final int secondValue = secondArg.getValueObject().getValue();		//Den wert noch auf diese adresse addieren
			result = startValue+curAdr+secondValue;									//Und zuruckgeben
		}
		return new Value(result);
	}
	/** This method corrects the wanted value.
	 * If the Mode of an Argument could be "immediate", this method must be used instead of getting the value manually!
	 * Means you dont use the getter for the Core.
	 * @param core The Mars core.
	 * @param arg The argument where the valueAdress was written.
	 * @param valueAdress The adress from where to get the value.
	 * @return The value which is important for a command.
	 * Thats the first if the mode is "immediate" and else the second.
	 */
	public static int correctImmediate(final Core core, final Argument arg, final Value valueAdress){
		if(arg.getModeObject()==Mode.immediate)
			return arg.getValueObject().getValue();
		else
			return core.get(valueAdress).getArgument(1).getValueObject().getValue();
	}
}
