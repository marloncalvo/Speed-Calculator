package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// statics
	public static final String SAVE_LOC = "savedata/";
	public static final String USER_EXT = ".save";
	
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
	
	public static String getUserLocation(String name) {
		return SAVE_LOC + name + USER_EXT;
	}
	
	public void storeUserData() 
			  throws IOException, ClassNotFoundException {
		System.out.println("WRITING");

		File file = new File(SAVE_LOC + name + USER_EXT);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdir();
		file.createNewFile();
	    FileOutputStream fileOutputStream = new FileOutputStream(SAVE_LOC + name + USER_EXT);
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(this);
	    objectOutputStream.flush();
	    objectOutputStream.close();
	}
	
	public static User readUserData(String name) 
			throws IOException, ClassNotFoundException {
		System.out.println("READING");
		FileInputStream fileInputStream = new FileInputStream(SAVE_LOC + name + USER_EXT);
	    ObjectInputStream objectInputStream
	      = new ObjectInputStream(fileInputStream);
	    User user = (User) objectInputStream.readObject();
	    objectInputStream.close(); 
	    
	    return user;
	}
}
