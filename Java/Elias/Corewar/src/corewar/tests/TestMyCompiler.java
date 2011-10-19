package corewar.tests;
/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 * Praktikum Softwareentwicklung II, SS2011
 * Fakultät 07 für Informatik und Mathematik, Hochschule München
 *   ____
 *  / ___|___  _ __ _____      ____ _ _ __
 * | |   / _ \| '__/ _ \ \ /\ / / _` | '__|
 * | |__| (_) | | |  __/\ V  V / (_| | |
 *  \____\___/|_|  \___| \_/\_/ \__,_|_|
 *
 * Sun Microsystems Inc. Java 1.6.0_24, Linux i386 2.6.32.23
 * emma (Intel Core i7 CPU 920/2667 MHz, 8 Cores, 24596 MB RAM)
 */
import corewar.filter.compiler.CompilerCore;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Testprogramm für eine Compilerimplementierung.
 * Aufruf mit dem quailifizierten Klassennamen eines Compilers und dem Namen einer
 * Testdatei.
 * Die Namen von Testdateien haben den folgenden Aufbau:
 * (basename).(index).true|false.(compiled).rc
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public final class TestMyCompiler
{
    /** Objekte dieser Klasse nicht sinnvoll. */
    private TestMyCompiler() {}

    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     * 1. Qualifizierter Klassenname der Compilerimplementierung.
     * 2. Testfall-Datei.
     * @throws Exception Fehler beim Laden des Compilers oder des Testfalls.
     * Test nicht abgewickelt.
     */
    public static void main(final String[] args) throws Exception
    {
        if(args.length == 0)
            fail("Required args are:%n\tcompiler-classname testfile%nE.g.:%n\tcorewar/filter/compiler/MyCoolCompiler test.0.true.000000000000.rc%n");

        // Compilerimplementierung laden
        final String compilerClassname = args[0];
        final CompilerCore core = (CompilerCore)Class.forName(compilerClassname.replace(File.separatorChar, '.')).newInstance();

        // Name der Testfall-Datei auswerten
        final String testFilename = args[1];
        final File testFile = new File(testFilename);
        final Pattern pattern = Pattern.compile("\\w+\\.\\d+\\.(\\w+)\\.(\\d*)\\.rc");
        final Matcher matcher = pattern.matcher(testFile.getName());
        if(!matcher.matches())
            throw new IllegalArgumentException("invalid testfile name: " + testFilename);
        final boolean wantResult = Boolean.parseBoolean(matcher.group(1));
        final String wantOutput = matcher.group(2);

        // Testfall-Datei
        final FileReader redcodeReader = new FileReader(testFile);

        // Compiler starten und Ergebnis aufzeichen
        final StringWriter decimalWriter = new StringWriter();
        final boolean haveResult = core.compile(redcodeReader, decimalWriter);

        // Erfolg/Misserfolg überprüfen
        if(wantResult != haveResult)
            fail("Test failed:%n\twanted result:\t%b%n\tgot result:\t%b%n",
                 wantResult,
                 haveResult);

        // Compilerausgabe überprüfen
        final String haveOutput = decimalWriter.toString().replaceAll("\\s+", "");
        if(!wantOutput.equals(haveOutput))
            fail("Test failed:%n\twanted output:\t\"%s\"%n\tgot output:\t\"%s\"%n",
                 wantOutput,
                 haveOutput);

        pass("Test passed.%n");
    }

    /** Gibt eine Meldung aus und beendet das Programm dann mit Erfolg.
     * @param fmt Formatstring.
     * @param args Argumente zum Formatstring.
     */
    private static void pass(final String fmt, final Object... args)
    {
        System.out.printf(fmt, args);
        System.exit(0);
    }

    /** Gibt eine Meldung aus und beendet das Programm dann mit Misserfolg.
     * @param fmt Formatstring.
     * @param args Argumente zum Formatstring.
     */
    private static void fail(final String fmt, final Object... args)
    {
        System.out.printf(fmt, args);
        System.exit(-1);
    }

}
