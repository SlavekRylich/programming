package film_database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Production implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final AtomicInteger count = new AtomicInteger(0); 
	protected int ID=1;
	private String name;
	private Human director;
	//private ArrayList<String> comment;
	//private int commentCounter=0;
	//private ArrayList<Byte> feed;
	private ArrayList<Feedback> feedback = new ArrayList<>();
	private short yearOfPublication;
	protected ArrayList<Human> effectives;
	
	public Production(String name, short yearOfPublication)	{
		ID = count.incrementAndGet();
		this.name=name;
		this.yearOfPublication=yearOfPublication;
		effectives = new ArrayList<>();
		
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Feedback> getFeedback() {
		return feedback;
	}


	public void setFeedback(byte feedback, String comment) {
		this.feedback.add(new Feedback(feedback, comment));
	}


	public short getYearOfPublication() {
		return yearOfPublication;
	}


	public void setYearOfPublication(short yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}


	public  int getID() {
		return ID;
	}


	public  void setID(int ID) {
		this.ID = ID;
	}


	public ArrayList<Human> getActors() {
		return effectives;
	}

	public void addActor(String name, String surname) {}
	
	public void deleteActor(int id)
	{
		Human human = FindByID(id);
		effectives.remove(human);
	}
	
	public String getDirector()
	{
		return this.director.getFullName();
	}
	public void setDirector(String name, String surname) {
		this.director = new Director(name, surname);
	}
	public Human FindByID(int number)
	{
		for (var item: effectives) 
		{
			int id = item.getID();
			
			  if (id == number)
			  {
			  return item;
			  }
		}
		return null;
	}
	
	public abstract byte getMaxfeedback();
	
}
