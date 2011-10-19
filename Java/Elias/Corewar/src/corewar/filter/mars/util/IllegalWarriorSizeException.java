package corewar.filter.mars.util;
/** Exception for an illegal Warriors size.
 * This exception appears, when an Warriors is bigger than its reserved space in the core.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class IllegalWarriorSizeException extends Exception {
	/** Custom constructor for IllegalWarriorSizeException.
	 * @param message The detail message.
	 */
	public IllegalWarriorSizeException(final String message) {
		super(message);
	}
}
