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

/** Interface für verschiedene Arten von Jobs.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public interface Job
{
    /** Erledigt diesen Job. Das kann eine Weile dauern.
     * @throws InterruptedException Die Arbeit wurde abgebrochen.
     */
    void complete() throws InterruptedException;

    /** Wert der Arbeit, wenn sie erfolgreich erledigt wuurde.
     * @return Wert der Arbeit oder 0, wenn sie nicht angefangen oder unterbrochen wurde.
     */
    int getWork();
    
}
