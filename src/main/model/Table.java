package model;

import java.sql.*;

import java.util.ArrayList;

public class Table
{
	private String name;
	private ArrayList<Column> columns;

	public Table(String name)
	{
		this.name = name;
		this.columns = new ArrayList<Column>();
	}

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

	public void addColumn(Column column)
	{
		this.columns.add(column);
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Column> getColumns()
	{
		return this.columns;
	}

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
		String ret = "\t*** " + this.name + " ***\t";
		for(Column c : this.columns)
			ret += "\n" + c.getName() + "\t\t" + c.getType();
		return ret;
	}
}