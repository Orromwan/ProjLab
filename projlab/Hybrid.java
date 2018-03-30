package projlab;

/**
 * A Hybrid mez� oszt�lya, t�rolja a mez� �llapot�t.
 */
public class Hybrid extends Field 
{
	// A hibrid �llapot�t t�rol� v�ltoz�
	private boolean status=false;


	/**
	 * A hibird aktiv�l�sa, lyuk lesz.
	 */
	void switchOn()
	{
		//PRINT
		System.out.println(toString()+" - switchOn called");
		status=true;
	}	

	/**
	 *  A hibrid bez�r�sa, egyszer� mez� lesz.
	 */
	void close()
	{
		//PRINT
		System.out.println(toString()+" - close called");
		status=false;
	}

	/**
	 * Vissza adja a hibrid �llapot�t.
	 * @return - A hibrid �llapota
	 */
	boolean getStatus()
	{
		//PRINT
		System.out.println(toString()+" - getStatus called");
		return status;
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
		if(status)
		{
			b.KillBox();
		}
		else
		{
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

				AcceptUnwillingWorkers(workers, d);
				workers.clear();
				b.UpdateBox(this); AddBox(b);
				return true;

			default:

				b.UpdateBox(this); AddBox(b);
				return true;
			}
		}
		return true;
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
		if(status)
		{
			w.kill();
		}
		else
		{
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
	}
}
