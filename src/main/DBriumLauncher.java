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
			DBFrame f = new DBFrame();
			Image icone = Toolkit.getDefaultToolkit().getImage("Image/DBrium.png");
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}
		
	} 
}