package model;

import java.sql.*;

public class ConnectDB
{
	private String url;
	private String user;
	private String pwd;
	private Connection conn;
	private Statement state;

	public ConnectDB()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception err)
		{
			System.out.println("Oracle Driver unreachable");
			System.out.println(err.getMessage());
		}
	}

	public ConnectDB(String url, String user, String pwd)
	{
		this();
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}

	public void connect() throws SQLException
	{
		try
		{
			this.conn = DriverManager.getConnection(this.url, this.user, this.pwd);
			this.state = this.conn.createStatement();
		}
		catch(SQLException err)
		{
			throw err;
		}
		
	}

	public void disconnect() throws SQLException
	{
		try
		{
			this.conn.close();
			this.state.close();
		}
		catch(SQLException err)
		{
			throw err;
		}
	}

	public Result sendQuery(Query query) throws SQLException
	{
		Result res = null;

		try
		{
			//this.state = this.conn.createStatement();
			res = new Result(state.executeQuery(query.toString()));
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			throw err;
		}

		return res;
	}

	public String getUrl()
	{
		return url;
	}

	public String getUser()
	{
		return user;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	@Override
	protected void finalize() throws Throwable 
	{
		try
		{
			super.finalize();
		
			if(!this.conn.isClosed())
				this.conn.close();
			if(!this.state.isClosed())
				this.state.close();
		}
		catch(Throwable t)
		{
            throw t;
        }
	}
}