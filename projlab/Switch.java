package projlab;

/**
 * A Switch mez� oszt�lya, figyeli a kapcsol� �llapot�t
 * �s annak f�ggv�ny�ben m�k�dteti a hozz� tartoz� hibrid mez�t.
 */
public class Switch extends Field 
{
	// A kapcsol�hoz tartoz� hybrid mez�
	private Hybrid Hybrid;
	
	//A hybrid mez� be�ll�t�s�hoz
	void setHybrid(Hybrid h)
	{
		Hybrid = h;
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
			boolean r = str >= fr;
			if(r)
			{
				r = Neighbors[d.getDir()].acceptBox(Box, d, str - fr);
				if(r)
				{
					b.updateBox(this);
					addBox(b);
					Hybrid.switchOn();
				}
			}
			return r;

		case WORKERS:
						
			acceptUnwillingWorkers(Workers, d);
			b.updateBox(this);
			addBox(b);
			Hybrid.switchOn();
			return true;

		default:
			
			b.updateBox(this);
			addBox(b);
			Hybrid.switchOn();
			return true;
		}
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
			boolean r = str >= fr;
			if(r)
			{
				if(Neighbors[d.getDir()].acceptBox(Box, d, str))
				{
					w.updateWorker(this);
					addWorker(w);
					Hybrid.close();
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
	/**
     * Visszaadja a switchet reprezent�l� karaktert 
     * @return - a karakter
     */
    @Override
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
		r+="L";
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
