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
	
	// A kapcsol� �llapota
	boolean status;
	
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
		case "BOX":
			
			if(neighbors[d.getDir()].AcceptBox(box, d, w.getStrength()))
			{
				w.UpdateWorker(this); AddWorker(w);
				hybrid.close();
			}				
			break;

		default:
			
			w.UpdateWorker(this); AddWorker(w);
			break;
		}
	}
}
