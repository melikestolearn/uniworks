package corewar.filter.mars.program;

import java.io.PrintWriter;

import corewar.common.instructions.Command;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.util.Scheduler;
import corewar.pipes.eventlog.Event;
import corewar.pipes.eventlog.PrintEvent;

/** A program running in the Mars.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Program {
	/** The Scheduler of this Program. */
	private final Scheduler myScheduler;
	/** The index of the program. */
	private final int programIndex;
	/** Represents the life of a program in the core. */
	private boolean alive;
	/** Handles the tasks a program produces. */
	private final QueueHandler queueHandler;

	/** Custom constructor for a program.
	 * @param sched The Scheduler of this Program.
	 * @param startAdr The start adress of the program.
	 * @param index The index number (starts from zero).
	 */
	public Program(final Scheduler sched, final Value startAdr, final int index) {
		myScheduler = sched;
		programIndex = index;
		alive = true;
		queueHandler = new QueueHandler(startAdr);
	}

	/** Runs the next Instruction.
	 * @param killer A boolean that indicates if the next Task must be killed.
	 * @param core The Mars core.
	 * @param cycle The current cycle.
	 * @param write The output Writer.
	 */
	public void step(final boolean killer, final Core core, final int cycle, final PrintWriter write)
	{
		final Task currentTask = queueHandler.getCurrentTask();			//Task holen
		final int currentAdress = currentTask.getAdress();			//Adresse des Task holen

		queueHandler.remove();		//Task aus der Warteliste entfernen


		final Instruction inst = core.get(new Value(currentAdress));		//Holen von Instruktion an der jetzigen Speicherstelle
		final Command com = inst.getCommand();

		if(killer) {
			CodeMap.getMap().get(Command.DAT).execute(core, write, this, currentTask, inst, currentAdress);
		}
		else {
			final Event exec = Event.newExecute(getProgramIndex(), currentTask.getIndex(), cycle, new Value(currentAdress), inst);		//Eventlog schreiben
			write.println(PrintEvent.printEvent(exec));
			CodeMap.getMap().get(com).execute(core, write, this, currentTask, inst, currentAdress);	//Befehl ausfuehren
		}

		currentTask.countAdress();			//Die adresse erhoehen
		queueHandler.add(currentTask);		//Den Task wieder hinten an die Warteliste dranhengen
	}

	/** Getter for the program index.
	 * @return The index, starts from zero.
	 */
	public int getProgramIndex() {
		return programIndex;
	}

	/** Getter for the variable alive.
	 * @return True if the program is alive, false if its dead.
	 */
	public boolean isAlive() {
		return alive;
	}
	/** Set the program dead and write Eventlog.
	 * @param write The output Writer.
	 * @param lastTask The last used Task.
	 */
	public void setDead(final PrintWriter write, final Task lastTask) {
		alive = false;

		lastTask.countAdress();			//Den letzten Task in die Warteliste schreiben und vorher seine Adresse erhoehen
		queueHandler.add(lastTask);
		killTask();						//Setzen der kill-flag damit der Task nach execute nicht doppelt angehaengt wird

		final byte[] taskIndexes = new byte[getTaskIndexCounter()];			//Byte Array
		for(int index=0; index<getTaskIndexCounter() && !queueHandler.isEmpty(); index++) {		//Durch die Warteliste durchgehn und sie dabei loeschen
			final int tmpValue = queueHandler.remove().getIndex();		//Index des jeweiligen Tasks
			taskIndexes[tmpValue] = 1;								//Eins (true) an die adresse schreiben die dem index entspricht
		}
		for(int count = 0; count<taskIndexes.length; count++) {		//Das Array durchlaufen
			if(taskIndexes[count]==1) {									//Wenn das Byte "gesetzt" ist
				final Event kill = Event.newKill(programIndex, count);		//Dann gebe ein kill-Event aus mit dem jeweiligen index
				write.println(PrintEvent.printEvent(kill));
			}
		}
	}

	/** Makes a new Task and adds it to the queue.
	 * @param adress The start adress of the new Task.
	 */
	public void newTask(final Value adress)
	{
		queueHandler.saveTask(adress);
	}

	/** Kills a Task. Means removes it from the queue. */
	public void killTask() {
		queueHandler.kill();
	}

	/** Checks if the program still has a Task to run.
	 * @return True if there are still Task(s), false if not.
	 */
	public boolean queueIsEmpty() {
		return queueHandler.isEmpty();
	}

	public int getTaskIndexCounter() {
		return queueHandler.getTaskCounter();
	}
	/** hashCode method.
	 * @return hashCode
	 */
	public int hashCode() {
		if(alive)
			return queueHandler.hashCode() +1;
		else
			return queueHandler.hashCode();
	}
	/** equals method.
	 * @param that Any kind of Object.
	 * @return True if this and that are equal.
	 */
	public boolean equals(final Object that) {
		boolean equalsResult = false;
		if(that==null)
			return false;
		if(getClass()!=that.getClass())
			return false;
		final Program tmpProgram = (Program) that;
		if(queueHandler.equals(tmpProgram.queueHandler))
			equalsResult = alive==tmpProgram.alive;
		return equalsResult;
	}
	/** This method sets the killer.
	 * So the next Task of every Program will be killed.
	 */
	public void killAll() {
		myScheduler.killAll();
	}
}
