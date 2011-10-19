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
package corewar;

import corewar.filter.compiler.CompilerCore;
import corewar.filter.mars.MarsCore;
import corewar.filter.viewer.ViewerCore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/** Starter für eine ganze Pipeline.
 * Das Programm kann auf zwei Arten aufgerufen werden:
 * <ol><li>Mit einer Liste von Dateinamen von Redcode-Programmen und
 * qualifizierten Klassennamen der Filterklassen.
 * Ein Beispiel:
 * <pre>java corewar/PipeRunner corewar.filter.compiler.RedcodeCompiler imp.rc corewar.filter.mars.trash.TrashMars corewar.filter.viewer.cheap.CheapDumper</pre>
 * Dabei muss ein Compiler, ein Mars, und wenigstens ein Viewer genannt werden.</li>
 * <li>Mit einem Responsefile.
 * Das Responsefile enthält ein Kommandozeilenargument pro Zeile.
 * Es wird als einziges Kommandozeilenargument übergeben mit einem @ vorne dran.
 * Wenn die Datei response.txt enthält:
 * <pre>corewar.filter.compiler.RedcodeCompiler
imp.rc
corewar.filter.mars.trash.TrashMars
corewar.filter.viewer.cheap.CheapDumper</pre>
 * kann das Programm aufgerufen werden mit
 * <pre>java corewar/PipeRunner @response.txt</pre></li></ol>
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public final class PipeRunner
{
    /** Def-Ctor, darf nicht aufgerufen werden. */
    private PipeRunner()
    {
    }

    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     * Mischung aus Dateinamen von Redcode-Programmen und qualifizierten Klassennamen.
     * Die Reihenfolge ist nicht relevant.
     * @throws IOException Fehler beim Lesen oder Schreiben einer Pipe.
     * @throws InstantiationException Filter konnte nicht instanziiert werden.
     * @throws IllegalAccessException Filter konnte nicht instanziiert werden.
     * @throws ClassNotFoundException Filter konnte nicht instanziiert werden.
     */
    public static void main(final String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        if(args.length == 0)
        {
            System.out.println("Valid args are:");
            System.out.println("\tredcode ... Compilerclass MARSClass Viewerclass ...");
            System.out.println("or:");
            System.out.println("\t@responsefile");
            System.out.println("where responsefile hold 1 line/arg of first call, eg");
            System.out.println("\t---8<---");
            System.out.println("\trecode");
            System.out.println("\t...");
            System.out.println("\tCompilerclass");
            System.out.println("\tMARSClass");
            System.out.println("\tViewerclass");
            System.out.println("\t...");
            System.out.println("\t---8<---");
        }
        // Aufruf mit Responsefile? Genau 1 Argument, das beginnt mit @
        else if(args.length == 1 && args[0].charAt(0) == '@')
        {
            // Responsefile lesen und jede Zeile an die Liste anfügen
            final List<String> argsList = new ArrayList<String>();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0].substring(1)))));
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine())
                argsList.add(line.trim());
            bufferedReader.close();
            // main rekursiv aufrufen, dabei die Liste als Array übergeben
            main(argsList.toArray(new String[argsList.size()]));
        }
        else
        {
            // Einzelne Kommandozeilenargumente
            // Jedes Argument, das ein File benennt, in den Compiler schicken und das Compilat speichern
            final List<Reader> compiledPrograms = new ArrayList<Reader>();
            for(String arg: args)
                if(new File(arg).exists())
                {
                    final Reader reader = new FileReader(arg);
                    final Writer writer = new StringWriter();
                    final CompilerCore compilerCore = (CompilerCore)locateCompatibleObjects("corewar.filter.compiler.CompilerCore", args).get(0);
                    System.out.printf("running %s(%s)%n", compilerCore.getClass().getSimpleName(), arg);
                    compilerCore.compile(reader, writer);
                    reader.close();
                    compiledPrograms.add(new StringReader(writer.toString()));
                }

            // Alle Compilate an den MARS verfüttern
            Writer writer = new StringWriter();
            final List<Object> marsCores = locateCompatibleObjects("corewar.filter.mars.MarsCore", args);
            final MarsCore marsCore = (MarsCore)marsCores.get(0);
            System.out.printf("running %s%n", marsCore.getClass().getSimpleName());
            marsCore.runBattle(writer, compiledPrograms.toArray(new Reader[compiledPrograms.size()]));
            String eventlog = writer.toString();

            // Das Ereignisprotokoll nacheinander durch alle Viewer schicken
            for(Object object: locateCompatibleObjects("corewar.filter.viewer.ViewerCore", args))
            {
                final ViewerCore viewerCore = (ViewerCore)object;
                writer = new StringWriter();
                System.out.printf("running %s%n", viewerCore.getClass().getSimpleName());
                viewerCore.present(new StringReader(eventlog), writer);
                eventlog = writer.toString();
            }
        }
    }

    /** Durchsucht alle Elemente von args nach Namen von Klassen,
     * die das gegebene Interface implementieren.
     * Von jeder dieser Klassen wird ein Objekt erzeugt und das Array aller
     * dieser Objekte zurück geliefert.
     * @param interfaceName Qualifizierter Name des Interfaces, zu dem kompatible Objekte
     * gesucht werden.
     * @param args Strings mit Namen von Klassen, die überprüft werden.
     * @return Liste von Objekten der Klassen, die zum Interface kompatibel sind.
     * Eventuell leer, aber nicht null.
     * @throws InstantiationException Filter konnte nicht instanziiert werden.
     * @throws IllegalAccessException Filter konnte nicht instanziiert werden.
     * @throws ClassNotFoundException Filter konnte nicht instanziiert werden.
     */
    @SuppressWarnings("rawtypes")
    private static List<Object> locateCompatibleObjects(final String interfaceName, final String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        final List<Object> result = new ArrayList<Object>();
        final Class interfaceObject = Class.forName(interfaceName);
        for(String arg: args)
            if(!new File(arg).exists())
            {
                final Object object = Class.forName(arg.replace(File.separatorChar, '.')).newInstance();
                if(interfaceObject.isInstance(object))
                    result.add(object);
            }
        return result;
    }

}
