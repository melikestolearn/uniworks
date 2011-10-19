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

import corewar.filter.ConsoleFilter;
import java.io.IOException;

/** Redcode-Compiler, der als Filter arbeitet.
 * Liest Quelltext von der Standard-Eingabe und schreibt Dezimalcode auf die Standard-Ausgabe.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version $Id: Compiler.java 16 2011-03-16 11:24:32Z schiedi $
 */
public final class Compiler
{
    /** Keine Aufrufe erlaubt. */
    private Compiler()
    {
        assert false: "This is a singleton, no objects permitted";
    }

    /** Compilertreiber.
     * Liefert am Ende den Exitstatus 0 (erfolgreich) oder -1 (Compilerfehler) zurück.
     * @param args Kommandozeilenargumente:
     * 1. Qualifizierter Klassenname einer CompilerCore-Implementierung.
     * Pfadtrenner werden durch "." ersetzt.
     * @throws IOException Fehler beim Schreiben oder Lesen einer Pipe.
     */
    public static void main(final String[] args) throws IOException
    {
        final ConsoleFilter consoleFilter = new ConsoleFilter();
        final CompilerCore compilerCore = (CompilerCore)consoleFilter.makeFilterObject(args[0]);
        final boolean result = compilerCore.compile(consoleFilter.getIncomingPipe(), consoleFilter.getOutgoingPipe());
        consoleFilter.close();
        System.exit(result? 0: -1);
    }

}
