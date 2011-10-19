package generics;

/* (C) 2011, R. Schiedermeier, rs@cs.hm.edu
 *   ____                      _          
 *  / ___| ___ _ __   ___ _ __(_) ___ ___ 
 * | |  _ / _ \ '_ \ / _ \ '__| |/ __/ __|
 * | |_| |  __/ | | |  __/ |  | | (__\__ \
 *  \____|\___|_| |_|\___|_|  |_|\___|___/
 *                                        
 * Oracle Corporation Java 1.7.0-ea, Linux i386 2.6.32.41
 * tura (Intel Atom CPU N270/1600 MHz, 2 Cores, 2048 MB RAM)
 */

/** Interface mit vier Typen für verschiedene Größen und jeweils einem Objekt
 * jeder Größe.
 * Alle Elemente sind implizit static, die Variablen auch final.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public interface Size
{
    class Xlarge implements Size
    {
        @Override
        public String toString()
        {
            return getClass().getSimpleName();
        }

    }

    Xlarge XL = new Xlarge();

    class Large extends Xlarge
    {
    }

    Large L = new Large();

    class Medium extends Large
    {
    }

    Medium M = new Medium();

    class Small extends Medium
    {
    }

    Small S = new Small();

}
