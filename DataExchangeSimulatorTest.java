/**
 * 
 */

/**
 * @author Aftab Makbul Makandar
 *
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;

public class DataExchangeSimulatorTest {
//	Test #1 :Byte stream with normal point
	@Test
	public void testByteStreamExampleNormal() throws DataExchangeException{
		DataExchangeSimulator simulator = new DataExchangeSimulator();
		String filename = "byteOutputTest.dat";
		
		simulator.byteStreamExample(filename);
		
//		checking if file exists or not
		File file = new File(filename);
		assertTrue(file.exists());
		
//		deleting file to cleanUp
		file.delete();
	}
	
//	Test#2 : Character with normal input
	@Test
	public void testCharacterStreamExampleNormal() throws DataExchangeException{
		DataExchangeSimulator simulator = new DataExchangeSimulator();
		String filename = "charOutputText.txt";
		String content = "Guten Tag, Folks";
		
		simulator.characterStreamExample(filename, content);
		
//		checking if file exists or not
		File file = new File(filename);
		assertTrue(file.exists());
		
//		deleting file to cleanUp
		file.delete();
	}
	
//	Test#3 : "Exception Scenario" byte stream with invalid filename
	@Test
	public void testByteStreamExampleWithException() {
		DataExchangeSimulator simulator = new DataExchangeSimulator();
		String invalidFilename = "/invalid/path/byteOutputTest.dat";
		
		DataExchangeException exception = assertThrows(DataExchangeException.class, () -> {
			simulator.byteStreamExample(invalidFilename);
		});
		
		assertTrue(exception.getMessage().contains("Failed to write byte data"));
		assertNotNull(exception.getCause());
	}
	
//	Test#4 : "Exception Scenario" char data with invalid filenmae
	@Test
	public void testCharacterStreamExampleWithException() {
		DataExchangeSimulator simulator = new DataExchangeSimulator();
		String invalidFilename = "invalid/path/charOutputTest.txt";
		
		DataExchangeException exception = assertThrows(DataExchangeException.class, () ->{
			simulator.characterStreamExample(invalidFilename, "Test Content");
		});
				
		assertTrue(exception.getMessage().contains("Faied to write char data"));
		assertNotNull(exception.getCause());	// checking if any org cause exists	
	}
	
//	Test#5: Byte stream with large data
	@Test
	public void testByteStreamExampleLargeData() throws DataExchangeException{
		DataExchangeSimulator simulator = new DataExchangeSimulator();
		String filename = "largeByteOutputTest.dat";
		byte[] largeData = new byte[1024 * 1024]; // large data
		
		// wirting large data to a file
		simulator.byteStreamExample(filename);
		
		// checking if file exists or not
		File file = new File(filename);
		assertTrue(file.exists());
		
		// deleting the file for clean up
		file.delete();
	}

}
