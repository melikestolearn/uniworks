/* Vorlesung Softwareentwicklung II, SS 2011
 * Fakultät für Informatik und Mathematik, Hochschule München
 * Entwickelt mit: Java 6 SE Development Kit, Linux x86 2.6, Netbeans 6.8
 */
package corewar.filter;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/** Objekt zur Verwaltung von Pipes von der/zur Konsole.
 * Konstruiert auch Objekte einer gegebenen Klasse.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public class ConsoleFilter
{
    /** Eingehende Pipe. */
    private final Reader incomingPipe;

    /** Ausgehende Pipe. */
    private final Writer outgoingPipe;

    /** Öffnet Pipes von der Konsole und zur Konsole. */
    public ConsoleFilter()
    {
        outgoingPipe = new OutputStreamWriter(System.out);
        incomingPipe = new InputStreamReader(System.in);
    }

    /** Erzeugt ein Objekt mit dem gegebenen Klassennamen.
     * Die Klasse muss einen öffentlichen Def-Ctor haben.
     * @param qualifiedClassname Name der Klasse, von der ein Objekt erzeugt werden soll.
     * Pfadtrenner werden durch "." ersetzt.
     * @return Objekt.
     * @throws IllegalArgumentException Beim Ereugen des Objekts ging etwas schief.
     */
    public Object makeFilterObject(final String qualifiedClassname)
    {
        try
        {
            // Objekt einer MarsCore-Implementierung erzeugen
            return Class.forName(qualifiedClassname.replace(File.separatorChar, '.')).newInstance();
        }
        catch(ClassNotFoundException ex)
        {
            throw new IllegalArgumentException(ex);
        }
        catch(IllegalAccessException ex)
        {
            throw new IllegalArgumentException(ex);
        }
        catch(InstantiationException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    /** Schließt die beiden Pipes.
     * @throws IllegalArgumentException Fehler beim Schließen der Pipes.
     */
    public void close()
    {
        try
        {
            incomingPipe.close();
            outgoingPipe.close();
        }
        catch(IOException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    public Reader getIncomingPipe()
    {
        return incomingPipe;
    }

    public Writer getOutgoingPipe()
    {
        return outgoingPipe;
    }

}
