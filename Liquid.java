package projlab;

public enum Liquid
{
	NONE(2),
	HONEY(3),
	OIL(1);

	private int F;

	Liquid(int i)
	{
		F = i;
	}

	int friction()
	{
		return F;
	}
}
