package projlab;

/**
 * A munk�s oszt�ly, t�rolja a munk�s pontjait
 * �s azt, hogy mely mez�n �ll.
 */
public class Worker 
{
	// A munk�s pontjai.
	private int points=0;
	
	// A munk�s ezen a mez�n �ll.
	private Field pos;
	
	// A munk�s ereje
	private int strength;
	
	//Konstruktor, amely a mez�re helyezi a munk�st
	Worker(Field f, int str)
	{
		f.AddWorker(this);
		pos=f;
		strength=str;
	}

	/**
	 * Munk�s mozgat�sa a param�ter�l kapott ir�nyba
	 * @param dir - Az ir�ny
	 */
	void MoveWorker(Direction dir)
	{
		//PRINT
		System.out.println(toString()+" - MoveWorker called");
		Field f = pos.getNeighbor(dir);
		
		f.AcceptWorker(this, dir);	
	}
	
	/**
	 * Munk�s �tker�l a param�ter�l kapott c�l mez�re.
	 * @param f - A c�l mez�
	 */
	void UpdateWorker(Field f)
	{
		//PRINT
		System.out.println(toString()+" - UpdateWorker called");
		
		pos.removeWorker(this);
		pos=f;
	}
	
	/**
	 * Munk�s pontjainak n�vel�se a param�ter�l kapott �rt�kkel.
	 * @param i - Az �rt�k
	 */
	void IncPoints(int i)
	{
		//PRINT
		System.out.println(toString()+" - IncPoints called");
		points+=i;
	}
	
	/**
	 * Munk�s meg�l�se, leker�l az aktu�lis mez�r�l.
	 */
	void kill()
	{
		//PRINT
		System.out.println(toString()+" - kill called");
		pos.removeWorker(this);
		pos=null;
	}
	
	/**
	 * Visszaadja a munk�s erej�t
	 */
	int getStrength()
	{
		return strength;
	}
}
