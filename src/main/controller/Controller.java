package controller;

import model.*;
import java.util.*;
import java.io.*;
import org.apache.commons.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import view.interfaces.DBFrame;

public class Controller
{
	private ArrayList<ConnectDB> connexions;
	private DBFrame frame;

	public Controller(DBFrame frame)
	{
		this.frame = frame;
		this.fillIntoConnexions();		
	}

	public static String getUserDataDirectory()
	{
		return System.getProperty("user.home") + File.separator + ".dbrium" + File.separator;
	}

	public void fillIntoConnexions()
	{
		this.connexions = new ArrayList<ConnectDB>();
		File folder = new File(getUserDataDirectory());
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null)
		{
		    for (File f : listOfFiles)
		    {
		    	if(FilenameUtils.getExtension(f.getName()).toString().equals("cdb"))
		    	{
		    		try
		    		{
		    			this.connexions.add(ConnectDB.loadConnect(getUserDataDirectory() + File.separator + f.getName()));
		    		}
		    		catch(Exception e){}
		    	}
		    }
		}
		else
		{
			folder.mkdirs();
		}
	}

	public ArrayList<ConnectDB> getConnexions()
	{
		return this.connexions;
	}

	public void deleteConnexion(ConnectDB con)
	{
		try
		{
			File file = new File(getUserDataDirectory() + File.separator + con.getName().replaceAll("\\s+","").toLowerCase() + ".cdb");
			file.delete();
		}
		catch(Exception err){}
	}

	public void deleteConnexion(String conName)
	{
		try
		{
			File file = new File(getUserDataDirectory() + File.separator + conName.replaceAll("\\s+","").toLowerCase() + ".cdb");
			file.delete();
		}
		catch(Exception err){}
	}

	public void saveConnexion(ConnectDB con) throws IOException
	{
		try
		{
			ConnectDB.saveConnect(getUserDataDirectory() + File.separator + con.getName().replaceAll("\\s+","").toLowerCase() + ".cdb", con);
		}
		catch(Exception err)
		{
			throw err;
		}
	}

	public ConnectDB loadConnexion(String conName) throws IOException
	{
		ConnectDB c = null;
		try
		{
			c = ConnectDB.loadConnect(getUserDataDirectory() + File.separator + conName.replaceAll("\\s+","").toLowerCase() + ".cdb");
		}
		catch(Exception err)
		{
			throw err;
		}
		return c;
	}

	/**
  * This method return an ArrayList of Querry after take the string of the curent textArea
  * @param textToToken the text to cut in differents Querry
  * @return The ArrayList of Querry
  */
  public ArrayList<Query> tokenArea (String textToToken)
  {
  		ArrayList<Query> querys = new ArrayList<Query>();
  		ArrayList<String> triggers = new ArrayList<String>();
    	StringTokenizer firstToken = new StringTokenizer(textToToken, "/", false);

	    //look if the querry have some triggers then cut the string in differents string contain in max one trigger
	    while(firstToken.hasMoreTokens()){
	      triggers.add(firstToken.nextToken());
	    }

	    //If the string haven't trigger add al of query in the ArrayList of querys
	    if(triggers.isEmpty()){
	      StringTokenizer queryToken = new StringTokenizer(textToToken, ";", false);
	      while(queryToken.hasMoreTokens()){
	        querys.add(new Query(queryToken.nextToken()));
	      }

    	//Recut the triggers and then the other querys
    	}
    	else
    	{
    		for(String s : triggers)
    		{
    			s = s.toUpperCase();
    			//if it's a triggers
		        if(s.contains("TRIGGER"))
		        {
		        	querys.add(new Query(s));
		        }
		        else
		        { 
		        	//not a trigger
		        	StringTokenizer queryToken = new StringTokenizer(textToToken, ";", false);
		         	while(queryToken.hasMoreTokens())
		         	{
		         		querys.add(new Query(queryToken.nextToken()));
		         	}
		        }
		    }
    	}
    	return querys;
	}

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