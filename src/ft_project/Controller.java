package ft_project;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import data.Question;
import data.Session;
import data.User;
import frames.Frame;
import frames.MainFrame;
import frames.QuestionFrame;
import frames.ResultsFrame;
import frames.UserHistroyFrame;

public class Controller {
	
	private static FrameController frameController;
	
	public static void main(String[] args) {
		init();
	}
	
	private static void init() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				frameController = FrameController.getFrameController();
				switchFrame(MainFrame.createMainFrame());
			}
		});
	}
	
	public static void frameCompleted(Frame frame) {
		// Button action commands load new frames
		String frameName = frame.getClass().getName();
		frameName = frameName.replace("frames.", "");
		try {
			Method ma = Controller.class.getDeclaredMethod(frameName, Frame.class);
			ma.invoke(Controller.class, frame);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void MainFrame(Frame frame) {
		HashMap<Object, Object> data = frame.getSessionData();
		System.out.println("Name: " + data.get("name"));
		User user = new User((String) data.get("name"));
		Session session = new Session(user, Integer.valueOf((String) data.get("num_problems")), Integer.valueOf((String)data.get("num_digits")), (String)data.get("op"));
		Thread t = new Thread(() -> {
			initGame(frame, session);
		});
		
		t.start();

	}
	
	private static void initGame(Frame frame, Session session) {
		GameController gm = GameController.getGameController(session);
		Thread t = new Thread(() -> {
			gm.init();
		});
		
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		Thread load = new Thread(() -> {
			session.compileData();
		});
		
		load.start();
		
		try {
			load.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switchFrame(ResultsFrame.createResultsFrame(session));
	}
	
	private static void ResultsFrame(Frame frame) {
		HashMap<Object, Object> data = frame.getSessionData();
		
		switchFrame(UserHistroyFrame.createUserHistoryFrame((User) data.get("user")));
	}
	
	public static void switchFrame(Frame frame)  {
		frameController.setContentPane(frame.getContentPane());
		frameController.repaint();
	    frameController.revalidate(); 
	}
}
