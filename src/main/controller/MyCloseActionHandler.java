package controller;

import view.elements.*;
import view.interfaces.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class MyCloseActionHandler implements ActionListener {

	JTabbedPane tabPane;
	int index;

	public MyCloseActionHandler(JTabbedPane tabPane, int index) {

		this.tabPane = tabPane;
		this.index = index;
	}

    public void actionPerformed(ActionEvent evt) {

        Component selected = tabPane.getSelectedComponent();
        if (selected != null) {

            tabPane.remove(selected);
            if (index > 0) {
            	index--;
            }
            // It would probably be worthwhile getting the source
            // casting it back to a JButton and removing
            // the action handler reference ;)

        }

    }

}