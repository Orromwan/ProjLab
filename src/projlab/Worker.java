package projlab;

/**
 * A munkás osztály, tárolja a munkás pontjait
 * és azt, hogy mely mezõn áll.
 */
public class Worker 
{
	// A munkás pontjai.
	private int points=0;
	
	// A munkás ezen a mezõn áll.
	private Field pos;
	
	//Konstruktor, amely a mezõre helyezi a munkást
	Worker(Field f)
	{
		f.AddWorker(this);
		pos=f;
	}

	/**
	 * Munkás mozgatása a paraméterül kapott irányba
	 * @param dir - Az irány
	 */
	void MoveWorker(Direction dir)
	{
		//PRINT
		System.out.println(toString()+" - MoveWorker called");
		Field f = pos.getNeighbor(dir);
		
		f.AcceptWorker(this, dir);	
	}
	
	/**
	 * Munkás átkerül a paraméterül kapott cél mezõre.
	 * @param f - A cél mezõ
	 */
	void UpdateWorker(Field f)
	{
		//PRINT
		System.out.println(toString()+" - UpdateWorker called");
		
		pos.removeWorker(this);
		pos=f;
	}
	
	/**
	 * Munkás pontjainak növelése a paraméterül kapott értékkel.
	 * @param i - Az érték
	 */
	void IncPoints(int i)
	{
		//PRINT
		System.out.println(toString()+" - IncPoints called");
		points+=i;
	}
	
	/**
	 * Munkás megölése, lekerül az aktuális mezõrõl.
	 */
	void kill()
	{
		//PRINT
		System.out.println(toString()+" - kill called");
		pos.removeWorker(this);
		pos=null;
	}
}
