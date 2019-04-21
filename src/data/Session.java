package data;

import java.time.Duration;
import java.util.ArrayList;

import frames.QuestionFrame;

import java.security.SecureRandom;

public class Session {
	private User user;
	private int num_problems;
	private int num_digits;
	private int num_correct;
	private String op;
	
	private ArrayList<Question> questions;
	
	private double score;
	private Duration total_time;
	private Duration avg_timepq;
	private double correct_perc;
	
	public Session(User user, int num_problems, int num_digits, String op) {
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
		for(Question question : questions) {
			if(question.isCorrect())
				num_correct++;
			System.out.println("ANothger check for time: " + question.getTime());
			total_time = total_time.plus(question.getTime());
		}
		
		System.out.print("TOTAL TIME??"  + total_time.getNano());
		
		correct_perc = ((double)num_correct/questions.size());
				
		avg_timepq = total_time.dividedBy(questions.size());
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
	
	public String getUserName() {
		return user.getName();
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
