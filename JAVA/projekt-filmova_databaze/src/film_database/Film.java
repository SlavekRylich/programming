package film_database;

import java.util.List;

public class Film extends Production{

	private final static byte MAXFEEDBACK = 5;
	
	private List<String> actors;
	
	public Film(String name, String director, short yearOfPublication,byte feedback) {
		super(name, director, yearOfPublication, feedback);
		
	}
	
	public Film(String name, String director, short yearOfPublication,byte feedback, List<String> actors) {
		super(name, director, yearOfPublication, feedback);
		this.actors=actors;
		
	}
	
	
	public List<String> getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors.add(actors);
	}

	
	
	

}
