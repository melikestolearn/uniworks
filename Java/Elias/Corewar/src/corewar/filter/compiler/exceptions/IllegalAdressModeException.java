package corewar.filter.compiler.exceptions;
/** IllegalAdressModeException is used if an specific mode cant be set.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class IllegalAdressModeException extends BadSyntaxException {
	/** Default constructor. */
	public IllegalAdressModeException() {
		super();
	}
	/** Custom constructor for IllegalAdressModeException.
	 * @param message The detail message
	 */
	public IllegalAdressModeException(final String message) {
		super(message);
	}
}
