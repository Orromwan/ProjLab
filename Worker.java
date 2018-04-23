package projlab;

/**
 * A munk�s oszt�ly, t�rolja a munk�s pontjait
 * �s azt, hogy mely mez�n �ll.
 */
public class Worker 
{
	// A munk�s pontjai.
	private int Points = 0;
	
	// A munk�s ezen a mez�n �ll.
	private Field Position;
	
	// A munk�s ereje
	private int Strength;
	
	//Konstruktor, amely a mez�re helyezi a munk�st
	void initWorker(Field f, int str)
	{
		f.addWorker(this);
		Position = f;
		Strength = str;
	}

	/**
	 * Munk�s mozgat�sa a param�ter�l kapott ir�nyba
	 * @param dir - Az ir�ny
	 */
	boolean moveWorker(Direction dir)
	{
		//PRINT
		System.out.println(toString() + " - moveWorker called");
		Field f = Position.getNeighbor(dir);
		
		return f.acceptWorker(this, dir);	
	}
	
	/**
	 * Munk�s �tker�l a param�ter�l kapott c�l mez�re.
	 * @param f - A c�l mez�
	 */
	void updateWorker(Field f)
	{
		//PRINT
		System.out.println(toString() + " - updateWorker called");
		
		Position.removeWorker(this);
		Position = f;
	}
	
	/**
	 * Munk�s pontjainak n�vel�se a param�ter�l kapott �rt�kkel.
	 * @param i - Az �rt�k
	 */
	void incPoints(int i)
	{
		//PRINT
		System.out.println(toString() + " - incPoints called");
		Points += i;
	}
	
	/**
	 * Munk�s meg�l�se, leker�l az aktu�lis mez�r�l.
	 */
	void kill()
	{
		//PRINT
		System.out.println(toString() + " - kill called");
		Position.removeWorker(this);
		Position = null;
		Game.killWorker(this);
	}
	
	/**
	 * Visszaadja a munk�s erej�t
	 */
	int getStrength()
	{
		return Strength;
	}
	
	/**
	 * M�zet �nt az aktu�lis mez�re
	 */
	void pourHoney()
	{
		Position.pourHoney();	
	}
	
	/**
	 * Olajat �nt az aktu�lis mez�re
	 */
	void pourOil()
	{
		Position.pourOil();
	}
	/**
     * Visszaadja a Workert reprezent�l� karaktert 
     * @return - a karakter
     */
 /*   String getChar()
    {
        return "W";
    }
    */
 /*   public Field getPosition()
    {
    	return Position;
    }*/
}
