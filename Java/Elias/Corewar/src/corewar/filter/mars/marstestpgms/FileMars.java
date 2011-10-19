package corewar.filter.mars.marstestpgms;

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
 * lilli (Intel CPU U7300/1300 MHz, 2 Cores, 4096 MB RAM)
 */
import corewar.filter.ConsoleFilter;
import corewar.filter.mars.MarsCore;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** Hauptprogramm zum Start eines Mars mit Eingabe von einer Dezimalcode-Datei
 * und Ausgabe auf eine Protokoll-Datei.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public final class FileMars
{
    /** Main-Klasse. Keine Instanzen erlaubt. */
    private FileMars()
    {
    }

    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     * 1. Qualifizierter Klassenname einer Mars-Implementierung.
     * 2. Dezimalcode-Datei.
     * 3. Weitere Dezimalcode-Datei.
     * 4. Protokoll-Datei.
     * @throws IOException Fehler beim Lesen oder Sschreiben eines Files.
     */
    public static void main(final String[] args) throws IOException
    {
        if(args.length != 4)
            throw new IllegalArgumentException("\nrequired args are: classname-mars program1.dc program2.dc event.log");

        final ConsoleFilter consoleFilter = new ConsoleFilter();
        int arg = 0;
        final MarsCore mars = (MarsCore)consoleFilter.makeFilterObject(args[arg++]);
        final Reader[] sources = new Reader[]
        {
            new FileReader(args[arg++]),
            new FileReader(args[arg++]),
        };
        final Writer destination = new FileWriter(args[arg++]);
        mars.runBattle(destination, sources);
        for(Reader source: sources)
            source.close();
        destination.flush();
        destination.close();
        consoleFilter.close();
    }

}
