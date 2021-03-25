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
	
	private String logFile;
	private int records; 
	
	public CustomLogger() {
		this.setLogFile("./conversations.txt");
		this.setRecords(0);
	}
	
	public void log(Object data) {
		
		try {
			setRecords(getRecords() + 1);
			
			String logText = String.valueOf(getRecords()).concat(data.toString()).concat("\n");
			
			Path path = Paths.get(getLogFile());
			
			if (!logFileExists(path)) {
				Files.createFile(path);
			}
			
			Files.write(path, logText.getBytes(), StandardOpenOption.APPEND);
		
		} catch (IOException e) {
		    System.out.println("Error: ".concat(e.getMessage()));
		}
	}
	
	public List<String> getLogs() {
		
		List<String> history = new ArrayList<String>();
		
		Path path = Paths.get(getLogFile());
		
		if (logFileExists(path)) {
		
			try (Stream<String> stream = Files.lines(Paths.get(getLogFile()))) {
			
				stream.forEach((line)->history.add(line));
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return history;
		
	}
	
	private boolean logFileExists(Path path) {
		return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

}
