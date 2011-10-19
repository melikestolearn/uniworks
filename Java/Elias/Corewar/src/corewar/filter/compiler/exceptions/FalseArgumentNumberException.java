package corewar.filter.compiler.exceptions;
/** FalseArgumentNumberException is thrown if the number of Arguments doesn't fit to an Redcode Command.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class FalseArgumentNumberException extends BadSyntaxException {
	/** Default constructor. */
	public FalseArgumentNumberException() {
		super();
	}
	/** Custom constructor for FalseArgumentNumberException.
	 * @param message The detail message
	 */
	public FalseArgumentNumberException(final String message) {
		super(message);
	}
}
