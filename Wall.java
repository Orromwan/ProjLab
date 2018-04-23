package projlab;

import java.util.*;

/**
 * A Wall mezõ osztálya
 */
public class Wall extends Field 
{

	/**
	 * Doboz fogadása, doboz erre a mezõre kerül.
	 * @param b - A doboz
	 * @param d - Ebbõl az irányból
	 * @return - sikeres volt-e a doboz fogadása
	 */
	boolean acceptBox(Box b, Direction d, int str)
	{
		//PRINT
		System.out.println(toString() + " - acceptBox called");
		// Falra sose tolhatunk dobozt.
		return false;
	}

	/**
	 * Munkás fogadása, munkás mezõre lép/mozog.
	 * @param w - a munkás
	 * @param d - irányból érkezik
	 */
	boolean acceptWorker(Worker w, Direction d)
	{
		//PRINT
		System.out.println(toString() + " - acceptWorker called");
		// Munkással nem léphetünk falra.
		return false;
	}

	/**
	 * Munkás(oka)t rá tolnak a mezõre
	 * @param l - Munkás/Munkások listája akik rákerülnek
	 * @param d - Ebbõl az irányból
	 * @return - Sikeres volt-e a mozgatás
	 */
	void acceptUnwillingWorkers(ArrayList<Worker> l, Direction d)
	{
		//PRINT
		System.out.println(toString() + " - acceptUnwillingWorkers called");
		for(Worker w : l)
		{
			w.kill();
		}
	}
	/**
     * Visszadja az õt reprezentáló karaktert
     * @return a visszatérési karakter
     */
    @Override
    String getChar()
    {
        return "X";
    }
}