/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 *                _ _      __           _   _  __       
 * __      ____ _(_) |_   / / __   ___ | |_(_)/ _|_   _ 
 * \ \ /\ / / _` | | __| / / '_ \ / _ \| __| | |_| | | |
 *  \ V  V / (_| | | |_ / /| | | | (_) | |_| |  _| |_| |
 *   \_/\_/ \__,_|_|\__/_/ |_| |_|\___/ \__|_|_|  \__, |
 *                                                |___/ 
 * Oracle Corporation Java 1.7.0-ea, Linux i386 2.6.32.23
 * fiona (Intel Atom CPU N270/1600 MHz, 2 Cores, 2048 MB RAM)
 */
package bossworkerjob;

/** Job, der den Prozessor mit sinnlosen mathematischen Funktionen beschäftigt.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class HeavyMathJob extends EasyStringJob
{
    /** Anzahl Funktionsaufrufe, die dieses System pro Sekunde schafft. */
    private static final int loopsPerSecond;

    /** Anzahl Schleifen dieses Jobs. */
    private final long loops;

    /** Kalibrierung der Anzahl Funktionsaufrufe/Sekunde. */
    static
    {
        // kurz warmlaufen lassen
        long startup = System.currentTimeMillis();
        double nonsense = 0.5;
        while(System.currentTimeMillis() - startup < 100)
            nonsense = sillyFunction(nonsense);

        // ¼" lang die Funktion aufrufen und mitzählen
        int counter = 0;
        startup = System.currentTimeMillis();
        while(System.currentTimeMillis() - startup < 250)
            for(int i = 0; i < 100000; i++)
            {
                nonsense = sillyFunction(nonsense);
                counter++;
            }

        loopsPerSecond = 4 * counter;
    }

    private static double sillyFunction(double d)
    {
        return Math.sin(Math.pow(Math.cos(d), d));
    }

    public HeavyMathJob(String string)
    {
        super(string);
        loops = loopsPerSecond * string.length();
    }

    @Override
    public void complete() throws InterruptedException
    {
        double d = 0.5;
        Thread currentThread = Thread.currentThread();
        for(long i = 0; i < loops; i++)
            if(currentThread.isInterrupted())
                throw new InterruptedException();
            else
                d = sillyFunction(d);
    }

}
