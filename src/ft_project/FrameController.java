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


//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//setBounds(100, 100, 450, 300);

//EventQueue.invokeLater(new Runnable() {
//	public void run() {
//		try {
//			MainFrame frame = new MainFrame();
//			frame.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//});