package projlab;

/**
 * A Map osztály, a raktárat tartja számon,
 * felépiti a raktárat, és elinditja a mozgást.
 */
public class Map 
{
	//A map dimenziói
	static int x=5;
	static int y=5;
	// A mezõk
	static private Field[][] fields = new Field[x][y];
	static private Worker[][] workers = new Worker[x][y];
	static private Box[][] boxes = new Box[x][y];
	static private String[][] map = new String[x][y];

	/**
	 * A pálya inicializálása a pályán lévõ elemek mátrixaival
	 */
	static void initmap(Field[][] f, Worker[][] w, Box[][] b)
	{
		fields=f;
		workers=w;
		boxes=b;
	}
	/**
	 * A raktár felépitése a mezõkbõl.
	 */
	static void create()
	{
		//PRINT
		System.out.println("Map - create called");
		for(int i=0; i<x ; i++)
			for(int j=0; j<y ; j++)
			{
				if(i>0) fields[i][j].addNeighbor(fields[i-1][j], Direction.LEFT);
				if(i<16) fields[i][j].addNeighbor(fields[i+1][j], Direction.RIGHT);
				if(j>0) fields[i][j].addNeighbor(fields[i][j-1], Direction.DOWN);
				if(j<16) fields[i][j].addNeighbor(fields[i][j+1], Direction.UP);
				fields[i][j].AddWorker(workers[i][j]);
				Game.AddWorker(workers[i][j]);
				fields[i][j].AddBox(boxes[i][j]);
			}
	}
	
	static void draw()
	{
		System.out.println("Map - draw called");
		for(int i=0; i<x ; i++)
		{
			for(int j=0; j<y ; j++)
			{
				map[i][j]+=fields[i][j].getChar();
				map[i][j]+=boxes[i][j].getChar();
				map[i][j]+=workers[i][j].getChar();
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}	
	}
}