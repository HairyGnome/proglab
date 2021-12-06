import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame
{
	public AppFrame(List<Integer> birth, List<Integer> death, boolean preBuild, String type)
	{
		
		Grid grid;
		Simulator sim = new Simulator(birth, death);
		if(type.equals("Hexagon"))
		{			
			grid = new HexGrid(sim, preBuild);
		}
		else
		{
			grid = new RectGrid(sim, preBuild);			
		}
		
		this.setMinimumSize(new Dimension(1650,800));
		
		this.add((JPanel)grid, BorderLayout.WEST);
		
		SettingsPanel settings = new SettingsPanel(sim, grid);
		this.add(settings, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
	}
	
	public AppFrame(Simulator sim, Grid grid)
	{
		
		this.add((JPanel)grid, BorderLayout.WEST);
		
		SettingsPanel settings = new SettingsPanel(sim, grid);
		this.add(settings, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
	}
}
