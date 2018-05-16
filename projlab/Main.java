package projlab;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {		
		Map map = new Map();
		map.initMapFromFile("test.txt");
		map.create();
		
		Game g = new Game();
        g.SetMap(map);
        
		Window window = new Window(map.getMap());
        g.SetWindow(window);
        window.setInputHandler(g);
	}
}
/*
	//1. Munk�s �res mez�re l�p
	public static void seq01()
	{			
		Field f1 = new Field();
		Field f2 = new Field();
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//2. Dobozt tol �res mez�re
	public static void seq02()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Field f3 = new Field();
		Box b1 = new Box(f2);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(f3, Direction.RIGHT);
		f3.addNeighbor(f2, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//3. Dobozt tol Falnak
	public static void seq03()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Wall wa1 = new Wall();
		Box b1 = new Box(f2);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(wa1, Direction.RIGHT);
		wa1.addNeighbor(f2, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//4. Dobozt tol nyitott hybridre
	public static void seq04()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Hybrid h1 = new Hybrid();
		h1.switchOn();
		Box b1 = new Box(f2);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(h1, Direction.RIGHT);
		h1.addNeighbor(f2, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//5. Kapcsol�ra tol
	public static void seq05()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Hybrid h1 = new Hybrid();
		Switch s1 = new Switch();
		s1.setHybrid(h1);
		Box b1 = new Box(f2);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(s1, Direction.RIGHT);
		s1.addNeighbor(f2, Direction.LEFT);
		s1.addNeighbor(h1, Direction.RIGHT);
		h1.addNeighbor(s1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//6. Letolja a kapcsol�r�l
	public static void seq06()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Hybrid h1 = new Hybrid();
		Switch s1 = new Switch();
		s1.setHybrid(h1);
		Box b1 = new Box(s1);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(s1, Direction.RIGHT);
		f2.addNeighbor(s1, Direction.LEFT);
		f2.addNeighbor(h1, Direction.RIGHT);
		s1.addNeighbor(f1, Direction.LEFT);
		s1.addNeighbor(f2, Direction.RIGHT);
		h1.addNeighbor(f2, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//7. Elfogad�helyre tol
	public static void seq07()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		EndPos e1 = new EndPos();
		Box b1 = new Box(f2);
		Worker w1 = new Worker(f1, 6);
		Game.SetStartingWorker(w1);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(e1, Direction.RIGHT);
		e1.addNeighbor(f2, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//8. T�bb dobozt tol
	public static void seq08()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Field f3 = new Field();
		Field f4 = new Field();
		Box b1 = new Box(f2);
		Box b2 = new Box(f3);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(f3, Direction.RIGHT);
		f3.addNeighbor(f2, Direction.LEFT);
		f3.addNeighbor(f4, Direction.RIGHT);
		f4.addNeighbor(f3, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//9. Falnak tol t�bb dobozt
	public static void seq09()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Field f3 = new Field();
		Wall wa1 = new Wall();
		Box b1 = new Box(f2);
		Box b2 = new Box(f3);
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(f3, Direction.RIGHT);
		f3.addNeighbor(f2, Direction.LEFT);
		f3.addNeighbor(wa1, Direction.RIGHT);
		wa1.addNeighbor(f3, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//10. �sszenyom
	public static void seq10() 
	{
		Field f1 = new Field();
		Field f2 = new Field();
		Field f3 = new Field();
		Wall wa1 = new Wall();
		Box b1 = new Box(f2);
		Worker w1 = new Worker(f1, 6);
		Worker w2 = new Worker(f3, 6);
		Worker w3 = new Worker(f3, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(f3, Direction.RIGHT);
		f3.addNeighbor(f2, Direction.LEFT);
		f3.addNeighbor(wa1, Direction.RIGHT);
		wa1.addNeighbor(f3, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}	

	//11. EnPosra l�p
	public static void seq11()
	{
		Field f1 = new Field();
		EndPos e1 = new EndPos();
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(e1, Direction.RIGHT);
		e1.addNeighbor(f1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//12. Lyukra l�p
	public static void seq12()
	{
		Field f1 = new Field();
		Hybrid h1 = new Hybrid();
		h1.switchOn();
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(h1, Direction.RIGHT);
		h1.addNeighbor(f1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//13. Falnak megy
	public static void seq13()
	{
		Field f1 = new Field();
		Wall wa1 = new Wall();
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(wa1, Direction.RIGHT);
		wa1.addNeighbor(f1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}
}
*/