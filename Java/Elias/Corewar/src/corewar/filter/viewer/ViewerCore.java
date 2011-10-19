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
package corewar.filter.viewer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** Interface für Viewer.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id: ViewerCore.java 16 2011-03-16 11:24:32Z schiedi $
 */
public interface ViewerCore
{
    /** Stellt ein Ereignis-Protokolls dar.
     * @param protocolReader Reader, offen zum Lesen eines Ereignis-Protokolls.
     * Wird nicht geschlossen.
     * @param copyWriter Writer, offen zur Ausgabe einer identischen Kopie des Ereignis-Protokolls.
     * Wird nicht geschlossen.
     * Falls null, wird nichts kopiert.
     * @throws IOException Fehler beim Lesen oder Schreiben einer Pipe.
     */
    void present(final Reader protocolReader, final Writer copyWriter) throws IOException;

}
