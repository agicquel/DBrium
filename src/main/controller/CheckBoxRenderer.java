package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer
{
	public CheckBoxRenderer()
	{
		setHorizontalAlignment(JLabel.CENTER);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if (isSelected)
		{
			setForeground(table.getSelectionForeground());
            //super.setBackground(table.getSelectionBackground());
            setBackground(table.getSelectionBackground());
        }
        else
        {
        	setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        
        setSelected((value != null && ((Boolean) value).booleanValue()));
        return this;
    }
}