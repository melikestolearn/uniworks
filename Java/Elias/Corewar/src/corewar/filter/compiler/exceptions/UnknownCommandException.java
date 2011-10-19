package corewar.filter.compiler.exceptions;
/** The UnknownCommandException is used for undefined commands wich are not declared in the Redcode compiler.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class UnknownCommandException extends BadSyntaxException {
	/** Default constructor. */
	public UnknownCommandException() {
		super();
	}
	/** Custom constructor for UnknownCommandException.
	 * @param message The detail message
	 */
	public UnknownCommandException(final String message) {
		super(message);
	}
}
