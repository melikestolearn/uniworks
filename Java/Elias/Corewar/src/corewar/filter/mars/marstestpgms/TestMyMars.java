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
import corewar.filter.mars.MarsCore;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/** Testprogramm für Mars-Implementierungen.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public final class TestMyMars
{
    /** Trenner zwischen den drei Abschnitten der Testfall-Datei. */
    private static final String SEGMENT_MARKER = ";;;\n";

    /** Objekte dieser Klasse nicht sinnvoll. */
    private TestMyMars()
    {
    }

    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     * 1. Qualifizierter Klassenname einer Mars-Implementierung.
     * 2. Kombinierte Datei aus Dezimalcodes und Eventlogs, getrennt mit Zeilen SEGMENT_MARKER.
     * @throws InstantiationException Fehler beim Initialisieren der Mars-Implementierung.
     * @throws ClassNotFoundException Fehler beim Initialisieren der Mars-Implementierung.
     * @throws IllegalAccessException Fehler beim Initialisieren der Mars-Implementierung.
     * @throws IOException Fehler beim Lesen oder Schreiben der Eingabe- oder Ausgabedatei.
     */
    public static void main(final String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException
    {
        // Check usage
        if(args.length < 2)
            throw new IllegalArgumentException(String.format("Required args are:%n\tmars-classname testfile%nE.g.:%n\tcorewar/filter/mars/MyCoolMars test1.dclog%n".replace('/', File.separatorChar)));

        // Argumente festhalten
        final String marsClassname = args[0];
        final String testcaseFilename = args[1];

        // Mars-Implementierung laden
        final MarsCore core = (MarsCore)Class.forName(marsClassname.replace(File.separatorChar, '.')).newInstance();

        // Testfall-Datei lesen und aufbereiten:
        // [0], [1] = Dezimalcodes,
        // [2] = Logfile oder null, wenn es geschrieben werden soll.
        final int maxParts = 3;
        String[] parts = loadCombinedTextfile(testcaseFilename);
        if(parts.length < 2 || parts.length > maxParts)
            throw new IOException("broken testcase file: " + testcaseFilename);
        parts = Arrays.copyOf(parts, maxParts);

        // Flag: true = Logfile erzeugen oder false = prüfen
        final boolean primingMode = parts[2] == null;

        int exitcode = -1;
        try
        {
            // Mars laufen lassen und Eventlog auffangen
            final StringWriter stringWriter = new StringWriter();
            core.runBattle(stringWriter, new StringReader(parts[0]), new StringReader(parts[1]));
            final String output = stringWriter.toString();

            // Priming-Mode?
            if(primingMode)
            {
                // Eventlog hinten an die Testfall-Datei anhängen
                final FileWriter input = new FileWriter(testcaseFilename, true);
                input.write(SEGMENT_MARKER);
                input.write(output);
                input.close();
                System.out.println("Test primed.");
                exitcode = 0;
            }
            else if(output.replaceAll("\\D", "").equals(parts[2].replaceAll("\\D", "")))
            {
                // Ist-Ergebnis mit Soll-Ergebnis vergleichen
                System.out.println("Test passed.");
                exitcode = 0;
            }
            else
            {
                System.out.printf("Test failed!%nWanted log:%n---8<---%n%s---8<---%nGot log:%n---8<---%n%s---8<---%n",
                                  parts[2],
                                  output);
                writeFile("wanted", parts[2]);
                writeFile("got", output);
            }
        }
        catch(Exception e)  // NOPMD -- ok, there must be no exception at all
        {
            System.out.printf("Test failed%n\tMars threw exception %s (%s)%n",
                              e.getClass().getSimpleName(),
                              e.getMessage());
        }

        System.exit(exitcode);
    }

    /** Liest eine Textdatei und trennt den Inhalt an allen Zeilen SEGMENT_MARKER
     * auf.
     * @param filename Name einer Textdatei, die aufgetrennt wird.
     * @return Array mit einem Element pro Abschnitt der Textdatei,
     * der mit einer Zeile SEGMENT_MARKER beginnt oder endet.
     * @throws IOException Datei konnte nicht gelesen werden.
     */
    private static String[] loadCombinedTextfile(final String filename) throws IOException
    {
        final StringBuilder builder = new StringBuilder();
        final FileReader fileReader = new FileReader(filename);
        int chr = fileReader.read();
        while(chr >= 0)
        {
            builder.append((char)chr);
            chr = fileReader.read();
        }
        fileReader.close();
        return builder.toString().split("(?m)^" + SEGMENT_MARKER);
    }

    /** Schreibt einen String auf eine Datei im Tempdir.
     * @param basename Namensrumpf, wird um ".log" ergänzt,
     * @param content Inhalt der Datei.
     * @throws IOException Datei konnte nicht geschrieben werden.
     */
    private static void writeFile(final String basename, final String content) throws IOException
    {
        final File outfile = new File(System.getProperty("java.io.tmpdir") + File.separator + basename + ".log");
        final FileWriter fileWriter = new FileWriter(outfile);
        fileWriter.write(content);
        fileWriter.close();
    }

}
