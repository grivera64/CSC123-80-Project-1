package project1;

/*
 * grivera64
 * Project 1
 * Program: SlotMachine.java
 * 4 February 2021
 * 
 * Purpose: Allows to check the probability of getting 3 same numbers in a row
 * -------------------------------------------------------------------------
 * In main: (15)
 * Ask for the number of times to run the game.
 * Read in a seed as type long.
 * For each time you run the game
 * Increment seed by 1
 * Call runGame with the incremented seed.
 * Based on the returned value keep count of the wins and losses.
 * Print out the number of wins as an integer and 
 * the percentage of wins to decimal places as below:
 * The number of games won is numWins and the percentage of wins is pctWins%.
 * 
 * In runGame: (10)
 * Use the Random class to do the following
 * Create a Random object.
 * Set the seed based on the input value.
 * Generate 3 integers between 1 and 5.
 * Return true if all three match (a win) or false otherwise.

 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class SlotMachine
{
	
	public static final Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		
		//local variables
		int numOfTimes,
		numWins = 0,
		numLosses = 0;
		long seedVal = (long) -1e10;
		boolean tryAgain = false;
		
		//request for the num of times to run the game
		do
		{
			System.out.printf("Please enter the number of times\nto run the game\n>> ");
			try
			{
				numOfTimes = keyboard.nextInt();
			}
			catch(InputMismatchException e)
			{
				numOfTimes = -1;
				System.out.printf("The input must be an integer that is\n"
						+ "greater than 0.\nPlease try again...\n\n");
			}
			
			keyboard.nextLine();
			
		} while (numOfTimes <= 0);
		
		//read seed value
		do
		{
			
			tryAgain = false;
			
			System.out.printf("Please enter a starting seed value:\n>> ");
			
			try
			{
				seedVal = keyboard.nextLong();
			}
			catch(InputMismatchException e)
			{
				tryAgain = true;
				System.out.printf("The seed must be a type long.\n"
						+ "Please try again...\n\n");
				
			}
			finally
			{
				keyboard.nextLine();
			}
			
		} while (tryAgain);
		
		//run game has many times as requested
		for (int repetitions = 0; repetitions < numOfTimes; repetitions++) 
		{
			boolean hasWon = runGame(++seedVal);
			if (hasWon)
			{
				numWins++;
			}
			else
			{
				numLosses++;
			}
		}
		
		//display results
		System.out.printf("Throughout %d games, you won %d times (%.2f %% success rate).\n", 
				numOfTimes, numWins, 
				((double) numWins / (double) numOfTimes) * 100f);
		
	}
	
	//checks whether the current game is a jackpot or not
	public static boolean runGame(long seed)
	{
		//creating local variables
		boolean state = true;
		Random random = new Random();
		random.setSeed(seed);
		int newNum = 0, oldNum = 0;
		
		//get 3 random numbers
		for (int i = 0; i < 3; i++)
		{
			newNum = random.nextInt(5) + 1;
			
			//check if there are a 3 in a row
			if (oldNum != newNum)
			{
				if (i == 0)
				{
					oldNum = newNum;
					continue;
				}
				else
				{
					state = false;
					break;
				}
			}
			
		}
		
		//return whether there was a win or not
		return state;
	}
	
}