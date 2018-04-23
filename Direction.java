package projlab;

public enum Direction
{	
	LEFT(3),
	RIGHT(1),
	DOWN(2),
	UP(0);
	
	private int D;
	
	Direction(int i)
	{
		D = i;
	}
	
	int getDir()
	{
		return D;
	}
}