package model;

import java.sql.*;
import java.util.ArrayList;

/**
* Database connexion handler
* @author Antoine Gicquel
*/
public class ConnectDB
{
	private String name;
	private String url, user, pwd;
	private boolean connected;
	private Connection conn;
	private Statement state;
	private ArrayList<Table> tables;

	/**
	* Constructor
	* initialyse url user and pwd with empty string
	* it also call the oracle driver for jdbc
	*/
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
		this.connected = false;
	}

	/**
	* Constructor
	* @param name the name of the connexion
	* @param url the url to the DB
	* @param user the username
	* @param pwd the password of the user
	*/
	public ConnectDB(String name, String url, String user, String pwd)
	{
		this();
		this.name = name;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}

	/**
	* Connect to the DB
	* @throws SQLException if url / user / pwd is wrong
	*/
	public void connect() throws SQLException
	{
		try
		{
			this.conn = DriverManager.getConnection(this.url, this.user, this.pwd);
			this.state = this.conn.createStatement();
			this.fillIntoTables();
			this.connected = true;
		}
		catch(SQLException err)
		{
			throw err;
		}
		
	}

	/**
	* Disconnect to the DB
	* @throws SQLException if you're already diconnected
	*/
	public void disconnect() throws SQLException
	{
		try
		{
			this.conn.close();
			this.state.close();
			this.connected = false;
		}
		catch(SQLException err)
		{
			throw err;
		}
	}

	/**
	* Send a query to the DB
	* @param query the query you wanna send
	* @return return a result object
	* @throws SQLException if query is not valid
	*/
	public Result sendQuery(Query query) throws SQLException
	{
		Result res = null;

		try
		{
			res = new Result(state.executeQuery(query.toString()));
		}
		catch(SQLException err)
		{
			//System.out.println("Query not correct : " + err.getMessage());
			throw err;
		}

		return res;
	}

	/**
	* Fill info about the tables of the connexion into the table array list
	* @throws SQLException if info about table can't be reached
	*/
	private void fillIntoTables() throws SQLException
	{
		try
		{
			this.tables = new ArrayList<Table>();
			ArrayList<String> tableNames = new ArrayList<String>();
			Result resTableName = this.sendQuery(new Query("SELECT table_name FROM user_tables WHERE table_name NOT LIKE '%$%'"));
			for(int i = 1; i < resTableName.getRows().size(); i++)
			{
				tableNames.add(resTableName.getRow(i).getAData(0).toString());
			}

			for(String tableName : tableNames)
				this.tables.add(new Table(tableName, this.sendQuery(new Query("SELECT COLUMN_NAME, DATA_TYPE FROM USER_TAB_COLUMNS WHERE UPPER(TABLE_NAME) = + '" + tableName.toUpperCase() + "'"))));

		}
		catch(SQLException err)
		{
			throw err;
		}
		
	}

	/**
	* @return give the name attribute
	*/
	public String getName()
	{
		return this.name;
	}
		

	/**
	* @return give the url attribute
	*/
	public String getUrl()
	{
		return this.url;
	}

	/**
	* @return give thr user name attribute
	*/
	public String getUser()
	{
		return this.user;
	}

	/**
	* @return give the password attribute
	*/
	public String getPwd()
	{
		return this.pwd;
	}

	public ArrayList<Table> getTables()
	{
		return this.tables;
	}

	/**
	* @return true if connected
	*/
	public boolean isConnected()
	{
		return this.connected;
	}

	/**
	* Set the name attribute
	* @param name the new url
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* Set the url attribute
	* @param url the new url
	*/
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	* Set the user attribute
	* @param user the new user
	*/
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	* Set the pwd attribute
	* @param pwd the new pwd
	*/
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	@Override
	public String toString()
	{
		String ret = this.name + " (" + this.url + ")";
		return ret;
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