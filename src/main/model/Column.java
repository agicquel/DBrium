package model;

import java.sql.*;

/**
* Column contain in the Table
* @author Antoine Gicquel
*/
public class Column
{
	private String name;
	private String type;

	/**
	* Construtor : initilize info about the column
	* @param name the name of the column
	* @param type the data type of the column
	*/
	public Column(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

	/**
	* @return get the name of the column
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* @return get the data type of the column
	*/
	public String getType()
	{
		return this.type;
	}

	/**
	* Set the name of the column
	* @param name the new name
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* Set the data type of the column
	* @param type the new type
	*/
	public void setType(String type)
	{
		this.type = type;
	}
	
}