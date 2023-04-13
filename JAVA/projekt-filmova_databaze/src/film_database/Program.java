package film_database;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Program {
	
	//private Production [] productionsArray;
	
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
		List<String> effectives;
		
		Scanner sc = new Scanner(System.in);
		int option=0;
		boolean end= false;
		while (!end) {
			System.out.println("Vyberte moznost");
			System.out.println("1. Pridat novy film");
			System.out.println("2. Upravit existujici film");
			System.out.println("3. Smazat film");
			System.out.println("4. Vypis filmu");
			System.out.println("5. Vyhledat film");
			System.out.println("6. Vypis hercu nebo animatoru ucinkujicich ve vice filmech");
			System.out.println("7. Vypis vsech filmu podle ucinkovani daneho herce nebo animatora");
			System.out.println("8. Ulozit film do souboru");
			System.out.println("9. Nacteni filmu ze souboru");
			System.out.println("10. Ukonceni programu");
			option=OnlyInt(sc);

			switch (option) {
			case 1: {
				int op;
				String name;
				String director;
				short year;
				byte feedback;
				byte age;
				Scanner scan = new Scanner(System.in);
				System.out.println("1. Hrany");
				System.out.println("2. Animovany");
				System.out.println("Zadejte volbu:");
				op=OnlyInt(scan);
				System.out.println("Zadejte nazev:");  
				name = scan.nextLine();
				System.out.println("Zadejte rezisera:");
				director = scan.nextLine();
				System.out.println("Zadejte rok vydani:");
				year = OnlyShort(scan);
				switch(op) {
				case 1:
				{
					System.out.println("Zadejte divacke hodnoceni 1-5:");
					feedback = OnlyByte(scan);
					System.out.println("Zadat herce:");
					System.out.println("Ano (y)\n Ne (n)");
					var opt = scan.next();
					boolean run=false;
					
					while (!run)
						switch (opt) {
						case "y": 
							effectives = new ArrayList<>();
							System.out.println("Kolik hercu chcete zadat:");
							int cnt = OnlyInt(scan);
							System.out.println("Zadejte herce:"); 
							for (int i = 0; i < cnt; i++) {
								effectives.add(scan.nextLine());
							}
							pruductionsList.add(new Film(name, director, year, feedback, effectives));
							run=true;
							break;
						case "n":
							pruductionsList.add(new Film(name, director, year, feedback));
							run=true;
							break;
						default:
							System.out.println("Zadejte pouze y nebo n");
						}
					
					
					break;
				}
				case 2:
					System.out.println("Zadejte divacke hodnoceni 1-10:");
					feedback = OnlyByte(scan);
					System.out.println("Zadejte doporuceny vek:");
					age = OnlyByte(scan);
					System.out.println("Zadat animatory:");
					System.out.println("Ano (y)\n Ne (n)");
					var opt = scan.next();
					boolean run=false;
					
					while (!run)
						switch (opt) {
						case "y": 
							effectives = new ArrayList<>();
							System.out.println("Kolik animatoru chcete zadat:");
							int cnt = OnlyInt(scan);
							System.out.println("Zadejte animatory:");
							for (int i = 0; i < cnt; i++) {
								effectives.add(scan.nextLine());
							}
							pruductionsList.add(new Anime(name, director, year, feedback,age, effectives));
							run=true;
							break;
						case "n":
							pruductionsList.add(new Anime(name, director, year, feedback,age));
							run=true;
							break;
						default:
							System.out.println("Zadejte pouze y nebo n");
						}
					
					break;
				}

				break;
				}
			case 2: {
				PrintProductions(pruductionsList);
				System.out.println("Jaky film chcete upravit:");
				
				break;
				}
			case 3: {
				
				break;
				}
			case 4: {
				PrintProductions(pruductionsList);
						
					
				break;
				}
			case 5: {
				Sort(pruductionsList);  // sort the list
				break;
				}
			case 6: {
				
				break;
				}
			case 7: {
				
				break;
				}
			case 8: {
				Scanner scan = new Scanner(System.in);
				PrintProductions(pruductionsList);
				System.out.println("Zadejte nazev filmu, ktery chcete ulozit");
				int select = Find(scan,pruductionsList);
				System.out.println(select);
				break;
				}
			case 9: {
				
				break;
				}
			case 10: {
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
	

} // end of class Program 
