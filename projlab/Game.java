package projlab;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A jatek osztaly, nyilvantartja a raktarat, elinditja és 
 * befejezi a jatekot, munkasok pontjait kiosztja.
 */
public class Game implements InputHandlerInterface
{
	// A palya
	static private Map GameMap;
	
	// Az utoljára aktiv munkas
	static private Worker Worker;
	// A window amit frissiteni kell
	static private Window window;
	// Fut-e eppen a dolog
    static private boolean running;

	//A jatekosok listaja
	static private ArrayList<Worker> Workers = new ArrayList<Worker>();

	/**
     * Beallitja tagvaltozonak a parameterkent kapott mapot
     * @param map 
     */
	public void SetMap(Map map)
    {
        GameMap = map;
    }
	
    /**
     * Beallitja tagvaltozonak a parameterkent kapott Window-ot
     * @param w 
     */
    public void SetWindow(Window w)
    {
        window = w;
        running = true;
    }

	/**
	 * Felhasznaloi inputok kezelese
     * @param s Input
	 * @return Vege van-e a jateknak
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
            if (window.ShowMenu) startNewGame();
			break;
		case "e":
			return true;
		}
		GameMap.draw();
		window.repaint();
		return false;
	}
	/**
	 * Az utoljara aktiv munkas pontjainak novelese a 
	 * parameterul kapott ertekkel.
	 * @param p - Az ertek.
	 */
	static void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println("Game - addPointsToLastWorker called");
		Worker.incPoints(p);
		GameMap.boxIsFinished();
	}

	//Aktualis munkas beallitasa
	static void setStartingWorker(Worker w)
	{
		Worker = w;
	}
	
	//Jatekos valtasa
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
    * Uj jatek inditasa ugyanazon a terkepen
    */
    public void startNewGame() {
    	String MapName = GameMap.MapName;
		GameMap = new Map();
		GameMap.initMapFromFile(MapName);
		GameMap.create();
		/*window = new Window(GameMap.getMap());
        SetWindow(window);
        window.setInputHandler(this);*/
		window.ShowMenu = false;
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
            if(handleInput(c + "") || Workers.isEmpty() || GameMap.isGameOver()) 
            {
            	running = false;
                window.showAlert("Vege a jateknak!");
            }	
            if (!Workers.isEmpty())
                changePlayer();
        }
    }
}
