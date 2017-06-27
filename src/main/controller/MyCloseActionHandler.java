package controller;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
* Listener used to close tabbed pane
*/
public class MyCloseActionHandler implements ActionListener {

    /**
    * The tabbed pane
    */
	JTabbedPane tabPane;

    /**
    *  
    */
	int index;

    /**
    * Constructor
    * @param tabPane The tabbed pane
    * @param index The index of the tabbed pane
    */
	public MyCloseActionHandler(JTabbedPane tabPane, int index) {

		this.tabPane = tabPane;
		this.index = index;
	}

    @Override
    public void actionPerformed(ActionEvent evt) {

        Component selected = tabPane.getSelectedComponent();
        if (selected != null)
        {

            tabPane.remove(selected);
            if (index > 0)
            	index--;
        }

    }

}