package corewar.pipes.eventlog;

import java.util.HashMap;
import java.util.Map;

import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.compiler.exceptions.BadCoresizeException;
import corewar.filter.mars.util.MarsConstants;
import corewar.pipes.decimal.Dezimal;
/** An Event that happens through the battle.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class Event {

	/** Empty decimal Code. */
	private static final String EMPTY_DECIMAL = "000000000000";
	/** The highest amount of cycles supported. Can be changed analog to the Events text format. */
	private static final int MAX_CYCLES_SUPPORTED = 1000000;

	/** The Eventtype. */
	private final EventType iType;

	/** Map of Event arguments.
	 * The arguments are: The Program index [ProgramIndex], the Task index [ThreatIndex], the cycles number [CycleNumber]
	 * and the absolute adress [AbsoluteAdress].
	 */
	private final Map<String, Integer> eventArgs = new HashMap<String, Integer>();

	/** The decimal code. */
	private final String decimalCode;

	/** Private custom constructor. The Events are made by factory methods.
	 * @param evType An EventType Object.
	 * @param progrIndex The Program index.
	 * @param threatInd The Task number.
	 * @param cycle The cycles number.
	 * @param absAdress The adress.
	 * @param decCode The Decimal code.
	 */
	private Event(final EventType evType, final int progrIndex, final int threatInd, final int cycle, final Value absAdress, final String decCode) {
		iType = evType;
		decimalCode = decCode;
		eventArgs.put("ProgramIndex", progrIndex);
		eventArgs.put("ThreatIndex", threatInd);
		eventArgs.put("CycleNumber", cycle);
		eventArgs.put("AbsoluteAdress", absAdress.getValue());

	}
	/** Creates an initializing-event.
	 * @param progrIndex The greatest Program index.
	 * @param threatInd The allowed Task number that can be made.
	 * @param cycle The Maxcycles number.
	 * @param absAdress The greatest adress in the Core (CORESIZE -1).
	 * @return The Init Event.
	 * @throws UnsupportedCyclesNumberException If the supported cycles are exceeded.
	 */
	public static Event newInit(final int progrIndex, final int threatInd, final int cycle, final Value absAdress) throws UnsupportedCyclesNumberException {
		checkMaxcycles();
		return new Event(EventType.Init, progrIndex, threatInd, cycle, absAdress, EMPTY_DECIMAL);
	}
	/** Creates an load-event.
	 * @param progrIndex The index of the Program loading.
	 * @param absAdress The adress where the current Instruction is been loaded.
	 * @param decCode The Decimal code.
	 * @return The Load Event.
	 */
	public static Event newLoad(final int progrIndex, final Value absAdress, final String decCode) {
		return new Event(EventType.Load, progrIndex, 0, 0, absAdress, decCode);
	}
	/** Creates an executing-event.
	 * @param progrIndex The index of the Program executing.
	 * @param threatInd The Task number of the Program executing.
	 * @param cycle The current cycles number.
	 * @param absAdress The adress of the Instruction.
	 * @param instr The Instruction beeing executed.
	 * @return The Execute Event.
	 */
	public static Event newExecute(final int progrIndex, final int threatInd, final int cycle, final Value absAdress, final Instruction instr) {
		String decCode;
		try {
			decCode = Dezimal.encode(instr);
		} catch (BadCoresizeException e) {
			throw new AssertionError("Cannot happen");
		}
		return new Event(EventType.Execute, progrIndex, threatInd, cycle, absAdress, decCode);
	}
	/** Creates an setMemory-event.
	 * @param absAdress The adress beeing setted.
	 * @param instr The Instruction which is setted.
	 * @return The setMemory Event.
	 */
	public static Event newSetMem(final Value absAdress, final Instruction instr) {
		String decCode;
		try {
			decCode = Dezimal.encode(instr);
		} catch (BadCoresizeException e) {
			throw new AssertionError("Cannot happen");
		}
		return new Event(EventType.SetMem, 0, 0, 0, absAdress, decCode);
	}
	/** Creates an kill-event.
	 * @param progrIndex The Program index.
	 * @param threatInd The number of the Task beeing killed.
	 * @return The Kill Event.
	 */
	public static Event newKill(final int progrIndex, final int threatInd) {
		return new Event(EventType.Kill, progrIndex, threatInd, 0, new Value(0), EMPTY_DECIMAL);
	}
	/** Creates an win-event.
	 * @param progrIndex The index of the Program won, or the number of Programs if its a draw.
	 * @return The Win Event.
	 */
	public static Event newWin(final int progrIndex) {
		return new Event(EventType.Win, progrIndex, 0, 0, new Value(0), EMPTY_DECIMAL);
	}
	/** Getter for Eventtyp.
	 * @return The Event type.
	 */
	public EventType getEventType() {
		return iType;
	}
	/** Getter for the arguments Map.
	 * @return Map containing some Event arguments.
	 */
	public Map<String, Integer> getEventArgs() {
		return eventArgs;
	}
	/** Getter for decimal code.
	 * @return String of decimal code.
	 */
	public String getDecimal(){
		return decimalCode;
	}
	/** Controlles the max cycles that have been set.
	 * @throws UnsupportedCyclesNumberException If the maximal cycles have been set, are bigger than the cycles supported.
	 */
	private static void checkMaxcycles() throws UnsupportedCyclesNumberException {
		if(MarsConstants.MAX_CYCLES>MAX_CYCLES_SUPPORTED)
			throw new UnsupportedCyclesNumberException("Max Cycles not supported");
	}
	/** hashCode method.
	 * @return hashCode
	 */
	public int hashCode() {
		return eventArgs.hashCode();
	}
	/** equals method.
	 * @param that Any kind of Object.
	 * @return True if this and that are equal.
	 */
	public boolean equals(final Object that) {
		boolean result = false;
		if(that==null)
			return false;
		if(getClass()==that.getClass())
			return false;
		final Event tmpEvent = (Event) that;
		if(eventArgs.equals(tmpEvent.eventArgs))
			result = iType==tmpEvent.iType;
		if(!decimalCode.equals(tmpEvent.decimalCode))
			result = false;
		return result;
	}
}
