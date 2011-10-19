package corewar.common.instructions;

import corewar.common.Constants;

/** Class that handles the value of an argument.
 *  Value is a part of an argument in an Redcode instruction,
 *  and it is part of the memory-adressing
 *  @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Value {
	/**This is the value of the argument. */
	private final int argValue;
	/** Custom constructor.
	 * @param number The value of the argument to set
	 */
	public Value(final int number) {
		if (number<0) {
			argValue = (Constants.CORE_SIZE+number%Constants.CORE_SIZE)%Constants.CORE_SIZE;
		}
		else {
			argValue = number%Constants.CORE_SIZE;
		}
	}
	/** Getter for the value.
	 * @return value Interval: [0 - coresize]
	 */
	public int getValue() {
		return argValue;
	}
}
