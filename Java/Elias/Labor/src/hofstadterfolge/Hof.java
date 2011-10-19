/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 *  ____                          _             
 * |  _ \ ___  ___ _   _ _ __ ___(_) ___  _ __  
 * | |_) / _ \/ __| | | | '__/ __| |/ _ \| '_ \ 
 * |  _ <  __/ (__| |_| | |  \__ \ | (_) | | | |
 * |_| \_\___|\___|\__,_|_|  |___/_|\___/|_| |_|
 *                                             
 * Oracle Corporation Java 1.7.0-ea, Linux i386 2.6.32.10
 * violet (Intel Core2 CPU 6600/2400 MHz, 2 Cores, 3328 MB RAM)
 */
package hofstadterfolge;

/** Gemeinsames Interface verschiedener Implementierungen der Hofstadter-Funktion.
 * Siehe: Douglas R. Hofstadter: Gödel, Escher, Bach; KlettCotta, 1985, S.149
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 */
public interface Hof
{
    /** Anzahl Aufrufe von hof seit Erzeugen dieses Objektes.
     * @return Anzahl Aufrufe.
     * Nicht negativ.
     */
    int getCalls();

    /** Anzahl Millisekunde seit Erzeugen dieses Objektes.
     * @return Anzahl Millisekunden.
     * Nicht negativ.
     */
    long getMillis();

    /** Berechnet den Funktionswert Q(n) der Hofstadter-Funktion.
     * @param n Argument. Muss wenigstens 1 sein.
     * @return Q(n).
     */
    int Q(int n);

}
