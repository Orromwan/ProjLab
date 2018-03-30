package projlab;

/**
 * V�g pozici� oszt�ly, erre a mez�re dobozt tolva a munk�s
 * pontot kap.
 */
public class EndPos extends Field 
{
	
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
			return true;

		default:
			
			b.UpdateBox(this); AddBox(b);
			Game.addPointsToLastWorker(1);
			return true;
		}
		

	}
}
