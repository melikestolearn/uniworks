package corewar.filter.mars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import corewar.common.Constants;
import corewar.common.instructions.Instruction;
import corewar.common.instructions.Value;
import corewar.filter.mars.util.IllegalWarriorSizeException;
import corewar.filter.mars.util.MarsConstants;
import corewar.filter.mars.util.RandomLoad;
import corewar.filter.mars.util.Scheduler;
import corewar.pipes.decimal.Decoder;
import corewar.pipes.eventlog.Event;
import corewar.pipes.eventlog.PrintEvent;
import corewar.pipes.eventlog.UnsupportedCyclesNumberException;

/** The main Mars.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class PhoenixMars implements MarsCore {
	/** Runs a battle between Redcode Programs. <p>
	 * {@inheritDoc}
	 * @param protocolWriter The output Writer. <p>
	 * {@inheritDoc}
	 * @param redcodeReaders Redcode readers, to read in decimal code. <p>
	 * {@inheritDoc}
	 */
	public void runBattle(final Writer protocolWriter, final Reader... redcodeReaders)	throws IOException {

		final PrintWriter write = new PrintWriter(protocolWriter);
		final Core core = new Core();
		boolean throwException = false;

		try {
			final Event init = Event.newInit(redcodeReaders.length-1, Constants.CORE_SIZE-1, MarsConstants.MAX_CYCLES-1, new Value(Constants.CORE_SIZE-1));
			write.println(PrintEvent.printEvent(init));
		}
		catch(UnsupportedCyclesNumberException ex) {	// Exception bei maxcycles ueberschritten
			System.err.println(ex.getClass().getSimpleName() +": Cycles number not supported!!");
			throwException = true;
		}

		// Eine Liste die Warriors enthaelt
		// Die innere Liste ist in Form von Dezimalcode
		final List<List<String>> warriorsStringList = new ArrayList<List<String>>(0);

		for(int count = 0; count<redcodeReaders.length; count++){	// In die Liste einfuegen
			final BufferedReader read = new BufferedReader(redcodeReaders[count]);
			warriorsStringList.add(splitDezimal(read));
		}

		Value[] startAdresses = null;		// Array von start Adressen
		try {
			startAdresses = loadWarriors(write, core, warriorsStringList);
		} catch (IllegalWarriorSizeException ex) {		// Exception wenn ein Warrior zu gross ist, und nicht in seinen Bereich passt
			System.err.println(ex.getClass().getSimpleName() +": Maximum supported Warrior size violated");
			throwException = true;
		}

		if(throwException)	// Bei einer Exception wird die methode beendet
			return;

		final Scheduler marsScheduler = new Scheduler(startAdresses);
		marsScheduler.run(core, write);

	}
	/** This method saves the decimal blocks of a Reader, into a list of strings.
	 * @param input A BufferedReader that contains decimal code.
	 * @return A list of strings, where every element is a decimal block.
	 * @throws IOException If the IO does not work.
	 */
	private static List<String> splitDezimal(final BufferedReader input) throws IOException{
		final List<String> result  = new ArrayList<String>(0);		//Die Liste zum zurueckgeben
		String line = input.readLine();
		while(line!=null) {
			result.add(line);
			line = input.readLine();
		}
		return result;
	}
	/** This methods loads an "unlimited" number of programs into the Core.
	 * The programs are given from a List that contains string Lists.
	 * For the Lists you may use the method "splitDezimal".
	 * @param write The output Writer.
	 * @param coreToInit Is the Mars Core.
	 * @param lists The List of Lists.
	 * @return An Array with the start adresses of the Warriors.
	 * @throws IllegalWarriorSizeException If a Warrior violates the size limitations.
	 */
	private static Value[] loadWarriors(final PrintWriter write, final Core coreToInit, final List<List<String>> lists) throws IllegalWarriorSizeException {
		final int warriorNumber = lists.size();
		final Value[] startAdr = new Value[warriorNumber];
		for(int count = 0; count<warriorNumber; count++){							//Schleife zum durchgehn der Warriors

			if(lists.get(count).size()>Constants.CORE_SIZE/warriorNumber)			//Test ob ein Warrior groesser ist als sein zugeteilter Platz im Core
				throw new IllegalWarriorSizeException("Too big Warrior");

			int random = 0;
			if(MarsConstants.SHOOT_OUT)
				random = RandomLoad.randomLoad(warriorNumber, lists.get(count).size());

			for(int index = 0; index<lists.get(count).size();index++) {					//Schleife zum durchgehn der Instructions
				final Instruction instrToSet = Decoder.decode(lists.get(count).get(index));		//Tmp. Instruction
				final int adress = (Constants.CORE_SIZE/warriorNumber)*count +index +random;			//Berechnen der Adresse wo der derzeitige Warrior, bzw. seine Instruction, hingespeichert wird

				coreToInit.set(new Value(adress), instrToSet);

				if(index==0)
					startAdr[count] = new Value(adress);

				final Event currentLoad = Event.newLoad(count, new Value(adress), lists.get(count).get(index));
				write.println(PrintEvent.printEvent(currentLoad));
			}
		}
		return startAdr;
	}
}
