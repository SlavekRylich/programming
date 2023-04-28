package film_database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import film_database.HumanRole.Role;

public abstract class Production implements Serializable{

//	public enum Role {		
//		Director,
//		Actor,
//		Animator
//	}
	
	
	private static final long serialVersionUID = 1L;
	private static final AtomicInteger count = new AtomicInteger(0); 
	protected int ID=1;
	private String name;
	private Human director;
	private ArrayList<Feedback> feedback = new ArrayList<>();
	private short yearOfPublication;
	//protected HashMap<Human, Role> effectives;
	
	public Production(String name, short yearOfPublication)	{
		ID = count.incrementAndGet();
		this.name=name;
		this.yearOfPublication=yearOfPublication;
		//effectives = new HashMap<Human, Role>();
		
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


//	public HashMap<Human, Role> getActors() {
//		return effectives;
//	}
//	
	public List<Human> getActors()
	{
		List<Human> actors = new ArrayList<>();
		
		for (HumanRole item : HumanRole.instances) {
			String name = item.getProduction().getName();
			
			if (name == this.name)
			{
				actors.add(item.getHuman());
			}
			
		}
		
		return actors;
	}
	
	public void PrintListActors()
	{
		for (HumanRole item : HumanRole.instances) {
			String name = item.getProduction().getName();
			if (name == this.name)
			{
				String nameActor = item.getHuman().getFullName();
				if (item.getRole() != Role.Director)
				{
					System.out.print(nameActor + ", ");
				}
			}
		}
		
		System.out.println();
	}
	
	public void PrintActorsWithID()
	{
		for (HumanRole item : HumanRole.instances) {
			String name = item.getProduction().getName();
			if (name == this.name)
			{
				String nameActorWithID = item.getHuman().getFullNameWithID();
				if (item.getRole() != Role.Director)
				{
					System.out.println(nameActorWithID);
				}
				
			}
			
		}
	}

	public abstract Human addActor(Human human,Production production);
	
	public void deleteActor(int id)
	{
		for (HumanRole item : HumanRole.instances) {
			String name = item.getProduction().getName();
			if (name == this.name)
			{
				int actorID = item.getHuman().getID();
				if (actorID == id)
				{
					item.deleteInstance(id);
				}
			}
		}
//		Human human = FindByID(id);
//		effectives.remove(human);
	}
	
	
	public String getDirector()
	{
		return this.director.getFullName();
	}
	public Human setDirector(Human human) {
		director= human;
		new HumanRole(this, human, HumanRole.Role.Director);
		return director;
	}
	public Human FindByID(int id)
	{
		for (HumanRole item : HumanRole.instances) {
			String name = item.getProduction().getName();
			if (name == this.name)
			{
				int actorID = item.getHuman().getID();
				if (actorID == id)
				{
					return item.getHuman();
				}
			}
		}
//		for (var item: effectives.keySet()) 
//		{
//			int id = item.getID();
//			
//			  if (id == number)
//			  {
//			  return item;
//			  }
//		}
		return null;
	}
	
	public void SortFeedback()
	{
		feedback.sort((o1, o2) -> (o1.getNumber().compareTo(o2.getNumber())));
		Collections.reverse(feedback);
	}
	public void PrintFeedback()
	{
		System.out.println("Hodnoceni:");
		for (Feedback feedback : feedback) {
			System.out.println(feedback.toString());
		}
	}
	
	public abstract String getType();
	
	public abstract byte getMaxfeedback();
	public abstract byte getAge();


	@Override
	public String toString() {
		return name;
	}

	
	
	
}
