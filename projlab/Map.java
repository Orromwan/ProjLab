package projlab;

/**
 * A Map oszt�ly, a rakt�rat tartja sz�mon,
 * fel�piti a rakt�rat, �s elinditja a mozg�st.
 */
public class Map 
{
	// A mez�k
	Field[] fields;
	
	/**
	 * A rakt�r fel�pit�se a mez�kb�l.
	 */
	void create()
	{
		//PRINT
		System.out.println(toString()+" - create called");
		
	}
	
	/**
	 * Szimul�lja a cselekm�nyt.
	 */
	void simulate()
	{
		//PRINT
		System.out.println(toString()+" - simulate called");
		
	}
}