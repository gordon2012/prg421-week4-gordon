// auto increment
// http://stackoverflow.com/a/4212603

// table exists?
// http://stackoverflow.com/a/5866339


import java.util.*;
import java.sql.*;
public class AnimalDB
{
	private static final AnimalDB instance = new AnimalDB();

	private Connection conn;
	private UI ui;

	private AnimalDB()
	{
		// init?
		ui = UI.getInstance();
	}

	public static final AnimalDB getInstance()
	{
		return instance;
	}

	public void init()
	{
		try
		{
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			ui.debug("Driver registered\n");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}

		try
		{
			conn = DriverManager.getConnection("jdbc:derby:db/AnimalDB;create=true");
			ui.debug("Connection established\n");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}



		Statement s;
		try
		{
			s = conn.createStatement();
			s.execute("CREATE TABLE Animals("
				+ "id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
				// + "id int, "
				+ "name varchar(40), "
				+ "color varchar(40), "
				+ "legs int, "
				+ "arms int, "
				+ "tails int, "
				+ "burrows boolean, "
				+ "swims boolean, "
				+ "flies boolean)");
			ui.debug("Table created\n");

			insert(new Animal("Lion", "Gold", 4, 0, 1, false, true, false));
			ui.debug("Row created\n");

			insert(new Animal("Tiger", "Yellow", 4, 0, 1, false, true, false));
			ui.debug("Row created\n");

			insert(new Animal("Bear", "Black", 2, 2, 0, false, true, false));
			ui.debug("Row created\n");

		}
		catch(SQLException e)
		{
			ui.debug("Table NOT created: " + e.getMessage() + "\n");
		}




	}

	public Map<Integer, Animal> listAnimals()
	{
		// Statement s;
		Map<Integer, Animal> animals = new HashMap<Integer, Animal>();
		try
		{
			// s = conn.createStatement();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Animals");
			while(rs.next())
			{
				// Animal animal = new Animal(
				animals.put(rs.getInt("id"), new Animal(
					rs.getString("name"),
					rs.getString("color"),
					rs.getInt("legs"),
					rs.getInt("arms"),
					rs.getInt("tails"),
					rs.getBoolean("burrows"),
					rs.getBoolean("swims"),
					rs.getBoolean("flies")
				));
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR(list): " + e.getMessage());
		}
		return animals;
	}


	public void insert(Animal animal)
	{
		try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Animals (name, color, legs, arms, tails, burrows, swims, flies) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"))
		{
			ps.setString(1, animal.getName());
			ps.setString(2, animal.getColor());
			ps.setInt(3, animal.getLegs());
			ps.setInt(4, animal.getArms());
			ps.setInt(5, animal.getTails());
			ps.setBoolean(6, animal.getBurrow());
			ps.setBoolean(7, animal.getSwim());
			ps.setBoolean(8, animal.getFly());
			ps.execute();
		}
		catch(Exception e)
		{
			System.out.println("ERROR(insert): " + e.getMessage());
		}

	}

	public List<Integer> getAllIds()
	{
		List<Integer> animals = new ArrayList<Integer>();
		try(ResultSet rs = conn.createStatement().executeQuery("SELECT id FROM Animals"))
		{
			while(rs.next())
			{
				animals.add(rs.getInt("id"));
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR(getAllIds): " + e.getMessage());
		}
		return animals;
	}


	public Animal getById(int id)
	{
		Animal animal = null;
		String qry = "SELECT * FROM Animals WHERE id = " + id;
		try(ResultSet rs = conn.createStatement().executeQuery(qry))
		{
			while(rs.next())
			{
				animal = new Animal(
					rs.getString("name"),
					rs.getString("color"),
					rs.getInt("legs"),
					rs.getInt("arms"),
					rs.getInt("tails"),
					rs.getBoolean("burrows"),
					rs.getBoolean("swims"),
					rs.getBoolean("flies")
				);
				// animals.add(rs.getInt("id"));
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR(getById): " + e.getMessage());
		}
		return animal;


		// return new Animal(
	}




}
