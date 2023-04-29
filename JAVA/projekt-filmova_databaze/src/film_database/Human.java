package film_database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public  class Human {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int ID=0;
	private String name;
	private String surname;
	//private Set<Production> productions;
	//private Type type;
	public static List<Human> instances = new ArrayList<>();
	
//	public enum Type {
//		Director,
//		Actor,
//		Animator
//	}
	
	public Human(String name, String surname) {		//public Human(String name, String surname,Production film,Type type) {
		instances.add(ID,this);
		ID = count.incrementAndGet();
		this.setName(name);
		this.setSurname(surname);	
		//productions = new HashSet<>();
		//this.type=type;
		//productions.add(film);
	}

//	public Set<Production> getPruductions() {
//		return productions;
//	}
//
//	public void setPruductions(Set<Production> productions) {
//		this.productions = productions;
//	}
//
//	public void addProductions(Production production)
//	{
//		productions.add(production);
//	}
//	public void rmProductions(Production production)
//	{
//		productions.remove(production);
//	}
	
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
	
	public String getFullName()
	{
		return name + " " + surname;
	}
	
	public String getFullNameWithID()
	{
		return this.getID()+ " " + name + " " + surname;
	}

//	public Type getType() {
//		return type;
//	}
	

	@Override
	public boolean equals(Object obj) {
		if ((obj!=null)&&(obj instanceof Human)){
			if ((this.name+this.surname)==((Human)obj).getName()+((Human)obj).getSurname())
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}
	
	public Human getHuman()
	{
		return this;
	}
	
	
	
	
}
