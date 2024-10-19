package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogFileCreator {

	public void createDailyLogs(String[] chargingStations, String[] energySources) {
		LocalDate currentDate = LocalDate.now();
		String dateString = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		for (String station : chargingStations) {
			String logFileName = "C:\\Users\\bhava\\Desktop\\logs\\" + station + "_" + dateString + ".log";
			createLogFile(logFileName);
		}

		for (String source : energySources) {
			String logFileName = "C:\\Users\\bhava\\Desktop\\logs\\" + source + "_" + dateString + ".log";
			createLogFile(logFileName);
		}

		String systemLogFileName = "C:\\Users\\bhava\\Desktop\\logs\\" + "System_" + dateString + ".log";
		createLogFile(systemLogFileName);
	}

	private void createLogFile(String fileName) {
		try {
			File logFile = new File(fileName);
			if (logFile.createNewFile()) {
				System.out.println("Log file created: " + logFile.getName());
			} else {
				System.out.println("Log file already exists: " + logFile.getName());
			}
			FileWriter writer = new FileWriter(logFile);
			writer.write("Log file created for: " + fileName + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while creating the log file.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LogFileCreator logCreator = new LogFileCreator();

		String[] chargingStations = { "Station1", "Station2", "Station3" };
		String[] energySources = { "Solar", "Wind", "Hydro" };

		logCreator.createDailyLogs(chargingStations, energySources);
	}
}
