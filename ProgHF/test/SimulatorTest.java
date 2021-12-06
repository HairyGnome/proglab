import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimulatorTest
{

	Simulator sim;
	
	@BeforeEach
	public void setUp()
	{
		List<Integer> birth = new ArrayList<Integer>();
		List<Integer> death = new ArrayList<Integer>();
		birth.add(1);
		birth.add(2);
		birth.add(3);
		sim = new Simulator(birth, death);
		
	}
	
	@Test
	void testAdding()
	{
		org.junit.Assert.assertEquals(0, sim.cells.size());
		Cell newCell = new RectCell(sim);
		org.junit.Assert.assertEquals(1, sim.cells.size());
	}
	
	@Test
	public void testAddingII()
	{
		org.junit.Assert.assertEquals(0, sim.cells.size());
		Grid grid = new RectGrid(sim, false);
		org.junit.Assert.assertEquals(420, sim.cells.size());
	}
	
	@Test
	public void testCalculate()
	{
		Grid grid = new RectGrid(sim, true);
		AppFrame frame = new AppFrame(sim, grid);
		sim.calculateAdjacentCells();
		org.junit.Assert.assertEquals(3, sim.cells.get(0).adjacentCells.size());
	}
	
	@Test
	public void testCalculateAdjAlive()
	{
		Grid grid = new RectGrid(sim, true);
		AppFrame frame = new AppFrame(sim, grid);
		sim.calculateAdjacentCells();
		sim.cells.get(1).invertState();
		sim.cells.get(0).calculateAdjAlive();
		org.junit.Assert.assertEquals(1, sim.cells.get(0).adjAlive);
	}
	
	@Test
	public void testStep()
	{
		Grid grid = new RectGrid(sim, true);
		AppFrame frame = new AppFrame(sim, grid);
		sim.calculateAdjacentCells();
		sim.cells.get(1).invertState();
		sim.step();
		org.junit.Assert.assertTrue(sim.cells.get(0).isAlive);
	}
	
	@Test
	public void testClick()
	{
		Grid grid = new RectGrid(sim, false);
		AppFrame frame = new AppFrame(sim, grid);
		sim.cells.get(0).doClick();
		sim.cells.get(0).doClick();
		org.junit.Assert.assertTrue(sim.cells.get(0).isCell);
		org.junit.Assert.assertTrue(sim.cells.get(0).isAlive);
	}
	
	@Test
	public void testGetSpeed()
	{
		org.junit.Assert.assertEquals(-1, sim.getSpeed());
	}
	
	@Test
	public void testSetSpeed()
	{
		sim.setSpeed(2);
		org.junit.Assert.assertEquals(2, sim.getSpeed());
	}

}
