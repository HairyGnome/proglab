import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsPanel extends JPanel
{
	Grid grid;
	
	Simulator sim;
	
	JButton stepButton;
	JButton startButton;
	JButton zeroTimes;
	JButton oneTimes;
	JButton twoTimes;
	JButton fourTimes;
	JButton eightTimes;
	
	JTextField filenameField;
	
	public SettingsPanel(Simulator sim, Grid grid)
	{
		this.sim = sim;
		
		this.grid = grid;
		
		this.setSize(200, 600);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		zeroTimes = new JButton("0x");
		oneTimes = new JButton("1x");
		twoTimes = new JButton("2x");
		fourTimes = new JButton("4x");
		eightTimes = new JButton("8x");
		
		zeroTimes.setEnabled(false);
		oneTimes.setEnabled(false);
		twoTimes.setEnabled(false);
		fourTimes.setEnabled(false);
		eightTimes.setEnabled(false);
		
		zeroTimes.addActionListener(new TimeButtonListener(0));
		oneTimes.addActionListener(new TimeButtonListener(1));
		twoTimes.addActionListener(new TimeButtonListener(2));
		fourTimes.addActionListener(new TimeButtonListener(4));
		eightTimes.addActionListener(new TimeButtonListener(8));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(zeroTimes);
		buttonsPanel.add(oneTimes);
		buttonsPanel.add(twoTimes);
		buttonsPanel.add(fourTimes);
		buttonsPanel.add(eightTimes);
		
		
		stepButton = new JButton("Step");
		stepButton.addActionListener(new StepButtonListener());
		stepButton.setEnabled(false);
		
		buttonsPanel.add(stepButton);
		
		startButton = new JButton("Start");
		startButton.addActionListener(new StartButtonListener());
		buttonsPanel.add(startButton);
		
		JLabel filenameLabel = new JLabel("Filename: ");
		filenameField = new JTextField();
		filenameField.setColumns(30);
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());
		
		JPanel savePanel = new JPanel();
		savePanel.add(filenameLabel);
		savePanel.add(filenameField);
		savePanel.add(saveButton);
		
		this.add(buttonsPanel);
		this.add(savePanel);
	}
	
	class TimeButtonListener implements ActionListener
	{
		int speed;
		
		public TimeButtonListener(int speed)
		{
			this.speed = speed;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(sim.getSpeed() < 0) { return; }
			sim.setSpeed(speed);
			sim.interrupt();
		}
		
	}
	
	class StartButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			sim.setSpeed(0);
			sim.calculateAdjacentCells();
			sim.start();
			JButton button = (JButton)e.getSource();
			stepButton.setEnabled(true);
			zeroTimes.setEnabled(true);
			oneTimes.setEnabled(true);
			twoTimes.setEnabled(true);
			fourTimes.setEnabled(true);
			eightTimes.setEnabled(true);
			button.setEnabled(false);
		}
	}
	
	class StepButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(sim.getSpeed() != 0) { return; }
			
			sim.step();
		}
		
	}
	
	class SaveButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(sim.getSpeed() > 0) { return; }
			FileHandler handler = new FileHandler();
			handler.save(sim, grid, filenameField.getText());
		}
		
	}
}
