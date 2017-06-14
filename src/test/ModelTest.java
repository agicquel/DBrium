import static org.junit.Assert.*;
import org.junit.*;

import model.*;

/**
* Test the whole model package
*/
public class ModelTest
{
	@Test
    public void connexionTest()
    {
    	try
		{
			ConnectDB test = new ConnectDB();
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