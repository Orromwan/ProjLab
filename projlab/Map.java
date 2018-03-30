package projlab;

/**
 * A Map osztály, a raktárat tartja számon,
 * felépiti a raktárat, és elinditja a mozgást.
 */
public class Map 
{
	// A mezõk
	Field[] fields;
	
	/**
	 * A raktár felépitése a mezõkbõl.
	 */
	void create()
	{
		//PRINT
		System.out.println(toString()+" - create called");
		
	}
	
	/**
	 * Szimulálja a cselekményt.
	 */
	void simulate()
	{
		//PRINT
		System.out.println(toString()+" - simulate called");
		
	}
}