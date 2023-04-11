package film_database;

import java.io.Console;
import java.util.ArrayList;
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
		}
		catch (Exception e) 
		{
			System.out.println("zadejte prosim cele cislo ");
			input.nextLine();
			number = OnlyShort(input);
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
				
				System.out.println("Zadejte nazev:");  // vyresit nacitani viceslovneho nazvu filmu
				name = scan.next();
				System.out.println("Zadejte rezisera:");
				director = scan.next();
				System.out.println("Zadejte rok vydani:");
				year = OnlyShort(scan);
				switch(op) {
				case 1:
				{
					System.out.println("Zadejte divacke hodnoceni 1-5:");
					feedback = scan.nextByte();
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
							for (int i = 0; i < cnt; i++) {
								effectives.add(scan.nextLine());  // vyresit nacitani 
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
					feedback = scan.nextByte();
					System.out.println("Zadejte doporuceny vek:");
					age = scan.nextByte();
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
				
				break;
				}
			case 3: {
				
				break;
				}
			case 4: {
				for (Production production : pruductionsList) {
					System.out.println(production.getName());
						
					}
				break;
				}
			case 5: {
				
				break;
				}
			case 6: {
				
				break;
				}
			case 7: {
				
				break;
				}
			case 8: {
				
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

}
