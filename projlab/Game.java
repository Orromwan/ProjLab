package projlab;

/** 
 * A j�t�k oszt�ly, nyilv�ntartja a rakt�rat, elinditja �s 
 * befejezi a j�t�kot, munk�sok pontjait kiosztja.
 */
public class Game
{
	// Az utolj�ra aktiv munk�s
	static private Worker worker;
	
	static Map map;
	
	/**
	 * Elinditja a j�t�kot.
	 */
	static void startGame()
	{
		//PRINT
		System.out.println("Game - startGame called");
		map.create();
	}
	
	/**
	 * J�t�k befejez�se
	 */
	static void endGame()
	{
		//PRINT
		System.out.println("Game - endGame called");
		
	}
	
	/**
	 * Az utolj�ra aktiv munk�s pontjainak n�vel�se a 
	 * param�ter�l kapott �rt�kkel.
	 * @param p - Az �rt�k.
	 */
	static void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println("Game - addPointsToLastWorker called");
		worker.IncPoints(p);
	}
	
	//Aktu�lis unk�s be�ll�t�sa;
	static void SetCurrentWorker(Worker w)
	{
		worker=w;
	}
}
