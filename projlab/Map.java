package projlab;

/**
 * A Map osztály, a raktárat tartja számon,
 * felépiti a raktárat, és elinditja a mozgást.
 */
public class Map 
{
	// A mezõk
	Field[][] fields = new Field[16][16];
	Worker[][] workers = new Worker[16][16];
	Box[][] boxes = new Box[16][16];

	/**
	 * A raktár felépitése a mezõkbõl.
	 */
	void create()
	{
		//PRINT
		System.out.println(toString()+" - create called");
		for(int i=0; i<16 ; i++)
			for(int j=0; j<16 ; j++)
			{
				if(i>0) fields[i][j].addNeighbor(fields[i-1][j], Direction.LEFT);
				if(i<16) fields[i][j].addNeighbor(fields[i+1][j], Direction.RIGHT);
				if(j>0) fields[i][j].addNeighbor(fields[i][j-1], Direction.DOWN);
				if(j<16) fields[i][j].addNeighbor(fields[i][j+1], Direction.UP);
				fields[i][j].AddWorker(workers[i][j]);
				fields[i][j].AddBox(boxes[i][j]);
			}
	}

	/**
	 * Szimulálja a cselekményt.
	 */
	void simulate()
	{
		//PRINT
		System.out.println(toString()+" - simulate called");

	}
}