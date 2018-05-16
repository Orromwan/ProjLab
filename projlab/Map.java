package projlab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * A Map osztaly, a raktarat tartja szamon,
 * felepiti a raktarat, es elinditja a mozgast.
 */
public class Map
{
	// A map dimenzioi
	static int x = 5;
	static int y = 5;
	// A mezok
	static private Field[][] Fields = new Field[y][x];
	static private Worker[][] Workers = new Worker[y][x];
	static private Box[][] Boxes = new Box[y][x];
	static private String[][] Map = new String[y][x];
	// A helyukre tolt dobozok szama
	private int BoxesAtFinish;
	// A dobozok szama
	private int BoxCount;
	//Utoljara betoltott palya
	public String MapName;

	/**
	 * A palya inicializalasa a palyan levo elemek matrixaival
	 */
	static void initMap(Field[][] f, Worker[][] w, Box[][] b)
	{
		Fields = f;
		Workers = w;
		Boxes = b;
	}
	/**
	 * A raktar felepitese a mezokbol.
	 */
	void create()
	{
		//PRINT
		System.out.println("Map - create called");
		for(int i = 0; i < y ; i++)
			for(int j = 0; j < x ; j++)
			{
				if(i > 0) Fields[i][j].addNeighbor(Fields[i - 1][j], Direction.LEFT);
				if(i < y - 1) Fields[i][j].addNeighbor(Fields[i + 1][j], Direction.RIGHT);
				if(j > 0) Fields[i][j].addNeighbor(Fields[i][j - 1], Direction.UP);
				if(j < x - 1) Fields[i][j].addNeighbor(Fields[i][j + 1], Direction.DOWN);
				if(Workers[i][j] != null)
				{
					Workers[i][j].initWorker(Fields[i][j], 3);
					Game.addWorker(Workers[i][j]);
				}
				if(Boxes[i][j] != null)	
					Boxes[i][j].initBox(Fields[i][j]);
				if(Fields[i][j].getClass().getName()=="projlab.Switch") {
					Switch s = (Switch)Fields[i][j];
					s.setHybrid((Hybrid)Fields[i - 1][j]);
					Fields[i][j] = s;
				}
			}
		this.draw();
	}

	/**
	 * A raktar megrajzolasa.
	 */
	void draw()
	{
		System.out.println("Map - draw called");
		for(int j = 0; j < x ; j++)
		{
			for(int i = 0; i < y ; i++)
			{
				Map[i][j] = Fields[i][j].getChar();
				Map[i][j] += "\t";
				
				System.out.print(Map[i][j]);
			}
			System.out.print("\n");
		}	
	}

	/**
	 * A raktar beolvasasa fajlbol.
     * @param fileName a fajl neve
	 */
	public void initMapFromFile(String fileName)
	{
        try {
        	MapName = fileName;
    		String line = null;
    		String[] tempFields = null;
    		int rowCount = 0, columnIndex = 0;
    		
            // Fajl olvasasa (default encodingban)
            FileReader fileReader = new FileReader(MapName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	tempFields = line.split("\t");
                rowCount = tempFields.length;
                
                if(columnIndex == 0)
                {
                	Fields = new Field[rowCount][rowCount];
                	Workers = new Worker[rowCount][rowCount];
                	Boxes = new Box[rowCount][rowCount];
                	Map = new String[rowCount][rowCount];
                	x = y = rowCount;
                }
                // A beolvasott palya
                System.out.println(line);
                
                for(int i = 0; i < rowCount; i++)
                {
                	switch(tempFields[i])
                	{
                	case "X":
                		Fields[i][columnIndex] = new Wall();
                		break;
                	case "B":
                		Fields[i][columnIndex] = new Field();
                		Boxes[i][columnIndex] = new Box();
                		BoxCount++;
                		break;
                	case "W":
                		Fields[i][columnIndex] = new Field();
                		Workers[i][columnIndex] = new Worker();
                		break;
                	case "L":
                		Fields[i][columnIndex] = new Switch();
                		break;
                	case "o":
                		Fields[i][columnIndex] = new Hybrid();
                		break;
                	case ":":
                		Fields[i][columnIndex] = new Field();
                		Fields[i][columnIndex].pourHoney();
                		break;
                	case "_":
                		Fields[i][columnIndex] = new Field();
                		Fields[i][columnIndex].pourOil();
                		break;
                	case "!":
                		Fields[i][columnIndex] = new EndPos();
                		break;
                	case ".":
                		Fields[i][columnIndex] = new Field();
                		break;
                	default:
                		Fields[i][columnIndex] = new Field();
                		break;
                	}
                }
                columnIndex++;
            }

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + MapName + "'");
            ex.printStackTrace();              
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + MapName + "'");
            ex.printStackTrace();
        }
	}

	/**
	 * Doboz kerult a vegpoziciora.
	 */
	public void boxIsFinished()
	{
		BoxesAtFinish++;
	}

	/**
	 * Leellenorzi, hogy minden doboz a helyere kerult-e.
	 */
	public boolean isGameOver()
	{
		if(BoxesAtFinish == BoxCount)
			return true;
		else
			return false;
	}

	String [][] getMap()
	{
		return Map;
	}
}