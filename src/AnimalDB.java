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

			conn = DriverManager.getConnection("jdbc:derby:db/AnimalDB;create=true");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
