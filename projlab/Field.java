package projlab;

import java.util.*;

/**
 * Mezõ osztály, számon tartja a rajta lévõ dobozt,
 * a mezõ szomszédos mezõit, a rajta lévõ munkásokat,
 * és mezõ állapotát.
 */
public class Field  
{
	// A mezõ pozíciója a térképen
	protected int xPos;
	protected int yPos;
	
	// A mezõn lévõ doboz
	protected Box Box;

	// A mezõ szomszédos mezõi
	protected Field[] Neighbors = new Field[4];

	// A mezõn álló munkások listája
	protected ArrayList<Worker> Workers = new ArrayList<Worker>();

	// Mi van a mezõn
	protected FieldStatus Containstate = FieldStatus.CLEAR;

	// Milyen folyadék van a mezõn
	protected Liquid Liquidstate = Liquid.NONE;

	//A pálya építéséhez
	void addNeighbor(Field f, Direction d)
	{
		Neighbors[d.getDir()] = f;
	}

	/**
	 * Vissza adja a megadott irányban lévõ szomszédos mezõt
	 * @param dir - Az irány
	 * @return - A szomszédos mezõ
	 */

	Field getNeighbor(Direction dir)
	{
		//PRINT
		System.out.println(toString() + " - getNeighbor called");
		return Neighbors[dir.getDir()];
	}

	//Munkás hozzáadása, update
	void addWorker(Worker w)
	{
		Workers.add(w);
		Containstate = FieldStatus.WORKERS;
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
		switch(Containstate)
		{
		case BOX:
			int str = w.getStrength();
			int fr = Liquidstate.friction();
			boolean r = (str >= fr) && (Neighbors[d.getDir()].acceptBox(Box, d, str));
			if(r)
			{
				w.updateWorker(this);
				addWorker(w);
			}
			return r;

		default:
			w.updateWorker(this);
			addWorker(w);
			return true;
		}
	}

	//Doboz hozzáadása, update
	void addBox(Box b)
	{
		this.Box = b;
		Containstate = FieldStatus.BOX;
	}

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
		switch(Containstate)
		{
		case BOX:
			int fr = Liquidstate.friction();
			boolean r = (str >= fr) && (Neighbors[d.getDir()].acceptBox(Box, d, str - fr));
			if(r)
			{		
				b.updateBox(this);
				addBox(b);
			}
			return r;

		case WORKERS:

			Neighbors[d.getDir()].acceptUnwillingWorkers(Workers, d);
			b.updateBox(this);
			addBox(b);
			return true;

		default:

			b.updateBox(this);
			addBox(b);
			return true;
		}
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
		switch(Containstate)
		{
		case BOX:

			for(Worker w : l)
				w.kill();
			break;

		default:

			for(Worker w : l)
				acceptWorker(w, d);
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
		System.out.println(toString() + " - removeBox called");
		Containstate = FieldStatus.CLEAR;
	}

	/**
	 * Munkás leszedése a mezõrõl.
	 * @param w - A munkás
	 */
	void removeWorker(Worker w)
	{
		//PRINT
		System.out.println(toString() + " - removeWorker called");
		Workers.remove(w);
		if(Workers.isEmpty())
			Containstate = FieldStatus.CLEAR;
	}

	/**
	 * Méz öntése a mezõre
	 */
	void pourHoney()
	{
		System.out.println(toString() + " - pourHoney called");
		Liquidstate = Liquid.HONEY;
	}

	/**
	 * Olaj öntése a mezõre
	 */
	void pourOil()
	{
		System.out.println(toString() + " - pourOil called");
		Liquidstate = Liquid.OIL;
	}
	/**
         * Visszaadja a mezõt reprezentáló karaktert, állapottól függõen
         * @return - a karakter amit visszaad
         */
	String getChar()
	{
		switch(Containstate)
		{
		case BOX:
			return "B";
		case WORKERS:
			return "W";
		case CLEAR:
			switch(Liquidstate)
			{
			case OIL:
				return "_";
			case HONEY:
				return ":";
			case NONE:
				return ".";
			default:
				return "";
			}
		default:
			return "";
		}
	}
	public void setXPos(int x)
	{
		xPos = x;
	}
	public void setYPos(int y)
	{
		yPos = y;
	}
	public int getXPos()
	{
		return xPos;
	}
	public int getYPos()
	{
		return yPos;
	}
}
