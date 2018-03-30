package projlab;

/**
 * Doboz osztály, számontartja az utolsó munkást aki mozgatta 
 * a dobozt és a doboz jelenlegi mezõjét.  
 */
public class Box {
	
	// A munkás aki utóljára megtolta a dobozt.
	private Worker worker;
	
	// A mezõ amin jelenleg van a doboz.
	private Field currentField;
	
	//Konstruktor, amely a mezõre helyezi a dobozt
	public Box(Field f)
	{
		f.AddBox(this);
		currentField=f;
	}
	

	/**
	 * Áthelyezi a dobozt a paraméterül kapott cél mezõre,
	 * a jelenlegi mezõrõl leszedi.	
	 * @param f - A cél mezõ
	 */
	void UpdateBox(Field f)
	{
		//PRINT
		System.out.println(toString()+" - UpdateBox called");
		currentField.removeBox(this);
		currentField=f;
	}

	/**
	 * Megöli a dobozt, leszedi az aktuális mezõrõl.      	
	 */
	void KillBox()
	{
		//PRINT
		System.out.println(toString()+" - KillBox called");
		currentField.removeBox(this);
		currentField=null;
	}
	
	/**
	 * A munkásnak megnöveli a pontjait a paraméterül kapott értékkel.
	 * @param p - Az érték
	 */
	void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println(toString()+" - addPointsToLastWorker called");
		worker.IncPoints(p);
	}
}
