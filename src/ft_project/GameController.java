package ft_project;

import java.time.Duration;
import java.time.Instant;

import data.Question;
import data.Session;
import frames.Frame;
import frames.QuestionFrame;

public class GameController  implements Game {
	
	private static GameController controller;
	private static volatile boolean isComplete = false;
	private Session session;
	
	public static GameController getGameController() {
		if(controller == null)
			try {
				throw new Exception("No Game Controller");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return controller;
	}
	
	public static GameController getGameController(Session session) {
		if(controller == null)
			controller = new GameController(session);
		
		return controller;
	}
	
	private GameController(Session session) {
		this.session = session;
	}
	
	public void init() {
		for(int i = 0; i < session.getNumOfProblems(); i++) {
			isComplete = false;
			System.out.println("i: " + i);
			Thread t = new Thread(() -> {
				Controller.switchFrame(new QuestionFrame(session.generateQuestion()));
			});
			
			t.start();
			
			try {
				t.join();
				while(!isComplete);
					Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Question question = session.getQuestions().get(i);
			System.out.println("Num of seconds: " + question.getTime().toNanos());
		}
	}
	
	public void isComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
}
