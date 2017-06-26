package model;

/**
* Class which implements Deletable interface can give a query to delete itself
* @author Antoine Gicquel
*/
public interface Deletable
{
	/**
	* @return give the query to delete the object of the DB server
	*/
	public Query delete();
}