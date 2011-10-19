/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 *  _____ _                        _     
 * |_   _| |__  _ __ ___  __ _  __| |___ 
 *   | | | '_ \| '__/ _ \/ _` |/ _` / __|
 *   | | | | | | | |  __/ (_| | (_| \__ \
 *   |_| |_| |_|_|  \___|\__,_|\__,_|___/
 *                                       
 * Oracle Corporation Java 1.7.0-ea, Linux i386 2.6.32.21
 * lilli (Intel CPU U7300/1300 MHz, 2 Cores, 4096 MB RAM)
 */
package threads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Hauptprogramm. 
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class Main
{
    /** Starter für Pingpong-Threads.
     * Wartet auf Tastatureingaben.
     * @param args Kommandozeilenargumente, werden ignoriert.
     * @throws IOException Tastaturfehler (?)
     */
    public static void main(String[] args) throws IOException
    {
    	List<Thread> pingPongs = new ArrayList<Thread>();
    	Clock cl = new Clock();
    	cl.setDaemon(true);
    	cl.start();
        boolean loop = true;
        while(loop) {        		
            switch(System.in.read())
            {
                case 'x':
                    loop = false;
                    break;
                case 'p':
                	PingPong p = new PingPong(pingPongs);
                	synchronized(pingPongs) {
                		pingPongs.add(p);
                    	p.start();
                	}
                	break;
                case 'i':
                	for(Thread ping: pingPongs) {
                		synchronized(pingPongs) {
                			ping.interrupt();
                		}
                	}
                	break;
            }
        }
    }
}
