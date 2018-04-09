package projlab;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Válassz teszt szekvenciát 1-10ig a szám megadásával:");

		/*
		String inputStr = System.console().readLine();
		int input = Integer.parseInt(inputStr);
		 */

		Scanner scin = new Scanner(System.in);
		int input = scin.nextInt();
		scin.close();


		switch(input) {
		case 1: seq01();break;
		case 2: seq02();break;
		case 3: seq03();break;
		case 4: seq04();break;
		case 5: seq05();break;
		case 6: seq06();break;
		case 7: seq07();break;
		case 8: seq08();break;
		case 9: seq09();break;
		case 10: seq10();break;
		case 11: seq11();break;
		case 12: seq12();break;
		case 13: seq13();break;
		default: System.out.println("Rossz érték!");break;


		}
	}

	//1. Munkás üres mezõre lép
	public static void seq01()
	{			
		Field f1 = new Field();
		Field f2 = new Field();
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//2. Dobozt tol Üres mezõre
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

	//5. Kapcsolóra tol
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

	//6. Letolja a kapcsolóról
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

	//7. Elfogadóhelyre tol
	public static void seq07()
	{
		Field f1 = new Field();
		Field f2 = new Field();
		EndPos e1 = new EndPos();
		Box b1 = new Box(f2);		
		Worker w1 = new Worker(f1, 6);
		Game.SetCurrentWorker(w1);
		f1.addNeighbor(f2, Direction.RIGHT);
		f2.addNeighbor(f1, Direction.LEFT);
		f2.addNeighbor(e1, Direction.RIGHT);
		e1.addNeighbor(f2, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//8. Több dobozt tol
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

	//9. Falnak tol több dobozt
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

	//10. Összenyom
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

	//11. EnPosra lép
	public static void seq11()
	{
		Field f1 = new Field();
		EndPos e1 = new EndPos();
		Worker w1 = new Worker(f1, 6);
		f1.addNeighbor(e1, Direction.RIGHT);
		e1.addNeighbor(f1, Direction.LEFT);
		w1.MoveWorker(Direction.RIGHT);
	}

	//12. Lyukra lép
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
