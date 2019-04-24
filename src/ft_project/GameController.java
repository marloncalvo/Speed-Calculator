package ft_project;

import data.Session;
import frames.Frame;
import frames.QuestionFrame;

public class GameController implements Game {
	
	private static volatile boolean isComplete = false;
	private Session session;
	
	
	
	public GameController(Session session) {
		this.session = session;
	}
	
	public void init() {
		for(int i = 0; i < session.getNumOfProblems(); i++) {
			isComplete = false;
			Thread t = new Thread(() -> {
				Frame frame = new QuestionFrame(this, session.generateQuestion());
				Controller.frameCompleted(frame);
			});
			
			t.start();
			
			try {
				t.join();
				while(!isComplete);
					Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void isComplete(boolean isComplete) {
		GameController.isComplete = isComplete;
	}
}
