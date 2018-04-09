package projlab;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A játék osztály, nyilvántartja a raktárat, elinditja és 
 * befejezi a játékot, munkások pontjait kiosztja.
 */
public class Game
{
	// Az utoljára aktiv munkás
	static private Worker worker;
	
	//A játékosok listája
	static private ArrayList<Worker> workers;
	
	static Map map;
	
	/**
	 * Elinditja a játékot.
	 */
	public void startGame()
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
	
	public void run() throws InterruptedException
	{
		while(true)
		{
			ChangePlayer();
			if(HandleInput()) break;
			Thread.sleep(2000);
		}
	}
	
	boolean HandleInput()
	{
		Scanner scin = new Scanner(System.in);
		String s = scin.nextLine();
		scin.close();
		switch (s)
		{
		case "o": worker.pourOil();
		case "h": worker.pourHoney();
		case "w": worker.MoveWorker(Direction.UP);
		case "a": worker.MoveWorker(Direction.LEFT);
		case "s": worker.MoveWorker(Direction.DOWN);
		case "d": worker.MoveWorker(Direction.RIGHT);
		case "e": return true;
		}
		return false;
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
	static void ChangePlayer()
	{
		int i = workers.indexOf(worker);
		if(null!=workers.get(i+1)) {worker=workers.get(i+1);}
		else{worker=workers.get(0);}
	}
}
