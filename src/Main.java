public class Main
{
	public static void main(String[] args)
	{
		UI ui = UI.getInstance();
		AnimalDB animalDB = AnimalDB.getInstance();

		animalDB.init();

		int menu = 0;
		do
		{
			ui.print("2/X\n");

			switch(ui.getInput("").substring(0).toUpperCase())
			{
				case "2":
					Animal animal = new Animal(
						ui.getInput("Name"),
						ui.getInt("# of Legs"),
						ui.getInt("# of Arms"),
						ui.getInt("# of Tails"),
						ui.getBool("Burrows[y/N]"),
						ui.getBool("Swims[y/N]"),
						ui.getBool("Flies[y/N]")
					);
					ui.print("\n" + animal);
					break;



				case "X":
					menu = -1;
			}
		}
		while(menu != -1);
		ui.print("Goodbye.");
	}
}
