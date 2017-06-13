/**
* j'aime la doc
*
*/

import java.sql.*;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.*;

import model.*;


public class DBriumLauncher
{
	public static void main(String args[])
	{
		System.out.println("hello world");

		try
		{
			ConnectDB test = new ConnectDB();
			test.setUrl("azerty");
			System.out.println(test.getUrl());

			test.setUrl("jdbc:oracle:thin:@localhost:49161:xe");
			test.setUser("system");
			test.setPwd("oracle");
			test.connect();
			System.out.println("Connection ok !");

			Query query = new Query("SELECT * FROM Agent WHERE lAgence = 2");
			Result result = test.sendQuery(query);

			System.out.println(result);

			test.disconnect();
			
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
		}
	} 
}