package frames;

import java.util.HashMap;

import javax.swing.JPanel;


public abstract class Frame {
	
	public abstract boolean isComplete();
	
	public abstract JPanel getContentPane();
	
	public abstract HashMap<Object, Object> getSessionData();
}
