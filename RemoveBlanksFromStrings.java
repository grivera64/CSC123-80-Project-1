package project1;

/*
 * grivera64
 * Project 1
 * Program: RemoveBlanksFromStrings.java
 * Required Files: input and output text files (sampleInput.txt & sampleOutput.txt)
 * 4 February 2021
 * 
 * Purpose: Removes blanks from an input file and outputs to another file
 * ---------------------------------------------------------------------------------
 * This program will read in lines one at a time from a file and write output to a file as 
 * described below.  You can use the file withblanks.txt to test your program, or you can 
 * create your own input file.  The withblanks.txt file is found under the 
 * Project Solutions/Project 1 tab.
 * 
 * The method main does the following: (10)
 * a.	Creates a Scanner to read from the keyboard.
 * b.	Reads in the name of the physical file to be read from (Hint: use next, not nextLine).
 * c.	Calls createFileScanner with the name of the physical file to read from, 
 * receiving the reference to the Scanner as the return value in a Scanner reference variable.
 * d.	Reads in the name of the physical file to write to.
 * e. Calls createPrintWriter with the name of the physical file to write to, 
 * 	receiving the reference to the PrintWriter as the return value in 
 * 	a PrintWriter reference variable.
 * f.	Create a one-dimensional String array of length 100.
 * g.	Call readInLines with a reference to the Scanner created to read from the file, 
 * 	and a reference to the String array, returning an int with the number of lines read in.
 * h.	Call removeBlanks with a reference to the String array described above and to 
 * 	an int with the number of lines read into the array as described above, 
 * 	and then perform the functions described below, accepting a reference to the String array as 
 * 	defined below.
 * i. Write the lines in the returned String array to the output file created in this program.
 * j.	Close the Scanner and PrintWriter objects.
 * 
 * createFileScanner: (10)
 * a.	Accepts as a String the name of the physical file.
 * b.	Creates a Scanner throwing an exception if the physical file doesn’t exist.
 * c.	Returns a reference to the Scanner.
 * 
 * createPrintWriter: (10)
 * a.	Accepts as a String the name of the physical file.
 * b.	Creates a PrintWriter.
 * c.	Returns a reference to the PrintWriter.
 * 
 * readInLines: (10)
 * a.	Accepts a reference to the Scanner for the physical file, and to a String Array.
 * b.	Read in the up to the first 100 lines in the file and place a reference to each line in 
 * the String array.
 * c.	Return an int with the number of lines read in.
 * 
 * removeBlanks: (10)
 * a.	Accepts two parameters including a reference to the String array described above and to 
 * an int with the number of lines read into the array.
 * b.	Create a second String array with the length equal to the number of lines read into 
 * the first array.
 * c.	Removes the blanks from each line and places a reference to the line in 
 * the corresponding cell of the second String array.
 * d.	Return a reference to the second String array

 */

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class RemoveBlanksFromStrings
{
	
	public static void main(String[] args) throws IOException
	{
		//creating input scanner
		Scanner keyboard = new Scanner(System.in);
		
		//creating local variables
		File file;
		Scanner inFile;
		PrintWriter outFile;
		//boolean invalid = false;
		String[] array;
		String input, output;
		
		//file read
		do
		{
			System.out.printf("Please enter the file to remove blanks from:\n>> ");
			input = keyboard.next();
			file = new File(input);
			inFile = createFileScanner(file);
			keyboard.nextLine();
			
		} while (inFile == null);
		
		//file to write to
		do
		{
			System.out.printf("Please enter the output file:\n>> ");
			output = keyboard.next();
			file = new File(output);
			outFile = createPrintWriter(file);
			keyboard.nextLine();
			
		} while (inFile == null);
		
		//processing the data and the actual writing
		array = new String[100];
		
		//reading the number of lines
		int lastLine = readInLines(inFile, array);
		
		//writing each line that doesn't have blanks
		String[] noBlankText = removesBlanks(array, lastLine);
		
		System.out.printf("Writing to directory %s the following:\n\n", output);
		
		//printing to file
		for (int index = 0; index <= lastLine; index++)
		{
			try
			{
				if (noBlankText[index] != null)
				{
					outFile.printf("%s", noBlankText[index]);
					System.out.printf("%s", noBlankText[index]);
				}
				if (noBlankText[index + 1] != null)
				{
					outFile.printf("\n");
					System.out.printf("\n");
				}
			}
			catch (IndexOutOfBoundsException e)
			{
				break;
			}
		}
		
		
		//terminating program and closing any leaks
		System.out.printf("\n\nProgram Terminated!\n\n");
		
		inFile.close();
		outFile.close();
		keyboard.close();
		
		
		
		
	}
	
	public static Scanner createFileScanner(File fileName) throws IOException
	{
		
		if (!fileName.exists())
		{
			System.out.printf("File does not exist! Please try again...\n\n");
			return null;
		}
		
		Scanner fileScanner = new Scanner(fileName);
		
		if (!fileScanner.hasNext())
		{
			System.out.printf("File has no information! Please try again...\n\n");
			return null;
		}
		
		return fileScanner;
	}
	
	public static PrintWriter createPrintWriter(File fileName) throws IOException
	{
		
		if (!fileName.exists())
		{
			System.out.printf("File does not exist! Please try again...\n\n");
			return null;
		}
		
		return new PrintWriter(fileName);
	}
	
	public static int readInLines(Scanner input, String[] array)
	{
		//local variables
		int index = -1;
		int lines = 0;
		
		//reads each line of input in the text file and extracts each individual word
		//in it
		while (input.hasNext() && index < 100)
		{
			String oneLine = input.nextLine();
			
			Scanner line = new Scanner(oneLine);
			
			while (line.hasNext())
			{
				index++;
				array[index] = line.next();
			}
			
			index++;
			array[index] = "\n";
			
			line.close();
			
			lines++;
		}
		
		//return the number of lines that the program needs to print
		return --lines;
	}
	
	public static String[] removesBlanks(String[] array, int lastLine) //WIP
	{
		//creating a muttable string that stores each complete line as a jumble of char's
		StringBuilder newOutput = new StringBuilder("");
		
		//creating array that stores spaceless words
		String[] array2 = new String[array.length];
		int index = 0;
		
		//store the entire line as a string that is created by the String builder combining
		//strings without using too much memory
		for (String result : array)
		{
			//System.out.println("Current result is " + result);
			
			if (result != null && (!result.contains("\n")))
			{
				newOutput.append(result);
				//System.out.println("DEBUG: " + newOutput);
			}
			else if (result != null)
			{
				array2[index] = newOutput.toString();
				//System.out.println("DEBUG: Line is " + array2[index]);
				index++;
				newOutput = new StringBuilder("");
			}
		}
		
		//return the array of lines that will be outputted
		return array2;
	}
	
}