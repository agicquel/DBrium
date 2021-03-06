package model;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
* Database connexion handler
* @author Antoine Gicquel
*/
public class ConnectDB
{
	/**
	* Connexion name
	*/
	private String name;

	/**
	* Connexion infos
	*/
	private String url, user, pwd;

	/**
	* State of the connexion
	*/
	private boolean connected;

	/**
	* JDBC Connexion object
	*/
	private Connection conn;

	/**
	* JDBC state object needed to send query
	*/
	private Statement state;

	/**
	* All the tables of the DB server
	*/
	private ArrayList<Table> tables;

	/**
	* All the triggers of the DB server
	*/
	private ArrayList<Trigger> triggers;

	/**
	* All the views of the DB server
	*/
	private ArrayList<View> views;

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
			this.state.setQueryTimeout(2);
			this.fillIntoTables();
			this.fillIntoTriggers();
			this.fillIntoViews();
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
	* Send a query to the DB (SELECT query)
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
	* Send a update to the DB like INSERT, UPDATE, or DELETE, even triggers
	* @param query the query you wanna send
	* @throws SQLException if something went wrong
	*/
	public void sendUpdate(Query query) throws SQLException
	{
		try
		{
			state.executeUpdate(query.toString());
		}
		catch(SQLException err)
		{
			throw err;
		}
	}

	/**
	* Fill info about the tables of the connexion into the table array list
	* @throws SQLException if info about table can't be reached
	*/
	public void fillIntoTables() throws SQLException
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
	* Fill info about the triggers of the connexion into the triggers array list
	* @throws SQLException if info about triggers can't be reached
	*/
	public void fillIntoTriggers() throws SQLException
	{
		try
		{
			this.triggers = new ArrayList<Trigger>();
			ArrayList<String> triggerNames = new ArrayList<String>();
			Result resTriggerName = this.sendQuery(new Query("SELECT TRIGGER_NAME FROM USER_TRIGGERS WHERE TRIGGER_NAME NOT LIKE '%$%'"));
			
			for(int i = 1; i < resTriggerName.getRows().size(); i++)
			{
				triggerNames.add(resTriggerName.getRow(i).getAData(0).toString());
			}

			for(String triggerName : triggerNames)
				this.triggers.add(new Trigger(triggerName, this.sendQuery(new Query("SELECT TRIGGER_BODY FROM USER_TRIGGERS WHERE TRIGGER_NAME = '" + triggerName.toUpperCase() + "'")).getRow(1).toString()));

		}
		catch(SQLException err)
		{
			throw err;
		}
	}

	/**
	* Fill info about the views of the connexion into the view array list
	* @throws SQLException if info about views can't be reached
	*/
	public void fillIntoViews() throws SQLException
	{
		try
		{
			this.views = new ArrayList<View>();
			ArrayList<String> viewNames = new ArrayList<String>();
			Result resViewName = this.sendQuery(new Query("SELECT VIEW_NAME FROM USER_VIEWS WHERE VIEW_NAME NOT LIKE '%$%'"));
			
			for(int i = 1; i < resViewName.getRows().size(); i++)
			{
				viewNames.add(resViewName.getRow(i).getAData(0).toString());
			}

			for(String viewName : viewNames)
				this.views.add(new View(viewName, this.sendQuery(new Query("SELECT TEXT FROM USER_VIEWS WHERE VIEW_NAME = '" + viewName.toUpperCase() + "'")).getRow(1).toString()));

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

	/**
	* @return give the tables array list
	*/
	public ArrayList<Table> getTables()
	{
		return this.tables;
	}

	/**
	* @return give the triggers array list
	*/
	public ArrayList<Trigger> getTriggers()
	{
		return this.triggers;
	}

	/**
	* @return give the views array list
	*/
	public ArrayList<View> getViews()
	{
		return this.views;
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

	/**
	* Save the connexions informations into a file
	* @param fileName the file name
	* @param con the connexion you wanna save
	* @throws IOException if cant save the file
	*/
	public static void saveConnect(String fileName, ConnectDB con) throws IOException
	{
		PrintWriter sortie = null;

		try
		{
			sortie = new PrintWriter(new FileWriter(fileName));
			sortie.print("name=" + con.getName() + "\n");
			sortie.print("url=" + con.getUrl() + "\n");
			sortie.print("user=" + con.getUser() + "\n");
			sortie.print("pwd=" + con.getPwd() + "\n");
		}
		catch(IOException err)
		{
			throw err;
		}
		finally
		{
			sortie.close();
		}
	}

	/**
	*  Load a connexion object from a file
	* @param fileName the file name
	* @return return the connectBD object
	* @throws IOException if cant load the file
	*/
	public static ConnectDB loadConnect(String fileName) throws IOException
	{
		ConnectDB con = new ConnectDB();
		Scanner scanner = null;
		Scanner scannerLine = null;
		String scan = null;

		try
		{
			scannerLine = new Scanner(new File(fileName)).useDelimiter("\\s*\n\\s*");
			while(scannerLine.hasNext())
			{
				scanner = new Scanner(scannerLine.next()).useDelimiter("\\s*=\\s*");
				scan = scanner.next();

				switch(scan)
				{
					case "name":
						con.setName(scanner.next());
						break;
					case "user":
						con.setUser(scanner.next());
						break;
					case "url":
						con.setUrl(scanner.next());
						break;
					case "pwd":
						con.setPwd(scanner.next());
						break;
				}
			}
		}
		catch(IOException err)
		{
			throw err;
		}
		finally
		{
			scanner.close();
			scannerLine.close();
		}

		return con;
	}
}