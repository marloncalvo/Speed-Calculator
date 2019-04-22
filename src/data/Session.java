package data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;

public class Session implements Serializable {
	/**
	 * 
	 */
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
		int base = 100;
		for(Question question : questions) {
			double mult = 1;
			
			if(!question.isCorrect())
				consecutive = 0;
			
			// make score
			if(consecutive != 0) {
				mult += .15*consecutive;
				System.out.println("mult: " + mult);
				double toDeduct = Math.pow(.85, question.getTime().getSeconds());
				System.out.println("dect: " + toDeduct);
				score += (base*toDeduct)*mult;
			}
			else
			{
				double toDeduct = Math.pow(.85, question.getTime().getSeconds());
				score += (base*toDeduct);
			}
			
			
			if(question.isCorrect()) {
				num_correct++;
				consecutive++;
			}
			System.out.println("ANothger check for time: " + question.getTime());
			total_time = total_time.plus(question.getTime());
		}
		
		System.out.print("TOTAL TIME??"  + total_time.getNano());
		
		correct_perc = ((double)num_correct/questions.size());
				
		avg_timepq = total_time.dividedBy(questions.size());
		
		user.getSessions().addFirst(this);
		
		try {
			user.storeUserData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Question generateQuestion() {
		SecureRandom rand = new SecureRandom();
		
		int pow = (int)Math.pow(10, num_digits);
		int digit = pow + 1;
		
		int num1 = rand.nextInt(digit);
		
		int num2;
		num2 = (int) (rand.nextInt(digit))%pow + 1; 
		
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
	
//	protected Session(double score, int num_problems, int num_digits, int num_correct,
//			LocalTime total_time, LocalTime avg_timepq, double correct_perc) {
//		this.score = score;
//		this.num_problems = num_problems;
//		this.num_digits = num_digits;
//		this.num_correct = num_correct;
//		this.total_time = total_time;
//		this.avg_timepq = avg_timepq;
//		this.correct_perc = correct_perc;
//	}

}
