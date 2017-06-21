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
import view.interfaces.*;

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

			ConnectDB.saveConnect("test.cdb", test);

			Result resTriggerName = test.sendQuery(new Query("SELECT nom, prenom FROM Agent"));
			System.out.println(resTriggerName);

			DBFrame f = new DBFrame();
			Image icone = Toolkit.getDefaultToolkit().getImage("Image/DBrium.png");

			Controller controller = new Controller();

			
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