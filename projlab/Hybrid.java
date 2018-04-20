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

				AcceptUnwillingWorkers(workers, d);
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
	}
	/**
         * Visszadja az �t reprezent�l� karaktert �llapott�l f�gg�en
         * @return a visszat�r�si karakter
         */
        @Override
        String getChar()
        {
            if (status)
                return "O";
            else
                return "o";
        }
}
