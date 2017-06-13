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
			//step1 load the driver class 
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("ca marche !");

			ConnectDB test = new ConnectDB();
			test.setUrl("azerty");
			System.out.println(test.getUrl());
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
		}
	} 
}