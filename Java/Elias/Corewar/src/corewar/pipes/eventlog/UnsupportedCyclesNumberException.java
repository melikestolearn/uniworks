package corewar.pipes.eventlog;

/** This Exception appears when the max cycles that have been set, are not supported by the current Event format.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class UnsupportedCyclesNumberException extends Exception {
	/** Custom constructor for UnsupportedCyclesNumberException.
	 * @param message The detail message.
	 */
	public UnsupportedCyclesNumberException(final String message) {
		super(message);
	}
}
