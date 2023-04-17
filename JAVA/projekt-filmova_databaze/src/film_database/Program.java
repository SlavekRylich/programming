package film_database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Program {
	
	private static int OnlyInt(Scanner input)
	{
		int number=0;
		try {
			number=input.nextInt();
			input.nextLine();
		}
		catch (Exception e) 
		{
			System.out.println("zadejte prosim cele cislo ");
			input.nextLine();
			number = OnlyInt(input);
		}
		return number;
	}
	private static short OnlyShort(Scanner input)
	{
		short number=0;
		try {
			number=input.nextShort();
			input.nextLine();
		}
		catch (Exception e) 
		{
			System.out.println("zadejte prosim cele cislo ");
			input.nextLine();
			number = OnlyShort(input);
		}
		return number;
	}
	private static byte OnlyByte(Scanner input)
	{
		byte number=0;
		try {
			number=input.nextByte();
			input.nextLine();
		}
		catch (Exception e) 
		{
			System.out.println("zadejte prosim cele cislo ");
			input.nextLine();
			number = OnlyByte(input);
		}
		return number;
	}


	
	public static void main(String[] args) {
		
		List<Production> pruductionsList = new ArrayList<>();
		int ID=0;
		
		Database database = new Database();
		
		Scanner sc = new Scanner(System.in);
		int option=0;
		boolean end= false;
		while (!end) {
			System.out.println("Vyberte moznost");
			System.out.println("1. Pridat novy film");
			System.out.println("2. Upravit existujici film");
			System.out.println("3. Smazat film");
			System.out.println("4. Vlozit hodnoceni filmu");
			System.out.println("5. Vypis filmu");
			System.out.println("6. Vyhledat film");
			System.out.println("7. Vypis hercu nebo animatoru ucinkujicich ve vice filmech");
			System.out.println("8. Vypis vsech filmu podle ucinkovani daneho herce nebo animatora");
			System.out.println("9. Ulozit film do souboru");
			System.out.println("10. Nacteni filmu ze souboru");
			System.out.println("11. Ukonceni programu");
			option=OnlyInt(sc);

			switch (option) {
			case 1: {						// "1. Pridat novy film"
				int op;
				String name;
				String directorName;
				String directorSurname;
				short year;
				byte feedback;
				byte age;
				Scanner scan = new Scanner(System.in);
				
				System.out.println("1. Hrany film");
				System.out.println("2. Animovany");
				System.out.println("Zadejte volbu:");
				op=OnlyInt(scan);
				System.out.println("Zadejte nazev:");  
				name = scan.nextLine();
				System.out.println("Zadejte jmeno a prijmeni rezisera:");
				directorName = scan.next();
				directorSurname= scan.next();
				System.out.println("Zadejte rok vydani:");
				year = OnlyShort(scan);
				
				switch(op) {
				case 1:
				{
					System.out.println("Zadejte divacke hodnoceni 1-5:");
					feedback = OnlyByte(scan);
					
					ID = database.addFilm(name, year, feedback);
					
					System.out.println("Zadat herce:");
					System.out.println("Ano (y)\n Ne (n)");
					var opt = scan.next();
					boolean run=false;
					
					while (!run)
						switch (opt) {
						case "y": 
							
							System.out.println("Kolik hercu chcete zadat:");
							int cnt = OnlyInt(scan); 
							for (int i = 0; i < cnt; i++) {
								System.out.println("Zadejte jmeno a prijmeni " + (i + 1) + ". herce:");
								database.getProduction(ID).addActor(scan.next(), scan.next());
							}
							run=true;
							break;
						case "n":
							run=true;
							break;
						default:
							System.out.println("Zadejte pouze y nebo n");
						}
					
					
					break;
				}
				case 2:
					boolean run=false;
					System.out.println("Zadejte divacke hodnoceni 1-10:");
					feedback = OnlyByte(scan);
					System.out.println("Zadejte doporuceny vek:");
					age = OnlyByte(scan);

					ID = database.addAnime(name,year, feedback, age);
					
					System.out.println("Zadat animatory:");
					System.out.println("Ano (y)\n Ne (n)");
					var opt = scan.next();
					
					
					while (!run)
						switch (opt) {
						case "y": 
							System.out.println("Kolik animatoru chcete zadat:");
							int cnt = OnlyInt(scan);
							for (int i = 0; i < cnt; i++) {
								System.out.println("Zadejte jmeno a prijmeni " + (i+1) + ". animatora:");
								database.getProduction(ID).addActor(scan.next(), scan.next());
							}
							run=true;
							break;
						case "n":
							run=true;
							break;
						default:
							System.out.println("Zadejte pouze y nebo n");
						}
					
					break;
				}
				
				database.getProduction(ID).setDirector(directorName,directorSurname);
				break;
				
				}
			case 2: {												//"2. Upravit existujici film"
				Scanner scan = new Scanner(System.in);
				Production change = FindProduct(scan,database);
				if (change != null)
				{
					boolean close = false;
					while(!close)
					{
						System.out.println("Vybran: " + change.getName());
						System.out.println("1. Nazev");
						System.out.println("2. Rezisera");
						System.out.println("3. Rok vydani");
						if (change.getClass()== Film.class)
						{
						System.out.println("4. Seznam hercu");
						}
						else if (change.getClass()== Anime.class)
						{
						System.out.println("4. Seznam animatoru");
						System.out.println("5. Doporuceny vek");
						}
						System.out.println("0. Ukoncit upravy");
						System.out.println("Upravit: ");

						switch (OnlyInt(scan)) {
						case 1:
							System.out.println(change.getName() +" - Upravit na: ");
							change.setName(scan.nextLine());
							break;
						case 2:
							System.out.println(change.getDirector() +" - Upravit na: ");
							change.setDirector(scan.next(),scan.next());
							break;
						case 3:
							System.out.println(change.getYearOfPublication() +" - Upravit na: ");
							change.setYearOfPublication(scan.nextShort());
							break;
						case 4:
							System.out.println("1: Pridat");				// potom vyresit podminku, kdyz pridam herce jestli podle jmena uz existuje tak se jen priradi
							System.out.println("2: Zmenit");				// nebo se vytrvori novy Human
							System.out.println("3: Odebrat");							// a zpetne reference - relace osob na filmy
							switch (OnlyInt(scan)) {
							case 1:
								System.out.println("Zadejte jmeno a prijmeni:");
								change.addActor(scan.next(), scan.next());
								break;
							case 2:
								System.out.println("Index Jmeno Prijmeni");
								for (Iterator<Human> iterator = change.getActors().iterator(); iterator.hasNext();) 
								{
									String string = (iterator.next().getFullNameWithID());
									System.out.println(string);
								}
								System.out.println("Ktereho chcete zmenit? (index):");
								int choice=OnlyInt(scan);
								change.deleteActor(choice);
								System.out.println("Zadejte jmeno a prijmeni noveho:");
								change.addActor(scan.next(), scan.next());
								break;
							case 3:
								System.out.println("Index Jmeno Prijmeni");
								for (Iterator<Human> iterator = change.getActors().iterator(); iterator.hasNext();) 
								{
									String string = (iterator.next().getFullNameWithID());
									System.out.println(string);
								}
								System.out.println("Ktereho chcete zmenit? (index):");
								choice=OnlyInt(scan);
								change.deleteActor(choice);
								break;
							default:
								System.out.println("Zadejte cisla pouze z nabidky");
								break;
							}
							
							
							break;
						case 5:
							if (change.getClass() == Anime.class)
							{
								System.out.println(((Anime) change).getAge() +" - Upravit na: ");
								((Anime)change).setAge(OnlyByte(scan));
							}
							else
							{
								System.out.println("Zadejte cisla pouze z nabidky");
							}
							break;
						case 0:
							close=true;
							break;
	
						default:
							System.out.println("Zadejte cisla pouze z nabidky");
							break;
						}
						
					}
					break;
					}
				else
					break;
				}
			
			case 3: {																		//"3. Smazat film"
				Scanner scan = new Scanner(System.in);
				Production remove = FindProduct(scan,database);
				if (remove !=null)
				{
					if (database.DelProduction(remove.getID()) != false) 
					{
						System.out.println("Uspesne smazano");
					}
				}
				break;
				}
			case 4: {																		//"4.Vlozit hodnoceni"
				Scanner scan = new Scanner(System.in);
				Production insertFeedback = FindProduct(scan,database);
				if (insertFeedback !=null)
				{
					
																	// tady sem skoncil, vlozit hodnoceni, nova trida Feedback
				}
				break;
			}
			case 5: {																		//"5. Vypis filmu"
				database.PrintDatabase();
					
				break;
				}
			case 6: {																		//"6. Vyhledat film"
				Sort(pruductionsList);  // sort the list
				break;
				}
			case 7: {																		//"7. Vypis hercu nebo animatoru ucinkujicich ve vice filmech"
				
				break;
				}
			case 8: {																		//"8. Vypis vsech filmu podle ucinkovani daneho herce nebo animatora"
				
				break;
				}
			case 9: {																		//"9. Ulozit film do souboru"
				Scanner scan = new Scanner(System.in);
				PrintProductions(pruductionsList);
				System.out.println("Zadejte nazev filmu, ktery chcete ulozit");
				int select = Find(scan,pruductionsList);
				System.out.println(select);
				break;
				}
			case 10: {																		//"10. Nacteni filmu ze souboru"
				
				break;
				}
			case 11: {																		//"11. Ukonceni programu"
				System.out.println();	
				System.out.println("konec programu...");
				}
				end = true;
				break;
				
			default:
				System.out.println("Zadejte prosim pouze z nabidky");
				break;
			
			} // end of switch
			
		} // end of while
		
		
		
	} // end of main 
	
	public static void PrintProductions(List<Production> pruductionsList) {
		int i=1;
		for (var piece: pruductionsList) {
			System.out.println(i + ". " + piece.getName());
			i++;
		}
	}
	
	public static <T> int Find(Scanner scan, List<Production> list)  // neni dodelane
	{
		Collections.sort(list,(o1, o2) -> (o1.getName().compareTo(o2.getName())));  // sort list by Name
		
		int count=0;
		for ( Production iterable_element : list) {
			if (scan.nextLine() == iterable_element.getName()) 
			{
				list.contains(iterable_element.getName());
				return count;
			}
			System.out.println(iterable_element.getName());
			count++;
		}
		return 0;
	}
	public static void Sort(List<Production> list)
	{
		int count=0;
		Collections.sort(list,(o1, o2) -> (o1.getName().compareTo(o2.getName())));  // sort list by Name
		for ( Production iterable_element : list) {
			
			System.out.println(iterable_element.getName());
			count++;
		}
	}
	
	public static Production FindProduct(Scanner sc, Database database)
	{
		if (database.PrintDatabase())
		{
			System.out.println("Zadejte nazev filmu:");
			
			return database.FindByName(sc.nextLine());
		}
		else
		{
			return null;
		}
	}
	
	

} // end of class Program 