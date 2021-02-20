//package project1;

/*
 * grivera64
 * Project 1
 * Program: ArrayOperations2D.java
 * 4 February 2021
 * 
 * Purpose: Creates an array of user's wishes and fills it up randomly and provides details
 * 			about each row
 * ------------------------------------------------------------------------------
 * Write a main program (5) that reads in the dimensions of a two-dimensional array and 
 * creates a two-dimensional array with those rows and columns, and then 
 * call the methods below in the order listed to produce the output described below.
 * The additional method are:
 * 
 * - fillRandom. (5) Accepts a reference to a two-dimensional int array and fills it with 
 * random integers from 0 to 99. Has a void return. (you can use Math.random() or 
 * a Random class object here.
 * 
 * - formatPrint. (5) This method should accept a reference to two-dimensional array and
 * print it out row by row. Has a void return.
 * 
 * - getTotal. (5) This method should accept a reference to two-dimensional array as its 
 * argument and return the total of all the values in the array as an int.
 * 
 * - getAverage. (5) This method should accept a two-dimensional array as its argument and
 * return the average of all the values in the array as a double. It calls calls getTotal and
 * getElementCount in performing this task.
 * 
 * - getRowTotal. (5) This method should accept a two-dimensional array as its first argument
 * and an integer as its second argument. The second argument should be the subscript of a row
 * in the array. The method should return the total of the values in the specified row as 
 * an int.
 * 
 * - getColumnTotal. (5) This method should accept a two-dimensional array as its first
 * argument and an integer as its second argument. The second argument should be the
 * subscript of a column in the array. The method should return the total of the values in the
 * specified column. as an int
 * 
 * - getHighestInRow. (5) This method should accept a two-dimensional array as its first
 * argument and an integer as its second argument. The second argument should be the
 * subscript of a row in the array. The method should return the highest value in the 
 * specified row of the array as an int.
 * 
 * - getLowestInRow. (5) This method should accept a two-dimensional array as its first
 * argument and an integer as its second argument. The second argument should be the sub-
 * script of a row in the array. The method should return the lowest value in the specified
 * row of the array.
 * 
 * - getElementCount. (5) This method should accept a two-dimensional array and returns the
 * total number of elements in the array as an int.
 * 
 * Demonstrate each of the methods in this program. Each (except for getElementCount, 
 * are called from main.
 * 
 * The main program will request the number of rows and columns as input, creates the two-
 * dimensional array, and first calls fillRandom.
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class ArrayOperations2D
{
	
	public static void main(String[] args)
	{
		
		//declaring local variables
		Scanner keyboard = new Scanner(System.in);
		int rows, columns,
		row, column;
		
		//requesting user for a valid row number (int)
		do
		{
			System.out.printf("Please enter the number of rows:\n%s", ">> ");
			
			try
			{
				rows = keyboard.nextInt();
			}
			catch (InputMismatchException e)
			{
				rows = -1;
				System.out.printf("Invalid input! Please try again...\n\n");
			}
			finally
			{
				keyboard.nextLine();
			}
			
		} while (rows <= 0);
		
		
		//requesting user for a valid column number (int)
		do
		{
			System.out.printf("Please enter the number of columns:\n%s", ">> ");
			
			try
			{
				columns = keyboard.nextInt();
			}
			catch (InputMismatchException e)
			{
				columns = -1;
				System.out.printf("Invalid input! Please try again...\n\n");
			}
			finally
			{
				keyboard.nextLine();
			}
			
		} while (columns <= 0);
		
		int[][] newArray = new int[rows][columns];
		
		System.out.printf("\n");
		
		//hard-coded rows and columns to check
		row = 0; 
		column = 0;
		
		//fill array with random numbers from 0-99 inclusive
		fillRandom(newArray);
		//print out full array
		formatPrint(newArray);
		System.out.printf("\n");
		
		//each is self explanatory in its output (view individual methods for details)
		System.out.printf("The total of the array is %d\n", getTotal(newArray));
		System.out.printf("The average of the array is %.2f\n" , getAverage(newArray));
		
		System.out.printf("-----\n");
		
		while (row < rows || column < columns)
		{
			if (row < rows)
			{
				System.out.printf("Row %d's total is %d\n", row + 1, 
						getRowTotal(newArray, row));
			}
			
			if (column < columns)
			{
				System.out.printf("Column %d's total is %d\n", column + 1, 
						getColumnTotal(newArray, column));
			}
			row++;
			column++;
			System.out.printf("-----\n");
		}
		
		System.out.printf("\n");
		
		for (int possRows = 0; possRows < rows; possRows++)
		{
			System.out.printf("The highest value in row %d is %d\n", possRows + 1, 
					getHighestInRow(newArray, possRows));
			System.out.printf("The lowest value in row %d is %d\n", possRows + 1, 
					getLowestInRow(newArray, possRows));
			System.out.printf("-----\n");
		}
		
		//terminate program
		System.out.printf("\nProgram Terminated!\n\n");
		
		//stop leakage of Scanner instance
		keyboard.close();
		
	}

	//fills the entire array with numbers from 0-99 inclusive
	public static void fillRandom(int[][] array)
	{
		//create instance of random object
		Random random = new Random();
		
		//checks in the array is invalid at start of program
		if (array.length <= 0)
		{
			System.out.printf("Error: The array is empty\n");
			System.exit(0);
		}
		
		//go through each element and assign a value from 0-99 (inclusive)
		for (int row = 0; row < array.length; row++)
		{
			for (int column = 0; column < array[row].length; column++)
			{
				array[row][column] = (int) random.nextInt(100);
			}
		}
			
	}
	
	//prints out the array in a formatted way
	public static void formatPrint(int[][] array)
	{
		//for each element, print it out in its own row and column in a formatted way
		for (int[] row : array)
		{
			String hyphensNeeded = "";
			for (int column : row)
			{
				System.out.printf(" | %02d | ", column);
				hyphensNeeded = hyphensNeeded.concat("---------");
			}
			System.out.printf("\n%s\n", hyphensNeeded);
		}
	}
	
	//finds the sum of all of the elements
	public static int getTotal(int[][] array)
	{
		//set local variables
		int sum = 0;
		
		//get all the values in the array
		for (int[] row : array)
		{
			for (int column : row)
			{
				sum += column;
			}
		}
		
		//return the sum
		return sum;
		
	}
	
	//returns the average of the entire array's element sum
	public static double getAverage(int[][] array)
	{
		//sum / element#
		return getTotal(array) / (double) getElementCount(array);
	}
	
	//the total of the element values in the provided row
	public static int getRowTotal(int[][] array, int row)
	{
		//creating local variables
		int total = 0;
		
		//find the total of the row
		for (int column : array[row])
			total += column;
		
		//return found total
		return total;
	}
	
	//the total of the element values in the provided column
	public static int getColumnTotal(int[][] array, int column) 
	{
		//creating local variables
		int total = 0;
		
		//find the total of the column
		for (int row = 0; row < array.length; row++)
			total += array[row][column];
		
		//return found total
		return total;
	}
	
	//finds the highest value in the provided row
	public static int getHighestInRow(int[][] array, int row)
	{
		//create local variables
		int highest = array[row][0];
		
		//search for the value that has no other number higher than it
		for (int column = 1; column < array[row].length; column++)
			if (highest < array[row][column])
				highest = array[row][column];
		
		//return such value
		return highest;
		
	}
	
	//finds the lowest value in the provided row
	public static int getLowestInRow(int[][] array, int row)
	{
		//create local variables
		int lowest = array[row][0];
		
		//check if there is any value that is smaller than the current one
		for (int column = 1; column < array[row].length; column++)
			if (lowest > array[row][column])
				lowest = array[row][column];
		
		//return such value
		return lowest;
		
	}
	
	//finds the number of elements in the array (Size of array)
	public static int getElementCount(int[][] array)
	{
		//return the area of the array (capacity)
		return array.length * array[0].length;
	}
	
}
