package film_database;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Human {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int ID=1;
	private String name;
	private String surname;
	private String fullName;
	private ArrayList<Production> productions;
	private ArrayList<Production> artworks;
	
	public Human(String name, String surname) {
		ID = count.incrementAndGet();
		this.setName(name);
		this.setSurname(surname);
		productions = new ArrayList<>();
		setArtworks(new ArrayList<>());
	}

	public ArrayList<Production> getPruductions() {
		return productions;
	}

	public void setPruductions(ArrayList<Production> pruductions) {
		this.productions = pruductions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public ArrayList<Production> getArtworks() {
		return artworks;
	}

	public void setArtworks(ArrayList<Production> artworks) {
		this.artworks = artworks;
	}
	
	public String getFullName()
	{
		return this.getID() + " " + this.getName() + " " + this.getSurname();
	}
	
	
	
	
}
