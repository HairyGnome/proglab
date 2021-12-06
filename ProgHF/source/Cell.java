import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class Cell extends JButton implements Serializable
{
	boolean isCell;
	boolean isAlive;
	int adjAlive;
	List<Cell> adjacentCells;
	Simulator sim;
	
	public Cell(Simulator sim)
	{
		this.adjAlive = 0;
		isAlive = true;
		adjacentCells = new ArrayList<Cell>();
		isCell = false;
		
		this.sim = sim;
		
		this.sim.addCell(this);
		
		this.addActionListener(new ButtonListener());

		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.setOpaque(false);
	}
	
	public abstract void calculateAdjacentCells(List<Cell> cells);
	
	public void calculateAdjAlive()
	{
		if(!isCell) { return; }
		this.adjAlive = 0;
		for(Cell cell : adjacentCells)
		{
			if(!(cell.isCell)) { continue; }
			
			if(cell.isAlive)
			{
				this.adjAlive++;
			}
		}
	}
	
	public abstract void invertState();
	
	
	public void doStep()
	{
		if(!isCell) { return; }
		
		if(isAlive)
		{
			if(!sim.getDeath().contains(adjAlive))
			{
				invertState();
			}
		}
		else
		{
			if(sim.getBirth().contains(adjAlive))
			{
				invertState();
			}

		}
	}
	
	class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(sim.getSpeed() >= 0) { return; }
			Cell clickedCell = (Cell)e.getSource();
			clickedCell.invertState();
		}
	}
}
