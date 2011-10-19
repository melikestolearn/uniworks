package corewar.filter.mars.util;

import java.util.Random;

import corewar.common.Constants;

/** Is used for random numbers management.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class RandomLoad {

	/**Constant value of 1000. */
	private static final int ONE_THOUSAND = 1000;
	/**Constant value of 10000. */
	private static final int TEN_THOUSAND = 10000;

	/** Constructor is hidden. */
	private RandomLoad() {}

	/** Generates a random number.
	 * Limitations are the Core size, the Warrior number and the Warrior size.
	 * The result is been calculated by the available space for the Warrior.
	 * @param warriorNumber The number of Warriors, that will participate in the battle.
	 * @param size The current Warrior size.
	 * @return A random number in the adress interval of the core, where the warrior can be loaded into.
	 */
	public static int randomLoad(final int warriorNumber, final int size) {
		final int rand1;
		final int rand2;
		final int random;
		final int result;
		final Random randomObject = new Random();

		rand1 = Math.abs(randomObject.nextInt()%ONE_THOUSAND);
		rand2 = Math.abs(randomObject.nextInt()%TEN_THOUSAND);

		random = Math.abs(rand2*rand1+rand2);

		result = random%(Constants.CORE_SIZE/warriorNumber-size);
		return result;
	}

}
