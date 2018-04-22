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
	static private ArrayList<Worker> workers = new ArrayList<Worker>();

	/**
	 * A játék menete
	 * @throws InterruptedException
	 */
	static void run() throws InterruptedException
	{
		worker = workers.get(0);
		while(true)
		{
			if(handleInput()) break;
			Thread.sleep(2000);
			changePlayer();
			Map.draw();
		}
	}

	/**
	 * Felhasználói inputok kezelése
	 * @return
	 */
	private static boolean handleInput()
	{
		/*Scanner scin = new Scanner(System.in);
		String s = scin.nextLine();
		scin.close();*/
		String s = System.console().readLine();
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

	//Aktuális unkás beállítása
	static void setStartingWorker(Worker w)
	{
		worker = w;
	}
	
	//Játékos váltása
	private static void changePlayer()
	{
		int i = workers.indexOf(worker);
		if(workers.get(i + 1) != null)
		{
			worker = workers.get(i + 1);
		}
		else
		{
			worker = workers.get(0);
		}
	}
	
	static void addWorker(Worker w)
	{
		workers.add(w);
	}
}
