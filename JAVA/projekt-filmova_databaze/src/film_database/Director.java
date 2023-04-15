package film_database;

public class Director extends Human{

	public Director(String name, String surname) {
		super(name, surname);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getName() + " " + super.getSurname() + " reziser";
	}

}
