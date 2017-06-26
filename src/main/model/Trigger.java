package model;

/**
* Simple Trigger Object containing the trigger's code and its code
* @author Antoine Gicquel
*/
public class Trigger implements Deletable, Codable
{
	/**
	* The trigger's name
	*/
	private String name;

	/**
	* The trigger's code
	*/
	private String code;

	/**
	* Constructor
	* @param name the trigger name
	*/
	public Trigger(String name)
	{
		this.name = name;
	}

	/**
	* Constructor
	* @param name the trigger name
	* @param code the code of the trigger
	*/
	public Trigger(String name, String code)
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
	* @return return a query to delete the trigger
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