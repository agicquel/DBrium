package model;

/**
* Query Object containing SQL code
* @author Antoine Gicquel
*/
public class Query implements Codable
{
	/**
	* The code the query
	*/
	private String query;

	/**
	* Constructor : create the query from a string
	* @param query the string containing the sql code
	*/
	public Query(String query)
	{
		this.query = query;
	}

	/**
	* @return give the code
	*/
	public String getCode()
	{
		return this.query;
	}

	@Override
	public String toString()
	{
		return this.getCode();
	}
} 