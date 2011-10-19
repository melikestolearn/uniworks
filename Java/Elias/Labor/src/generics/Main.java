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
package generics;

import static generics.Size.*;

/**
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class Main
{
    /** Hauptprogramm.
     * @param args Kommandozeilenargumente.
     */
    public static void main(String[] args)
    {
        // Aufgabe 1
        System.out.println("----------------------------------------------------------------------\nAufgabe 1");
        Box<Medium> mediumBox;       
        mediumBox = new Box<Medium>();
        mediumBox.add(M);               // o.k.
        mediumBox.add(S);               // auch o.k.
        //mediumBox.add(L);               // unzulässig!
        //mediumBox.add(XL);              // unzulässig!
        System.out.println(mediumBox);

        // Aufgabe 2
        System.out.println("----------------------------------------------------------------------\nAufgabe 2");
        System.out.println(countElements(mediumBox));
        Box<Large> largeBox = new Box<Large>();
        largeBox.add(L);
        //largeBox.add(XL);               // unzulässig!
        largeBox.add(M);
        largeBox.add(M);
        largeBox.add(S);
        largeBox.add(L);
        System.out.println(mediumBox);
        System.out.println(largeBox);
        System.out.println(countElements(largeBox));
        System.out.println(hasLessElements(mediumBox, largeBox));
        System.out.println(hasLessElements(largeBox, mediumBox));

        // Aufgabe 3
        System.out.println("----------------------------------------------------------------------\nAufgabe 3");
        //largeBox.moveInto(mediumBox);   // unzulässig!
        mediumBox.moveInto(largeBox);   // o.k.
        largeBox.moveInto(largeBox);    // o.k.
        System.out.println(mediumBox);
        System.out.println(largeBox);

        // Aufgabe 4
        System.out.println("----------------------------------------------------------------------\nAufgabe 4");
        fill(5, M, mediumBox);
        //fill(4, L, mediumBox);          // unzulässig
        fill(11, S, mediumBox);         // unzulässig
        fill(2, L, largeBox);          
        fill(7, S, largeBox);          
        //fill(0, XL, largeBox);          // unzulässig
        System.out.println(mediumBox);
        System.out.println(largeBox);
        
        // Aufgabe 5
        System.out.println("----------------------------------------------------------------------\nAufgabe 5");
        //mediumBox.copyEveryOther(largeBox);
        largeBox.copyEveryOther(mediumBox);
        mediumBox.copyEveryOther(mediumBox);
        System.out.println(mediumBox);
        System.out.println(largeBox);
        System.out.println(new Box<Large>().copyEveryOther(largeBox).copyEveryOther(mediumBox));
    }
    
    // Aufgabe 2
    static int countElements(Box<?> b)
    {
        int count = 0;
        for(Size box: b) {
        	count++;
        }
        return count;
    }

    static boolean hasLessElements(Box<?> b0, Box<?> b1)
    {
        return countElements(b0)<countElements(b1);
    }

    // Aufgabe 4
    static <T extends Size> void fill(int n, T s, Box<? super T> b)
    {
    	for(int i=0; i<n;i++);
    		b.add(s);
    }
}
