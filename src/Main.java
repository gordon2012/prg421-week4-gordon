import java.util.*;
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
			ui.print("[1] List Animals\n[2] Add Animal\n[3] Remove Animal\n[X] Exit\n");

			switch(ui.getInput("").substring(0).toUpperCase())
			{
				case "1":
					List<String> animals = animalDB.listAnimals();
					for(String a : animals)
					{
						ui.print(a);
					}
					break;

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
