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
import java.util.List;

/** Implementierung eines Boss.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class FatBoss implements Boss
{
    /** Arbeit, die bisher erledigt ist. */
    private int workdone = 0;

    /** Liste der Jobs, die noch zu bearbeiten sind. */
    private final List<Job> jobs = new ArrayList<Job>();

    /** Erzeugt einen neuen Boss mit den Jobst, die in args geliefert werden.
     * @param args Nimmt jeden String, der nicht mit einer Ziffer beginnt, als Auftrag. 
     * Ignoriert den Rest.
     */
    public FatBoss(String... args)
    {
        for(String arg: args)
            if(!Character.isDigit(arg.charAt(0)))
                jobs.add(new EasyStringJob(arg));
    }

    @Override
    public Job getNextJob()
    {
        // TODO: Nächsten Job vom Boss holen, wenn es noch einen gibt
        Job job = null;
    	if(!jobs.isEmpty())
        	job = jobs.remove(0);
    	return job;
    }

    @Override
    public void go(Collection<Worker> workers, int work2finish)
    {
        // TODO: Arbeiter starten
                // TODO: Warten bis genug Arbeit erledigt ist
        // TODO: Arbeiter stoppen
    	final ArrayList<Thread> workersList = new ArrayList<Thread>();
    	
    	for(Worker w: workers)
    		workersList.add(new Thread(w));
    	
    	for(Thread t: workersList)
    		t.start();
    	
    	synchronized(this) {
    		while(workdone<work2finish) {
    			try {
					wait();
				} catch (InterruptedException e) {}
    		}
    	}
    	
    	
    	for(Thread t: workersList)
    		t.interrupt();
        
    	Main.printf("work completed%n");
    }

    @Override
    public void jobDone(Job job)
    {
        // TODO: Buchführen, dass der Job erledigt ist
    	workdone += job.getWork();
    	//jobs.remove(job);
        Main.printf("%s done (%d): have now %d%n",
                    job,
                    job.getWork(),
                    workdone);
    }

}
