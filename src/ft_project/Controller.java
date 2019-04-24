package ft_project;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.WindowConstants;

import data.Session;
import data.User;
import frames.Frame;
import frames.MainFrame;
import frames.RankingFrame;
import frames.ResultsFrame;
import frames.UserHistroyFrame;
import utils.FileUtils;

public class Controller {
	
	private static FrameController frameController;
	
	public static void main(String[] args) {
		init();
	}
	
	private static void init() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frameController = FrameController.getFrameController();
				frameController.setSize(new Dimension(600, 400));
				switchFrame(MainFrame.createMainFrame());
				frameController.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
	}
	
	public static void frameCompleted(Frame frame) {
		// Button action commands load new frames
		String frameName = frame.getClass().getName();
		frameName = frameName.replace("frames.", "");
		try {
			Method m = Controller.class.getDeclaredMethod(frameName, Frame.class);
			m.invoke(Controller.class, frame);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void MainFrame(Frame frame) {
		HashMap<Object, Object> data = frame.getSessionData();
		String name = (String) data.get("name");
		
		User user = null;
		if(FileUtils.checkFileExists(User.getUserLocation(name))) {
			try {
				user = User.readUserData(name);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		else {
			user = new User(name);
		}
		
		Session session = new Session(user, Integer.valueOf((String) data.get("num_problems")), Integer.valueOf((String)data.get("num_digits")), (String)data.get("op"));
		Thread t = new Thread(() -> {
			initGame(frame, session);
		});
		
		t.start();

	}
	
	private static void initGame(Frame frame, Session session) {
		GameController gm = new GameController(session);
		Thread t = new Thread(() -> {
			gm.init();
		});
		
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
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
			e.printStackTrace();
		}
		
		switchFrame(ResultsFrame.createResultsFrame(session));
	}
	
	@SuppressWarnings("unused")
	private static void QuestionFrame(Frame frame) {
		switchFrame(frame);
	}
	
	@SuppressWarnings("unused")
	private static void ResultsFrame(Frame frame) {
		HashMap<Object, Object> data = frame.getSessionData();
		
		switchFrame(UserHistroyFrame.createUserHistoryFrame((User) data.get("user")));
	}

	@SuppressWarnings("unused")
	private static void UserHistroyFrame(Frame frame) {
		HashMap<Object, Object> data = frame.getSessionData();

		if((int)data.get("toLoad") == UserHistroyFrame.MAIN)
			switchFrame(MainFrame.createMainFrame());
		else
			switchFrame(RankingFrame.createRankingFrame());
	}

	@SuppressWarnings("unused")
	private static void RankingFrame(Frame frame) {
		switchFrame(MainFrame.createMainFrame());
	}
	
	private static void switchFrame(Frame frame)  {
		frameController.setContentPane(frame.getContentPane());
		frameController.repaint();
	    frameController.revalidate(); 
	}
	
	public static void closeApplication() {
		frameController.dispose();
		System.exit(0);
	}
}
