package controller;

import model.ConnectDB;
import model.Table;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Simple way to pick a specific table from a 
* ConnectDB Object
* To use it, you have to initialize a TablePicker
* , show it, then get infos, and finnaly close it :
* TablePicker tp = new TablePicker(test);
* tp.showIt();
* tp.getTable(
* tp.getConnectDB()
* tp.close();
* @author Antoine Gicquel
*/
public class TablePicker implements ActionListener
{
	private ArrayList<ConnectDB> cons;
	private boolean done;

	private JFrame frame;
	private JButton validateBnt;
	private JComboBox<ConnectDB> conChooser;
	private JList<Table> tableChooser;

	/**
	* Construcor : initialize all components
	* @param cdb connexion you wanna incluse into the table picker
	*/
	public TablePicker(ConnectDB ... cdb)
	{
		this.cons = new ArrayList<ConnectDB>();
		this.done = false;
		for(ConnectDB c : cdb)
			this.cons.add(c);

		this.frame = new JFrame("Choisir sa table");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.getContentPane().setLayout(new BorderLayout());

		this.validateBnt = new JButton("Valider");
		this.validateBnt.addActionListener(this);
		frame.getContentPane().add(this.validateBnt, BorderLayout.SOUTH);

		this.conChooser = new JComboBox<ConnectDB>();
		this.conChooser.addActionListener(this);
		for(ConnectDB c : this.cons)
			this.conChooser.addItem(c);
		frame.getContentPane().add(this.conChooser, BorderLayout.NORTH);

		this.tableChooser = new JList<Table>(this.cons.get(0).getTables().toArray(new Table[this.cons.get(0).getTables().size()]));
		this.tableChooser.setSelectedIndex(0);
		frame.getContentPane().add(this.tableChooser, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(false);
	}

	/**
	* Show the TablePicker frame until the
	* validate button is pressed
	*/
	public synchronized void showIt()
	{
		frame.setVisible(true);
		try
		{
			while(!done)
			{
				wait();
			}
		}
		catch(Exception err){}
	}

	public synchronized void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.conChooser)
		{
			int index = this.conChooser.getSelectedIndex();
			this.tableChooser = new JList<Table>(this.cons.get(index).getTables().toArray(new Table[this.cons.get(index).getTables().size()]));
		}
		else if(e.getSource() == this.validateBnt)
		{
			frame.setVisible(false);
			done = true;
			notifyAll();
		}
	}

	/**
	* Close the Table Picker frame
	*/
	public void close()
	{
		this.frame.dispose();
	}

	/**
	* @return give the selected connexion
	*/
	public ConnectDB getConnectDB()
	{
		ConnectDB ret = null;
		if(done)
			ret = this.conChooser.getItemAt(this.conChooser.getSelectedIndex());
		return ret;
	}

	/**
	* @return  give the selected table bound with the connexion
	*/
	public Table getTable()
	{
		Table ret = null;
		if(done)
			ret = this.tableChooser.getSelectedValue();
		return ret;
	}
}