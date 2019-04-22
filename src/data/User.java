package data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class User {
	// User global info
	private String name;
	private double max_score_index;
		
	// All session info
	private LinkedList<Session> sessions;
	
	public User(String name) {
		sessions = new LinkedList<>();
		this.name = name;
	}
	
	public LinkedList<Session> getSessions() {
		return sessions;
	}
	
	public String getName() {
		return name;
	}
}
