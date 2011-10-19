/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 * Praktikum Softwareentwicklung II, SS2011
 * Fakultät 07 für Informatik und Mathematik, Hochschule München
 *   ____
 *  / ___|___  _ __ _____      ____ _ _ __
 * | |   / _ \| '__/ _ \ \ /\ / / _` | '__|
 * | |__| (_) | | |  __/\ V  V / (_| | |
 *  \____\___/|_|  \___| \_/\_/ \__,_|_|
 *
 * Sun Microsystems Inc. Java 1.6.0_24, Linux i386 2.6.32.21
 * tura (Intel Atom CPU N270/1600 MHz, 2 Cores, 2048 MB RAM)
 */
package corewar.filter.mars;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** Interface für MARS-Implementierungen.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id: MarsCore.java 17 2011-03-20 09:11:44Z schiedi $
 */
public interface MarsCore
{
	/** Wickelt einen Wettkampf zwischen Redcode-Programmen ab.
	 * @param protocolWriter Writer, offen zur Ausgabe des Ereignis-Protokolls.
	 * Wird nicht geschlossen.
	 * @param redcodeReaders Reader, alle offen zum Lesen von dezimalem Redcode.
	 * Werden nicht geschlossen.
	 * @throws IOException Fehler beim Lesen oder Schreiben einer Pipe.
	 */
	void runBattle(Writer protocolWriter, Reader... redcodeReaders) throws IOException;

}
