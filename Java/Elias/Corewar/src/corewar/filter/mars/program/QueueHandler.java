package corewar.filter.mars.program;

import java.util.ArrayDeque;
import java.util.Queue;

import corewar.common.instructions.Value;

/** A tool to handle the Task queue.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class QueueHandler {
	/** Queue which contains Tasks. */
	private final Queue<Task> taskQueue;
	/** Counter of Tasks produced. */
	private int taskCounter = 0;
	/** Variable for saving Tasks temporarily. */
	private Task savedTask = null;
	/** Is used to check if a Task must be killed. */
	private boolean taskKillFlag = false;
	/** Custom constructor for the queue.
	 * @param startAdress The start adress of the Program (first Task).
	 */
	public QueueHandler(final Value startAdress) {
		taskQueue = new ArrayDeque<Task>();

		taskQueue.add(new Task(taskCounter, startAdress.getValue()));
		taskCounter++;

	}
	/** Retrieves, but does not remove the head of this queue.
	 * @return The head Task, or null if the Queue is empty.
	 */
	public Task getCurrentTask() {
		return taskQueue.peek();
	}
	/** Appends an existing Task to the Queue, if the kill flag is false (default).
	 * Also if the value savedTask is not null, it appends it too.
	 * @param taskToAdd The Task to add.
	 */
	public void add(final Task taskToAdd) {
		if(!taskKillFlag) {
			taskQueue.add(taskToAdd);
		}
		taskKillFlag = false;
		if(savedTask!=null) {
			taskQueue.add(savedTask);
			savedTask = null;
		}
	}
	/** Saves a Task in the savedTask variable.
	 * Needs the start adress.
	 * @param adr The start adress of the new Task (Core adress).
	 */
	public void saveTask(final Value adr) {
		final Task task = new Task(taskCounter, adr.getValue());
		taskCounter++;
		savedTask = task;
	}
	/** Retrieves and removes the head Task.
	 * @return The head Task, or null if the queue is empty.
	 */
	public Task remove(){
		return taskQueue.poll();
	}
	/** Returns true if this queue is empty.
	 * @return True if the queue is empty, false if not.
	 */
	public boolean isEmpty() {
		return taskQueue.isEmpty();
	}
	/** Sets the kill flag.
	 * This way the Task will be killed later.
	 */
	public void kill(){
		taskKillFlag = true;
	}
	/** Returns the kill flag.
	 * @return True if the kill flag is set, false if not.
	 */
	public boolean isSetToKill() {
		return taskKillFlag;
	}
	/** Getter for the number of Tasks made until now.
	 * @return The last Task Index +1.
	 */
	public int getTaskCounter() {
		return taskCounter;
	}
	/** hashCode method.
	 * @return hashCode
	 */
	public int hashCode() {
		return taskQueue.hashCode() +taskCounter;
	}
	/** equals method.
	 * @param that Any kind of Object.
	 * @return True if this and that are equal.
	 */
	public boolean equals(final Object that) {
		boolean result = false;
		if(that==null)
			return false;
		if(getClass()!=that.getClass())
			return false;
		final QueueHandler tmpHandler = (QueueHandler) that;
		if(taskQueue.equals(tmpHandler.taskQueue))
			result = taskCounter==tmpHandler.taskCounter;
		return result;
	}
}
