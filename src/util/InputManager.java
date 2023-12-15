package util;

import java.io.File;
import java.io.IOException;

public class InputManager {

	public static void main(String[] args) throws IOException {
		setupInputFiles();
		renameAllFiles();
	}

	private static void setupInputFiles() throws IOException {
		File ressourceFolder = new File("ressources");
		File testFolder = createDir(ressourceFolder, "test");
		File taskFolder = createDir(ressourceFolder, "task");

		for (int i = 1; i < 26; i++) {
			createFile(taskFolder, i + ".txt");
			createFile(testFolder, i + ".txt");
		}
	}

	private static File createDir(File parent, String dirName) throws IOException {
		File dir = new File(parent, dirName);
		if (dir.exists()) {
			System.out.println(String.format("Directory '%s' exists", dir.getName()));
			return dir;
		}
		dir.mkdir();
		return dir;
	}

	private static File createFile(File parent, String fileName) throws IOException {
		File file = new File(parent, fileName);
		if (file.exists()) {
			System.out.println(String.format("File '%s' exists", file.getName()));
			return file;
		}
		file.createNewFile();
		return file;
	}
	
	private static void renameAllFiles() {
		File testFolder = new File("ressources", "test");
		File taskFolder = new File("ressources", "task");
		
		for (int i = 1; i < 10; i++) {
			File testFile = new File(testFolder, i + ".txt");
			File taskFile = new File(taskFolder, i + ".txt");
			renameFile(testFile, "0" + i + ".txt");
			renameFile(taskFile, "0" + i + ".txt");
		}
	}
	
	private static void renameFile(File file,String filename) {
		 File newFile = new File(file.getParentFile(), filename);
		 if (newFile.exists()) {
			 return;
		 }
		 
		 file.renameTo(newFile);
	}
}
