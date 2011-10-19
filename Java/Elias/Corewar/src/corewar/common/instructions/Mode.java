package corewar.common.instructions;

/** The enum that represents the mode of an argument.
 * Modes are ways of memory-adressing.
 * The modes are: direct, immediate, indirect
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public enum Mode {
	/** List of Modes.
	 * All three modes are listed here.
	 */
	immediate ('#'), direct (' '), indirect ('@');
	/** Custom constructor.
	 * @param c The sign to identify the mode
	 */
	private final char modechar;
	/** Custom Constructor.
	 * @param sign Of an spezific mode
	 */
	Mode(final char sign) {
		modechar = sign;
	}
	/** Getter for Mode.
	 * @return character: ' ', '#', '@'
	 */
	public char getMode() {
		return modechar;
	}
	/** Method for selecting a Mode by its char.
	 * @param sign A char that is a mode sign
	 * @return Mode, an enum part
	 */
	public static Mode getMode(final char sign){
		if(sign=='#') {
			return immediate;
		}
		else if(sign=='@') {
			return indirect;
		}
		else {
			return direct;
		}
	}
}
