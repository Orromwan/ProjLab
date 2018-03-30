package projlab;

import java.util.*;

/**
 * Mez� oszt�ly, sz�mon tartja a rajta l�v� dobozt,
 * a mez� szomsz�dos mez�it, a rajta l�v� munk�sokat,
 * �s mez� �llapot�t.
 */
public class Field 
{
	// A mez�n l�v� doboz
	protected Box box;

	// A mez� szomsz�dos mez�i
	protected Field[] neighbors = new Field[4];

	// A mez�n �ll� munk�sok list�ja
	protected ArrayList<Worker> workers = new ArrayList<Worker>();

	// Mi van a mez�n
	protected String containstate="CLEAR";
	
	// Milyen folyad�k van a mez�n
	protected Liquid liquidstate=Liquid.NONE;
	
	//A p�lya �p�t�s�hez
	void addNeighbor(Field f, Direction d)
	{
		neighbors[d.getDir()]=f;
	}

	/**
	 * Vissza adja a megadott ir�nyban l�v� szomsz�dos mez�t
	 * @param dir - Az ir�ny
	 * @return - A szomsz�dos mez�
	 */

	Field getNeighbor(Direction dir)
	{
		//PRINT
		System.out.println(toString()+" - getNeighbor called");
		return neighbors[dir.getDir()];
	}

	//Munk�s hozz�ad�sa, update
	void AddWorker(Worker w)
	{
		workers.add(w);
		containstate="WORKER";
	}

	/**
	 * Munk�s fogad�sa, munk�s mez�re l�p/mozog.
	 * @param w - a munk�s
	 * @param d - ir�nyb�l �rkezik
	 */
	void AcceptWorker(Worker w, Direction d)
	{
		//PRINT
		System.out.println(toString()+" - AcceptWorker called");
		switch(containstate)
		{
		case "BOX":

			if(neighbors[d.getDir()].AcceptBox(box, d, w.getStrength()))
			{
				w.UpdateWorker(this); AddWorker(w);
			}				
			break;

		default:

			w.UpdateWorker(this); AddWorker(w);
			break;
		}
	}

	//Doboz hozz�ad�sa, update
	void AddBox(Box b)
	{
		this.box=b;
		containstate="BOX";
	}

	/**
	 * Doboz fogad�sa, doboz erre a mez�re ker�l.
	 * @param b - A doboz
	 * @param d - Ebb�l az ir�nyb�l
	 * @return - sikeres volt-e a doboz fogad�sa
	 */
	boolean AcceptBox(Box b, Direction d, int str)
	{
		//PRINT
		System.out.println(toString()+" - AcceptBox called");
		switch(containstate)
		{
		case "BOX":

			boolean r = neighbors[d.getDir()].AcceptBox(box, d, str-liquidstate.friction()) || str>=liquidstate.friction();
			if(r)
			{
				b.UpdateBox(this); AddBox(b);
			}
			return r;

		case "WORKER":

			neighbors[d.getDir()].AcceptUnwillingWorkers(workers, d);
			workers.clear();
			b.UpdateBox(this); AddBox(b);
			return true;

		default:

			b.UpdateBox(this); AddBox(b);
			return true;
		}
	}

	/**
	 * Munk�s(oka)t r� tolnak a mez�re
	 * @param l - Munk�s/Munk�sok list�ja akik r�ker�lnek
	 * @param d - Ebb�l az ir�nyb�l
	 * @return - Sikeres volt-e a mozgat�s
	 */
	boolean AcceptUnwillingWorkers(ArrayList<Worker> l, Direction d)
	{
		//PRINT
		System.out.println(toString()+" - AcceptUnwillingWorkers called");
		switch(containstate)
		{
		case "BOX":

			for(Worker w : l)
				w.kill();
			return true;
								
		default:

			for(Worker w : l)
				AcceptWorker(w, d);
			return true;
		}
	}

	/**
	 * Doboz leszed�se a mez�r�l.
	 * @param b - A doboz
	 */
	void removeBox(Box b)
	{
		//PRINT
		System.out.println(toString()+" - removeBox called");
		containstate="CLEAR";
	}

	/**
	 * Munk�s leszed�se a mez�r�l.
	 * @param w - A munk�s
	 */
	void removeWorker(Worker w)
	{
		//PRINT
		System.out.println(toString()+" - removeWorker called");
		workers.remove(w);
		if(workers.isEmpty())
			containstate="CLEAR";
	}
	
	/**
	 * M�z �nt�se a mez�re
	 */
	void pourHoney()
	{
		liquidstate=Liquid.HONEY;
	}
	
	/**
	 * Olaj �nt�se a mez�re
	 */
	void pourOil()
	{
		liquidstate=Liquid.OIL;
	}
}
