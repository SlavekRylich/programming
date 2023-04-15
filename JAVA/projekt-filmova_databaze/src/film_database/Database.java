package film_database;

import java.util.HashMap;

public class Database {

	private int ID=1;
	private HashMap<Integer, Production> databaseItems;
	
	public Database() 
	{
		setDatabaseItems(new HashMap<Integer, Production>());
	}

	public HashMap<Integer, Production> getDatabaseItems() {
		return databaseItems;
	}

	public void setDatabaseItems(HashMap<Integer, Production> databaseItems) {
		this.databaseItems = databaseItems;
	}
	
	public int addFilm(String name,short year,byte feedback)
	{
		Production newItem = new Film(name, year, feedback);
		databaseItems.put(ID++, newItem);
		return ID - 1 ;
	}
	
	public int addAnime(String name, short year,byte feedback, byte age)
	{
		Production newItem = new Anime(name, year, feedback, age);
		databaseItems.put(ID++, newItem);
		return ID - 1 ;
	}
	
	public Production getProduction(int ID)
	{
		return databaseItems.get(ID);
	}
	
	public void PrintDatabase()
	{
		for (Integer item: databaseItems.keySet()) {
		    String key = item.toString();
		    String value = databaseItems.get(item).toString();
		    System.out.println(key + " " + value);
		}
	}
	public Production FindByName(String word)
	{
		for (Integer item: databaseItems.keySet()) {
		    var key = item.toString();
		    String name = databaseItems.get(item).getName();
		    if (name.equals(word))
		    {
		    	return databaseItems.get(item);
		    }
		}
		return null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}
}
