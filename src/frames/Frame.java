package frames;

import java.util.HashMap;

import javax.swing.JPanel;

import ft_project.FrameController;

public abstract class Frame {
	private static final FrameController frame = FrameController.getFrameController();

	public abstract boolean isComplete();
	
	public abstract JPanel getContentPane();
	
	public abstract HashMap<Object, Object> getSessionData();
}
