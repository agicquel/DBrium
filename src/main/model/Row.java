package model;

import java.sql.*;

/**
* The Object contained into Result
* @author Antoine Gicquel
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

	/**
	* Get a specefic data of the data array
	* @param index index
	* @return the data, or null if index not valid
	*/
	public Object getAData(int index)
	{
		Object ret = null;
		if(index >= 0 && index < this.data.length)
			ret = this.data[index];
		return ret;
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