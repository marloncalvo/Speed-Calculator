package data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Ranking {
	private static final String SAVE_LOCATION = User.SAVE_LOC + "rankings.save";
	private static final int NAME = 0;
	private static final int SCORE = 1;
	private static final int TOP = 10;
	
	private static Ranking ranking = null;
	private final File rankingsFile;
	private LinkedHashMap<String, Double> data;
	
	public static Ranking getRankings() {
		if(ranking == null)
			return new Ranking();
		
		return ranking;
	}
	
	private Ranking() {
		rankingsFile = new File(SAVE_LOCATION);
		
		try {
			rankingsFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEntry(User user) {
		loadData();
		
		String newLine = user.getName() + " " + user.getMaxScore(), 
				formerSpotKey = "";
		boolean indexFound = true, requiresChange = true;

		if(data.containsKey(user.getName())) {

			for(Map.Entry<String, Double> entry : data.entrySet()) {
				
				// found the spot where user score should go
				if(entry.getValue() < user.getMaxScore()) {

					// if the location is the user's old high score
					if(entry.getKey().equals(user.getName())) {
						data.replace(entry.getKey(), user.getMaxScore());
						indexFound = true;
						requiresChange = false;
						break;
					}
					
					// if it is another user high score
					else {
						formerSpotKey = entry.getKey();
						indexFound = true;
						break;
					}
				}
			}
		}
		else {
			for(Map.Entry<String, Double> entry : data.entrySet()) {
				if(entry.getValue() < user.getMaxScore()) {
					formerSpotKey = entry.getKey();
					indexFound = true;
					break;
				}
			}
		}
		
		PrintWriter write = null;
		try {
			write = new PrintWriter(rankingsFile);
			} catch (IOException e) {
				e.printStackTrace();
		}
		
		write.println("#Rankings List for Arith Helper - Author: Marlon Calvo");
		
		int j = 0;
		
		// could be that it was not found in hashmap, or that it simply replaced old value
		if(!indexFound || !requiresChange) {
			for(Map.Entry<String, Double> entry : data.entrySet()) {
				write.println(entry.getKey() + " " + entry.getValue());
				j++;
			}
			
			if(!indexFound && j < TOP)
				write.println(newLine);
		}
		else {
			
			boolean isPrinted = false;
			for(Map.Entry<String, Double> entry : data.entrySet()) {
				if(j >= TOP)
					break;
				
				if(entry.getKey().equals(user.getName())) {
					continue;
				}
				if(entry.getKey().equals(formerSpotKey)) {
					write.println(newLine);
					isPrinted = true;
					if(++j < TOP)
						write.println(entry.getKey() + " " + entry.getValue());
					continue;
				}
				
				write.println(entry.getKey() + " " + entry.getValue());
				j++;
			}
			
			if(j < TOP && !isPrinted)
				write.println(newLine);
		}
		
		System.out.println("j: " + j + " TOP:" + TOP);
		System.out.println(j < TOP);
		
		write.close();
		loadData();
	}
	
	private void loadData() {
		Scanner scan = null;
		data = new LinkedHashMap<>();
		try {
			scan = new Scanner(rankingsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(scan.hasNextLine()) {
			scan.nextLine();
		}
		
		int i = 0;
		while(scan.hasNext() && i < TOP ) {
			String[] t = scan.nextLine().split("\\s+");
			System.out.println(Arrays.toString(t));
			data.put(t[NAME], Double.valueOf(t[SCORE]));
			i++;
		}
		
		scan.close();
	}
}
