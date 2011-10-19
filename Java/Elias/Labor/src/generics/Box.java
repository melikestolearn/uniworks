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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Ein Behälter für Size-Objekte, ohne null-Werte.
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version $Id$
 */
public class Box<T extends Size> implements Iterable<T>
{
    /** Liste mit den Size-Objekten. */
    private final List<T> items = new ArrayList<T>();

    /** Fügt ein weiteres Objekt an.
     * @param size Weiteres Size-Objekt. Darf nicht null sein.
     * @exception NullPointerException item ist null.
     */
    public void add(T size)
    {
        if(size == null)
            throw new NullPointerException();
        items.add(size);
    }

    /** Liefert einen Iterator über den Inhalt der Box.
     * @return Iterator der Size-Objekte. Nicht null.
     */
    @Override
    public Iterator<T> iterator()
    {
        return items.iterator();
    }

    /** Löscht den Inhalt dieser Box. Anschließend ist sie leer.
     */
    public void clear()
    {
        items.clear();
    }

    /** {@inheritDoc } */
    @Override
    public String toString()
    {
        return "Box" + items;
    }

    /** Überträgt den Inhalt dieser Box in die andere Box that.
     * @param that Andere Box. Darf nicht null sein.
     */
    public void moveInto(Box<? super T> that)
    {
    	if(this.equals(that))
    		return;
    	for(T b: this)
    		that.add(b);
    	this.clear();
    }

    /** Kopiert jedes zweite Element aus der anderen Box that in diese Box.
     * @param that Andere Box. Darf nicht null sein.
     * @return Diese Box.
     */
    public Box<T> copyEveryOther(Box<? extends T> that)
    {
    	if(that.equals(this))
    		return this;
    	boolean flag = true;
    	for(T s: that) {
    		if(flag)
    			add(s);
    		flag = !flag;
    	}
        return this;
    }
}
