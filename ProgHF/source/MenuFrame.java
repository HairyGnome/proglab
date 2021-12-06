import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MenuFrame extends JFrame
{
	
	JPanel menu;
	JTextField birthField;
	JTextField deathField;
	JTextField filenameField;
	JComboBox<String> typeBox;
	JComboBox<String> prebuildBox;
	
	public MenuFrame()
	{
	    menu = new JPanel();

		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		JLabel birthLabel = new JLabel("Birth: ");
		JLabel deathLabel = new JLabel("Survival: ");
		
		birthField = new JTextField("Please specify number of adjacent cells required for the birth of a new cell (separate by white space, defaults to 3)");
		deathField = new JTextField("Please specify number of adjacent cells required for the survival of a cell (separate by white space, defaults to 2 3)");
		
		JPanel birthPanel = new JPanel();
		JPanel deathPanel = new JPanel();
		
		birthPanel.add(birthLabel);
		birthPanel.add(birthField);
		deathPanel.add(deathLabel);
		deathPanel.add(deathField);
		
		menu.add(birthPanel);
		menu.add(deathPanel);
		
		JLabel prebuildLabel = new JLabel("Prebuild fields: ");
		prebuildBox = new JComboBox<String>(new String[] {"True", "False"});
		
		JPanel prebuildPanel = new JPanel();
		
		prebuildPanel.add(prebuildLabel);
		prebuildPanel.add(prebuildBox);
		
		menu.add(prebuildPanel);
		
		JLabel typeLabel = new JLabel("Field type: ");
		typeBox = new JComboBox<String>(new String[]{"Rectangle", "Hexagon"});
		
		JPanel typePanel = new JPanel();
				
		typePanel.add(typeLabel);
		typePanel.add(typeBox);
		
		menu.add(typePanel);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new StartButtonListener());
		menu.add(startButton, BorderLayout.SOUTH);
		
		JLabel filenameLabel = new JLabel("Filename: ");
		filenameField = new JTextField();
		filenameField.setColumns(30);
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadButtonListener());
		
		JPanel loadPanel = new JPanel();
		loadPanel.add(filenameLabel);
		loadPanel.add(filenameField);
		loadPanel.add(loadButton);
		menu.add(loadPanel);
		
		this.add(menu, BorderLayout.CENTER);
		
		this.setPreferredSize(new Dimension(750,400));
		this.setResizable(false);
		
		this.pack();
		
		this.setTitle("Game of Life");
	}
	
	public List<Integer> getBirthNumber()
	{
		java.util.List<Integer> birthList = new ArrayList<Integer>();
		try
		{
			for(String text : birthField.getText().split(" "))
			{
				birthList.add(Integer.parseInt(text));
			}
		}
		catch(Exception ex)
		{
			birthList = new ArrayList<Integer>();
			birthList.add(3);
		}
		return birthList;
	}
	
	public List<Integer> getDeathNumber()
	{
		java.util.List<Integer> deathList = new ArrayList<Integer>();
		try
		{
			for(String text : deathField.getText().split(" "))
			{
				deathList.add(Integer.parseInt(text));
			}
		}
		catch(Exception ex)
		{
			deathList = new ArrayList<Integer>();
			deathList.add(2);
			deathList.add(3);
		}
		return deathList;
	}
	
	
	public static void main(String[] args)
	{
		MenuFrame menu = new MenuFrame();
		menu.setVisible(true);
	}
	
	class StartButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			JButton button = (JButton)e.getSource();
			JFrame window = (JFrame)SwingUtilities.windowForComponent( button );
			
			boolean preBuild = Boolean.parseBoolean((String) prebuildBox.getSelectedItem());
			String type = (String) typeBox.getSelectedItem();
			
			JFrame frame = new AppFrame(getBirthNumber(), getDeathNumber(), preBuild, type);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			window.dispose();
		}
	}
	
	class LoadButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();
			JFrame window = (JFrame)SwingUtilities.windowForComponent( button );
			
			FileHandler handler = new FileHandler();
			List<Serializable> loaded = handler.load(filenameField.getText());
			
			if(loaded.get(0) == null) { return; }
			if(loaded.get(1) == null) { return; }
			
			JFrame frame = new AppFrame((Simulator)loaded.get(0), (Grid)loaded.get(1));
			frame.setVisible(true);
			window.dispose();
		}
		
	}
}
