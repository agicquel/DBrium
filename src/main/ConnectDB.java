package model;

import java.sql.*;

public class ConnectDB
{
	//Class.forName("org.postgresql.Driver");

	private String url = "jdbc:postgresql://localhost:5432/Ecole";
	private String user;
	private String passwd;
}