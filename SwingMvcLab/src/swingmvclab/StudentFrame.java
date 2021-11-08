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
 * A megjelen�tend� ablakunk oszt�lya.
 */
public class StudentFrame extends JFrame {
    
    /*
     * Ebben az objektumban vannak a hallgat�i adatok.
     * A program indul�s ut�n bet�lti az adatokat f�jlb�l, bez�r�skor pedig kimenti oda.
     * 
     * NE M�DOS�TSD!
     */
    private StudentData data;
    private JTextField nameField;
    private JTextField neptunField;
    private JButton addButton;
    /*
     * Itt hozzuk l�tre �s adjuk hozz� az ablakunkhoz a k�l�nb�z� komponenseket
     * (t�bl�zat, beviteli mez�, gomb).
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
        inputPanel.add(new JLabel("N�v:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Neptun:"));
        inputPanel.add(neptunField);
        inputPanel.add(addButton);
        
        this.add(inputPanel, BorderLayout.SOUTH);
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE M�DOS�TSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgat�i nyilv�ntart�s");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Indul�skor bet�ltj�k az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bez�r�skor mentj�k az adatokat
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

        // Fel�p�tj�k az ablakot
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
     * A program bel�p�si pontja.
     * 
     * NE M�DOS�TSD!
     */
    public static void main(String[] args) {
        // Megjelen�tj�k az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
