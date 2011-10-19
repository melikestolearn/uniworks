package corewar.filter.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import corewar.common.instructions.Instruction;
import corewar.filter.compiler.exceptions.BadCoresizeException;
import corewar.filter.compiler.exceptions.BadSyntaxException;
import corewar.pipes.decimal.Dezimal;
import corewar.pipes.source.Semantics;
import corewar.pipes.source.Syntax;
/** This is the Ultimate Redcode Compiler.
 * No further description required ;)
 * @author E. Paralikes, Paralike@hm.edu & T. Mayer, Mayer25@hm.edu
 */
public class RedCompiler implements CompilerCore {
	/** This is the method for compiling.
	 * The method compile reads Redcode from a reader,
	 * encodes it into decimal code and writes it on a writer
	 * @param input Is the input reader
	 * @param output Is the output writer
	 * @return true if compiling was successful, false if not
	 * @throws IOException Could be thrown
	 */
	public boolean compile(final Reader input, final Writer output) throws IOException {
			final BufferedReader read = new BufferedReader(input);
			final PrintWriter write = new PrintWriter(output);
			boolean result = true;				//Rueckgabe wert
			String line = read.readLine();
			int lineIndex = 1;					// Zaehler der Zeilen fuer die Exceptions
			try {
				while(line!=null) {
					final Instruction instr = Syntax.parseInstruction(line);	// Object erstellen
					if(instr!=null) {											// Wenn instr==null ist die Zeile leer und wird uebersprungen
						Semantics.checkSem(instr);								// Den Syntax ueberpruefen
						write.println(Dezimal.encode(instr));					// Schreiben
					}
					line = read.readLine();
					lineIndex++;
				}
			}
			catch(BadSyntaxException ex) {
				System.err.println(ex.getClass().getSimpleName() +": line(" +lineIndex +")");
				result = false;
			}
			catch(BadCoresizeException ex) {
				System.err.println(ex.getClass().getSimpleName() +": Coresize not supported!!");
				result = false;
			}
			return result;
	}
}
