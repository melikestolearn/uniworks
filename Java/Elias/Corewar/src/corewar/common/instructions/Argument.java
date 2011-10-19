package corewar.common.instructions;
/**Redcode Argument.
 * An Redcode argument is set from an adresstyp(Mode) and an adressvalue(Value)
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Argument {
	/** Mod is an adressstyp. */
	private final Mode mod;
	/** Val is an adressvalue. */
	private final Value val;
	/**Argument enhält mode und value.
	 * @param mod from typ Mode
	 * @param val from typ Value
	 */
	public Argument(final Mode mod, final Value val) {
		this.mod = mod;
		this.val = val;
	}
	/** Getter for mode.
	 * @return The mode of the adress, as Object of type Mode
	 */
	public Mode getModeObject() {
		return mod;
	}
	/** Getter for Value.
	 * @return The value of the adress, as Object of type Value
	 */
	public Value getValueObject() {
		return val;
	}
}
