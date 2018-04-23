package projlab;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A j�t�k oszt�ly, nyilv�ntartja a rakt�rat, elinditja �s 
 * befejezi a j�t�kot, munk�sok pontjait kiosztja.
 */
public class Game
{
	// A p�lya
	static private Map Map;
	
	// Az utolj�ra aktiv munk�s
	static private Worker Worker;

	//A j�t�kosok list�ja
	static private ArrayList<Worker> Workers = new ArrayList<Worker>();
	
	static Scanner scin = new Scanner(System.in);

	/**
	 * A j�t�k menete
	 * @throws InterruptedException
	 */
	static void run(Map map) throws InterruptedException
	{
		Map = map;
		Worker = Workers.get(0);
		while(true)
		{
			
			if(handleInput())
			{
				scin.close();
				break;
			}
			if(Workers.isEmpty()) break;
			changePlayer();
			Map.draw();
		}
		System.out.println("Game ended");
	}

	/**
	 * Felhaszn�l�i inputok kezel�se
	 * @return
	 */
	private static boolean handleInput()
	{
		//String s = System.console().readLine();
		/*Scanner scin = new Scanner(System.in);
		String s = scin.nextLine();
		scin.close();*/
		/*InputStreamReader streamReader = new InputStreamReader(System.in);
	    BufferedReader bufferedReader = new BufferedReader(streamReader);
	    String s = bufferedReader.readLine();*/
		System.out.println(Worker.toString()+" k�re");
		String s = scin.nextLine();
		
		switch (s)
		{
		case "o":
			Worker.pourOil(); break;
		case "h":
			Worker.pourHoney(); break;
		case "w":
			/*
			if(Worker.moveWorker(Direction.UP))
			{
				newField = Worker.getPosition();
				updateMapWorker(oldField, newField);
			}
			*/
			Worker.moveWorker(Direction.UP); break;
		case "a":
			Worker.moveWorker(Direction.LEFT); break;
		case "s":
			Worker.moveWorker(Direction.DOWN); break;
		case "d":
			Worker.moveWorker(Direction.RIGHT); break;
                case "wpoints":
                        System.out.println("Points :"+Worker.getPoints()); break;
		case "e":
			return true;
		}
                if (s.startsWith("changestr")){
                    String[] splitted = s.split(" ");
                    int requiredStr = Integer.parseInt(splitted[1]);
                    Worker.setStrength(requiredStr);
                }
		Map.draw();
		return false;
	}
	/*private static void updateMapWorker(Field oldField, Field newField)
	{
		int oldXPos = oldField.getXPos();
		int oldYPos = oldField.getYPos();
		int newXPos = newField.getXPos();
		int newYPos = newField.getYPos();
	}*/
	/**
	 * Az utolj�ra aktiv munk�s pontjainak n�vel�se a 
	 * param�ter�l kapott �rt�kkel.
	 * @param p - Az �rt�k.
	 */
	static void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println("Game - addPointsToLastWorker called");
		Worker.incPoints(p);
	}

	//Aktu�lis unk�s be�ll�t�sa
	static void setStartingWorker(Worker w)
	{
		Worker = w;
	}
	
	//J�t�kos v�lt�sa
	private static void changePlayer()
	{
		int i = Workers.indexOf(Worker);
		if(i!=Workers.size()-1)
		{
			Worker = Workers.get(i + 1);
		}
		else
		{
			Worker = Workers.get(0);
		}
	}
	
	static void addWorker(Worker w)
	{
		Workers.add(w);
	}
	
	static void killWorker(Worker w)
	{
		Workers.remove(w);
	}
}
