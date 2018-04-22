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
		System.out.println(toString() + " - AcceptBox called");
		switch(containstate)
		{
		case BOX:
			
			int fr = liquidstate.friction();
			boolean r = str >= fr;
			if(r)
			{
				r = neighbors[d.getDir()].AcceptBox(box, d, str - fr);
				if(r)
				{
					b.UpdateBox(this);
					AddBox(b);
					Game.addPointsToLastWorker(1);
				}
			}
			return r;

		case WORKERS:
			
			AcceptUnwillingWorkers(workers, d);
			b.UpdateBox(this);
			AddBox(b);
			Game.addPointsToLastWorker(1);
			return true;

		default:
			
			b.UpdateBox(this);
			AddBox(b);
			Game.addPointsToLastWorker(1);
			return true;
		}
		

	}
	/**
         * Visszaadja az EndPost reprezentáló karaktert
         * @return - a karakter
         */
        @Override
        String getChar()
        {
            return "!";
        }
}