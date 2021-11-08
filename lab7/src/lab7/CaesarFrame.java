package lab7;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;



public class CaesarFrame extends JFrame
{
	
	Object[] offsets = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U','V','W','X','Y','Z'};

	private JPanel upperPanel;
	private JPanel lowerPanel;
	private JTextField upperTextfield;
	private JTextField lowerTextfield;
	private JButton goButton;
	private JComboBox combobox;
	
	boolean upperFocus;
	
	public CaesarFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SwingLab");
		setSize(400,110);
		setResizable(false);
		
		//Define JTextFields
		upperTextfield = new JTextField();
		upperTextfield.setColumns(20);
		///upperTextfield.addKeyListener(new InputFieldKeyListener());
		upperTextfield.getDocument().addDocumentListener(new InputFieldKeyListener());;
		upperTextfield.addFocusListener(new InputFocusListener());
		lowerTextfield = new JTextField();
		lowerTextfield.setColumns(20);
		lowerTextfield.addFocusListener(new OutputFocusListener());
		///lowerTextfield.setEditable(false);
		
		//Define JButton
		goButton = new JButton("Code!");
		goButton.addActionListener(new OkButtonListener());
		
		//Define JComboBox
		combobox = new JComboBox(offsets);
		
		//Initialize upper JPanel
		upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());
		upperPanel.add(combobox);
		upperPanel.add(upperTextfield);
		upperPanel.add(goButton);
		this.add(upperPanel, BorderLayout.NORTH);
		
		//Initialize lower JPanel
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.add(new JLabel("Output:"));
		lowerPanel.add(lowerTextfield);
		this.add(lowerPanel, BorderLayout.SOUTH);
	}
	
	class OkButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{	
			if(upperFocus)
			{				
				lowerTextfield.setText(CaesarCipher.CaesarCode(upperTextfield.getText(), (char)(combobox.getSelectedItem())));
			}
			else
			{
				upperTextfield.setText(CaesarCipher.CaesarDecode(lowerTextfield.getText(), (char)(combobox.getSelectedItem())));
			}
		}
	}
	
	class InputFieldKeyListener /*extends KeyAdapter*/ implements DocumentListener
	{
		/*@Override
		public void keyTyped(KeyEvent e)
		{
			lowerTextfield.setText(CaesarCipher.CaesarCode(upperTextfield.getText(), (char)(combobox.getSelectedItem())));
		}*/

		@Override
		public void insertUpdate(DocumentEvent e)
		{
			// TODO Auto-generated method stub
			lowerTextfield.setText(CaesarCipher.CaesarCode(upperTextfield.getText(), (char)(combobox.getSelectedItem())));
			
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			// TODO Auto-generated method stub
			lowerTextfield.setText(CaesarCipher.CaesarCode(upperTextfield.getText(), (char)(combobox.getSelectedItem())));
			
		}

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			// TODO Auto-generated method stub	
		}
	}
	
	class InputFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			upperFocus = true;
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			
		}
	}
	
	class OutputFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			upperFocus = false;
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			
		}
	}
	
}
