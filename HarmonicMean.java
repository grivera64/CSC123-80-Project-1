package project1;

/*
 * grivera64
 * Project 1
 * Program: HarmonicMean.java
 * 4 February 2021
 * 
 * Purpose: Finds the means between 2 integers
 * ---------------------------------------------------------------------
 * Prompt:
 * If it takes one hose 12 hours to fill a pool, and another hose 4 hours, 
 * then together they fill the pool in (2 * 4 * 12) / (4 +12) = 6 hours. 
 * The harmonic mean of two positive numbers a and b is 2ab/(a + b).
 * 
 * Write a method double harmonicMean(int x, int y) (5)
 * that returns the harmonic mean of a > 0 and b > 0.
 * 
 * Write another method double arithmeticMean(int x, int y), (5)
 * that returns the average of a and b.
 * 
 * Finally, include a third method double geometricMean(int x, int y) (5)
 * that returns the geometric mean of a and b, that is, the square root of a * b.
 * 
 * Test your methods in a main program (10) that reads two positive integers and displays their
 * harmonic mean, arithmetic mean, and geometric mean. For example, if a and b have values 12
 * and 4, the harmonic mean is 6.0, the arithmetic mean is 8.0, and the geometric mean is 
 * sqrt(48) = 6.928.
 * 
 * Did you notice that the harmonic mean times the arithmetic mean equals the square of the
 * geometric mean? This identity might be helpful to you when you design your methods.
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class HarmonicMean
{
	//create command line reader
	public static final Scanner keyboard = new Scanner(System.in);
	
	//test the 3 methods
	public static void main(String[] args)
	{
		//create local variables
		int userInt1 = -1, userInt2 = -1;
		double harmonic, arithmetic, geometric;
		
		//read two integer values
		while (userInt1 <= 0)
		{
			try
			{
				System.out.printf("Enter another integer greater than 0\na = ");
				userInt1 = keyboard.nextInt();
				
				if (userInt1 <= 0)
				{
					throw new InputMismatchException();
				}
				
			}
			catch (InputMismatchException e)
			{
				userInt1 = -1;
				System.out.printf("Invalid input!\nPlease try again...\n");
			}
			finally
			{
				keyboard.nextLine();
				System.out.println();
			}
		}
		
		while (userInt2 <= 0)
		{
			try
			{
				System.out.printf("Enter an integer greater than 0\nb = ");
				userInt2 = keyboard.nextInt();
				
				if (userInt2 <= 0)
				{
					throw new InputMismatchException();
				}
				
			}
			catch (InputMismatchException e)
			{
				userInt2 = -1;
				System.out.printf("Invalid input!\nPlease try again...\n");
			}
			finally
			{
				keyboard.nextLine();
				System.out.println();
			}
		}
		
		
		
		//tests
		harmonic = harmonicMean(userInt1, userInt2);
		arithmetic = arithmeticMean(userInt1, userInt2);
		geometric = geometricMean(userInt1, userInt2);
		
		System.out.printf("The means of a = %d and b = %d are:\n"
				+ "%-12s%.7f\n%-12s%.7f\n%-12s%.7f\n", userInt1, userInt2,
				"Harmonic:", harmonic, "Arithmetic:", arithmetic, "Geometic:", geometric);
		
		
	}
	
	//returns the harmonic mean by the formula
	public static double harmonicMean(int x, int y)
	{
		return (2.0 * x * y) / (x + y);
	}
	
	//returns the arithmetic mean by taking the sum divided by the terms
	public static double arithmeticMean(int x, int y)
	{
		return (x + y) / 2;
	}
	
	//returns the geometric mean by the formula
	public static double geometricMean(int x, int y)
	{
		return Math.sqrt(x * y);
	}
	
	
}