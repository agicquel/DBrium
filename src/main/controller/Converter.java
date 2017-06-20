package controller;

import model.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

/**
* Converter class with static methods only to convert data from model to view
*/
public class Converter
{

	/**
	* Convert a Result Object to JTable
	* @param res the result
	* @return the JTable containing data from result
	*/
	public static JTable convertResultToJTable(Result res)
	{
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel model = new DefaultTableModel() {
	      public Class getColumnClass(int columnIndex) {
	        Object o = getValueAt(0, columnIndex);
	        if (o == null) {
	          return Object.class;
	        } else {
	          return o.getClass();
	        }
	      }

	      public boolean isCellEditable(int rowIndex, int columnIndex) {
	      	return false;
	      }
	    };
	    JTable table = new JTable(model);

	    // Column first
	    for(int i = 0; i < res.getRow(0).getData().length; i++)
	    	model.addColumn(res.getRow(0).getAData(i).toString(), new Object[]{});

	    // Row after
	    for(int i = 1; i < res.getRows().size(); i++)
	    	model.addRow(res.getRow(i).getData());

	    // center table
	    for(int i = 0; i < table.getColumnCount(); i++)
	    	table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );

	    return table;
	}
}