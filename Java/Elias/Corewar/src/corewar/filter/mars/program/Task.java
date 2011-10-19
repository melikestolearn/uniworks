package corewar.filter.mars.program;

/** This is the Task (Threat).
 * A Program contains a number of Tasks and it swiches betweed them.
 * On every cycle the next Task runs. New Tasks can be made (Split) and others can be killed
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Task {
	/** Index to identify an Task. The indexes are not beeing recycled. */
	private final int index;
	/** Actual adress in the core. */
	private int adress;

	/** Custom Task constructor.
	 * @param taskIndex The task index. Is different for every Task.
	 * @param startAdress The start adress for this Task.
	 */
	public Task(final int taskIndex, final int startAdress) {
		index = taskIndex;
		adress = startAdress;
	}

	public int getIndex() {
		return index;
	}
	public int getAdress() {
		return adress;
	}
	/** Counts the adress.
	 * Adds one to the adress to get to the next Instruction.
	 */
	public void countAdress() {
		adress++;
	}
	/** Sets the adress of the Task.
	 * @param newAdress The adress to set
	 */
	public void setAdress(final int newAdress) {
		adress = newAdress;
	}
	/** hashCode method.
	 * @return The hashcode of this Task.
	 */
	public int hashCode() {
		return adress;
	}
	/** equals-Method.
	 * @param that Any kind of Object.
	 * @return True if two Tasks got the same adress, or else false.
	 */
	public boolean equals(final Object that) {
		final boolean result;
		if(that==null)
			return false;
		if(getClass() != that.getClass())
			return false;
		final Task tmpTask = (Task)that;
		if(adress==tmpTask.adress)
			result = true;
		else
			result = false;
		return result;
	}
}
