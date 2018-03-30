package projlab;

/**
 * Vég pozició osztály, erre a mezõre dobozt tolva a munkás
 * pontot kap.
 */
public class EndPos extends Field 
{
	
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
			Game.addPointsToLastWorker(1);
			return true;
		}
		

	}
}
