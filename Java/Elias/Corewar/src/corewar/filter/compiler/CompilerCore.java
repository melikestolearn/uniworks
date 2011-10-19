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
package corewar.filter.compiler;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** Interface für Redcode-Compiler.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id: CompilerCore.java 16 2011-03-16 11:24:32Z schiedi $
 */
public interface CompilerCore
{
    /** Liest Quelltext von einem Reader und schreibt dezimalen Redcode auf einen Writer.
     * @param input Reader, offen zum Lesen von Redcode-Quelltext.
     * Wird bis zum Ende gelesen. Wird nicht geschlossen.
     * @param output Writer, offen zur Ausgabe von dezimalem Redcode.
     * Wird nicht geschlossen.
     * @return true = Compiler erfolgreich; false = Syntaxfehler.
     * @throws IOException I/O-Fehler beim Lesen des Quelltextes oder Schreiben des
     * Dezimalcodes.
     */
    boolean compile(final Reader input, final Writer output) throws IOException;

}
