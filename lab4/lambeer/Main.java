package lambeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main
{
	
	static HashMap<String, Command> commands;
	
	static
	{
		commands = new HashMap<String, Command>();
		commands.put("add", Main::addCommand);
		commands.put("list", Main::listCommand);
		commands.put("save", Main::saveCommand);
		commands.put("load", Main::loadCommand);
		commands.put("seach", Main::searchCommand);
		commands.put("find", Main::findCommand);
		commands.put("delete", Main::deleteCommand);
		commands.put("exit", Main::exitCommand);
	}
	
	public static void listCommand(String[] cmd)
	{
		if(cmd.length < 2)
		{		    			
			Beer.list();
		}
		else
		{
			Beer.list(cmd);
		}
	}
	
	public static void addCommand(String[] cmd)
	{
		if(cmd.length < 4) { return; }
    	Beer.add(cmd);
	}
	
	public static void saveCommand(String[] cmd)
	{
		if(cmd.length < 2) { return; }
		try
		{			
			Beer.save(cmd);
		}
		catch(IOException e)
		{
			return;
		}
	}
	
	public static void loadCommand(String[] cmd)
	{
		if(cmd.length < 2) { return; } 
		try
		{			
			Beer.load(cmd);
		}
		catch(IOException e)
		{
			return;
		}
	}
	
	public static void searchCommand(String[] cmd)
	{
		if(cmd.length < 2) { return; }
		Beer.search(cmd);
	}
	
	public static void findCommand(String[] cmd)
	{
		if(cmd.length < 2) { return; }
		Beer.find(cmd);
	}
	
	public static void deleteCommand(String[] cmd)
	{
		if(cmd.length < 2) { return; }
		Beer.delete(cmd);
	}
	
	public static void exitCommand(String[] cmd)
	{
		System.exit(0);
	}
	
	public static void main(String[] args)  throws IOException
	{		
		
		Beer beer0 = new Beer("Borsodi","barna",25);
		Beer beer1 = new Beer("Heineken", "vilagos", 0.1);
		Beer beer2 = new Beer("Soproni", "IPA", 5.7);
		Beer beer3 = new Beer("Staropramen", "vilagos", 6.3);
		Beer beer4 = new Beer("Asahi", "vilagos", 4.2);
		Beer beer5 = new Beer("Faxe", "vilagos", 9.8);
		Beer beer6 = new Beer("Faxe", "barna", 13.5);
		Beer beer7 = new Beer("Faxe", "barna", 1);
		Beer beer8 = new Beer("Faxe", "vilagos", 4.6);
		
		Beer.beers.add(beer0);
		Beer.beers.add(beer1);
		Beer.beers.add(beer2);
		Beer.beers.add(beer3);
		Beer.beers.add(beer4);
		Beer.beers.add(beer5);
		Beer.beers.add(beer6);
		Beer.beers.add(beer7);
		Beer.beers.add(beer8);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    
	    String[] cmd;
	    String command;
	    
		while(true)
	    {
	    	command = reader.readLine();
		    cmd = command.split(" ");
		    if(!commands.containsKey(cmd[0]))
		    {
		    	System.out.println("Unknown command");
		    	continue;
		    }
		    commands.get(cmd[0]).execute(cmd);
	    }
	}
}