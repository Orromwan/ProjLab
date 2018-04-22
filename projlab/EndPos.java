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
         * Visszaadja az EndPost reprezent�l� karaktert
         * @return - a karakter
         */
        @Override
        String getChar()
        {
            return "!";
        }
}