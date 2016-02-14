import java.util.*;
import java.sql.*;
public class AnimalDB
{
	private static final AnimalDB instance = new AnimalDB();
	private Connection conn;
	private UI ui;


	private AnimalDB()
	{
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
			ui.print("ERROR(init-register): " + e.getMessage() + "\n");
		}

		try
		{
			conn = DriverManager.getConnection("jdbc:derby:db/AnimalDB;create=true");
			ui.debug("Connection established\n");
		}
		catch(Exception e)
		{
			ui.print("ERROR(init-connection): " + e.getMessage() + "\n");
		}

		try(Statement s = conn.createStatement())
		{
			s.execute("CREATE TABLE Animals("
				+ "id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
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
			ui.print("ERROR(insert): " + e.getMessage() + "\n");
		}
	}


	public void delete(int id)
	{
		String qry = "DELETE FROM Animals WHERE id=" + id;
		try(Statement s = conn.createStatement())
		{
			int count = s.executeUpdate(qry);
			ui.debug(count + " row(s) deleted\n");
		}
		catch(Exception e)
		{
			ui.print("ERROR(delete): " + e.getMessage() + "\n");
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
			ui.print("ERROR(getAllIds): " + e.getMessage() + "\n");
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
			}
		}
		catch(Exception e)
		{
			ui.print("ERROR(getById): " + e.getMessage() + "\n");
		}
		return animal;
	}
}
