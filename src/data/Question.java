package data;

import java.io.Serializable;
import java.time.Duration;

import utils.NumberUtils;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int num1;
	private int num2;
	private long correct_answer;
	private long user_answer;
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
	
	public void setUserAnswer(long user_answer) {
		this.user_answer = user_answer;
	}
	
	public void setTime(Duration time) {
		this.time = time;
	}
	
	public int getNum1() {
		return num1;
	}
	
	public int getNum2() {
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
	
	public long getUserAnswer() {
		return user_answer;
	}
	
	public long getCorrectAnswer() {
		return correct_answer;
	}
	
	private void doRandom() {

		int n = NumberUtils.generateRandomInt(3);
		
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
	
	public boolean isAdd() {
		if(getArithOpInt() == 0)
			return true;
		else
			return false;
	}
	
	public boolean isSub() {
		if(getArithOpInt() == 1)
			return true;
		else
			return false;
	}
	
	public boolean isMul() {
		if(getArithOpInt() == 2)
			return true;
		else
			return false;
	}
	
	public boolean isDiv() {
		if(getArithOpInt() == 3)
			return true;
		else
			return false;
	}
	
	public int getArithOpInt() {
		switch(this.op) {
		case ADD:
			return 0;
		case SUB:
			return 1;
		case MUL:
			return 2;
		case DIV:
			return 3;
		}
		
		return -999;
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
