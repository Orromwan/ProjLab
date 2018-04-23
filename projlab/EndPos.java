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
					Game.addPointsToLastWorker(1);
				}
			}
			return r;

		case WORKERS:
			
			acceptUnwillingWorkers(Workers, d);
			b.updateBox(this);
			addBox(b);
			Game.addPointsToLastWorker(1);
			return true;

		default:
			
			b.updateBox(this);
			addBox(b);
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
		switch(Containstate)
		{
		case BOX:
			return "!B";
		case WORKERS:
			return "!W";
		case CLEAR:
			switch(Liquidstate)
			{
			case OIL:
				return "_!";
			case HONEY:
				return ":!";
			case NONE:
				return "!";
			default:
				return "";
			}
		default:
			return "";
		}
	}
}
