package swingmvclab;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/*
 * A megjelenítendõ ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    
    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    private JTextField nameField;
    private JTextField neptunField;
    private JButton addButton;
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     * (táblázat, beviteli mezõ, gomb).
     */
    private void initComponents()
    {
        this.setLayout(new BorderLayout());
        
        JTable table = new JTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(new JTable().getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(new JTable().getDefaultRenderer(Boolean.class)));
        table.setDefaultRenderer(Integer.class, new StudentTableCellRenderer(new JTable().getDefaultRenderer(Integer.class)));
        this.add(scrollPane, BorderLayout.CENTER);
        
        addButton = new JButton("Felvesz");
        addButton.addActionListener(new addButtonListener());
        
        nameField = new JTextField();
        neptunField = new JTextField();
        
        nameField.setColumns(20);
        neptunField.setColumns(6);
        
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Név:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Neptun:"));
        inputPanel.add(neptunField);
        inputPanel.add(addButton);
        
        this.add(inputPanel, BorderLayout.SOUTH);
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatói nyilvántartás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }
    
    class addButtonListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			data.addStudent(nameField.getText(), neptunField.getText());
		}
    	
    }
    
    class StudentTableCellRenderer implements TableCellRenderer
    {

    	 private TableCellRenderer renderer;

    	 public StudentTableCellRenderer(TableCellRenderer defRenderer)
    	 {
    		 this.renderer = defRenderer;
    	 }
    	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    	 {
    			 Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    			 Student student = data.students.get(table.getRowSorter().convertRowIndexToModel(row));
    			 if(student.getGrade() < 2 || !student.hasSignature())
    			 {
    				 component.setBackground(Color.RED);
    			 }
    			 else
    			 {
    				 component.setBackground(Color.GREEN);    				 
    			 }
    			 // component.setBackground(...)
    			 return component;
    	 }
    }
    
    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
