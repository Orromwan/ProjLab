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
	boolean AcceptBox(Box b, Direction d)
	{
		//PRINT
		System.out.println(toString()+" - AcceptBox called");
		// Falra sose tolhatunk dobozt.
		return false;
	}

	/**
	 * Munkás fogadása, munkás mezõre lép/mozog.
	 * @param w - a munkás
	 * @param d - irányból érkezik
	 */
	void AcceptWorker(Worker w, Direction d)
	{
		//PRINT
		System.out.println(toString()+" - AcceptWorker called");
		// Munkással nem léphetünk falra.
	}

	/**
	 * Munkás(oka)t rá tolnak a mezõre
	 * @param l - Munkás/Munkások listája akik rákerülnek
	 * @param d - Ebbõl az irányból
	 * @return - Sikeres volt-e a mozgatás
	 */
	boolean AcceptUnwillingWorkers(ArrayList<Worker> l, Direction d)
	{
		//PRINT
		System.out.println(toString()+" - AcceptUnwillingWorkers called");
		for(Worker w : l)
		{
			w.kill();
		}
		System.out.println(toString()+" true");
		return true;
	}
}