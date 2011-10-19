package corewar.filter.mars.util;

import corewar.common.Constants;

/** The Mars constants are stored here.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class MarsConstants {

	/** Boolean to choose test Battle or real battle. <p>
	 * If false, the Mars runs on test mode and loads the Warriors on the first start adresses in the Core.
	 * If true the Mars runs on battle mode and loads the Warriors on random adresses in their reserved space. <p>
	 * By default: false
	 */
	public static final boolean SHOOT_OUT = getProbShootout("shootout", false);
	/** The amount of maximal cycles.
	 *  If the mars reaches this value the battle will be declared as a draw.
	 */
	public static final int MAX_CYCLES = getProbCycles("maxcycles", 100*Constants.CORE_SIZE);

	/** Constructor is hidden. */
	private MarsConstants() {}

	/** Setter for Shootout.
	 * Shootout is declared as a system property.
	 * @param probName The property name.
	 * @param defValue The default value.
	 * @return True or false.
	 */
	private static boolean getProbShootout(final String probName, final boolean defValue)
	{
		boolean result = defValue;
		final String val = System.getProperty(probName);
		if(val!=null && (val.toLowerCase()).equals("true"))
			result = Boolean.parseBoolean(val);
		return result;
	}

	/** Setter for maxcycles.
	 * Maxcycles is declared as a system property.
	 * @param probName The property name.
	 * @param defValue The default value.
	 * @return A number smaller than the max supported cycles, which is 1000000.
	 */
	private static int getProbCycles(final String probName, final int defValue){
		int result = defValue;		//100x Coresize
		final String size = System.getProperty(probName);
		if(size!=null)
			result = Integer.parseInt(size);
		return result;
	}
}
