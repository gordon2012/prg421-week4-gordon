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
//				+ "id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
				+ "id int, "
				+ "name varchar(40), "
				+ "color varchar(40), "
				+ "legs int, "
				+ "arms int, "
				+ "tails int, "
				+ "burrows boolean, "
				+ "swims boolean, "
				+ "flies boolean)");
			System.out.println("Table created");
		}
		catch(SQLException e)
		{
			System.out.println("Table NOT created: " + e.getMessage());
		}

		String qry = "INSERT INTO Animals VALUES (1, 'Worm', 'Red', 0, 0, 1, true, true, false)";
		Statement s2;
		try
		{
			s2 = conn.createStatement();
			s2.execute(qry);
			System.out.println("Row created");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
			// PreparedStatement ps = conn.prepareStatement("INSERT INTO Animals VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			// ps.



	}

	List<String> listAnimals()
	{
		// Statement s;
		List<String> result = new ArrayList<String>();
		try
		{
			// s = conn.createStatement();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Animals");
			while(rs.next())
			{
				String res = "";
				res+= rs.getInt("id") + "\t";
				res+= rs.getString("name") + "\t";
				res+= rs.getString("color") + "\t";
				res+= rs.getInt("legs") + "\t";
				res+= rs.getInt("arms") + "\t";
				res+= rs.getInt("tails") + "\t";
				res+= rs.getBoolean("burrows") + "\t";
				res+= rs.getBoolean("swims") + "\t";
				res+= rs.getBoolean("flies") + "\n";
				result.add(res);
			}

		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}



}
