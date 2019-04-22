package data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.Duration;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int num1;
	private int num2;
	private double correct_answer;
	private double user_answer;
	private String op;
	private Duration time;
	
	public static final String ADD = "+";
	public static final String SUB = "-";
	public static final String MUL = "*";
	public static final String DIV = "/";
	
	protected Question(int num1, int num2, String op) {
		this.num1 = num1;
		this.num2 = num2;
		if(op.equalsIgnoreCase("random"))
			this.op = "random";
		else
			this.op = op.substring(op.indexOf("[") + 1, op.lastIndexOf("]"));
		
		switch(this.op) {
		case ADD:
			add();
			break;
		case SUB:
			sub();
			break;
		case MUL:
			mul();
			break;
		case DIV:
			div();
			break;
		default:
			doRandom();
			break;
		}
	}
	
	public void setUserAnswer(double user_answer) {
		this.user_answer = user_answer;
	}
	
	public void setTime(Duration time) {
		this.time = time;
	}
	
	public double getNum1() {
		return num1;
	}
	
	public double getNum2() {
		return num2;
	}
	
	public String getArithOp() {
		return op;
	}
	
	public Duration getTime() {
		return time;
	}
	
	public boolean isCorrect() {
		if(correct_answer == user_answer)
			return true;
		else
			return false;
	}
	
	public double getUserAnswer() {
		return user_answer;
	}
	
	public double getCorrectAnswer() {
		return correct_answer;
	}
	
	private void doRandom() {
		SecureRandom rand = new SecureRandom();
		int n = rand.nextInt(4);
		
		switch(n) {
		case 0:
			{
				add();
				this.op = ADD;
				break;
			}
			case 1:
			{
				sub();
				this.op = SUB;
				break;
			}
			case 2:
			{
				mul();
				this.op = MUL;
				break;
			}
			case 3:
			{
				div();
				this.op = DIV;
				break;
			}
		}
	}
	
	private void add() {
		correct_answer = num1 + num2;
	}
	
	private void sub() {
		correct_answer = num1 - num2;
	}
	
	private void mul() {
		correct_answer = num1 * num2;
	}
	
	private void div() {
		correct_answer = num1 / num2;
	}
	
}
