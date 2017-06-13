package model;

import java.sql.*;

public class ConnectDB
{
	//Class.forName("oracle.jdbc.driver.OracleDriver");

	private String url;
	private String user;
	private String pwd;

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
}