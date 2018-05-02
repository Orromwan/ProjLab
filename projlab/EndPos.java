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

			return false;

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

	boolean acceptWorker(Worker w, Direction d)
	{
		//PRINT
		System.out.println(toString() + " - acceptWorker called");
		switch(Containstate)
		{
		case BOX:

			return false;

		default:
			w.updateWorker(this);
			addWorker(w);
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
		String r=new String();
		switch(Liquidstate)
		{
		case OIL:
			r+="_";
			break;
		case HONEY:
			r+=":";
			break;
		default:
			r+=" ";
			break;
		}
		r+="!";
		switch(Containstate)
		{
		case BOX:
			r+="B";
			break;
		case WORKERS:
			r+="W";
			break;			
		default:
			r+=" ";
			break;
		}
		return r;
	}
}
