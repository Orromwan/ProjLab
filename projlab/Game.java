package projlab;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A j�t�k oszt�ly, nyilv�ntartja a rakt�rat, elinditja �s 
 * befejezi a j�t�kot, munk�sok pontjait kiosztja.
 */
public class Game
{
	// Az utolj�ra aktiv munk�s
	static private Worker worker;

	//A j�t�kosok list�ja
	static private ArrayList<Worker> workers = new ArrayList<Worker>();

	/**
	 * A j�t�k menete
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
	 * Felhaszn�l�i inputok kezel�se
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

	//Aktu�lis unk�s be�ll�t�sa
	static void setStartingWorker(Worker w)
	{
		worker = w;
	}
	
	//J�t�kos v�lt�sa
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
