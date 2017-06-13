package model;

import java.sql.*;

public class Result
{

	// tempo
	private ResultSet res;
	private ResultSetMetaData meta;

	public Result(ResultSet resultRaw)
	{
		this.res = resultRaw;
		try
		{
			this.meta = resultRaw.getMetaData();
		}
		catch(SQLException err){}
	}

	@Override
	public String toString()
	{
		String ret = null;

		try
		{
			ret = "\n**************************************************************************************************************\n";

			//display column name
		    for(int i = 1; i <= this.meta.getColumnCount(); i++)
		    	ret += "\t" + this.meta.getColumnName(i).toUpperCase() + "\t *";

		    ret += "\n**************************************************************************************************************\n";

		    // display rows contents
		    while(this.res.next())
	    	{
	    		for(int i = 1; i <= this.meta.getColumnCount(); i++)
	    			ret += "\t" + this.res.getObject(i).toString() + "\t |";

	    		ret += "\n---------------------------------\n";

	    	}
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			ret = err.getMessage();
		}

    	return ret;
	}

}