package projlab;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A j�t�k oszt�ly, nyilv�ntartja a rakt�rat, elinditja �s 
 * befejezi a j�t�kot, munk�sok pontjait kiosztja.
 */
public class Game implements InputHandlerInterface
{
	// A p�lya
	static Map GameMap;
	
	// Az utolj�ra aktiv munk�s
	static private Worker Worker;
	// A window amit friss�teni kell
	static private Window window;
	// Fut-e �ppen a dolog
    static private boolean running;

	//A j�t�kosok list�ja
	static private ArrayList<Worker> Workers = new ArrayList<Worker>();
	
	static Scanner scin = new Scanner(System.in);

	/**
     * Be�ll�tja tagv�ltoz�nak a param�terk�nt kapott mapot
     * @param map 
     */
	public void SetMap(Map map)
        {
            GameMap = map;
        }
	
    /**
     * Be�ll�tja tagv�ltoz�nak a param�terk�nt kapott Window-ot
     * @param w 
     */
    public static void SetWindow(Window w)
    {
        window = w;
        running = true;
    }

	/**
	 * Felhaszn�l�i inputok kezel�se
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
	 * Az utolj�ra aktiv munk�s pontjainak n�vel�se a 
	 * param�ter�l kapott �rt�kkel.
	 * @param p - Az �rt�k.
	 */
	static void addPointsToLastWorker(int p)
	{
		//PRINT
		System.out.println("Game - addPointsToLastWorker called");
		Worker.incPoints(p);
		GameMap.boxIsFinished();
	}

	//Aktu�lis munk�s be�ll�t�sa
	static void setStartingWorker(Worker w)
	{
		Worker = w;
	}
	
	//J�t�kos v�lt�sa
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
