//package project1;

/*
 * grivera64
 * Project 1
 * Program: CalculatePi.java
 * 4 February 2021
 * 
 * Purpose: Calculuates the value of pi using the infinite series
 * ----------------------------------------------------------------------------------
 * Calculate the value of pi from the infinite series:
 * pi = 4 -4/3 + 4/5 -4/7 + 4/9 -4/11 + ...
 * Calculate by using the first 200000 terms and printing the result for each 2000 
 * The number in a table. Print correct to 8 decimal places
 */

public class CalculatePi
{
	
	
	
	public static void main(String[] args)
	{
		//print the summation of the first 200,000 digits in pi's infinite summation
		CalculatePi.print(200000);
		
	}
	
	public static void print(int terms)
	{
		
		//declare local variables
		int term;
		double infiniteSum = 0;
		int termDenominator = 3;
		
		//loop through all the terms of the infinite summation
		for (term = 1; term <= terms; term++)
		{
			//start the first term with 4
			if (term == 1)
			{
				infiniteSum += 4.0;
				continue;
			}
			//if even, then it's subtraction
			else if (term % 2 == 0)
			{
				infiniteSum -= 4.0 / termDenominator;
			}
			//else addition
			else
			{
				infiniteSum += 4.0 / termDenominator;
			}
			
			//the denominator increases per term
			termDenominator += 2;
			
			//print after every 2,000 terms
			if (term % (2000) == 0)
			{
				CalculatePi.printMsg(term, infiniteSum);
			}
			
		}
	}
	
	public static void printMsg(int term, double infiniteSum)
	{
		System.out.printf("The value of pi after summing %,7d terms is %.8f\n", 
				term, infiniteSum);
	}
	
}
