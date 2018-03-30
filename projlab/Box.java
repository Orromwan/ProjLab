package projlab;

/**
 * Doboz oszt�ly, sz�montartja az utols� munk�st aki mozgatta 
 * a dobozt �s a doboz jelenlegi mez�j�t.  
 */
public class Box {
	
	// A munk�s aki ut�lj�ra megtolta a dobozt.
	private Worker worker;
	
	// A mez� amin jelenleg van a doboz.
	private Field currentField;
	
	//Konstruktor, amely a mez�re helyezi a dobozt
	public Box(Field f)
	{
		f.AddBox(this);
		currentField=f;
	}
	

	/**
	 * �thelyezi a dobozt a param�ter�l kapott c�l mez�re,
	 * a jelenlegi mez�r�l leszedi.	
	 * @param f - A c�l mez�
	 */
	void UpdateBox(Field f)
	{
		//PRINT
		System.out.println(toString()+" - UpdateBox called");
		currentField.removeBox(this);
		currentField=f;
	}

	/**
	 * Meg�li a dobozt, leszedi az aktu�lis mez�r�l.      	
	 */
	void KillBox()
	{
		//PRINT
		System.out.println(toString()+" - KillBox called");
		currentField.removeBox(this);
		currentField=null;
	}
	
	/**
	 * A munk�snak megn�veli a pontjait a param�ter�l kapott �rt�kkel.
	 * @param p - Az �rt�k
	 */
	void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println(toString()+" - addPointsToLastWorker called");
		worker.IncPoints(p);
	}
}
