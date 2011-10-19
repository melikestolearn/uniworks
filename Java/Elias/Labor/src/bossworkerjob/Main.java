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

import java.util.ArrayList;
import java.util.Collection;

/** Starterklasse für Boss, Worker, Jobs.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class Main
{
    private static final Long startup = System.currentTimeMillis();

    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     * 1. Argument = Anzahl Worker.
     * 2. Argument = Summe der Arbeit, die zu verrichten ist.
     * Rest: Jobs, die abgearbeitet werden.
     */
    public static void main(String[] args)
    {
        System.out.printf("Processors: %d%n", Runtime.getRuntime().availableProcessors());
        
        int numWorkers = Integer.parseInt(args[0]);
        int totalworth = Integer.parseInt(args[1]);
        Boss boss = new FatBoss(args);
        Collection<Worker> workers = new ArrayList<Worker>();
        for(int w = 0; w < numWorkers; w++)
            workers.add(new HardWorker(boss));
        boss.go(workers, totalworth);
    }

    /** Ersatz für System.out.printf.
     * Packt Zeitstempel, Thread-Id, Aufruferklasse, Aufrufermethode an den Anfang
     * jeder Zeile.
     * @param fmt Formatstring.
     * @param args Argumente zum Formatstring.
     * @see System.out.printf
     */
    public static void printf(String fmt, Object... args)
    {
            StackTraceElement traceElement = new Throwable().getStackTrace()[1];
            System.out.printf("%05d %s[%s.%s] ",
                              System.currentTimeMillis() - startup,
                              Thread.currentThread().getName(),
                              traceElement.getClassName(),
                              traceElement.getMethodName());
            System.out.printf(fmt, args);
    }

}
