package film_database;

public class Anime extends Production {
	private static final long serialVersionUID = 1L;
	private final static byte MAXFEEDBACK = 10;
	private byte age;
	

	
	public Anime(String name, short yearOfPublication, byte age) {
		super(name, yearOfPublication);
		this.setAge(age);
	}
	
	@Override
	public String toString() {
		return super.getName();	
		}


	public byte getMaxfeedback() {
		return MAXFEEDBACK;
	}

	@Override
	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}
	
	@Override
	public Human addActor(Human human, Production production) {
		super.UniquenessHumanRole(human, production, HumanRole.Role.Animator);
		return human;	
	}

	@Override
	public String getType() {
		return "Anime";
	}
}
