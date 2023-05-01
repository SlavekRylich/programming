package film_database;

public class Film extends Production{
	private static final long serialVersionUID = 1L;
	private final static byte MAXFEEDBACK = 5;
	
	public Film(String name, short yearOfPublication) {
		super(name, yearOfPublication);
		
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
	

	public byte getMaxfeedback() {
		return MAXFEEDBACK;
	}
	
	@Override
	public Human addActor(Human human, Production production) {
		super.UniquenessHumanRole(human, production, HumanRole.Role.Actor);
		return human;	
	}

	@Override
	public byte getAge() {
		return 0;
	}

	@Override
	public String getType() {
		return "Film";
	}
	

}
