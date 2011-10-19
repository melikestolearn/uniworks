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
 * Sun Microsystems Inc. Java 1.6.0_24, Linux i386 2.6.32.10
 * violet (Intel Core2 CPU 6600/2400 MHz, 2 Cores, 3328 MB RAM)
 */

import corewar.filter.mars.MarsCore;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** Hauptprogramm zum Start eines Mars mit Eingabe von einer Dezimalcode-Datei
 * und Ausgabe auf eine Protokoll-Datei.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public final class ParallelFileMars
{
    /** Main-Klasse. Keine Instanzen erlaubt. */
    private ParallelFileMars()
    {
        throw new AssertionError("no objects permitted.");
    }

    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     * 1. Qualifizierter Klassenname einer Mars-Implementierung.
     * 2. Dezimalcode-Datei Programm 1.
     * 3. Weitere Dezimalcode-Datei Programm 2.
     * 4. Protokoll-Datei Simulation Programm 1 gegen Programm 2.
     * 5. Weitere Protokoll-Datei Simulation Programm 2 gegen Programm 1.
     * @throws IOException Fehler beim Lesen oder Schreiben eines Files.
     * @throws ClassNotFoundException Mars-Implementierung existiert nicht.
     * @throws InstantiationException Mars-Implementierung kann nicht instanziiert werden (kein Def-Ctor?).
     * @throws IllegalAccessException Mars-Implementierung hat keinen public Def-Ctor.
     */
    public static void main(final String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // Exactly 5 args required, no more, no less
        if(args.length != 5)
            throw new IllegalArgumentException("\nrequired args are: classname-mars program1.dc program2.dc event12.log event21.log");

        // get the Mars
        final MarsCore mars = (MarsCore)Class.forName(args[0].replace(File.separatorChar, '.')).newInstance();

        // local thread class, running the Mars in the background
        final class BackgroundMarsRunner extends Thread
        {
            /** Readers providing decimal codes */
            private final Reader[] readers;

            /** Writer consuming the event log. */
            private final Writer destination;

            /** Ctor setting everything up und auto-starting the thread.
             * Usually bad style, but o.k. here, since the class is final.
             * @param logFilenameArgsIndex Index of logfile name in args array.
             * @param codeFilenameArgIndex Indices of compiled code filenames in args array.
             * @exception IOException Failure reading or writing files.
             */
            public BackgroundMarsRunner(final int logFilenameArgsIndex, final int... codeFilenameArgIndex) throws IOException
            {
                // Wrap up everything in readers and writers, then fire off simulation
                destination = new FileWriter(args[logFilenameArgsIndex]);
                readers = new Reader[]
                {
                    new FileReader(args[codeFilenameArgIndex[0]]),
                    new FileReader(args[codeFilenameArgIndex[1]]),
                };
                start();
            }

            @Override
            public void run()
            {
                try
                {
                    // run simlation, then close I/O
                    mars.runBattle(destination, readers);
                    destination.flush();    // you never know :-)
                    destination.close();
                    for(Reader reader: readers)
                        reader.close();
                }
                catch(IOException ex)
                {
                    // close has failed - now what!?
                    throw new RuntimeException(ex);
                }
            }

        };
        // Args are:
        // 1 - redcode
        // 2 - other redcode
        // 3 - logfile first vs second 
        // 4 - logfile second vs first
        new BackgroundMarsRunner(3, 1, 2);
        new BackgroundMarsRunner(4, 2, 1);
    }

}
