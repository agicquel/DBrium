package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import javax.swing.*;
import java.awt.*;
import javax.swing.DefaultListModel;

public class MonRenderer extends JLabel implements ListCellRenderer<Object>
{
    public MonRenderer()
    {
        setOpaque(true);
    }

    public Component getListCellRendererComponent (JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {

        //recuperer l'indice
        //int ind = ((Integer)value).intValue();
        
        setIcon(new ImageIcon("Image/table.png"));

        setText("" + list.getModel().getElementAt(index));

        if (isSelected) {

            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());

        } else {

            setBackground(Color.white);
            setForeground(list.getForeground());
        }

        return this;
    }
}