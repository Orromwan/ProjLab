package projlab;

/**
 * A munkás osztály, tárolja a munkás pontjait
 * és azt, hogy mely mezõn áll.
 */
public class Worker 
{
	// A munkás pontjai.
	private int Points = 0;
	
	// A munkás ezen a mezõn áll.
	private Field Position;
	
	// A munkás ereje
	private int Strength;
	
	//Konstruktor, amely a mezõre helyezi a munkást
	void initWorker(Field f, int str)
	{
		f.addWorker(this);
		Position = f;
		Strength = str;
	}

	/**
	 * Munkás mozgatása a paraméterül kapott irányba
	 * @param dir - Az irány
	 */
	boolean moveWorker(Direction dir)
	{
		//PRINT
		System.out.println(toString() + " - moveWorker called");
		Field f = Position.getNeighbor(dir);
		
		return f.acceptWorker(this, dir);	
	}
	
	/**
	 * Munkás átkerül a paraméterül kapott cél mezõre.
	 * @param f - A cél mezõ
	 */
	void updateWorker(Field f)
	{
		//PRINT
		System.out.println(toString() + " - updateWorker called");
		
		Position.removeWorker(this);
		Position = f;
	}
	
	/**
	 * Munkás pontjainak növelése a paraméterül kapott értékkel.
	 * @param i - Az érték
	 */
	void incPoints(int i)
	{
		//PRINT
		System.out.println(toString() + " - incPoints called");
		Points += i;
	}
	
	/**
	 * Munkás megölése, lekerül az aktuális mezõrõl.
	 */
	void kill()
	{
		//PRINT
		System.out.println(toString() + " - kill called");
		Position.removeWorker(this);
		Position = null;
	}
	
	/**
	 * Visszaadja a munkás erejét
	 */
	int getStrength()
	{
		return Strength;
	}
	
	/**
	 * Mézet önt az aktuális mezõre
	 */
	void pourHoney()
	{
		Position.pourHoney();	
	}
	
	/**
	 * Olajat önt az aktuális mezõre
	 */
	void pourOil()
	{
		Position.pourOil();
	}
	/**
     * Visszaadja a Workert reprezentáló karaktert 
     * @return - a karakter
     */
    String getChar()
    {
        return "W";
    }
    
    public Field getPosition()
    {
    	return Position;
    }
}
