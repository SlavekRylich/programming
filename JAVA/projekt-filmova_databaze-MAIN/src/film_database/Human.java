package film_database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public  class Human implements Comparable<Human>{
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int ID=0;
	private String name;
	private String surname;
	public static List<Human> instances = new ArrayList<>();


	
	public Human(String name, String surname) {		
		instances.add(ID,this);
		ID = count.incrementAndGet();
		this.setName(name);
		this.setSurname(surname);	
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
	
	public String getFullName()
	{
		return name + " " + surname;
	}
	
	public String getFullNameWithID()
	{
		return this.getID()+ " " + name + " " + surname;
	}


	@Override
	public boolean equals(Object obj) {
		if ((obj!=null)&&(obj instanceof Human)){
			if ((this.ID)==((Human)obj).getID())
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

	
	@Override
	public int compareTo(Human o)
    {
        String s1 =  this.name + this.surname;
        String s2 =  o.name  + o.surname;
        return s1.compareTo(s2);
    }

	
	
	
}
