package projlab;

/**
 * A Hybrid mezõ osztálya, tárolja a mezõ állapotát.
 */
public class Hybrid extends Field 
{
	// A hibrid állapotát tároló változó
	private boolean status=false;


	/**
	 * A hibird aktiválása, lyuk lesz.
	 */
	void switchOn()
	{
		//PRINT
		System.out.println(toString()+" - switchOn called");
		status=true;
	}	

	/**
	 *  A hibrid bezárása, egyszerû mezõ lesz.
	 */
	void close()
	{
		//PRINT
		System.out.println(toString()+" - close called");
		status=false;
	}

	/**
	 * Vissza adja a hibrid állapotát.
	 * @return - A hibrid állapota
	 */
	boolean getStatus()
	{
		//PRINT
		System.out.println(toString()+" - getStatus called");
		return status;
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
	 * Munkás fogadása, munkás mezõre lép/mozog.
	 * @param w - a munkás
	 * @param d - irányból érkezik
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
