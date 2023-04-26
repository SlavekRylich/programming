package film_database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HumanRole {

	private static final AtomicInteger count = new AtomicInteger(0); 
	private int ID=1;
	public static List<Object> instances = new ArrayList<>();
	private Production production;
	private Human human;
	private Role role;
	
	public enum Role {		
		Director,
		Actor,
		Animator
	}
	
	public HumanRole(Production production, Human human, Role role)
	{

		ID = count.incrementAndGet();
		instances.add(new java.lang.ref.WeakReference<HumanRole>(this));
		this.setProduction(production);
		this.setHuman(human);
		this.role=role;
	}

	public Human getHuman() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return human.toString() + " " + production.getName() + " " + role;
	}

	
}
