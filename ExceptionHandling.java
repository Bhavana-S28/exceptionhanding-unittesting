package exceptionHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionHandling {

	public static void openFile() throws IOException {

		
		File file = new File("C:\\Users\\walde\\Desktop\\Java\\file1.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);

			}
		} catch (IOException e) {
			throw e;
		}
	}

	public static void main(String[] args) {

		try {
			openFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// Divide by zero exception
		int num1 = 12;
		int num2 = 0;

		// Array out of bound exception
		int[] myArray = { 10, 20, 30, 40, 50, 60, 70 };

		try {
			
			//Selecting array item
//			System.out.println(myArray[9]);

			// Divide
//			System.out.println(num1 / num2);

		} catch (ArithmeticException e) {

			System.out.println("Arithmetic exception "+e);

		} catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("Array exception "+e);

		} catch (Exception e) {

			System.out.println("Something went wrong");
		}

	}

}
