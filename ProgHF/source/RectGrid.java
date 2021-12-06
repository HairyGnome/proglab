import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

public class RectGrid extends JPanel implements Grid
{
	public RectGrid(Simulator sim, boolean preBuild)
	{	
		super();
		this.setPreferredSize(new Dimension(840, 800));
		this.setLayout(new GridLayout(20, 21));
		init(sim, preBuild);
	}
	
	public RectGrid(Simulator sim)
	{
		this.setPreferredSize(new Dimension(840, 800));
		this.setLayout(new GridLayout(20, 21));
	}
	
	public void init(Simulator sim, boolean preBuild)
	{
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 21; j++)
			{
				RectCell newCell = new RectCell(sim);
				this.add(newCell, i, j);
				
				if(!preBuild) { continue; }
				newCell.invertState();
			}
		}
	}
}
