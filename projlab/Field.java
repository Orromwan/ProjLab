package projlab;

import java.util.*;

/**
 * Mez� oszt�ly, sz�mon tartja a rajta l�v� dobozt,
 * a mez� szomsz�dos mez�it, a rajta l�v� munk�sokat,
 * �s mez� �llapot�t.
 */
public class Field  
{
	// A mez� poz�ci�ja a t�rk�pen
	protected int xPos;
	protected int yPos;
	
	// A mez�n l�v� doboz
	protected Box Box;

	// A mez� szomsz�dos mez�i
	protected Field[] Neighbors = new Field[4];

	// A mez�n �ll� munk�sok list�ja
	protected ArrayList<Worker> Workers = new ArrayList<Worker>();

	// Mi van a mez�n
	protected FieldStatus Containstate = FieldStatus.CLEAR;

	// Milyen folyad�k van a mez�n
	protected Liquid Liquidstate = Liquid.NONE;

	//A p�lya �p�t�s�hez
	void addNeighbor(Field f, Direction d)
	{
		Neighbors[d.getDir()] = f;
	}

	/**
	 * Vissza adja a megadott ir�nyban l�v� szomsz�dos mez�t
	 * @param dir - Az ir�ny
	 * @return - A szomsz�dos mez�
	 */

	Field getNeighbor(Direction dir)
	{
		//PRINT
		System.out.println(toString() + " - getNeighbor called");
		return Neighbors[dir.getDir()];
	}

	//Munk�s hozz�ad�sa, update
	void addWorker(Worker w)
	{
		Workers.add(w);
		Containstate = FieldStatus.WORKERS;
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

	//Doboz hozz�ad�sa, update
	void addBox(Box b)
	{
		this.Box = b;
		Containstate = FieldStatus.BOX;
	}

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
	 * Munk�s(oka)t r� tolnak a mez�re
	 * @param l - Munk�s/Munk�sok list�ja akik r�ker�lnek
	 * @param d - Ebb�l az ir�nyb�l
	 * @return - Sikeres volt-e a mozgat�s
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
	 * Doboz leszed�se a mez�r�l.
	 * @param b - A doboz
	 */
	void removeBox(Box b)
	{
		//PRINT
		System.out.println(toString() + " - removeBox called");
		Containstate = FieldStatus.CLEAR;
	}

	/**
	 * Munk�s leszed�se a mez�r�l.
	 * @param w - A munk�s
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
	 * M�z �nt�se a mez�re
	 */
	void pourHoney()
	{
		System.out.println(toString() + " - pourHoney called");
		Liquidstate = Liquid.HONEY;
	}

	/**
	 * Olaj �nt�se a mez�re
	 */
	void pourOil()
	{
		System.out.println(toString() + " - pourOil called");
		Liquidstate = Liquid.OIL;
	}
	/**
         * Visszaadja a mez�t reprezent�l� karaktert, �llapott�l f�gg�en
         * @return - a karakter amit visszaad
         */
	String getChar()
	{
		String r=new String();
		switch(Liquidstate)
		{
		case OIL:
			r+="_";
			break;
		case HONEY:
			r+=":";
			break;
		default:
			r+=" ";
			break;
		}
		r+=".";
		switch(Containstate)
		{
		case BOX:
			r+="B";
			break;
		case WORKERS:
			r+="W";
			break;			
		default:
			r+=" ";
			break;
		}
		return r;
	}
/*	public void setXPos(int x)
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
	}*/
}
