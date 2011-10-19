package corewar.common;

/** Constants for corewar.
 * The corewar project constants are stored here
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class Constants {
	/**Size of the Mars Core.*/
	public static final int CORE_SIZE = getProb("coresize", 4000);
	/**Maximal size of the Mars Core. */
	public static final int MAX_CORE_SIZE = 10000;
	/** Private constructor, to avoid Objects. */
	private Constants() {}
	/** Setter for Coresize.
	 * CORE_SIZE is declared as a system property
	 * Throws NumberFormatException if an illegal coresize is given
	 * @param probName The property name, here for coresize
	 * @param defValue Default coresize value
	 * @return The custom coresize or, if not given, the default
	 */
	private static int getProb(final String probName, final int defValue)
	{
		int result = defValue;
		final String size = System.getProperty(probName);
		if(size!=null)
			result = Integer.parseInt(size);
		return result;
	}
}
