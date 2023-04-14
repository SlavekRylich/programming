package film_database;

public class Animator extends Human{

	public Animator(String name, String surname) {
		super(name, surname);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getName() + " " + super.getSurname() + " jsem animator";
	}

}
