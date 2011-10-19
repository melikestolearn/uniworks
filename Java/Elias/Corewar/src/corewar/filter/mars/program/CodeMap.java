package corewar.filter.mars.program;

import java.util.HashMap;
import java.util.Map;

import corewar.common.instructions.Command;
import corewar.filter.mars.code.AddCode;
import corewar.filter.mars.code.CompareCode;
import corewar.filter.mars.code.CoreCode;
import corewar.filter.mars.code.DJumpOnZeroCode;
import corewar.filter.mars.code.DataCode;
import corewar.filter.mars.code.JumpCode;
import corewar.filter.mars.code.JumpOnZeroCode;
import corewar.filter.mars.code.KillCode;
import corewar.filter.mars.code.MoveCode;
import corewar.filter.mars.code.SplitCode;
/** Contains a Map of command code.
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public final class CodeMap {
	/** Map which contains Commands as it keys for the equal Command-execution-programs. */
	private static Map<Command, CoreCode> commandMap = new HashMap<Command, CoreCode>();

	static
	{
		commandMap.put(Command.ADD, new AddCode());
		commandMap.put(Command.CMP, new CompareCode());
		commandMap.put(Command.DAT, new DataCode());
		commandMap.put(Command.DJZ, new DJumpOnZeroCode());
		commandMap.put(Command.JMP, new JumpCode());
		commandMap.put(Command.JMZ, new JumpOnZeroCode());
		commandMap.put(Command.MOV, new MoveCode());
		commandMap.put(Command.SPL, new SplitCode());
		commandMap.put(Command.KLL, new KillCode());
	}

	/** Constructor is hidden. */
	private CodeMap() {}

	public static Map<Command, CoreCode> getMap() {
		return commandMap;
	}
}
