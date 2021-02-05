//package project1;

/*
 * grivera64
 * Project 1
 * Program: NameSearch.java
 * Required Files: BoyNames.txt & GirlNames.txt
 * 4 February 2021
 * 
 * Purpose: Searching for the boy/girl name in two text files to see if popular
 * --------------------------------------------------------------------
 * In the following problem use these two files which have been placed in 
 * Project Solutions/Project 1 tab.
 * 
 * - GirlNames.txt – This file contains a list of the 200 most popular names given 
 * to girls born in the United States for the years 2000 through 2009.
 * 
 * - BoyNames.txt – This file contains a list of the 200 most popular names given 
 * to boys born in the United States for the years 2000 through 2009.
 * 
 * In main: (20)
 * Get the names of the two files with the lists. 
 * Call getArray with the name of the file (a String). 
 * 		Return a String array with the names in the file.
 * Ask the user if they wish to continue querying against the list.
 * 		The user should be able to enter a boy’s name, a girl’s name, or both, and 
 * 		the application will display messages indicating whether the names were 
 * 		among the most popular. 
 * To determine if the name is on the list call isFound with
 * 		a reference to the name being searched for and the array of names as 
 * 		the two parameters. Ignore capitalization in your compares and in any other inputs.
 * 
 * In getArray: (10)
 * Create a File object and a Scanner for that file object.
 * Call getNumNames to determine the number of items in the file. Send getNumNames the
 * reference to the File object.
 * Read the list of names from the file into an array and then return that array.
 * Close the Scanner
 * 
 * In getNumNames: (10)
 * Accept the File object name and count the number of items in the file.
 * Return the number of items in the file.
 * 
 * In isFound: (10)
 * Accept the name being searched for and the array with all the names. 
 * Return true if the name is found, otherwise false. Ignore capitalization in your compares.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class NameSearch
{
	
	public static final Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException
	{
		
		//local variables
		String boyDir,
				girlDir;
		
		String[] boyNames, girlNames;
		boolean nextName = false, nameFoundBoy = false, nameFoundGirl = false;
		String userInput, userNameBoy, userNameGirl;
		
		
		System.out.printf("Please enter the directory of the Boy Names file:\n>> ");
		boyDir = keyboard.nextLine();
		
		boyNames = getArray(boyDir);
		
		System.out.printf("Please enter the directory of the Girl Names file:\n>> ");
		girlDir = keyboard.nextLine();
		
		girlNames = getArray(girlDir);
		
		do
		{
			do
			{
				System.out.printf("Enter \'Y\' or \'N\' if you want to search for a name:\n>> ");
				userInput = keyboard.nextLine();
				
				if (userInput.length() == 1 
						&& (userInput.contains("N") || userInput.contains("n")))
				{
					System.out.printf("\n\nThank you for using this program!\n\n");
					keyboard.close();
					System.exit(0);
				}
				else if (userInput.length() == 1 
						&& (userInput.contains("Y") || userInput.contains("y")))
				{
					nextName = true;
				}
				else
				{
					nextName = false;
				}
				
				System.out.printf("\n\n");
				
			} while (!nextName);
			
			System.out.printf("Please enter a boy name or \'N\' for none:\n>> ");
		
			userNameBoy = keyboard.nextLine().trim();
			if (!userNameBoy.equalsIgnoreCase("N"))
			{
				nameFoundBoy = isFound(userNameBoy, boyNames);
			}
			
			System.out.printf("Please enter a girl name or \'N\' for none:\n>> ");
			
			userNameGirl = keyboard.nextLine().trim();
			if (!userNameGirl.equalsIgnoreCase("N"))
			{
				nameFoundGirl = isFound(userNameGirl, girlNames);
			}
			
			System.out.printf("\n");
			
			//System.out.println("DEBUG: " + nameFoundBoy);
			//System.out.println("DEBUG: " + ((nameFoundBoy == true) ? "is": "is not"));
			
			if (!userNameBoy.equalsIgnoreCase("N"))
			{
				System.out.printf("%s %s of the most popular boy names.\n", userNameBoy, 
						(nameFoundBoy == true) ? "is": "is not");
			}
			
			if (!userNameGirl.equalsIgnoreCase("N"))
			{
				System.out.printf("%s %s of the most popular girl names.\n", userNameGirl, 
						(nameFoundGirl == true) ? "is": "is not");	
			}
			
		} while (true);
		
	}
	
	@SuppressWarnings("resource")
	public static String[] getArray(String dir) throws IOException
	{
		
		File file;
		Scanner in;
		int amount;
		
		file = new File(dir);
		
		while (true)
		{
			if (!(file.exists()))
			{
				
				System.out.printf("File %s not found.\n\n"
						+ "Please enter a valid file directory:\n>> ", dir);
				file = new File(keyboard.nextLine());
				System.out.printf("\n");
				
			}
			else
			{
				System.out.printf("File found!\n\n");
				break;
			}
		}
		
		
		
		in = new Scanner(file);
		
		if (!(in.hasNext()))
		{
			System.out.printf("The file is corrupt.\nPlease re-run the program!\n\n");
			System.exit(0);
		}
		
		amount = getNumNames(file);
		
		//System.out.println("DEBUG: The array length is " + amount);
		String[] names = new String[amount];
		int currIndex;
		
		for (currIndex = 0; currIndex < amount && in.hasNext(); currIndex++)
		{
			names[currIndex] = in.nextLine().trim();
		}
		
		
//		for (String name : names)
//		{
//			System.out.println("DEBUG: " + name);
//		}
		
		//close files, though for some reason the compiler still complains
		in.close();
		
		return names;
		
		
	}
	
	public static int getNumNames(File file) throws IOException
	{
		Scanner in = new Scanner(file);
		int amount = 0;
		
		while (in.hasNext())
		{
			amount++;
			in.nextLine();
		}
		
		in.close();
		
		return amount;
	}
	
	public static boolean isFound(String userName, String[] nameList)
	{
		boolean found = false;
		
		for (String possible : nameList)
		{
			//System.out.println("DEBUG " + possible);
			if (possible.equalsIgnoreCase(userName.trim()))
			{
				//System.out.println("DEBUG: MATCH FOUND");
				found = true;
				break;
			}
//			else
//			{
//				found = false;
//			}
		}
		
		//System.out.println("DEBUG: Returning " + found);
		
		return found;
	}
	
}
