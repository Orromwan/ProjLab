package projlab;

/**
 * A Hybrid mezõ osztálya, tárolja a mezõ állapotát.
 */
public class Hybrid extends Field 
{
	// A hibrid állapotát tároló változó
	private boolean Status = false;


	/**
	 * A hibrid aktiválása, lyuk lesz.
	 */
	void switchOn()
	{
		//PRINT
		System.out.println(toString() + " - switchOn called");
		Status = true;
	}	

	/**
	 *  A hibrid bezárása, egyszerû mezõ lesz.
	 */
	void close()
	{
		//PRINT
		System.out.println(toString() + " - close called");
		Status = false;
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
		if(Status)
		{
			b.KillBox();
		}
		else
		{
			switch(Containstate)
			{
			case BOX:
				
				int fr = Liquidstate.friction();
				boolean r = str >= fr;
				if(r)
				{
					r = Neighbors[d.getDir()].acceptBox(Box, d, str - fr);
					if(r)
					{
						b.updateBox(this);
						addBox(b);
					}
				}
				return r;

			case WORKERS:

				acceptUnwillingWorkers(Workers, d);
				b.updateBox(this);
				addBox(b);
				return true;

			default:

				b.updateBox(this);
				addBox(b);
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
	boolean acceptWorker(Worker w, Direction d)
	{
		//PRINT
		System.out.println(toString() + " - acceptWorker called");
		if(Status)
		{
			w.kill();
			return false;
		}
		else
		{
			switch(Containstate)
			{
			case BOX:
				
				int str = w.getStrength();
				int fr = Liquidstate.friction();
				boolean r = str >= fr;
				if(r)
				{
					if(Neighbors[d.getDir()].acceptBox(Box, d, str))
					{
						w.updateWorker(this);
						addWorker(w);
						return true;
					}
					else return false;
				}
				else return false;

			default:
				w.updateWorker(this);
				addWorker(w);
				return true;
			}
		}
	}
	/**
         * Visszadja az õt reprezentáló karaktert állapottól függõen
         * @return a visszatérési karakter
         */
        @Override
        String getChar()
        {
            if (Status)
                return "O";
            else
                return "o";
        }
}
