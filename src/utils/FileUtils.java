package utils;

import java.io.File;

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
}
