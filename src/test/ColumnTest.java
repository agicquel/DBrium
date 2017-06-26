import static org.junit.Assert.*;
import org.junit.*;

import model.Column;

public class ColumnTest
{
	private String name = "nameTest";
    private String type = "typeTest";

    @Test
    public void testGetter()
    {
    	Column c = new Column(name, type);
    	assertEquals(name, c.getName());
    	assertEquals(type, c.getType());
    }

    @Test
    public void testSetter()
    {
    	Column c = new Column(name, type);
    	name = "nameTest2";
    	type = "typeTest2";

    	c.setName(name);
    	c.setType(type);

    	assertEquals(name, c.getName());
    	assertEquals(type, c.getType());
    }

}