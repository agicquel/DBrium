import java.sql.*;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.*;

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
		System.out.println("hello world");
		TableBuilder tb = new TableBuilder();
		System.out.println(tb.run());
		tb.close();

		System.out.println("finnn");
	} 
}