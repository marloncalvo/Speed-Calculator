package data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

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
	
	public Session getSession(String name) {
		for(Session s : sessions) {
			if(s.toString() == name) {
				return s;
			}
		}
		
		return null;
	}
	
	public LinkedList<Session> getSessions() {
		return sessions;
	}
	
	public String getName() {
		return name;
	}
}
