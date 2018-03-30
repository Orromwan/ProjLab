package projlab;

import java.util.*;

/**
 * Mezõ osztály, számon tartja a rajta lévõ dobozt,
 * a mezõ szomszédos mezõit, a rajta lévõ munkásokat,
 * és mezõ állapotát.
 */
public class Field 
{
	// A mezõn lévõ doboz
	protected Box box;

	// A mezõ szomszédos mezõi
	protected Field[] neighbors = new Field[4];

	// A mezõn álló munkások listája
	protected ArrayList<Worker> workers = new ArrayList<Worker>();

	// Mi van a mezõn
	protected String containstate="CLEAR";
	
	// Milyen folyadék van a mezõn
	protected Liquid liquidstate=Liquid.NONE;
	
	//A pálya építéséhez
	void addNeighbor(Field f, Direction d)
	{
		neighbors[d.getDir()]=f;
	}

	/**
	 * Vissza adja a megadott irányban lévõ szomszédos mezõt
	 * @param dir - Az irány
	 * @return - A szomszédos mezõ
	 */

	Field getNeighbor(Direction dir)
	{
		//PRINT
		System.out.println(toString()+" - getNeighbor called");
		return neighbors[dir.getDir()];
	}

	//Munkás hozzáadása, update
	void AddWorker(Worker w)
	{
		workers.add(w);
		containstate="WORKER";
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

	//Doboz hozzáadása, update
	void AddBox(Box b)
	{
		this.box=b;
		containstate="BOX";
	}

	/**
	 * Doboz fogadása, doboz erre a mezõre kerül.
	 * @param b - A doboz
	 * @param d - Ebbõl az irányból
	 * @return - sikeres volt-e a doboz fogadása
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
	 * Munkás(oka)t rá tolnak a mezõre
	 * @param l - Munkás/Munkások listája akik rákerülnek
	 * @param d - Ebbõl az irányból
	 * @return - Sikeres volt-e a mozgatás
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
	 * Doboz leszedése a mezõrõl.
	 * @param b - A doboz
	 */
	void removeBox(Box b)
	{
		//PRINT
		System.out.println(toString()+" - removeBox called");
		containstate="CLEAR";
	}

	/**
	 * Munkás leszedése a mezõrõl.
	 * @param w - A munkás
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
	 * Méz öntése a mezõre
	 */
	void pourHoney()
	{
		liquidstate=Liquid.HONEY;
	}
	
	/**
	 * Olaj öntése a mezõre
	 */
	void pourOil()
	{
		liquidstate=Liquid.OIL;
	}
}
