/*
JDBC Program
Gordon Doskas
PRG/421
February 13, 2016
Roland Morales

This program allows for the manipulation of animal objects that each contain the properties of number of name, color, legs, arms, and tails; and whether or not they burrow, swim, and fly.

A menu system allows for the users to inspect an animal's properties, add an animal by inputing these properties (for the bools, a y or Y indicates true, all others false). It also allows animals to be deleted.

The list of animals is saved in a derby database. The derby library is included and is located at lib/derby.jar. It was downloaded from https://db.apache.org/derby/derby_downloads.html



To compile: run
> javac -d ./build/classes -cp ./lib/derby.jar -sourcepath ./src ./src/Main.java

To execute:
on Windows run
> java -cp ./lib/derby.jar;./build/classes Main
on Linux/Mac run
> java -cp ./lib/derby.jar:./build/classes Main

Or "run" on Windows or "./run.sh" on Linux/Mac to do both

Passing debug as an argument on the java command or run scripts will enable verbose debug information.

Finally, deleting the "db" directory will wipe the database and it will be reconstructed on the next run with default data.
*/
import java.util.*;
public class Main
{
	public static void main(String[] args)
	{
		UI ui = UI.getInstance();
		if(args.length > 0 && args[0].equals("debug"))
		{
			ui.setDebug(true);
		}

		AnimalDB animalDB = AnimalDB.getInstance();
		animalDB.init();

		List<Integer> animals = null;

		int menu = 0;
		do
		{
			ui.print("\n[1] Inspect Animal\n[2] Add Animal\n[3] Remove Animal\n[X] Exit\n");

			switch(ui.getChar(""))
			{
				case "1":
					ui.print("Which one? (0 to cancel)\n");
					animals = animalDB.getAllIds();
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
					while(menu > (animals.size()));
					if(menu > 0)
					{
						ui.print(animalDB.getById(animals.get(menu-1)) + "\n");
					}
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
					break;

					case "3":
						ui.print("Which one? (0 to cancel)\n");
						animals = animalDB.getAllIds();
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
						while(menu > (animals.size()));
						if(menu > 0)
						{
							animalDB.delete(animals.get(menu-1));
						}
						break;

				case "X":
					menu = -1;
			}
		}
		while(menu != -1);
		ui.print("Goodbye.");
	}
}
