import static org.junit.Assert.*;
import org.junit.*;

import model.Row;

public class RowTest
{
	private Object o[];

    @Test
    public void RowTest()
    {
    	o = new Object[3];
    	int size = o.length;

    	Row r = new Row(o);
    	assertSame(r.getData().length, size);
    }
}