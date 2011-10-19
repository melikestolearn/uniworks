package corewar.filter.compiler.exceptions;
/** This exception informs about a false coresize given.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class BadCoresizeException extends Exception {
	/** Default constructor. */
	public BadCoresizeException() {
		super();
	}
	/** Custom constructor for BadCoresizeException.
	 * @param message The detail message
	 */
	public BadCoresizeException(final String message) {
		super(message);
	}
}
