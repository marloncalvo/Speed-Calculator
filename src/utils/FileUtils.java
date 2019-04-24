package utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	public static boolean checkFileExists(String name) {
		File file = new File(name);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.exists());
		if(file.exists())
			return true;
		else
			return false;
	}
	
	public static String makeFile(String name) {
		File file = new File(name);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
		
		return file.getAbsolutePath();
	}
}
