package projlab;

/**
 * A Hybrid mez� oszt�lya, t�rolja a mez� �llapot�t.
 */
public class Hybrid extends Field 
{
	// A hibrid �llapot�t t�rol� v�ltoz�
	private boolean Status = false;


	/**
	 * A hibrid aktiv�l�sa, lyuk lesz.
	 */
	void switchOn()
	{
		//PRINT
		System.out.println(toString() + " - switchOn called");
		Status = true;
	}	

	/**
	 *  A hibrid bez�r�sa, egyszer� mez� lesz.
	 */
	void close()
	{
		//PRINT
		System.out.println(toString() + " - close called");
		Status = false;
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
	 * Munk�s fogad�sa, munk�s mez�re l�p/mozog.
	 * @param w - a munk�s
	 * @param d - ir�nyb�l �rkezik
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
	 * Visszadja az �t reprezent�l� karaktert �llapott�l f�gg�en
	 * @return a visszat�r�si karakter
	 */
	@Override
	String getChar()
	{
		if (Status)
			return " O ";
		else
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
			r+="o";
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
	}

}
