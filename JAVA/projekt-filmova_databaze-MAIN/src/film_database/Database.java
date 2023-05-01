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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


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
	
	public int getID() {
		return filmID;
	}

	public void setID(int iD) {
		this.filmID = iD;
	}
	
	public HashMap<Integer, Production> getDatabaseFilms() {
		return databaseFilms;
	}

	public void setDatabaseFilms(HashMap<Integer, Production> databaseItems) {
		this.databaseFilms = databaseItems;
	}
	
	public HashMap<Integer, Human> getDatabaseHuman() {
		return databaseHuman;
	}

	public void setDatabaseHuman(HashMap<Integer, Human> databaseHuman) {
		this.databaseHuman = databaseHuman;
	}
	
	public Production getProduction(int ID)
	{
		return databaseFilms.get(ID);
	}
	
	public Human getHuman(int ID)
	{
		return databaseHuman.get(ID);
	}
	
	public int addFilm(String name,short year)
	{
		
		for (Integer item: databaseFilms.keySet()) {
			String filmName = databaseFilms.get(item).getName();
			
			if (filmName.equals(name))
			{
				System.out.println("Film " + filmName + " jiz v databazi exituje");
				return -1;
			}
			
		}
		
		databaseFilms.put(filmID++, new Film(name, year));
		return filmID - 1 ;
	}
	
	public int addAnime(String name, short year, byte age)
	{
		for (Integer item: databaseFilms.keySet()) 
		{
			
			String filmName = databaseFilms.get(item).getName();
			
			if (filmName.equals(name))
			{
				System.out.println("Film " + filmName + " jiz exituje");
				return -1;
			}
			
		}
		
		databaseFilms.put(filmID++, new Anime(name, year, age));
		return filmID - 1 ;
	}
	
	public Human addHuman(String name, String surname) {
				
		for (Integer item: databaseHuman.keySet()) 
		{									
		    
		    if (databaseHuman.get(item).getFullName().equals(name + " " + surname))
		    {
		    	return databaseHuman.get(item);
		    }
		    
		}
		
		Human human = new Human(name,surname);		
		databaseHuman.put(humanID++, human) ;
		return human;
	}
	
	public HumanRole addHumanRole(Production production, Human human, HumanRole.Role role)
	{
		for (Iterator<HumanRole> iterator = HumanRole.instances.iterator(); iterator.hasNext();) 
		
		{
		    HumanRole value = iterator.next();
		    Production product = value.getProduction();
		    Human hum = value.getHuman();
		    HumanRole.Role rol=value.getRole();
		    
		    if (product != production && hum != human && rol != role)
		    {
			    return new HumanRole(production, human, role);
		    }
		    
		}
		
		return null;
		
	}
	
	public boolean deleteProduction(Integer productKey)
	{
		
		for (Integer item : databaseFilms.keySet()) 
		{
			
			int id = databaseFilms.get(item).getID();
			
			if (id == productKey)
			{
				
				for (Iterator<HumanRole> iterator = HumanRole.instances.iterator(); iterator.hasNext();) 
				{
					
				    HumanRole value = iterator.next();
				    int idx = value.getProduction().getID();
				    
				    if (id == idx)
				    {
					    iterator.remove();
				    }
				    
				}
				
				return databaseFilms.remove(id) != null;
			}
			
		}
		
		return false;
	}
	
	public boolean PrintDatabaseIOnlyName()
	{
		if (databaseFilms.size() != 0)
		{
			
			for (Integer item: databaseFilms.keySet()) 
			{
				
			    String key = item.toString();
			    String value = databaseFilms.get(item).toString();
			    System.out.println(key + " " + value);
			    
			}
			
			return true;
			
		}
		else 
		{
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}
	
	public boolean PrintAllDatabase()
	{
		if (databaseFilms.size() != 0)
		{
			
	        System.out.println(String.format("%-25s%-8s%-9s%-30s%-20s%-20s%-20s",
	        		" ","ID", "Typ", "Nazev", "Reziser", "Rok vydani", "Doporuceny vek"));
			
	        for (Integer item: databaseFilms.keySet()) 
	        {
	        	
			    String filmId = item.toString();
			    String name = databaseFilms.get(item).toString();
			    String filmType = databaseFilms.get(item).getClass().getSimpleName();
			    String director = databaseFilms.get(item).getDirector();
			    short year = databaseFilms.get(item).getYearOfPublication();
			    short age = databaseFilms.get(item).getAge();
			    System.out.println();

			    if (databaseFilms.get(item).getClass() == Anime.class)
			    {
	                System.out.println(String.format("%-25s%-8s%-9s%-30s%-20s%-20s%-20s"," ", filmId,filmType, name, director, year, age));
				    System.out.print(String.format("%-25s%-8s" , " ", "Seznam animatoru: "));
			    }
			    else 
			    {
	                System.out.println(String.format("%-25s%-8s%-9s%-30s%-20s%-20s"," ", filmId,filmType, name, director, year));
				    System.out.print(String.format("%-25s%-8s" , " ", "Seznam hercu: "));
			    }
			    
			    databaseFilms.get(item).PrintListActors();
			    System.out.println();
			    
			}
	        
			return true;
			
		}
		else 
		{
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}
	
	public void FindHuman()
	{
		if (databaseHuman.size() != 0)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Zadejte vyraz:");
			int numberMatches=0;
			String name = null;
			String string = sc.nextLine();
			System.out.println("Nalezeno:");
			
			for (Integer item: databaseHuman.keySet()) 
			{
				
				name = databaseHuman.get(item).getFullName();
				int condition= (name.indexOf(string));
				
				if ( condition > -1)
				{
				    numberMatches++;
				    System.out.println("- " +name);
				}
				
			}
			
			if (numberMatches != 0) 
			{
				System.out.println("Zadejte osobu:");
				String findHuman = sc.nextLine();
				Human human= null;
				int count = 0;
				
				for (Integer key : databaseHuman.keySet()) 
				{
					
					name = databaseHuman.get(key).getFullName();
					
					if (name.equals(findHuman))
					{
						human = databaseHuman.get(key);
					}
					
				}
				
				if (human == null) 
				{
					System.out.println("Zadali jste spatne osobu");
					return;
				}
				
				
				for (Iterator<HumanRole> iterator = HumanRole.instances.iterator(); iterator.hasNext();) 
				{
					
				    HumanRole value = iterator.next();
				     name = value.getHuman().getFullName();
				   
					    if (name.equals(findHuman))
					    {
						    System.out.println(value.getProduction().getName() + " - " + value.printRole());
						    count++;
						}
					    
				}
				
				if (count<1) 
				{
					System.out.println(human.getFullName() +" se nepodili na zadnem filmu");
				}
				
				return;
				
			}
			else
			{
				System.out.println("Nenalezen zadny zaznam");
				return;
			}
			
		}
		else 
		{
			System.out.println("V databazi neni zadana zadna osoba");
			return;
		}
	}
	
	public boolean Sort()
	{
		if (databaseFilms.size() != 0)
		{
			
			for (Integer item: databaseFilms.keySet()) 
			{
			    String key = item.toString();
			    databaseFilms.get(item).SortFeedback();
			    String value = databaseFilms.get(item).toString();
			    System.out.println(key + " " + value);
			}
			
			return true;
			
		}
		else 
		{
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
		
	}
	
	public Production FindProductionByString() 
	{
		if (databaseFilms.size() != 0)
		{
			System.out.println("Zadejte vyraz:");
			Scanner scan = new Scanner(System.in);
			int countMatches=0;
			String filmName = null;
			String string = scan.nextLine();
			System.out.println("Nalezeno:");
			
			for (Integer item: databaseFilms.keySet()) 
			{
				filmName = databaseFilms.get(item).getName();
				int matches= (filmName.indexOf(string));
				
				if ( matches > -1)
				{
				    countMatches++;
				    System.out.println("- " +filmName);
				}
				
			}
			
			if (countMatches != 0) 
			{
				System.out.println("Vyberte film:");
				String findFilm=scan.nextLine();
				
				return getProductionByName(findFilm);
			}
			
			
			return null;
		
		}
		else 
		{
			System.out.println("V databazi neni zadany zadny film");
			return null;
		}
			
	}
	
	public Production getProductionByName(String word)
	{
		
		for (Integer item: databaseFilms.keySet()) 
		{
		    String name = databaseFilms.get(item).getName();
		    
		    if (name.equals(word))
		    {
		    	return databaseFilms.get(item);
		    }
		    
		}
		
		System.out.println("Zadejte spravne cely nazev");
		return null;
		
	}
	
	public   Map<Human, List<HumanRole>> FindHumanInMultipleFilms()
	{
		int i =0;
		Set<HumanRole> list = null;
		Map<Human, List<HumanRole>> dictionaryTemp = new HashMap<>();
		
		
		if (databaseFilms.size() != 0)
		{
			ArrayList<HumanRole> copyInstances = HumanRole.instances;
			copyInstances.sort((o1, o2) -> (o1.getHuman().getFullName().compareTo(o2.getHuman().getFullName())));
			
			
			for (Iterator<HumanRole> iterator1 = copyInstances.iterator(); iterator1.hasNext();) 
			{
				
			    HumanRole film1 = iterator1.next();
			    Human actor1 = film1.getHuman();
			    int j =0;
			    list = new HashSet<>();
			    
			    for (Iterator<HumanRole> iterator2 = copyInstances.iterator(); iterator2.hasNext(); ) 
			    {
			    	
				    HumanRole film2 = iterator2.next();
				    Human actor2 = film2.getHuman();
				    
				    if (j>i)
				    {
				    	if (dictionaryTemp.containsKey(actor1)) break;
				    	
					    if (actor1.equals(actor2))
					    {
					    	list.add(film1);
					    	list.add(film2);
					    	
					    }
					    
				    }
				    
				    j++;
				    
				}
			    
			    if (list.size()>0) 
			    	{
				    	List<HumanRole> sortedList = new ArrayList<>(list);
				        sortedList.sort((o1, o2) -> (o1.getProduction().getName().compareTo(o2.getProduction().getName())));
			    		dictionaryTemp.put(actor1,sortedList);
			    	}
			    
			    i++;
			    
			}
			
			
			
	        
	        TreeMap<Human, List<HumanRole>> sorted = new TreeMap<>(dictionaryTemp);
	 
	        
	        sorted.putAll(dictionaryTemp);
			return sorted;
			
		}
		else 
		{
			System.out.println("V databazi neni zadany zadny film");
			return null;
		}
		
	}
	
	public boolean SaveToFile(Production production) throws IOException
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		
		if (directory.exists())
		{
			
			for (Integer item : databaseFilms.keySet()) 
			{
				
				if (databaseFilms.get(item).equals(production)) 
				{
					FileWriter fw = null; BufferedWriter out = null;
					String filmName=databaseFilms.get(item).getName();
					
					if (filmName.contains(":"))
					{
						String copyFilmName=filmName;
						filmName="";
						
						for (char c : copyFilmName.toCharArray()) 
						{
							if (c == ':')
							{
								filmName+="_";
							}
							else filmName+=c;
							
						}
						
					}
					
					File file = new File(directory.getName()+ File.separator + filmName.trim() +".txt");
					fw = new FileWriter(file);
					out = new BufferedWriter(fw);
					
					try 
					{
						 	out.write(new String("ID; " + databaseFilms.get(item).getID() + " \n"
						 			+ "Typ; "+ databaseFilms.get(item).getType() + " \n"
						 			+ "Nazev; "+ databaseFilms.get(item).getName() + "\n"
						 			+ "Reziser; "+ databaseFilms.get(item).getDirector() + "\n"
						 			+ "Rok; "+ databaseFilms.get(item).getYearOfPublication() + "\n"));
						 	
						 	if (databaseFilms.get(item).getClass() == Anime.class) 
						 	{
						 		out.write(new String("Doporuceny vek; " + databaseFilms.get(item).getAge() + "\n")); 
						 	}
						 	
						 	out.write(new String("Ucinkujici; "));
						 	
						 	for (Human actor : databaseFilms.get(item).getActors()) 
						 	{
								out.write(new String( actor.getFullName() + ", " ));
							}
						 	
						 	out.write(new String("\nHodnoceni; "));
						 	for (Feedback feedback : databaseFilms.get(item).getFeedback()) 
						 	{
						 		out.write(new String(  "\n" +feedback.getNumber() + " - " + feedback.getComment()));
							}
					 	
					 }
					catch (IOException e) 
					{
						System.out.println("Soubor nelze otevøít");
					} 
					
					finally 
					{
						out.close(); fw.close();
					}
					
					return true;
				}
				
			}
			
		}
		else 
		{
			directory.mkdir();
			SaveToFile(production);
		}
		
		return false;
		
	}

	public void LoadFromFile() throws IOException
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		
		if (directory.exists())
		{
			FileReader fr = null; BufferedReader in = null;
			Scanner scan = new Scanner(System.in);
			
			for (File file : directory.listFiles()) 
			{
				System.out.println(file.getName());
			}
			
			System.out.println("Vyberte soubor:");
			String film= scan.nextLine();
			
		
			for (File file : directory.listFiles()) 
			{

				if (film.equals(file.getName()))
				{
					
					try 
					{
						String oddelovac ="[ ;] " ;
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
						
						
						radek = in.readLine();
						castiTextu = radek.split(oddelovac);
						id = Integer.parseInt(castiTextu[1].trim());
		
						
						radek = in.readLine();
						castiTextu = radek.split(oddelovac);
						type=castiTextu[1].trim();
						
						
						radek = in.readLine();
						castiTextu = radek.split(oddelovac);
						name=castiTextu[1].trim();
						
						
						
						radek = in.readLine();
						castiTextu = radek.split(oddelovac);
						String[] splitDirectorName = castiTextu[1].split(" ");
						directorName=splitDirectorName[0].trim();
						directorSurame=splitDirectorName[1].trim();
						
						
						radek = in.readLine();
						castiTextu = radek.split(oddelovac);
						year=Short.parseShort(castiTextu[1]);
						
						
						if (type.equalsIgnoreCase("Film"))
						{
							idx = addFilm(name, year);
							
						}
						
						else 
						{
							
							radek = in.readLine();
							castiTextu = radek.split(oddelovac);
							age=Byte.parseByte(castiTextu[1]);
							idx = addAnime(name, year, age);
						}
						
						
						getProduction(idx).setDirector(addHuman(directorName, directorSurame));
						getProduction(idx).setID(id);
						
						
						radek = in.readLine();
						castiTextu = radek.split(oddelovac);
						String splitActors = ",";			
						String splitNames = " ";			
						
						if (castiTextu.length > 1)
						{
							for (String s : castiTextu[1].split(splitActors))
							{
							
								String []string = s.trim().split(splitNames);
								
								if (string.length > 1) 
								{
									getProduction(idx).addActor(addHuman(string[0], string[1]), getProduction(idx)); 
								}
								
							}
							
						}
						
						
						radek = in.readLine();
						
						while ((radek = in.readLine()) != null)
						{
							castiTextu = radek.split(" - ");
							getProduction(idx).setFeedback(Byte.parseByte(castiTextu[0].trim()), castiTextu[1].trim());
						}
						
						in.close(); 
						fr.close();
						
					} 
					catch (NullPointerException e) 
					{
						
					}
					finally 
					{
						in.close(); 
						fr.close();
					}
					
				}
				
			}
			
		}
		else 
		{
			System.out.println("Adresar: \"" + System.getProperty("user.dir") + File.separator +"database"+"\" - nenalezen");
		}
	 
	}


	
}  