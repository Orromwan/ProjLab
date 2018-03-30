package projlab;

public enum Liquid
{
	NONE(2),
	HONEY(3),
	OIL(1);

	private int f;

	Liquid(int i)
	{
		f=i;
	}

	int friction()
	{
		return f;
	}
}
