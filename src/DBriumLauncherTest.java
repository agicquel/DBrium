import org.junit.*; 
import static org.junit.Assert.*; 
public class DBriumLauncherTest
{
	@Test()
	public void testOsef()
	{
		new DBriumLauncher().main(new String [5]);
	}
}