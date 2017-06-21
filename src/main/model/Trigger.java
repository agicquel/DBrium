package model;

import java.sql.*;

/**
* Simple Trigger Object containing the trigger's code and its code
* @author Antoine Gicquel
*/
public class Trigger
{
	private String name;
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

}