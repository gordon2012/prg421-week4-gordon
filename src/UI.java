import java.util.Scanner;

public class UI
{
	private static final UI instance = new UI();
	private Scanner input;
	private boolean debug;

	private UI()
	{
		input = new Scanner(System.in);
		debug = false;
	}

	public static final UI getInstance()
	{
		return instance;
	}

	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}


	// Print methods
	//
	public void print(String s)
	{
		System.out.print(s);
	}

	public void print(Object o)
	{
		print(o.toString());
	}

	public void debug(String s)
	{
		if(debug) print(s);
	}

	public void debug(Object o)
	{
		debug(o.toString());
	}


	// User Input methods
	//
	public String getInput(String prompt)
	{
		print(prompt);
		print("> ");
		return input.nextLine();
	}

	public String getChar(String prompt)
	{
		return getInput(prompt).substring(0).toUpperCase();
	}

	public int getInt(String prompt)
	{
		int in;
		do
		{
			try
			{
				in = Integer.parseInt(getInput(prompt));
				return in;
			}
			catch(NumberFormatException e) {}
		}
		while(true);
	}

	public boolean getBool(String prompt)
	{
		return getInput(prompt).substring(0).toUpperCase().equals("Y");
	}
}
