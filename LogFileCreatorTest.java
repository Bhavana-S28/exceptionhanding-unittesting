package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LogFileCreatorTest {

    private LogFileCreator logFileCreator;
    private Path tempDirectory;

    @BeforeEach
    public void setUp() throws IOException {
        logFileCreator = new LogFileCreator();
        // Create a temporary directory for log files
        tempDirectory = Files.createTempDirectory("logTestDir");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary directory and its contents after each test
        Files.walk(tempDirectory)
                .map(Path::toFile)
                .forEach(File::delete);
        Files.deleteIfExists(tempDirectory);
    }

    // Test Case 1: Valid input for both charging stations and energy sources
    @Test
    public void testCreateDailyLogs_ValidInput() {
        String[] chargingStations = { "Station1", "Station2" };
        String[] energySources = { "Solar", "Wind" };
        logFileCreator.createDailyLogs(chargingStations, energySources);

        String dateString = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertTrue(new File(tempDirectory.toFile(), "Station1_" + dateString + ".log").exists(), "Station1 log file should exist.");
        assertTrue(new File(tempDirectory.toFile(), "Station2_" + dateString + ".log").exists(), "Station2 log file should exist.");
        assertTrue(new File(tempDirectory.toFile(), "Solar_" + dateString + ".log").exists(), "Solar log file should exist.");
        assertTrue(new File(tempDirectory.toFile(), "Wind_" + dateString + ".log").exists(), "Wind log file should exist.");
        assertTrue(new File(tempDirectory.toFile(), "System_" + dateString + ".log").exists(), "System log file should exist.");
    }

    // Test Case 2: Empty charging stations and energy sources arrays
    @Test
    public void testCreateDailyLogs_EmptyArrays() {
        String[] chargingStations = {};
        String[] energySources = {};
        logFileCreator.createDailyLogs(chargingStations, energySources);

        assertEquals(0, tempDirectory.toFile().listFiles().length, "No log files should be created.");
    }

    // Test Case 3: Null charging stations array
    @Test
    public void testCreateDailyLogs_NullChargingStations() {
        String[] energySources = { "Solar", "Wind" };
        logFileCreator.createDailyLogs(null, energySources);

        String dateString = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertTrue(new File(tempDirectory.toFile(), "System_" + dateString + ".log").exists(), "System log file should exist when charging stations are null.");
    }

    // Test Case 4: Null energy sources array
    @Test
    public void testCreateDailyLogs_NullEnergySources() {
        String[] chargingStations = { "Station1", "Station2" };
        logFileCreator.createDailyLogs(chargingStations, null);

        String dateString = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertTrue(new File(tempDirectory.toFile(), "Station1_" + dateString + ".log").exists(), "Station1 log file should exist when energy sources are null.");
        assertTrue(new File(tempDirectory.toFile(), "Station2_" + dateString + ".log").exists(), "Station2 log file should exist when energy sources are null.");
    }

    // Test Case 5: Ensure system log file is always created
    @Test
    public void testCreateDailyLogs_SystemLogCreation() {
        String[] chargingStations = { "Station1" };
        String[] energySources = { "Solar" };
        logFileCreator.createDailyLogs(chargingStations, energySources);

        String dateString = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertTrue(new File(tempDirectory.toFile(), "System_" + dateString + ".log").exists(), "System log file should always be created.");
    }
}
