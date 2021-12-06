import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileHandler
{
	public FileHandler() {}
	
	@SuppressWarnings("resource")
	public void save(Simulator sim, Grid grid, String filename)
	{
		try
		{
			FileOutputStream f = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(f);
			List<Serializable> forSave = new ArrayList<Serializable>();
			sim.emptyAdjacents();
			forSave.add(sim);
			forSave.add(grid);
			out.writeObject(forSave);
			out.close();
		}
		catch(IOException ex)
		{
			System.out.println("Saving failed");
		}
	}
	
	public List<Serializable> load(String filename)
	{
		try
		{
			FileInputStream f = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(f);
			List<Serializable> loaded;
			loaded = (List<Serializable>) in.readObject();
			in.close();
			return loaded;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			return null;
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			return null;
		}
		catch(ClassCastException ex)
		{
			ex.printStackTrace();
			return null;
		}

	}
}
