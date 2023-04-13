package film_database;

import java.io.Serializable;

public abstract class Production implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String director;
	private String comment;
	private byte feedback;
	private short yearOfPublication;
	
	public Production(String name, String director, short yearOfPublication, byte feedback)	{
		this.name=name;
		this.director=director;
		this.yearOfPublication=yearOfPublication;
		this.feedback=feedback;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public byte getFeedback() {
		return feedback;
	}


	public void setFeedback(byte feedback) {
		this.feedback = feedback;
	}


	public short getYearOfPublication() {
		return yearOfPublication;
	}


	public void setYearOfPublication(short yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
