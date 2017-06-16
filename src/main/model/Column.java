package model;

import java.sql.*;

public class Column
{
	private String name;
	private String type;

	public Column(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

	public String getName()
	{
		return this.name;
	}

	public String getType()
	{
		return this.type;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
}