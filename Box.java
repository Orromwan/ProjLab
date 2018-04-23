package projlab;

/**
 * Doboz oszt�ly, sz�montartja az utols� munk�st aki mozgatta 
 * a dobozt �s a doboz jelenlegi mez�j�t.  
 */
public class Box {
	
	// A mez� amin jelenleg van a doboz.
	private Field CurrentField;
	
	//Konstruktor, amely a mez�re helyezi a dobozt
	void initBox(Field f)
	{
		f.addBox(this);
		CurrentField = f;
	}
	

	/**
	 * �thelyezi a dobozt a param�ter�l kapott c�l mez�re,
	 * a jelenlegi mez�r�l leszedi.	
	 * @param f - A c�l mez�
	 */
	void updateBox(Field f)
	{
		//PRINT
		System.out.println(toString() + " - UpdateBox called");
		CurrentField.removeBox(this);
		CurrentField = f;
	}

	/**
	 * Meg�li a dobozt, leszedi az aktu�lis mez�r�l.      	
	 */
	void KillBox()
	{
		//PRINT
		System.out.println(toString() + " - KillBox called");
		CurrentField.removeBox(this);
		CurrentField = null;
	}
	/**
         * Visszaadja a Boxot reprezent�l� karaktert 
         * @return - a karakter
         */
 /*       String getChar()
        {
            return "B";
        }
	*/
}
