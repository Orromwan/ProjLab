package projlab;

/**
 * A Switch mez� oszt�lya, figyeli a kapcsol� �llapot�t
 * �s annak f�ggv�ny�ben m�k�dteti a hozz� tartoz� hibrid mez�t.
 */
public class Switch extends Field 
{
	// A kapcsol�hoz tartoz� hybrid mez�
	private Hybrid hybrid;
	
	//A hybrid meu� be�ll�t�s�hoz
	void setHybrid(Hybrid h)
	{
		hybrid=h;
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
					hybrid.switchOn();
				}
			}
			return r;

		case WORKERS:
						
			AcceptUnwillingWorkers(workers, d);
			b.UpdateBox(this); AddBox(b);
			hybrid.switchOn();
			return true;

		default:
			
			b.UpdateBox(this); AddBox(b);
			hybrid.switchOn();
			return true;
		}
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
					hybrid.close();
				}
			}
			break;

		default:
			
			w.UpdateWorker(this); AddWorker(w);
			break;
		}
	}
}
