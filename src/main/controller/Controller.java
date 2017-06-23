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
		this.connexions = new ArrayList<ConnectDB>();
		this.frame = frame;

		File folder = new File(getUserDataDirectory());
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null)
		{
		    for (File f : listOfFiles)
		    {
		    	if(FilenameUtils.getExtension(f.getName()).toString().equals("cdb"))
		    	{
		    		System.out.println(f.getName());
		    		try
		    		{
		    			this.connexions.add(ConnectDB.loadConnect(getUserDataDirectory() + File.separator + f.getName()));
		    		}
		    		catch(Exception e){}
		    	}
		    	//System.out.println(FilenameUtils.getExtension(f.getName()).toString());
		    	//if(FilenameUtils.getExtension(f))
		      // Do something with child
		    }
		}
		else
		{
			folder.mkdirs();
			// Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }

		for(ConnectDB c : this.connexions)
		{
			System.out.println(c);
		}

		System.out.println(getUserDataDirectory());
	}

	public static String getUserDataDirectory()
	{
		return System.getProperty("user.home") + File.separator + ".dbrium" + File.separator;
	}

	public ArrayList<ConnectDB> getConnexions()
	{
		return this.connexions;
	}

	public void deleteConnexion(ConnectDB con)
	{
		try
		{
			File file = new File(getUserDataDirectory() + File.separator + con.getName().toUpperCase() + ".cdb");
			file.delete();
		}
		catch(Exception err){}
	}

	public void saveConnexion(ConnectDB con) throws IOException
	{
		try
		{
			ConnectDB.saveConnect(getUserDataDirectory() + File.separator + con.getName().toUpperCase() + ".cdb", con);
		}
		catch(Exception err)
		{
			throw err;
		}
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
}