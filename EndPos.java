package projlab;

/**
 * Vég pozició osztály, erre a mezőre dobozt tolva a munkás
 * pontot kap.
 */
public class EndPos extends Field 
{

	/**
	 * Doboz fogadása, doboz erre a mezőre kerül.
	 * @param b - A doboz
	 * @param d - Ebből az irányból
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
	 * Visszaadja az EndPost reprezentáló karaktert
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
