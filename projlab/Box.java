package projlab;

/**
 * Doboz osztály, számontartja az utolsó munkást aki mozgatta 
 * a dobozt és a doboz jelenlegi mezõjét.  
 */
public class Box {
	
	// A mezõ amin jelenleg van a doboz.
	private Field CurrentField;
	
	//Konstruktor, amely a mezõre helyezi a dobozt
	void initBox(Field f)
	{
		f.addBox(this);
		CurrentField = f;
	}
	

	/**
	 * Áthelyezi a dobozt a paraméterül kapott cél mezõre,
	 * a jelenlegi mezõrõl leszedi.	
	 * @param f - A cél mezõ
	 */
	void updateBox(Field f)
	{
		//PRINT
		System.out.println(toString() + " - UpdateBox called");
		CurrentField.removeBox(this);
		CurrentField = f;
	}

	/**
	 * Megöli a dobozt, leszedi az aktuális mezõrõl.      	
	 */
	void KillBox()
	{
		//PRINT
		System.out.println(toString() + " - KillBox called");
		CurrentField.removeBox(this);
		CurrentField = null;
	}
	/**
	 * Visszaadja a Boxot reprezentáló karaktert 
	 * @return - a karakter
	 */
	/*String getChar()
	{
		return "B";
	}*/
}
