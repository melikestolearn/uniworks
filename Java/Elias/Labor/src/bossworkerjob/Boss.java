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

import java.util.Collection;

/** Schnittstelle aller Bosse.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public interface Boss
{
    /** Liefert neue Arbeit, wenn es noch welche gibt.
     * @return Arbeit oder null, wenn es keine mehr gibt.
     */
    Job getNextJob();

    /** Startet den Boss, der jetzt die Arbeit erledigen lässt.
     * @param workers Sammlung von Arbeitern, die alle eingespannt werden können.
     * @param work2finish Gesamtwert der Arbeit, die der Boss verrichten lässt,
     * bevor er die Arbeiter stoppt.
     */
    void go(Collection<Worker> workers, int work2finish);

    /** Arbeiter rufen diese Methode auf, wenn sie eine Arbeit erledigt haben.
     * @param job Erledigte Arbeit.
     * Darf nicht null sein.
     */
    void jobDone(Job job);

}
