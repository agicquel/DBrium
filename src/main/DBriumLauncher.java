/**
* j'aime la doc
*
*/

import java.sql.*;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.*;


public class DBriumLauncher
{
	public static void main(String args[])
	{
		System.out.println("hello world");

		try
		{
			//step1 load the driver class 
			Class.forName("oracle.jdbc.OracleDriver");  
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
		}
	} 
}