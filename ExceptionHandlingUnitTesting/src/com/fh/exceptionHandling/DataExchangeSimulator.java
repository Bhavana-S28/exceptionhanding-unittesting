package com.fh.exceptionHandling;
import java.io.*;

//Custom exception class for DataExchangeSimulator
class DataExchangeException extends Exception {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public DataExchangeException(String message, Throwable cause) {
     super(message, cause);
 }

 public DataExchangeException(String message) {
     super(message);
 }
}

public class DataExchangeSimulator {

 // Res. Management with try-with-resources and Exception Chaining
 public void byteStreamExample(String filename) throws DataExchangeException {
     byte[] data = {1, 2, 3, 4, 5};
     try (FileOutputStream fos = new FileOutputStream(filename)) {
         fos.write(data);
     } catch (IOException e) {
         throw new DataExchangeException("Failed to write byte data to a file", e);
     }
 }

 // Res. Management with try-with-resources and Exception Chaining
 public void characterStreamExample(String filename, String content) throws DataExchangeException {
     try (FileWriter writer = new FileWriter(filename)) {
         writer.write(content);
     } catch (IOException e) {
         throw new DataExchangeException("Failed to write character data to a file", e);
     }
 }

 // Main method to test the class
 public static void main(String[] args) {
     DataExchangeSimulator simulator = new DataExchangeSimulator();
     
     // Test the byte stream example
     try {
         simulator.byteStreamExample("byteData.txt");
         System.out.println("Byte data written to byteData.txt successfully.");
     } catch (DataExchangeException e) {
         e.printStackTrace();
     }

     // Test the character stream example
     try {
         simulator.characterStreamExample("characterData.txt", "Hello, world!");
         System.out.println("Character data written to characterData.txt successfully.");
     } catch (DataExchangeException e) {
         e.printStackTrace();
     }
 }
}

