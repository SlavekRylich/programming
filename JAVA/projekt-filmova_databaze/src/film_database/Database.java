package film_database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import film_database.Human.Type;


public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	private int filmID=1;
	private int humanID=1;
	private HashMap<Integer, Production> databaseFilms;
	private HashMap<Integer, Human> databaseHuman;
	
	
	public Database() 
	{
		setDatabaseFilms(new HashMap<Integer, Production>());
		setDatabaseHuman(new HashMap<Integer, Human>());
	}

	public HashMap<Integer, Production> getDatabaseItems() {
		return databaseFilms;
	}

	public void setDatabaseFilms(HashMap<Integer, Production> databaseItems) {
		this.databaseFilms = databaseItems;
	}
	
	public int addFilm(String name,short year)
	{
		Production newItem = new Film(name, year);
		databaseFilms.put(filmID++, newItem);
		return filmID - 1 ;
	}
	
	public int addAnime(String name, short year, byte age)
	{
		Production newItem = new Anime(name, year, age);
		databaseFilms.put(filmID++, newItem);
		return filmID - 1 ;
	}
	
	public Human addHuman(String name, String surname, Production film) {
		
		
		for (Integer item: databaseHuman.keySet()) {									
		    
		    if (databaseHuman.get(item).getFullName().equals(name + " " + surname))
		    {
		    	databaseHuman.get(item).addProductions(film);
		    	return databaseHuman.get(item);
		    }
		    
		}
		Human human = new Human(name,surname,film, null);
		databaseHuman.put(humanID++, human) ;
		return human;
	}
	
	public boolean DelProduction(Integer productKey)
	{
		
		return databaseFilms.remove(productKey) != null;
	}
	
	public Production getProduction(int ID)
	{
		return databaseFilms.get(ID);
	}
	
	public Human getHuman(int ID)
	{
		return databaseHuman.get(ID);
	}
	
	public boolean PrintDatabaseIOnlyName()
	{
		if (databaseFilms.size() != 0)
		{
			for (Integer item: databaseFilms.keySet()) {
			    String key = item.toString();
			    String value = databaseFilms.get(item).toString();
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
		if (databaseFilms.size() != 0)
		{
		    System.out.println("     Typ     Nazev          Reziser             Rok vydani   Doporuceny vek");
			for (Integer item: databaseFilms.keySet()) {
			    String toStr = item.toString();
			    String name = databaseFilms.get(item).toString();
			    String director = databaseFilms.get(item).getDirector();
			    short year = databaseFilms.get(item).getYearOfPublication();
			    short age = databaseFilms.get(item).getAge();

			    if (databaseFilms.get(item).getClass() == Anime.class)
			    {
			    	System.out.println(toStr + "    " + name + "       " + director + "             " + year + "          " + age);
				    System.out.print("Seznam animatoru:");
			    }
			    else 
			    {
				    System.out.println(toStr + "    " + name + "         " + director + "             " + year);
				    System.out.print("Seznam hercu:");
			    }
			    databaseFilms.get(item).PrintListActors();
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
		for (Integer item: databaseFilms.keySet()) {
		    String name = databaseFilms.get(item).getName();
		    if (name.equals(word))
		    {
		    	return databaseFilms.get(item);
		    }
		}
		System.out.println("Film nenalezen");
		return null;
	}

	public int getID() {
		return filmID;
	}

	public void setID(int iD) {
		this.filmID = iD;
	}
	public boolean Sort()
	{
		if (databaseFilms.size() != 0)
		{
			for (Integer item: databaseFilms.keySet()) {
			    String key = item.toString();
			    databaseFilms.get(item).SortFeedback();
			    
			    String value = databaseFilms.get(item).toString();
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
		for (Integer item: databaseFilms.keySet()) {
			name = databaseFilms.get(item).getName();
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
		int shoda=0;
		ArrayList<Production> temp = new ArrayList<>();
		Set<Human> hs;
		
		if (databaseFilms.size() != 0)
		{
			
			for (Integer item1: databaseFilms.keySet()) {
			    Production film1 = databaseFilms.get(item1);
			    temp.add(film1);
			    
			    for (Human actor1 : film1.effectives.keySet()) {
					
				
					
				
			    	for (Integer item2: databaseFilms.keySet()) {
					    Production film2 = databaseFilms.get(item2);
					    
					    			
					    if (temp.contains(film2))
					    	continue;
					    if (!film1.getName().equals(film2.getName()))
					    {
					    	
					    	for (Human actor2 : film2.effectives.keySet()) {
								
							
								
						    	if (actor2.getFullName().equals(actor1.getFullName()))
						    	{
						    		actor1.addProductions(film1);
						    		actor1.addProductions(film2);
							    	System.out.println("shoda: " + actor1.getFullName() + " - " + film1.getName() + " - " + film2.getName());
							    	shoda++;
						    	}
						    
					    	}
					    	
					    }
					    
						
					}
					
				}
			    
			    
			}
			System.out.println(shoda);
			return;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return;
		}
	}

	
	public void SaveToFile() throws IOException
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		if (directory.exists())
		{
			
			for (Integer item : databaseFilms.keySet()) {
				FileWriter fw = null; BufferedWriter out = null;
				
				File file = new File(directory.getName()+ File.separator + databaseFilms.get(item).getName().trim() +".txt");
				fw = new FileWriter(file);
				out = new BufferedWriter(fw);
				
				try {
				 	out.write(new String("ID: " + databaseFilms.get(item).getID() + " \n"
				 			+ "Typ: "+ databaseFilms.get(item).getType() + " \n"
				 			+ "Nazev: "+ databaseFilms.get(item).getName() + "\n"
				 			+ "Reziser: "+ databaseFilms.get(item).getDirector() + "\n"
				 			+ "Rok: "+ databaseFilms.get(item).getYearOfPublication() + "\n"));
				 	
				 	if (databaseFilms.get(item).getClass() == Anime.class) {
				 		out.write(new String("Doporuceny vek: " + databaseFilms.get(item).getAge() + "\n")); }
				 	
				 	out.write(new String("Ucinkujici: "));
				 	for (Human film : databaseFilms.get(item).getActors().keySet()) {
						out.write(new String( film.getFullName() + ", " ));
					}
				 	
					 	out.write(new String("\nHodnoceni: "));
					 	for (Feedback feedback : databaseFilms.get(item).getFeedback()) {
						out.write(new String(  "\n" +feedback.getNumber() + " - " + feedback.getComment()));
					}
				 	}
				
				catch (IOException e) {
				System.out.println("Soubor nelze otev��t");
				} 
				
				finally {
				out.close(); fw.close();
				}
				
			}
		}
		else 
		{
			directory.mkdir();
			SaveToFile();
		}
		
	}

	public void LoadFromFiles() throws IOException
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		
		if (directory.exists())
		{
			
			for (File file : directory.listFiles()) {
				
				FileReader fr = null; BufferedReader in = null;
				String oddelovac ="[ :] " ;
				String radek;
				String [] castiTextu;
				int id= 0;
				int idx=0;
				String name = null;
				short year=0;
				String type=null;
				String directorName=null;
				String directorSurame=null;
				byte age=0;
				
				fr = new FileReader(file);
				in = new BufferedReader(fr);
				
				//ID
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				id = Integer.parseInt(castiTextu[1].trim());

				//Type
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				type=castiTextu[1].trim();
				
				//Name
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				name=castiTextu[1].trim();
				
				
				//Reziser
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				String[] splitDirectorName = castiTextu[1].split(" ");
				directorName=splitDirectorName[0].trim();
				directorSurame=splitDirectorName[1].trim();
				
				//Year
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				year=Short.parseShort(castiTextu[1]);
				
				
				if (type.equalsIgnoreCase("Film"))
				{
					idx = addFilm(name, year);
				}
				
				else 
				{
					//Age
					radek = in.readLine();
					castiTextu = radek.split(oddelovac);
					age=Byte.parseByte(castiTextu[1]);
					idx = addAnime(name, year, age);
				}
				
				//Director
				
				getProduction(idx).setDirector(addHuman(directorName, directorSurame, getProduction(idx)));
				getProduction(idx).setID(id);
				
				//Actors
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				String splitActors = ",";			//  brad pitt, tom hanks, ade adel, 
				String splitNames = " ";			//brad pitt 
				
				//if ()              // dodelat, kdyt nejsou zadani herci (jakoze nemusi) tak aby to fungovalo i takto
				for (String s : castiTextu[1].split(splitActors))
				{
					
					String []string = s.trim().split(splitNames);
					if (string.length > 1) {
						
						getProduction(idx).addActor(addHuman(string[0], string[1], getProduction(idx))); 
						
						}
					
				}
				
				//Feedbacks
				radek = in.readLine();
				while ((radek = in.readLine()) != null)
				{
					castiTextu = radek.split(" - ");
					getProduction(idx).setFeedback(Byte.parseByte(castiTextu[0].trim()), castiTextu[1].trim());
				}
					in.close(); 
					fr.close();
			}
			
		}
		else 
		{
			System.out.println("Adresar: \"" + System.getProperty("user.dir") + File.separator +"database"+"\" - nenalezen");
		}
	 
	}

	public void ClearDatabaseFile()
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		if (directory.exists())
		{
			
		}
	}

	public HashMap<Integer, Human> getDatabaseHuman() {
		return databaseHuman;
	}

	public void setDatabaseHuman(HashMap<Integer, Human> databaseHuman) {
		this.databaseHuman = databaseHuman;
	}
	
}  // end of database class