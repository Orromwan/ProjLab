package projlab;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A játék osztály, nyilvántartja a raktárat, elinditja és 
 * befejezi a játékot, munkások pontjait kiosztja.
 */
public class Game implements InputHandlerInterface
{
	// A pálya
	static Map GameMap;
	
	// Az utoljára aktiv munkás
	static private Worker Worker;
	// A window amit frissíteni kell
	static private Window window;
	// Fut-e éppen a dolog
    static private boolean running;

	//A játékosok listája
	static private ArrayList<Worker> Workers = new ArrayList<Worker>();
	
	static Scanner scin = new Scanner(System.in);

	/**
     * Beállítja tagváltozónak a paraméterként kapott mapot
     * @param map 
     */
	public void SetMap(Map map)
        {
            GameMap = map;
        }
	
    /**
     * Beállítja tagváltozónak a paraméterként kapott Window-ot
     * @param w 
     */
    public static void SetWindow(Window w)
    {
        window = w;
        running = true;
    }

	/**
	 * Felhasználói inputok kezelése
	 * @return
	 */
	private boolean handleInput(String s)
	{
		
		switch (s)
		{
		case "o":
			Worker.pourOil();
			break;
		case "h":
			Worker.pourHoney();
			break;
		case "w":
			Worker.moveWorker(Direction.UP);
			break;
		case "a":
			Worker.moveWorker(Direction.LEFT);
			break;
		case "s":
			Worker.moveWorker(Direction.DOWN);
			break;
		case "d":
			Worker.moveWorker(Direction.RIGHT);
			break;
		case "p":
            System.out.println("Worker points: "+ Worker.getPoints());
			break;
		case "q":
            window.ShowMenu = !window.ShowMenu;
			break;
		case "n":
            if (window.ShowMenu)
            {
            	String MapName = GameMap.MapName;
        		GameMap = new Map();
        		GameMap.initMapFromFile(MapName);
        		GameMap.create();
        		window = new Window(GameMap.getMap());
                SetWindow(window);
                window.setInputHandler(this);
        		window.ShowMenu = false;
            }
			break;
		case "e":
			return true;
		}
		GameMap.draw();
		window.repaint();
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
		Worker.incPoints(p);
		GameMap.boxIsFinished();
	}

	//Aktuális munkás beállítása
	static void setStartingWorker(Worker w)
	{
		Worker = w;
	}
	
	//Játékos váltása
	private static void changePlayer()
	{
		int i = Workers.indexOf(Worker);
		if(i != Workers.size() - 1)
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
   	/**
         * A beerkezo c inputot kezeli a megfelelo modon, ez a Window-tol jon
         * @param c 
         */
         @Override
         public void DispatchInput(char c) {
            if (running)
            {
                Worker = Workers.get(0);
		if(handleInput(c+"") || Workers.isEmpty() || GameMap.isGameOver()) 
                {   running = false;
                    window.showAlert("Vege a jateknak!");
                }
			
                if (!Workers.isEmpty())
                    changePlayer();
            }
        }
}
