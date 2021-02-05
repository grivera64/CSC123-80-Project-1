//package project1;

/*
 * grivera64
 * Project 1
 * Program: LastDigit.java
 * 4 February 2021
 * 
 * Purpose: Determines the last digit of the value of 2 to the power of n
 * ----------------------------------------------------------------------------------
 * Prompt:
 * Write a program that prompts a user for an integer n > 0 and determines the last digit 
 * of 3n. Hint: The last digit depends on the value n % 4. If x = n % 4, then the last digit 
 * of 3n is: -2x3 + 8x2 - 4x + 1.
 * 
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class LastDigit
{
	//declaring global variables for program
	public static final Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		//declaring local variables for program
		int userVal;
		
		//Prompting user for positive integer until they provide one
		do
		{
			
			try
			{
				System.out.printf("Please enter a positive, non-zero integer\n>> ");
				userVal = keyboard.nextInt();
			}
			catch (InputMismatchException e)
			{
				userVal = -1;
			}
			finally
			{
				keyboard.nextLine();
				System.out.printf("\n");
			}
			
			if (userVal <= 0)
			{
				System.out.printf("Invalid Response!\nPlease try again...\n");
			}
			
		}	while (userVal <= 0);
		
		//use the given formula
		int ratio = userVal % 4;
		
		int lastDigit = -2 * (int) Math.pow(ratio, 3) + 8 * (int) Math.pow(ratio, 2) 
				- 4 * ratio + 1;
		
		System.out.printf("The last digit of 3 to the power of %d is %d\n", userVal, lastDigit);
		
		
		
		
	}
}
