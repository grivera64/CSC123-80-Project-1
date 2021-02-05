package project1;

/*
 * grivera64
 * Project 1
 * Program: StringRotation.java
 * 4 February 2021
 * 
 * Purpose: Scrambles a word by rotating the string from left to right
 * -----------------------------------------------------------------------------
 * Write a program that rotates a given string n characters to the right 
 * (program can be done all in main. . For example, if the input to your program is
 * "rotatemeplease 4", then the output is "easerotatemepl"

 */


import java.util.Scanner;
import java.util.InputMismatchException;

public class StringRotation
{
	
	public static final Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		//local variables
		StringBuilder displayString;
		int spaces = -1;
		
		//request for a string to manipulate
		System.out.printf("Please enter a String to be rotated:\n>> ");
		displayString = new StringBuilder(keyboard.nextLine());
		
		//System.out.printf("DEBUG:\n%s\n%s", userString, displayString);
		
		//request for a degree to rotate the string by
		do
		{
			System.out.printf("Please enter the amount of spaces for the string "
					+ "to be rotated:\n>> ");
			try
			{
				spaces = keyboard.nextInt();
			}
			catch (InputMismatchException e)
			{
				spaces = -1;
			}
			finally
			{
				keyboard.nextLine();
				if (spaces <= 0)
				{
					System.out.printf("The number of spaces must be greater than 0\n"
							+ "Please try again...\n\n");
				}
			}
			
		} while (spaces <= 0);
		
		//rotate string
		for (int rotations = 0; rotations < spaces; rotations++)
		{
			displayString.insert
				(0, displayString.charAt(displayString.length() - 1))
				.delete(displayString.length() - 1, displayString.length());
		}
		
		displayString.trimToSize();
		
		//display the result
		System.out.printf("Result:\n%s\n", displayString);
		
		
	}
	
}