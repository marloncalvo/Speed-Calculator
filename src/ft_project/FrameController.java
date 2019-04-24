package ft_project;

import javax.swing.JFrame;

public class FrameController extends JFrame{

	private static final long serialVersionUID = 1L;
	private static FrameController frame;
	
	private FrameController() {
		setVisible(true);
	}
	
	public static FrameController getFrameController() {
		if(frame == null) 
			frame = new FrameController();
			
		
		return frame;
	}
}