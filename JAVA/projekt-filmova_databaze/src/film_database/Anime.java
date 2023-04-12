package film_database;

import java.util.List;

public class Anime extends Production {


	private final static byte MAXFEEDBACK = 10;
	
	private byte age;
	private List<String> animators;

	
	public Anime(String name, String director, short yearOfPublication,byte feedback, byte age) {
		super(name, director, yearOfPublication, feedback);
		this.age=age;
	}
	
	public Anime(String name, String director, short yearOfPublication,byte feedback, byte age, List<String> animators) {
		super(name, director, yearOfPublication, feedback);
		this.age=age;
		this.animators=animators;
	}


	public List<String> getAnimators() {
		return animators;
	}


	public void setAnimators(String animators) {
		this.animators.add(animators);
	}

	public static byte getMaxfeedback() {
		return MAXFEEDBACK;
	}

}
