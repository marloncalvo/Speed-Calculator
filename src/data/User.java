package data;

import java.time.LocalTime;
import java.util.ArrayList;

public class User {
	// User global info
	private String name;
	private double max_score_index;
		
	// All session info
	private ArrayList<Session> sessions;
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
