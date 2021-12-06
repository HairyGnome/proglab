import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class HexGrid extends JPanel implements Grid
{
	public HexGrid(Simulator sim, boolean preBuild)
	{	
		super();
		
		this.setPreferredSize(new Dimension(840, 800));
		this.setLayout(null);
		
		init(sim, preBuild);
		
	}
	
	public void init(Simulator sim, boolean preBuild)
	{
		for(int i = -40; i < 840; i+=120)
		{
			for(int j = -40; j < 800; j += 70)
			{
				HexCell newCell = new HexCell(sim);
				newCell.setBounds(i, j, 80, 80);
				this.add(newCell);
				
				if(!preBuild) { continue; }
				newCell.invertState();
			}
		}
		
		for(int i = 20; i < 840; i+=120)
		{
			for(int j = -5; j < 800; j += 70)
			{
				HexCell newCell = new HexCell(sim);
				newCell.setBounds(i, j, 80, 80);
				this.add(newCell);
				if(!preBuild) { continue; }
				newCell.invertState();
			}
		}
	}
}
