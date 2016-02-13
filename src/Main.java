// http://stackoverflow.com/a/1066603
// Iterator it = animals.entrySet().iterator();

import java.util.*;
public class Main
{
	public static void main(String[] args)
	{
		UI ui = UI.getInstance();
		if(args.length > 0 && args[0].equals("debug"))
		{
			// System.out.println(args[0]);
			ui.setDebug(true);
		}

		AnimalDB animalDB = AnimalDB.getInstance();
		animalDB.init();

		// ui.debug("TEST DEBUG\n");

		int menu = 0;
		do
		{
			ui.print("\n[1] Inspect Animal\n[2] Add Animal\n[3] Remove Animal\n[X] Exit\n");

			switch(ui.getChar(""))
			{
				case "1":
					ui.print("Which one? (0 to cancel)\n");
					List<Integer> animals = animalDB.getAllIds();
					for(int i = 0; i < animals.size(); i++)
					{
						ui.print((i+1));
						ui.debug("(id: " + animals.get(i) + ")");
						ui.print(" - " + animalDB.getById(animals.get(i)).getName() +"\n");
					}

					do
					{
						menu = ui.getInt("");
					}
// -1				0 1 2 3
// 					1 2 3 4
					while(menu > (animals.size()));
					if(menu > 0) ui.print(animalDB.getById(animals.get(menu-1)));

					break;

				case "2":
					animalDB.insert(new Animal(
						ui.getInput("Name"),
						ui.getInput("Color"),
						ui.getInt("# of Legs"),
						ui.getInt("# of Arms"),
						ui.getInt("# of Tails"),
						ui.getBool("Burrows[y/N]"),
						ui.getBool("Swims[y/N]"),
						ui.getBool("Flies[y/N]")
					));
					// ui.print("\n" + animal);
					break;



				case "X":
					menu = -1;
			}
		}
		while(menu != -1);
		ui.print("Goodbye.");
	}
}
