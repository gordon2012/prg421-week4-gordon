// http://stackoverflow.com/a/1066603
// Iterator it = animals.entrySet().iterator();

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

			switch(ui.getChar(""))
			{
				case "1":
					// Map<Integer, Animal> animals = animalDB.listAnimals();
					// for(Animal a : animals)

					List<Integer> animals = animalDB.getAllIds();
					// for(Integer id : animals){ui.print(id + "\n");}

					for(int i = 0; i < animals.size(); i++)
					{
						ui.print((i+1) + "(id: " + animals.get(i) + ") - " + animalDB.getById(animals.get(i)).getName() +"\n");
					}



					// while(it.hasNext())
					// {
					// 	Map.Entry pair = (Map.Entry)it.next();
					// 	Animal a = (Animal)pair.getValue();
					// 	ui.print(pair.getKey() + " - " + a.getName() + "\n");
					// 	it.remove();
					// }



					// for(int i = 0; i < animals.size(); i++)
					// {
					// 	ui.print(a);
					// }
					//
					// do
					// {
					//
					// }
					// while(false);

					// switch(ui.getChar(""))
					// {
					//
					// }

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
