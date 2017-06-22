package model;

import java.sql.*;
import java.util.ArrayList;

/**
* Table data object containing columns object
* @author Antoine Gicquel
*/
public class Table
{
	private String name;
	private ArrayList<Column> columns;

	/**
	* Construcor
	* @param name the name of the table
	*/
	public Table(String name)
	{
		this.name = name;
		this.columns = new ArrayList<Column>();
	}

	/**
	* Construcor : fill the column arraylist from a Result object
	* @param name the name of the table
	* @param r the result object
	*/
	public Table(String name, Result r)
	{
		this(name);
		for(int i = 0; i < r.getRows().size(); i++)
		{
			String namec = r.getRow(i).getAData(0).toString();
			String type = r.getRow(i).getAData(1).toString();
			this.columns.add(new Column(namec, type));
		}

	}

	/**
	* Add a column into the column array list
	* @param column the column you want to add
	*/
	public void addColumn(Column column)
	{
		this.columns.add(column);
	}

	/**
	* @return get the name of the table
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* Set the name of table
	* @param name the new name of the table
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* @return get the column array list
	*/
	public ArrayList<Column> getColumns()
	{
		return this.columns;
	}

	/**
	* Get a specif column from the array list
	* @param index the index of the column
	* @return the column object, null if not found
	*/
	public Column getColumn(int index)
	{
		Column ret = null;
		if(index >= 0 && index < this.columns.size())
			ret = this.columns.get(index);
		return ret;
	}

	@Override
	public String toString()
	{
		String ret = this.name + " (" + this.columns.size() + " colonnes)";
		return ret;
	}

	/**
	* Make the query to delete the table of the server
	* @return the delete query
	*/
	public Query deleteTable()
	{
		return new Query("DROP TABLE " + this.name + " \n");
	}

	/**
	* Make the query to delete all t-uples of the server
	* @return the truncate query
	*/
	public Query deleteFromTable()
	{
		return new Query("DELETE FROM " + this.name + " \n");
	}
}