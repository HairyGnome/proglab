import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class RectCell extends Cell
{
	
	static Polygon shape;
	static
	{
		shape = new Polygon();
		shape.addPoint(0,0);
		shape.addPoint(0,40);
		shape.addPoint(40,40);
		shape.addPoint(40,0);
	}
	
	public RectCell(Simulator sim)
	{
		super(sim);
	}	
	
	public void calculateAdjacentCells(List<Cell> cells)
	{
		for(Cell cell : cells)
		{
			if(cell == this) { continue; }
			if(!cell.isCell) { continue; }
			if(Math.abs(this.getX() - cell.getX()) <= 40 && Math.abs(this.getY() - cell.getY()) <= 40)
			{
				this.adjacentCells.add((RectCell) cell);
			}
		}
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
	public boolean contains(int x, int y)
	{
		return shape.contains(x, y);
	}
}
