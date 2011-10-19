package corewar.filter.mars;

import corewar.common.Constants;
import corewar.common.instructions.Argument;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.pipes.decimal.Decoder;
/** This is the Mars Core.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Core {
	/** This Array is used as the core. It contains Instruction Objects. */
	private Instruction[] memArray;
	/** Custom Constructor.
	 * At the start the Core is filled with "DAT #0".
	 */
	Core() {
		memArray = new Instruction[Constants.CORE_SIZE];
		final Instruction dat0 = Decoder.decode("000000000000");
		int index;
		for(index = 0; index<memArray.length; index++) {
			memArray[index] = dat0;
		}
	}
	/** Setter for a Core adress.
	 * Replaces the old Instruction with the given.
	 * @param addr The adress to set.
	 * @param instToSet The Instruction to set.
	 */
	public void set(final Value addr, final Instruction instToSet){
		memArray[addr.getValue()] = instToSet;
	}
	/** Getter for the Instruction which is writen at a specific adress.
	 * @param adr The adress.
	 * @return The Instruction at the adress.
	 */
	public Instruction get(final Value adr) {
		return memArray[adr.getValue()];
	}
	/** Update an Instruction.
	 * The part of the Instruction that changes is only the destination adress.
	 * @param addr The adress to update.
	 * @param inc The relative difference from the old value.
	 */
	public void update(final Value addr, final Value inc) {
		final Instruction gotInst = memArray[addr.getValue()];					// Alte Instruktion
		final int oldVal = gotInst.getArgument(1).getValueObject().getValue();	// Alter Wert
		final Argument argDest = new Argument(gotInst.getArgument(1).getModeObject(), new Value(oldVal + inc.getValue())); //Neues Argument
		memArray[addr.getValue()] = new Instruction(gotInst.getCommand(), gotInst.getArgument(0), argDest);
	}
}
