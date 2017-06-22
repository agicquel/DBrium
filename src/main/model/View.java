package model;

import java.sql.*;

/**
* Simple View Object 
* @author Antoine Gicquel
*/
public class View
{
	private String name;
	private String text;

	/**
	* Constructor
	* @param name the view name
	*/
	public View(String name)
	{
		this.name = name;
	}

	/**
	* Constructor
	* @param name the view name
	* @param text the text of the view
	*/
	public View(String name, String text)
	{
		this.name = name;
		this.text = text;
	}

	/**
	* @return give the name of the trigger
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* Set the name of trigger
	* @param name the new name of the trigger
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* @return give the text of the trigger
	*/
	public String getText()
	{
		return this.text;
	}

	/**
	* Set the text of trigger
	* @param text the new text of the trigger
	*/
	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		String ret = this.name;
		return ret;
	}

}