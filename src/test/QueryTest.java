import static org.junit.Assert.*;
import org.junit.*;

import model.Query;

public class QueryTest
{
	private String query = "test";

    @Test
    public void QueryTest()
    {
    	Query q = new Query(query);
        assertEquals(query, q.getCode());
        assertEquals(query, q.toString());
    }


}