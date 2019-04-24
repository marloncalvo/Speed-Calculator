package utils;

import java.security.SecureRandom;

public class NumberUtils {
	public static int numberOfDigits(int a) {
		return (int) (Math.log10(a) + 1);
	}
	
	public static int numberOfDigits(double a) {
		return (int) (Math.log10(a) + 1);
	}
	
	public static int numberOfDigits(long a) {
		double digits = Math.log10(a);
		return (int) (digits < 0 ? digits : digits + 1);
	}
	
	public static int maxValue(int...values) {
		int max = 0;
		for(int value : values) {
			if (value > max)
				max = value;
		}
		
		return max;
	}
	
	/**
	 * Returns a random integer, inclusive of the passed integer
	 * @param a
	 * @return [0 , Number_Passed]
	 */
	public static int generateRandomInt(int a) {
		SecureRandom rand = new SecureRandom();
		return rand.nextInt(a+1);
	}
	
	/**
	 * Returns a random integer within the given range, inclusive
	 * @param Starting index
	 * @param Ending index
	 * @return [Starting index , Ending index]
	 */
	public static int generateRandomInt(int a, int b) {
		SecureRandom rand = new SecureRandom();
		return b - rand.nextInt((b-a)+1);
	}
}
