package util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
* Renderer of JList
*/
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
        
        try
        {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/res/img/table.png"))));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

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