import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator extends Thread implements Serializable
{
	java.util.List<Integer> birth;
	java.util.List<Integer> death;
	int speed;
	List<Cell> cells;
	final HashMap<Integer, Integer> WAIT_TIMES = new HashMap<Integer, Integer>(){{
			put(0,Integer.MAX_VALUE);
			put(1,1600);
			put(2,800);
			put(4,400);
			put(8,200);
	}};
	
	public Simulator(java.util.List<Integer> birth, java.util.List<Integer> death)
	{
		this.birth = birth;
		this.death = death;
		this.speed = -1;
		this.cells = new ArrayList<Cell>();
	}
	public void addCell(Cell cell)
	{
		cells.add(cell);
	}
	public void emptyAdjacents()
	{
		for(Cell cell : this.cells)
		{
			cell.adjacentCells = new ArrayList<Cell>();
		}
	}
	public void step()
	{
		for(Cell cell : cells)
		{
			cell.calculateAdjAlive();
		}
		for(Cell cell: cells)
		{
			cell.doStep();
		}
	}
	
	public void calculateAdjacentCells()
	{
		for(Cell cell : cells)
		{
			cell.calculateAdjacentCells(cells);
		}
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			synchronized(this)
			{
				try
				{	
					this.wait(WAIT_TIMES.get(speed));
				}
				catch(InterruptedException ex)
				{
					continue;
				}
			}
			step();
		}
	}
	
	public List<Integer> getBirth()
	{
		return birth;
	}
	
	public List<Integer> getDeath()
	{
		return death;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
}
