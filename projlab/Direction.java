package projlab;

public enum Direction
{	
	LEFT(3),
	RIGHT(1),
	DOWN(2),
	UP(0);
	
	private int d;
	
	Direction(int i)
	{
		d=i;
	}
	
	int getDir()
	{
		return d;
	}
}