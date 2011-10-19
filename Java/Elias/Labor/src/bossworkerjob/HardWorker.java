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

/** Ein einfacher Arbeiter :-)
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class HardWorker implements Worker
{
    /** Der Boss dieses Arbeiters. */
    private final Boss boss;

    /** Neuer Arbeiter. 
     * @param boss Boss, darf nicht null sein.
     */
    public HardWorker(Boss boss)
    {
        if(boss == null)
            throw new NullPointerException("Boss must exist");
        this.boss = boss;
    }

    @Override
    public void run()
    {
        // Job vom Boss holen
        Job job = null;
        synchronized(boss) {
        	job = boss.getNextJob();
        }
        final Thread thisThread = Thread.currentThread();
        try                                                                     
        {       
        	while(job!=null && !thisThread.isInterrupted()) {
                // Job bearbeiten
                Main.printf("working on: %s%n", job);                          
                job.complete();
                Main.printf("finished: %s%n", job);    
                // Job als erledigt melden
                // TODO: Neuen Job holen und erledigen, bis es keinen mehr gibt oder ein Interrupt kommt
                synchronized(boss) {
                	boss.jobDone(job);
                	boss.notify();
                	job = boss.getNextJob();
                }
        	}
        }                                                                       
        catch(InterruptedException ex)                                          
        {
        	thisThread.interrupt();
        }                                                                      
            // Ende
            Main.printf("done%n");
    }

}
