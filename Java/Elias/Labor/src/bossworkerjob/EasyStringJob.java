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

/** Job mit einem String, der durch Abwarten bearbeitet wird. 
 * Belastet den Prozessor nicht.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class EasyStringJob implements Job
{
    /** Text, der bearbeitet werden soll. */
    private final String text;

    /** Millisekunden Wartezeit pro Buchstabe. */
    private final int millisPerChar;

    {
        final String secondsString = System.getenv("SECONDS");
        millisPerChar = 1000 * (secondsString == null ? 1 : Integer.parseInt(secondsString));
    }

    /** Erzeugt einen neuen Job.
     * @param text Text, der bearbeitet werden soll. 
     * Darf nicht null oder leer sein.
     */
    public EasyStringJob(String text)
    {
        if(text == null || text.isEmpty())
            throw new IllegalArgumentException("need a non-empty string");
        this.text = text;
    }

    @Override
    public void complete() throws InterruptedException
    {
        Thread.sleep(text.length() * millisPerChar);
    }

    @Override
    public int getWork()
    {
        return text.length();
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + ":" + text;
    }

}
