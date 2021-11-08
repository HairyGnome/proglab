package swingmvclab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel
{

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();

	@Override
	public int getRowCount()
	{
		return students.size();
	}

	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		 Student student = students.get(rowIndex);
		 switch(columnIndex)
		 {
		 	case 0: return student.getName();
		 	case 1: return student.getNeptun();
		 	case 2: return student.hasSignature();
		 	default: return student.getGrade();
		 }
	}
	
	@Override
	public String getColumnName(int column)
	{
		switch(column)
		{
			case 0: return "Név";
			case 1: return "Neptun";
			case 2: return "Aláírás";
			default: return "Jegy";
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		switch(columnIndex)
		{
			case 0: return String.class;
			case 1: return String.class;
			case 2: return Boolean.class;
			default: return Integer.class;
		}
	}
    
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		switch(columnIndex)
		{
			case 0: return false;
			case 1: return false;
			case 2: return true;
			default: return true;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		Student student = students.get(rowIndex);
		switch(columnIndex)
		{
			case 0:
				student.setName((String)aValue);
				break;
			case 1:
				student.setNeptun((String)aValue);
				break;
			case 2:
				student.setSignature((Boolean)aValue);
				break;
			default:
				student.setGrade((Integer)aValue);
				break;
		}
	}
	
	public void addStudent(String name, String neptun)
	{
		students.add(new Student(name, neptun, false, 0));
		super.fireTableDataChanged();
	}
}
