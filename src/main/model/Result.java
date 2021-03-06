package model;

import java.sql.*;
import java.util.ArrayList;

/**
* The Object returned from a Query Execution
* @author Antoine Gicquel
*/
public class Result
{
	/**
	* JDBC ResultRes Object where to find the datas
	*/
	private ResultSet res;

	/**
	* JDBC ResultSetMetaData Object where to find the other datas
	*/
	private ResultSetMetaData meta;

	/**
	* Array List of rows extracted of the result set
	*/
	private ArrayList<Row> rows;

	/**
	* Constructor : Create from a ResultSet
	* @param resultRaw the resultRaw which the infos came from
	* @throws SQLException if result is not valid
	*/
	public Result(ResultSet resultRaw) throws SQLException
	{
		this.res = resultRaw;
		if(resultRaw != null)
		{
			try
			{
				this.meta = resultRaw.getMetaData();
				this.fillIntoRows();
			}
			catch(SQLException err)
			{
				throw err;
			}
		}
	}

	/**
	* Fill the ArrayList rows from the res attribute
	* @throws SQLException if info about rows cant be extracted
	*/
	private void fillIntoRows() throws SQLException
	{
		try
		{
			this.rows = new ArrayList<Row>();
			ArrayList<Object> row = new ArrayList<Object>();

			for(int i = 1; i <= this.meta.getColumnCount(); i++)
				row.add(this.meta.getColumnName(i).toUpperCase());

			this.rows.add(new Row(row.toArray(new Object[row.size()])));
			row = new ArrayList<Object>();

			while(this.res.next())
    		{
	    		for(int i = 1; i <= this.meta.getColumnCount(); i++)
		    		row.add(this.res.getObject(i));
	    		
	    		this.rows.add(new Row(row.toArray(new Object[row.size()])));
	    		row = new ArrayList<Object>();
    		}
		}
		catch(SQLException err)
		{
			throw err;
		}
	}

	/**
	* @return the arraylist rows
	*/
	public ArrayList<Row> getRows()
	{
		return this.rows;
	}

	/**
	* @param index the index of the row
	* @return return a specific row
	*/
	public Row getRow(int index)
	{
		Row row = null;

		if(index >= 0 && index < this.rows.size())
			row = this.rows.get(index);
		return row;
	}

	@Override
	public String toString()
	{
		String ret = "";

		for(Row r : this.rows)
			ret += r.toString() + "\n";
			//ret += String.format("%4d", r.toString() + "\n");

    	return ret;
	}

}