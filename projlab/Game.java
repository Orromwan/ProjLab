package projlab;

/** 
 * A játék osztály, nyilvántartja a raktárat, elinditja és 
 * befejezi a játékot, munkások pontjait kiosztja.
 */
public class Game
{
	// Az utoljára aktiv munkás
	static private Worker worker;
	
	static Map map;
	
	/**
	 * Elinditja a játékot.
	 */
	static void startGame()
	{
		//PRINT
		System.out.println("Game - startGame called");
		map.create();
	}
	
	/**
	 * Játék befejezése
	 */
	static void endGame()
	{
		//PRINT
		System.out.println("Game - endGame called");
		
	}
	
	/**
	 * Az utoljára aktiv munkás pontjainak növelése a 
	 * paraméterül kapott értékkel.
	 * @param p - Az érték.
	 */
	static void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println("Game - addPointsToLastWorker called");
		worker.IncPoints(p);
	}
	
	//Aktuális unkás beállítása;
	static void SetCurrentWorker(Worker w)
	{
		worker=w;
	}
}
