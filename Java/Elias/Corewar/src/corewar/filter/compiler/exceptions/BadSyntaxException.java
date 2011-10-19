package corewar.filter.compiler.exceptions;
/** BadSyntaxException is the base (superclass), for the syntax exceptions.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class BadSyntaxException extends Exception {
	/** Default constructor. */
	public BadSyntaxException() {
		super();
	}
	/** Custom constructor for BadSyntaxException.
	 * @param message The detail message
	 */
	public BadSyntaxException(final String message) {
		super(message);
	}
}
