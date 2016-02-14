public class Animal
{
	String name;
	String color;
	int legs;
	int arms;
	int tails;
	boolean burrow;
	boolean swim;
	boolean fly;

	Animal(String name, String color, int legs, int arms, int tails, boolean burrow, boolean swim, boolean fly)
	{
		this.name = name;
		this.color = color;
		this.legs = legs;
		this.arms = arms;
		this.tails = tails;
		this.burrow = burrow;
		this.swim = swim;
		this.fly = fly;
	}

	public String toString()
	{
		return(name + ": " + "\n" +
			"  " + color + "\n" +
			"  " + legs + (legs == 1 ? " leg" : " legs") + "\n" +
			"  " + arms + (arms == 1 ? " arm" : " arms") + "\n" +
			"  " + tails + (tails == 1 ? " tail" : " tails") + "\n" +
			"  " + (burrow ? "burrows" : "cannot burrow") + "\n" +
			"  " + (swim ? "swims" : "cannot swim") + "\n" +
			"  " + (fly ? "flies" : "cannot fly"));
	}

	public String getName()
	{
		return name;
	}

	public String getColor()
	{
		return color;
	}

	public int getLegs()
	{
		return legs;
	}

	public int getArms()
	{
		return arms;
	}

	public int getTails()
	{
		return tails;
	}

	public boolean getBurrow()
	{
		return burrow;
	}

	public boolean getSwim()
	{
		return swim;
	}

	public boolean getFly()
	{
		return fly;
	}
}
