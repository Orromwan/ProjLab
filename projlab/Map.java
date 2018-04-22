package projlab;

/**
 * A Map osztály, a raktárat tartja számon,
 * felépiti a raktárat, és elinditja a mozgást.
 */
public class Map 
{
	//A map dimenziói
	static int x = 5;
	static int y = 5;
	// A mezõk
	static private Field[][] fields = new Field[y][x];
	static private Worker[][] workers = new Worker[y][x];
	static private Box[][] boxes = new Box[y][x];
	static private String[][] map = new String[y][x];

	/**
	 * A pálya inicializálása a pályán lévõ elemek mátrixaival
	 */
	static void initmap(Field[][] f, Worker[][] w, Box[][] b)
	{
		fields = f;
		workers = w;
		boxes = b;
	}
	/**
	 * A raktár felépitése a mezõkbõl.
	 */
	static void create()
	{
		//PRINT
		System.out.println("Map - create called");
		for(int i=0; i<y ; i++)
			for(int j=0; j<x ; j++)
			{
				if(i > 0) fields[i][j].addNeighbor(fields[i - 1][j], Direction.UP);
				if(i < y - 1) fields[i][j].addNeighbor(fields[i + 1][j], Direction.DOWN);
				if(j > 0) fields[i][j].addNeighbor(fields[i][j - 1], Direction.LEFT);
				if(j < x - 1) fields[i][j].addNeighbor(fields[i][j + 1], Direction.RIGHT);
				if(workers[i][j] != null)
				{
					workers[i][j].InitWorker(fields[i][j], 3);
					Game.AddWorker(workers[i][j]);
				}
				if(boxes[i][j] != null)	
					boxes[i][j].InitBox(fields[i][j]);
			}
		draw();
	}
	
	static void draw()
	{
		System.out.println("Map - draw called");
		for(int i = 0; i < y ; i++)
		{
			for(int j = 0; j < x ; j++)
			{
				map[i][j] = fields[i][j].getChar();
				
				if(boxes[i][j] != null)
					map[i][j] += boxes[i][j].getChar();
				else map[i][j] += " ";
				
				if(workers[i][j] != null)
					map[i][j] += workers[i][j].getChar();
				else map[i][j] += " ";
				
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}	
	}
}