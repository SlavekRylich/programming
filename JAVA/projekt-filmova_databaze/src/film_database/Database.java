package film_database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
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
	
	public Production Find(Scanner scan, Database database) 
	{
		System.out.println("Zadejte vyraz:");
		
		int count=0;
		String name = null;
		String string = scan.nextLine();
		System.out.println("Nalezeno:");
		for (Integer item: databaseItems.keySet()) {
			name = databaseItems.get(item).getName();
			int condition= (name.indexOf(string));
			if ( condition > -1)
			{
			    count++;
			    System.out.println("- " +name);
			}
			
		}
		if (count != 0) 
			{
				System.out.println("Vyberte film:");
				return FindByName(scan.nextLine());
			}
		 
		return null;
	}
	
	public void FindHuman()
	{
		int jump=0;
		
		if (databaseItems.size() != 0)
		{
			for (Integer item1: databaseItems.keySet()) {
			    String key1 = item1.toString();
			    Production value1 = databaseItems.get(item1);
			    for (Human effectives_element1 : value1.effectives) {
			    	
			    	for (Integer item2: databaseItems.keySet()) {
					    String key2 = item2.toString();
					    Production value2 = databaseItems.get(item2);
					    System.out.println(value1.getName()+ " vs " + value2.getName()+": ");
					    
					    if (!value1.getName().equals(value2.getName()))
					    {
					    	
					    	for (Human effectives_element2 : value2.effectives) {
						    	if (effectives_element2.getFullName().equals(effectives_element1.getFullName()))
						    	{
							    	System.out.println("shoda");
						    	}
						    	System.out.println(effectives_element1 +" vs " +effectives_element2);
					    	}
					    	
					    }
					    else continue;
						
					}
					
				}
			    jump++;
			}
			System.out.println(jump);
			return;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return;
		}
	}

//	private static int getDifferenceBetweenTwoArray(int[] array1 , int[] array2)
//	{
//	    int differenceCount = 0;
//	    //if you dont want to sort your original arrays, create temporary arrays
//	    int temp1[] = Arrays.copyOf(array1 , array1.length);
//	    int temp2[] = Arrays.copyOf(array2 , array2.length);
//	    Arrays.sort(temp1);
//	    Arrays.sort(temp2);
//
//	    for(Integer i : temp1)
//	    {
//	        if(Arrays.binarySearch(temp2, i) < 0)
//	            differenceCount++;
//	    }
//	    for(Integer i: temp2)
//	    {
//	        if(Arrays.binarySearch(temp1, i) < 0)
//	            differenceCount++;
//	    }   
//
//	    return differenceCount;
//	}
	
	public void SaveToFile()
	{
		for (Integer item : databaseItems.keySet()) {
			FileWriter fw = null; BufferedWriter out = null;
			try {
			fw = new FileWriter(databaseItems.get(item).getName().trim() +".txt");
			out = new BufferedWriter(fw);
			for (int i=0;i<10;i++){
				System.out.println("Zadej jmeno:");
				byte []data = new byte[100];
				System.in.read(data);
				out.write(new String("Jmeno je: " + data));
				out.newLine();
				}
			} catch (IOException e) {
			System.out.println("Soubor nelze otevøít");
			} finally {
			out.close(); fw.close(); // nutno doimplementovat null check atd.
			}
			
		}
	}

	
}  // end of database class