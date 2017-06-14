package model;

import java.sql.*;

/**
* The Object contained into Result
*/
public class Row
{
	private Object data[];

	/**
	* Constructor of Row
	* @param data list of data object
	*/
	public Row(Object ... data)
	{
		this.data = new Object[data.length];
		for(int i = 0; i < data.length; i++)
		{
			this.data[i] = data[i];
		}
	}

	/**
	* @return return the data array
	*/
	public Object[] getData()
	{
		return data;
	}

	@Override
	public String toString()
	{
		String ret = "| \t";
		for(Object o : this.data)
			ret += o.toString() + "\t|\t";
		return ret;
	}
}