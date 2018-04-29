package projlab;

import java.util.*;

/**
 * A Wall mez� oszt�lya
 */
public class Wall extends Field 
{

	/**
	 * Doboz fogad�sa, doboz erre a mez�re ker�l.
	 * @param b - A doboz
	 * @param d - Ebb�l az ir�nyb�l
	 * @return - sikeres volt-e a doboz fogad�sa
	 */
	boolean acceptBox(Box b, Direction d, int str)
	{
		//PRINT
		System.out.println(toString() + " - acceptBox called");
		// Falra sose tolhatunk dobozt.
		return false;
	}

	/**
	 * Munk�s fogad�sa, munk�s mez�re l�p/mozog.
	 * @param w - a munk�s
	 * @param d - ir�nyb�l �rkezik
	 */
	boolean acceptWorker(Worker w, Direction d)
	{
		//PRINT
		System.out.println(toString() + " - acceptWorker called");
		// Munk�ssal nem l�phet�nk falra.
		return false;
	}

	/**
	 * Munk�s(oka)t r� tolnak a mez�re
	 * @param l - Munk�s/Munk�sok list�ja akik r�ker�lnek
	 * @param d - Ebb�l az ir�nyb�l
	 * @return - Sikeres volt-e a mozgat�s
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
     * Visszadja az �t reprezent�l� karaktert
     * @return a visszat�r�si karakter
     */
    @Override
    String getChar()
    {
        return " X ";
    }
}