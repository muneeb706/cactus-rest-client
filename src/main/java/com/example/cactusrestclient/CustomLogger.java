package com.example.cactusrestclient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomLogger {
	
	private static String logFile = "./conversations.txt";
	private static int records = 0; 
	
	public static void log(Object data) {
		
		try {
			records += 1;
			
			String logText = String.valueOf(records).concat(data.toString()).concat("\n");
			
			Path path = Paths.get(logFile);
			
			if (!logFileExists(path)) {
				Files.createFile(path);
			}
			
		    Files.write(path, logText.getBytes(), StandardOpenOption.APPEND);
		
		} catch (IOException e) {
		    System.out.println("Error: ".concat(e.getMessage()));
		}
	}
	
	public static List<String> getLogs() {
		
		List<String> history = new ArrayList<String>();
		
		Path path = Paths.get(logFile);
		
		if (logFileExists(path)) {
		
			try (Stream<String> stream = Files.lines(Paths.get(logFile))) {
			
				stream.forEach((line)->history.add(line));
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return history;
		
	}
	
	private static boolean logFileExists(Path path) {
		return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
	}

}
