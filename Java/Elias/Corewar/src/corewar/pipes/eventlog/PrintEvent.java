package corewar.pipes.eventlog;
/** Class for printing an Event.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class PrintEvent {
	/** Hidden constructor. */
	private PrintEvent() {}

	/** Method to convert an Event to String.
	 * @param event The Event to print.
	 * @return result The String output of the Event.
	 */
	public static String printEvent(final Event event) {
		final String tmpSymbol = String.valueOf(event.getEventType().getSymbol());
		final String result =
		tmpSymbol +event.getEventArgs().get("ProgramIndex")
		+String.format("%04d", event.getEventArgs().get("ThreatIndex"))
		+String.format("%06d", event.getEventArgs().get("CycleNumber"))
		+String.format("%04d", event.getEventArgs().get("AbsoluteAdress"))
		+event.getDecimal();
		return result;
	}
}
