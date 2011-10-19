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

import corewar.filter.ConsoleFilter;
import java.io.IOException;

/** Treiber eines MARS.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id: Mars.java 16 2011-03-16 11:24:32Z schiedi $
 */
public final class Mars
{
    /** Default-Ctor. Darf nicht aufgerufen werden. */
    private Mars()
    {
        assert false: "Utility class without instances";
    }

    /** Hauptprogramm.
     * Liest dezimalen Redcode von der Standard-Eingabe und schreibt ein
     * Ereignis-Protokoll auf die Standard-Ausgabe.
     * @param args Kommandozeilenargumente.
     * 1. Qualifizierter Klassenname einer MarsCore-Implementierung.
     * @throws IOException Fehler beim Schreiben oder Lesen einer Pipe.
     */
    public static void main(final String[] args) throws IOException
    {
        final ConsoleFilter consoleFilter = new ConsoleFilter();
        final MarsCore mars = (MarsCore)consoleFilter.makeFilterObject(args[0]);
        mars.runBattle(consoleFilter.getOutgoingPipe(), consoleFilter.getIncomingPipe());
        consoleFilter.close();
    }
}
