package projlab;

/**
 * A Switch mezõ osztálya, figyeli a kapcsoló állapotát
 * és annak függvényében mûködteti a hozzá tartozó hibrid mezõt.
 */
public class Switch extends Field 
{
	// A kapcsolóhoz tartozó hybrid mezõ
	private Hybrid Hybrid;
	
	//A hybrid mezõ beállításához
	void setHybrid(Hybrid h)
	{
		Hybrid = h;
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
	 * Munkás fogadása, munkás mezõre lép/mozog.
	 * @param w - a munkás
	 * @param d - irányból érkezik
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
     * Visszaadja a switchet reprezentáló karaktert 
     * @return - a karakter
     */
    @Override
    String getChar()
    {
        return "L";
    }
}
