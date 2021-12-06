import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RectGridTest
{
	Simulator sim;
	
	@BeforeEach
	public void setUp()
	{
		List<Integer> birthList = new ArrayList<Integer>();
		List<Integer> deathList = new ArrayList<Integer>();
		
		birthList.add(3);
		
		deathList.add(2);
		deathList.add(3);
		
		sim = new Simulator(birthList, deathList);
	}
	
	@Test
	public void testNotPrebuilt() throws Exception
	{
		Grid grid = new RectGrid(sim, false);
		for(Cell cell : sim.cells)
		{
			Assert.assertFalse(cell.isCell);
		}
	}
	
	@Test
	public void testPrebuilt() throws Exception
	{
		Grid grid = new RectGrid(sim, true);
		for(Cell cell : sim.cells)
		{
			Assert.assertFalse(cell.isAlive);
			Assert.assertTrue(cell.isCell);
		}
	}
	
}
