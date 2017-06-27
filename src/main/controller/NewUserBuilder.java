package controller;

import model.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Create a new user into the db server
* the current user needs to have sufficients rights
* To use it, you have to initialize a TableBuilder object
* and execute run like this : 
* NewUserBuilder nub = new NewUserBuilder();
* nub.showIt();
* Query q = nub.getQuery();
* nub.close();
* The current process will be paused until the sql is generate
* @author Antoine Gicquel
*/
public class NewUserBuilder implements ActionListener
{
	/**
	* true when user finished to create the user
	*/
	private boolean done;

	/**
	* the query which create the new user
	*/
	private Query query;

	private JFrame frame;
	private JButton validateBnt;
	private JTextField userName;
	private JTextField pwd;
	private JCheckBox selectCB;
	private JCheckBox insertCB;
	private JCheckBox updateCB;


	/**
	* Constructor : initialize the frame
	*/
	public NewUserBuilder()
	{
		this.done = false;
		this.query = null;
		this.frame = new JFrame("Nouvel utilisateur");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.getContentPane().setLayout(new BorderLayout());

		Container userInfo = new Container();
		userInfo.setLayout(new GridLayout(5, 2));		

		userInfo.add(new JLabel("Nom d'utilisateur : "));
		this.userName = new JTextField();
		userInfo.add(this.userName);

		userInfo.add(new JLabel("Mot de passe : "));
		this.pwd = new JTextField();
		userInfo.add(this.pwd);

		userInfo.add(new JLabel("Droit de SELECT : "));
		this.selectCB = new JCheckBox();
		userInfo.add(this.selectCB);

		userInfo.add(new JLabel("Droit de INSERT : "));
		this.insertCB = new JCheckBox();
		userInfo.add(this.insertCB);

		userInfo.add(new JLabel("Droit de UPDATE : "));
		this.updateCB = new JCheckBox();
		userInfo.add(this.updateCB);

		this.validateBnt = new JButton("G\u00e9n\u00e9rer");
		this.validateBnt.addActionListener(this);

		this.frame.getContentPane().add(userInfo, BorderLayout.CENTER);
		this.frame.getContentPane().add(this.validateBnt, BorderLayout.SOUTH);

		this.frame.pack();
		this.frame.setVisible(false);

	}

	/**
	* Show the NewUserBuilder frame until the
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
		if(e.getSource() == this.validateBnt)
		{
			boolean check = true;

			if(userName.getText() == null || userName.getText().equals(""))
			{
				check = false;
				JOptionPane.showMessageDialog(new JFrame(), "Nom de l'utilisateur invalide");
			}

			if(pwd.getText() == null || pwd.getText().equals(""))
			{
				check = false;
				JOptionPane.showMessageDialog(new JFrame(), "Mot de passe invalide");
			}

			if(check)
			{
				frame.setVisible(false);
				generateQuery();
				done = true;
				notifyAll();
			}
		}
	}

	/**
	* Create the query to create the user
	*/
	private void generateQuery()
	{
		String q = "CREATE USER " + userName.getText() + " IDENTIFIED BY " + pwd.getText() + ";\n";
		if(selectCB.isSelected() || insertCB.isSelected() || updateCB.isSelected())
		{
			q += "GRANT ";
			if(selectCB.isSelected())
				q += "SELECT,";
			if(insertCB.isSelected())
				q += "INSERT,";
			if(updateCB.isSelected())
				q += "UPDATE,";

			q = q.substring(0, q.length() - 1); // remove the comma
			q += " ON <TABLE NAME> TO " + userName.getText() + "; -- need to set the table name\n";
		}
		
		this.query = new Query(q);

	}

	/**
	* @return return the user creator query
	*/
	public Query getQuery()
	{
		return this.query;
	}

	/**
	* Close the NewUserBuilder frame
	*/
	public void close()
	{
		this.frame.dispose();
	}
}