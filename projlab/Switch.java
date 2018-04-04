package projlab;

/**
 * A Switch mezõ osztálya, figyeli a kapcsoló állapotát
 * és annak függvényében mûködteti a hozzá tartozó hibrid mezõt.
 */
public class Switch extends Field 
{
	// A kapcsolóhoz tartozó hybrid mezõ
	private Hybrid hybrid;
	
	//A hybrid meuõ beállításához
	void setHybrid(Hybrid h)
	{
		hybrid=h;
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
	 * Munkás fogadása, munkás mezõre lép/mozog.
	 * @param w - a munkás
	 * @param d - irányból érkezik
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
