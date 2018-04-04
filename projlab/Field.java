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
	protected FieldStatus containstate=FieldStatus.CLEAR;

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
		containstate=FieldStatus.WORKERS;
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
		case BOX:
			int str=w.getStrength();
			int fr = liquidstate.friction();
			boolean r = str>=fr;
			if(r)
			{
				if(neighbors[d.getDir()].AcceptBox(box, d, str))
				{
					w.UpdateWorker(this); AddWorker(w);
				}
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
		containstate=FieldStatus.BOX;
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
		case BOX:
			int fr = liquidstate.friction();
			boolean r = str>=fr;
			if(r)
			{
				r = neighbors[d.getDir()].AcceptBox(box, d, str-fr);
				if(r)
				{
					b.UpdateBox(this); AddBox(b);
				}
			}
			return r;

		case WORKERS:

			neighbors[d.getDir()].AcceptUnwillingWorkers(workers, d);
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
	void AcceptUnwillingWorkers(ArrayList<Worker> l, Direction d)
	{
		//PRINT
		System.out.println(toString()+" - AcceptUnwillingWorkers called");
		switch(containstate)
		{
		case BOX:

			for(Worker w : l)
				w.kill();
			break;

		default:

			for(Worker w : l)
				AcceptWorker(w, d);
			break;
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
		containstate=FieldStatus.CLEAR;
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
			containstate=FieldStatus.CLEAR;
	}

	/**
	 * Méz öntése a mezõre
	 */
	void pourHoney()
	{
		System.out.println(toString()+" - pourHoney called");
		liquidstate=Liquid.HONEY;
	}

	/**
	 * Olaj öntése a mezõre
	 */
	void pourOil()
	{
		System.out.println(toString()+" - pourOil called");
		liquidstate=Liquid.OIL;
	}
}
