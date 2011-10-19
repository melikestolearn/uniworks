package corewar.filter.mars.util;

import java.io.PrintWriter;

import corewar.common.instructions.Value;
import corewar.filter.mars.Core;
import corewar.filter.mars.program.Program;
import corewar.pipes.eventlog.Event;
import corewar.pipes.eventlog.PrintEvent;

/** Scheduler is arranging Mars programs.
 * Ensures fair play.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class Scheduler {
	/** An array of Programs. */
	private final Program[] programs;

	/** This boolean can be used to kill one Task of every Program. */
	private boolean killer = false;

	/** Custom constructor for Scheduler.
	 * @param adr An array of start adresses. The adresses are assinged by the program index.
	 */
	public Scheduler(final Value[] adr) {
		programs = new Program[adr.length];	//Array von Programmen

		int index = 0;
		for(int i = 0; i<programs.length; i++) {
			programs[i] = new Program(this, adr[i], index);	//Programme in den Array laden, gleichzeitig start Adresse uebergeben
			index++;
		}
	}

	/** Runs the game.
	 * Every turn each program executes only one instruction.
	 * @param core The Mars Core.
	 * @param write The output Writer.
	 */
	public void run(final Core core, final PrintWriter write){

		int programsAlive = programs.length;		//Anzahl der noch lebenden Programmen, anfangs alle
		int cycles = 0;
		int killerCount = programsAlive;		// Zaehlt die Programme von denen noch ein Task gekillt werden muss
		while(programsAlive>1 && cycles<MarsConstants.MAX_CYCLES) {
			if(!killer)							// Wenn der killer inaktiv ist wird killerCount auf default gesetzt
				killerCount = programsAlive;
			for(int i = 0; i<programs.length; i++){		//Fuer jedes program eine Instruktion ausfuehren
				if(programs[i].isAlive()){
					programs[i].step(killer, core, cycles, write);
					if(!programs[i].isAlive())				//Berechnen von programsAlive
						programsAlive--;
					if(killer)
						killerCount--;
				}
			}
			if(killerCount==0)		// Wenn alle Programme einen Task gekillt haben, wird der killer zurueckgesetzt
				killer = false;
			cycles++;
		}

		final int winner = getWinner(programsAlive, cycles);
		final Event win = Event.newWin(winner);
		write.println(PrintEvent.printEvent(win));

	}
	/** Finds the winner of the match.
	 * @param programsAlive The number of programs alive.
	 * @param cycles The final number of cycles.
	 * @return The winner index, or the number of programs, at a draw.
	 */
	private int getWinner(final int programsAlive, final int cycles) {
		int winner = programs.length;
		final boolean allDead = programsAlive==0;
		if(cycles<MarsConstants.MAX_CYCLES && !allDead) {
			for(Program pr: programs) {
				if(pr.isAlive())
					winner = pr.getProgramIndex();
			}
		}
		return winner;
	}
	/** Sets the killer.
	 * So the next Task of every Program will be killed.
	 */
	public void killAll() {
		killer = true;
	}
}
