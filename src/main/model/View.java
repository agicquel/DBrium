package model;

/**
* Simple View Object 
* @author Antoine Gicquel
*/
public class View implements Deletable, Codable
{
	/**
	* The view's name
	*/
	private String name;

	/**
	* The view's cpde
	*/
	private String code;

	/**
	* Constructor
	* @param name the view name
	*/
	public View(String name)
	{
		this.name = name;
	}

	/**
	* Constructor
	* @param name the view name
	* @param code the text of the view
	*/
	public View(String name, String code)
	{
		this.name = name;
		this.code = code;
	}

	/**
	* @return give the name of the trigger
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* Set the name of trigger
	* @param name the new name of the trigger
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* @return give the code of the trigger
	*/
	public String getCode()
	{
		return this.code;
	}

	/**
	* Set the code of trigger
	* @param code the new code of the trigger
	*/
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	* @return return a query to delete the view
	*/
	public Query delete()
	{
		return new Query("DROP TRIGGER " + this.name);
	}

	@Override
	public String toString()
	{
		String ret = this.name;
		return ret;
	}

}