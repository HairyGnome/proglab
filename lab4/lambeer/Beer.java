package lambeer;


import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Beer implements Serializable, Comparable<Beer>
{
	String name;
	String style;
	double strength;
	
	public static ArrayList<Beer> beers = new ArrayList<Beer>();
	
	static HashMap<String, Comparator<Beer>> comps;
	static
	{
		comps = new HashMap<String, Comparator<Beer>>();
		comps.put("name", Comparator.comparing(Beer::GetName));
		comps.put("style",  Comparator.comparing(Beer::GetStyle));
		comps.put("strength",  Comparator.comparing(Beer::GetStrength));		
	}
	
	static List<String> Iparams;
	static
	{
		Iparams = new LinkedList<String>();
		for(String key : comps.keySet())
		{
			Iparams.add(key);
		}
	}

	Beer(String name, String style, double strength)
	{
		this.name = name;
		this.style = style;
		this.strength = strength;
	}
	
	public String GetName()
	{
		return name;
	}
	
	public String GetStyle()
	{
		return style;
	}
	
	public double GetStrength()
	{
		return strength;
	}
	
	@Override
	public String toString()
	{
		String string = "Name: " + name + ", Style: " + style + ", Strength: " + strength;
		return string;
	}
	
	public static void add(String[] cmd)
	{
		Beer.beers.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
	}
	
	public static void list(String[] cmd)
	{

		ArrayList<Beer> tmp = new ArrayList<Beer>();
		for(Beer beer : Beer.beers)
		{
			tmp.add(beer);
		}
		/*if(cmd[1].equals("name"))
		{
			tmp.sort((s0,s1) -> s0.GetName().toLowerCase().compareTo(s1.GetName().toLowerCase()));
		}
		else
		{
			if(cmd[1].equals("style"))
			{
				tmp.sort((s0, s1) -> s0.GetStyle().toLowerCase().compareTo(s1.GetStyle().toLowerCase()));
			}
			else
			{
				if(cmd[1].equals("strength"))
				{
					tmp.sort((s0, s1) -> Double.compare(s0.GetStrength(), s1.GetStrength()));
				}
			}
		}*/
		tmp.sort((s0,s1) -> 
		{
			for(String param : cmd)
			{
				if(comps.containsKey(param) && comps.get(param).compare(s0,s1) != 0)
				{
					return comps.get(param).compare(s0, s1);
				}
			}
			return 0;
		});
		//tmp.sort(comps.get(Iparams.get(0)).thenComparing(comps.get(Iparams.get(1))).thenComparing(comps.get(Iparams.get(2))));
		for(int idx = 0; idx < Beer.beers.size(); idx++)
		{
			System.out.println(tmp.get(idx).toString());
		}
	}
	
	public static void list()
	{
		for(int idx = 0; idx < Beer.beers.size(); idx++)
		{
			System.out.println(Beer.beers.get(idx).toString());
		}
	}
	
	public static void save(String[] cmd) throws IOException
	{
		try
		{	
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(cmd[1]));
			writer.writeObject(Beer.beers);
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Could not save to file");
			return;
		}
		System.out.println("File saved");
	}
	
	public static void load(String[] cmd) throws IOException
	{
		try
		{
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(cmd[1]));
			Beer.beers = (ArrayList<Beer>)reader.readObject();
			reader.close();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Unable to load from file");
			return;
		}
		catch(IOException e)
		{
			System.out.println("Unable to load file");
			return;
		}
		System.out.println("File loaded");
	}
	
	public static void search(String[] cmd)
	{
		for(Beer beer : Beer.beers)
		{
			if(beer.name.equals(cmd[1]))
			{
				System.out.println(beer.toString());
			}
		}
	}
	
	public static void find(String[] cmd)
	{
		for(Beer beer : Beer.beers)
		{
			if(beer.name.contains(cmd[1]))
			{
				System.out.println(beer.toString());
			}
		}
	}
	
	public static void delete(String[] cmd)
	{
		Iterator<Beer> iterator = Beer.beers.iterator();
	    while (iterator.hasNext())
	    {
	        if(iterator.next().name.equals(cmd[1]))
	        {
	        	iterator.remove();
	        }
	    }
	    System.out.println("Item removed successfully");
	}
	

	@Override
	public int compareTo(Beer o) {
		return this.name.compareTo(o.GetName());
	}
	
}
