import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class SettingsTest
{

	Simulator sim;
	SettingsPanel settings;
	
	@BeforeEach
	public void setUp()
	{
		List<Integer> birthList = new ArrayList<Integer>();
		List<Integer> deathList = new ArrayList<Integer>();
		
		birthList.add(3);
		
		deathList.add(2);
		deathList.add(3);
		Grid grid = new RectGrid(sim);
		sim = new Simulator(birthList, deathList);
		settings = new SettingsPanel(sim,grid);
	}
	
	@Test
	public void testBeforeStart()
	{
		org.junit.Assert.assertEquals(-1, sim.getSpeed());
	}
	
	@Test
	public void testStartButton()
	{
		settings.startButton.doClick();
		org.junit.Assert.assertEquals(0, sim.getSpeed());
	}
	
	@Test
	public void testOneTimesButton()
	{
		settings.startButton.doClick();
		settings.oneTimes.doClick();
		org.junit.Assert.assertEquals(1, sim.getSpeed());
	}
	
	@Test
	public void testTwoTimesButton()
	{
		settings.startButton.doClick();
		settings.twoTimes.doClick();
		org.junit.Assert.assertEquals(2, sim.getSpeed());
	}
	
	@Test
	public void testFourTimesButton()
	{
		settings.startButton.doClick();
		settings.fourTimes.doClick();
		org.junit.Assert.assertEquals(4, sim.getSpeed());
	}
	
	@Test
	public void testEightTimesButton()
	{
		settings.startButton.doClick();
		settings.eightTimes.doClick();
		org.junit.Assert.assertEquals(8, sim.getSpeed());
	}

}
