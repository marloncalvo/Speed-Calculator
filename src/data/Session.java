package data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;

public class Session implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private int num_problems;
	private int num_digits;
	private int num_correct;
	private String sessionName;
	private String op;
	
	private ArrayList<Question> questions;
	
	private double score;
	private Duration total_time;
	private Duration avg_timepq;
	private double correct_perc;
	
	public Session(User user, int num_problems, int num_digits, String op) {
		sessionName = user.getName() + " " + LocalTime.now();
		
		questions = new ArrayList<>();
		total_time = Duration.ZERO;
		this.num_correct = 0;
		this.user = user;
		this.num_problems = num_problems;
		this.num_digits = num_digits;
		this.op = op;
		score = 0;
	}
	
	public void compileData() {
		int consecutive = 0;
		double difficulty = Math.pow(10, num_digits-1);
		
		final int BASE_SCORE = 100;
		final double BASE_MULTIPLIER = 2.5;
		final double CONS_MULTIPLER = .15;
		final double TIME_MULTIPLIER = .85;
		
		// compile data for each question
		for(Question question : questions) {
			double mult = 1;
			double base = BASE_SCORE * difficulty;

			if(question.isMul() || question.isDiv())
				base *= BASE_MULTIPLIER;
			
			if(!question.isCorrect())
				consecutive = 0;
			else {
				if(consecutive != 0) {
					mult += CONS_MULTIPLER*consecutive;
					double toDeduct = Math.pow(TIME_MULTIPLIER, question.getTime().getSeconds());
					score += (base*toDeduct)*mult;
				}
				else
				{
					double toDeduct = Math.pow(TIME_MULTIPLIER, question.getTime().getSeconds());
					score += (base*toDeduct);
				}
				
				num_correct++;
				consecutive++;
			}
			
			total_time = total_time.plus(question.getTime());
		}
		
		
		// run statistics
		correct_perc = ((double)num_correct/questions.size());
		avg_timepq = total_time.dividedBy(questions.size());
		
		saveData();
	}
	
	private void saveData() {
		if(score > user.getMaxScore()) {
			System.out.println("test: true");
			user.setMaxScore(score);
			Ranking rank = Ranking.getRankings();
			rank.addEntry(user);
		}
		user.getSessions().addFirst(this);
		
		try {
			user.storeUserData();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Question generateQuestion() {
		SecureRandom rand = new SecureRandom();
		
		int pow = (int)Math.pow(10, num_digits);
		int digit = pow - 1;
		
		int num1 = rand.nextInt(digit) + 1;
		int num2 = rand.nextInt(digit) + 1; 
		
		Question question = new Question(num1, num2, op);
		questions.add(question);
		
		System.out.println("question size: " + questions.size());
		
		return question;
	}
	
	public double getCorrectPerc() {
		return correct_perc;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public int getNumCorrect() {
		return num_correct;
	}
	
	public double getScore() {
		return score;
	}
	
	public User getUser() {
		return user;
	}
	
	public Duration getAvgTime() {
		return avg_timepq;
	}
	
	public Duration getTotalTime() {
		return total_time;
	}
	
	public int getNumOfProblems() {
		return num_problems;
	}
	
	@Override
	public String toString() {
		return sessionName;
	}
}
