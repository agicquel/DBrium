import java.sql.*;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.*;

import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import model.*;
import controller.*;

/**
* Launcher
* Parse args to determine gui or terminal mode
*/
public class DBriumLauncher
{
	public static void main(String args[])
	{
		try
		{
			System.out.println("hello world");

			ConnectDB test = new ConnectDB();
			test.setName("Connection Test");
			test.setUrl("jdbc:oracle:thin:@localhost:49161:xe");
			test.setUser("system");
			test.setPwd("oracle");
			test.connect();
			System.out.println("Connection ok !");

			Result resTriggerName = test.sendQuery(new Query("SELECT TRIGGER_NAME FROM USER_TRIGGERS WHERE TRIGGER_NAME NOT LIKE '%$%'"));
			System.out.println(resTriggerName);

			
			test.disconnect();
			System.out.println("finnn");
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}
		
	} 
}