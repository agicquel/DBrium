package model;

import java.sql.*;

public class ConnectDB
{
	//Class.forName("oracle.jdbc.driver.OracleDriver");

	private String url = "jdbc:postgresql://localhost:5432/Ecole";
	private String user;
	private String passwd;
}