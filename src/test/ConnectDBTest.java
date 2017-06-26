import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

import model.*;

public class ConnectDBTest
{
	// Infos about the connexion have to be correct
	private String name = "test";
	private String url = "jdbc:oracle:thin:@localhost:49161:xe";
	private String user = "system";
	private String pwd = "oracle";

	@Test
	public void sendTest()
	{
		ConnectDB test = new ConnectDB(name, url, user, pwd);
		Result r = null;

		try
		{
			test.connect();
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}

		try
		{
			test.sendUpdate(new Query("DROP TABLE test"));
		}
		catch(Exception err){}

		try
		{
			test.sendUpdate(new Query("CREATE TABLE Test ( key VARCHAR2(2) )"));
			r = test.sendQuery(new Query("SELECT * FROM Test"));
			assertNotNull(r);
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}

		try
		{
			test.sendUpdate(new Query("DROP TABLE Test"));
			r = test.sendQuery(new Query("SELECT * FROM Test"));
		}
		catch(Exception err)
		{
			r = null;
		}
		assertNull(r);

		try
		{
			test.disconnect();
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}
		
	}

    @Test
    public void connectTest()
    {
    	try
    	{
			ConnectDB test = new ConnectDB(name, url, user, pwd);
			test.connect();
			assertTrue(test.isConnected());
			test.disconnect();
			assertFalse(test.isConnected());
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}
    }

    @Test
    public void saveAndLoadTest()
    {
    	ConnectDB test = new ConnectDB(name, url, user, pwd);
    	ConnectDB test2 = null;
    	try
    	{
			ConnectDB.saveConnect("ConnectDBTestTmp", test);
			test2 = ConnectDB.loadConnect("ConnectDBTestTmp");
			new File("ConnectDBTestTmp").delete();
		}
		catch(Exception err)
		{
			System.out.println("erreur : " + err.getMessage());
			err.printStackTrace();
		}

		assertEquals(test.getName(), test2.getName());
		assertEquals(test.getUser(), test2.getUser());
		assertEquals(test.getUrl(), test2.getUrl());
		assertEquals(test.getPwd(), test2.getPwd());
    }

}