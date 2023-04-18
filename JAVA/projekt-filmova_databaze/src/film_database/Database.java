package film_database;

import java.util.HashMap;
import java.util.Scanner;

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
	
	public int addFilm(String name,short year)
	{
		Production newItem = new Film(name, year);
		databaseItems.put(ID++, newItem);
		return ID - 1 ;
	}
	
	public int addAnime(String name, short year, byte age)
	{
		Production newItem = new Anime(name, year, age);
		databaseItems.put(ID++, newItem);
		return ID - 1 ;
	}
	
	public boolean DelProduction(Integer productKey)
	{
		
		return databaseItems.remove(productKey) != null;
	}
	
	public Production getProduction(int ID)
	{
		return databaseItems.get(ID);
	}
	
	public boolean PrintDatabaseIOnlyName()
	{
		if (databaseItems.size() != 0)
		{
			for (Integer item: databaseItems.keySet()) {
			    String key = item.toString();
			    String value = databaseItems.get(item).toString();
			    System.out.println(key + " " + value);
			}
			return true;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}
	public boolean PrintAllDatabase()
	{
		if (databaseItems.size() != 0)
		{
		    System.out.println("     Typ     Nazev          Reziser             Rok vydani   Doporuceny vek");
			for (Integer item: databaseItems.keySet()) {
			    String toStr = item.toString();
			    String name = databaseItems.get(item).toString();
			    String director = databaseItems.get(item).getDirector();
			    short year = databaseItems.get(item).getYearOfPublication();
			    short age = databaseItems.get(item).getAge();

			    if (databaseItems.get(item).getClass() == Anime.class)
			    {
			    	System.out.println(toStr + "    " + name + "       " + director + "             " + year + "          " + age);
				    System.out.print("Seznam animatoru:");
			    }
			    else 
			    {
				    System.out.println(toStr + "    " + name + "         " + director + "             " + year);
				    System.out.print("Seznam hercu:");
			    }
			    databaseItems.get(item).PrintListActors();
			    System.out.println();
			}
			return true;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return false;
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
		System.out.println("Film nenalezen");
		return null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}
	public boolean Sort()
	{
		if (databaseItems.size() != 0)
		{
			for (Integer item: databaseItems.keySet()) {
			    String key = item.toString();
			    databaseItems.get(item).SortFeedback();
			    
			    String value = databaseItems.get(item).toString();
			    System.out.println(key + " " + value);
			}
			return true;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}
	
	public Production Find(Scanner scan, Database database)  // neni dodelane
	{
		//Collections.sort(list,(o1, o2) -> (o1.getName().compareTo(o2.getName())));  // sort list by Name
		System.out.println("Zadejte nazev:");
		
		int count=0;
		String name = null;
		String string = scan.nextLine();
		for (Integer item: databaseItems.keySet()) {
			var key = item.toString();
			name = databaseItems.get(item).getName();
			int condition= (name.indexOf(string));
			if ( condition > -1)
			{
			    count++;
			    System.out.println(name);
			}
			
		}
		if (count != 0) 
			{
				System.out.println("Ktery ste meli na mysli:");
				return FindByName(scan.nextLine());
			}
//		else System.out.println("Nenalezeno");
//		condition= (name.indexOf(scan.nextLine()));
//		if ( condition > -1)
//		{
//			Production production = databaseItems.get(item);
//			production.SortFeedback();
//			System.out.println("Hodnocei komentar");
//			for (var feedback : production.getFeedback()) {
//				System.out.println(feedback.toString());
//			}
//		    count++;
//		}
//		else System.out.println("Nenalezeno");
		/*
		 * for ( Production iterable_element : databaseItems.) { if (scan.nextLine() ==
		 * iterable_element.getName()) { list.contains(iterable_element.getName());
		 * return null; } System.out.println(iterable_element.getName()); count++; }
		 */
		return null;
	}
}  // end of database class

