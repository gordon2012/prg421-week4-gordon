import java.util.*;
import java.sql.*;
public class AnimalDB
{
	private static final AnimalDB instance = new AnimalDB();

	private Connection conn;

	private AnimalDB()
	{
		// init?
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
			System.out.println("Driver registered");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}

		try
		{
			conn = DriverManager.getConnection("jdbc:derby:db/AnimalDB;create=true");
			System.out.println("Connection established");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
			// auto increment
			// http://stackoverflow.com/a/4212603

			// table exists?
			// http://stackoverflow.com/a/5866339



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
			System.out.println("Table created");


			insert(new Animal("Lion", "Gold", 4, 0, 1, false, true, false));

			// conn.createStatement().execute("INSERT INTO Animals VALUES (, 'Worm', 'Red', 0, 0, 1, true, true, false)");
			// System.out.println("Row created");
			// conn.createStatement().execute("INSERT INTO Animals VALUES ('Lion', 'Gold', 4, 0, 1, false, true, false)");
			// System.out.println("Row created");
			// conn.createStatement().execute("INSERT INTO Animals VALUES ('Fish', 'Green', 0, 0, 1, false, true, false)");
			// System.out.println("Row created");
		}
		catch(SQLException e)
		{
			System.out.println("Table NOT created: " + e.getMessage());
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
		// String qry = "INSERT INTO Animals VALUES (1, 'Worm', 'Red', 0, 0, 1, true, true, false)";
		// Statement s2;
		// try
		// {
		// 	s2 = conn.createStatement();
		// 	s2.execute(qry);
		// 	System.out.println("Row created");
		// }
		// catch(Exception e)
		// {
		// 	System.out.println("ERROR: " + e.getMessage());
		// }



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





}
