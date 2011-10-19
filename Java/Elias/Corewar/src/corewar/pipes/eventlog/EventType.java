package corewar.pipes.eventlog;
/** Represents the Events which appear in the Mars.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public enum EventType {
	/** The Events. */
	Init ('I'), Load ('L'), Execute ('X'), SetMem ('M'), Kill ('K'), Win ('W');

	/** Symbol of the Event. */
	private final char symbol;

	/** Custom constructor.
	 * @param sym The sign that represents the type of the Event.
	 */
	EventType(final char sym){
		symbol = sym;
	}

	/** Getter for EventSymbol.
	 *  @return The Event Symbol.
	 */
	public char getSymbol(){
		return symbol;
	}
}
