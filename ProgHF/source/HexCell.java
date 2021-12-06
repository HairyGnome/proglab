import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class HexCell extends Cell
{
	static Polygon shape;
	
	static
	{
		shape = new Polygon();
		for(int i = 0; i < 6; i++)
		{
			shape.addPoint((int)(Math.round(40 + 40 * Math.cos(i*2*Math.PI/6))),
					(int)(Math.round(40 + 40 * Math.sin(i*2*Math.PI/6))));
		}
	}
	
	public HexCell(Simulator sim)
	{
		super(sim);	
	}
	
	public void invertState()
	{
		isCell = true;
		if(isAlive)
		{
			isAlive = false;
		}
		else
		{
			isAlive = true;
		}
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{	
		if(!isCell)
		{
			g.setColor(Color.WHITE);
		}
		else
		{			
			if(isAlive)
			{
				g.setColor(Color.GREEN);
			}
			else
			{
				g.setColor(Color.RED);
			}
		}
		g.fillPolygon(shape);
		g.setColor(Color.BLACK);
		g.drawPolygon(shape);
	}

	@Override
	public void calculateAdjacentCells(List<Cell> cells)
	{
		if(!isCell) { return; }
		for(Cell cell : cells)
		{
			if(cell == this) { continue; }
			if(!cell.isCell) { continue; }
			if((Math.abs(this.getX() - cell.getX()) == 60 && Math.abs(this.getY() - cell.getY()) == 35) ||
					(Math.abs(this.getX() - cell.getX()) == 0 && Math.abs(this.getY() - cell.getY()) == 70))
			{
				this.adjacentCells.add(cell);
			}
		}
	}
	
	
	
	@Override
	public boolean contains(int x, int y)
	{
		return shape.contains(x,y);
	}
	
	
}
