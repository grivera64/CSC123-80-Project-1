//package project1;

/*
 * grivera64
 * Project 1
 * Program: CaesarCipherFromFile.java
 * 20 February 2021
 * 
 * Purpose: Encrypts/Decrypts the input file using the Caeasar Cipher algorithm
 * ------------------------------------------------------------------------
 * Rewrite the CaesarCipher program to accept input from a file (i.e. caesar_input.txt found in
 * Project Solutions/Project 1 tab) and write the output to a file.
 * Read in the shift for coding only once from the terminal.
 * Use nextLine() to read in a line at a time.
 * You can use caesar_input.txt and see the result with a shift of 3 in caesar_output.txt. 
 * Both files are posted in Project Solutions/Project 1 tab.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class CaesarCipherFromFile
{
	
	public static void main(String[] args) throws IOException
	{
		
		//local variables
		String input, output;
		File in = null, out = null;
		Scanner keyboard, inFile = null;
		PrintWriter outFile;
		ArrayList<char[]> lines = new ArrayList<char[]>();
		int change = -1;
		keyboard = new Scanner(System.in);
		
		
		//request for an input file to read
		do
		{
			if (in != null)
			{
				System.out.printf("File does not exist!\nTry again...\n\n");
			}
			System.out.printf("Please enter a file to Caesar Cipher:\n>> ");
			input = keyboard.nextLine();
			in = new File(input);
			if (in.exists())
			{
				inFile = new Scanner(in);
			}
			else
			{
				inFile = null;
			}
			
		} while (inFile == null);
		
		
		//request for an output file to write
		do
		{
			if (out != null)
			{
				System.out.printf("File does not exist!\nTry again...\n\n");
			}
			System.out.printf("Please enter a file to output to:\n>> ");
			output = keyboard.nextLine();
			out = new File(output);
			if (out.exists())
			{
				outFile = new PrintWriter(out);
			}
			else
			{
				outFile = null;
			}
			
		} while (outFile == null);
		
		//request for change factor
		do
		{
			System.out.printf("Please enter the shift:\n>> ");
			try
			{
				change = keyboard.nextInt();
			}
			catch (InputMismatchException e)
			{
				change = -1;
			}
			finally
			{
				keyboard.nextLine();
				if (change <= 0)
				{
					System.out.printf("The shift must be greater than 0.\n"
							+ "Please try again...\n\n");
				}
			}
			
		} while (change <= 0);
		
		if (change >= 26)
		{
			change %= 26;
			change++;
		}
		
		//read file
		while (inFile.hasNext())
		{
			lines.add(inFile.nextLine().toCharArray());
		}
		
		
		//change letter data only
		for (int index = 0; index < lines.size(); index++)
		{
			for (int character = 0; character < lines.get(index).length; character++)
			{
				
				//System.out.println("DEBUG: " + lines.get(index)[character] + " vs " + 
				//(char) (lines.get(index)[character] + change));
				
				if (!Character.isLetter(lines.get(index)[character]))
				{
					//System.out.println("DEBUG: Skipped");
					lines.get(index)[character] = (char) 0;
					continue;
				}
				else if (Character.isLetter(lines.get(index)[character]) 
						&& Character.isLetter(lines.get(index)[character] + change)
						&& 
						//some weird bit-wise XOR operator magic
						!(Character.isLowerCase(lines.get(index)[character]) ^ 
								Character.isLowerCase(lines.get(index)[character] + change)
						))
				{
					lines.get(index)[character] += change;
					//System.out.println("DEBUG: Normal letter set");
				}
				else if (lines.get(index)[character] + change >= 'Z' 
						&& lines.get(index)[character] < 'z')
				{
					lines.get(index)[character] = (char) 
							(65 + 
								(change - 
										((byte) 'Z' - 
												(byte) (lines.get(index)[character]))
										- 1)
							);
					
					//System.out.println("DEBUG: Changed to capital letter");
				}
				else if (lines.get(index)[character] + change >= 'z')
				{
					lines.get(index)[character] = (char) 
							(97 + 
								(change - 
										((byte) 'z' - 
												(byte) (lines.get(index)[character]))
										- 1)
							);
					
					//System.out.println("DEBUG: Changed to lowercase letter");
				}
				
			}
		}
		
		//place into output file
		for (int index = 0; index < lines.size(); index++)
		{
			for (int index2 = 0; index2 < lines.get(index).length; index2++)
			{
				if (lines.get(index)[index2] != 0)
				{
					outFile.printf("%c", lines.get(index)[index2]);
				}
			}
			if (index < lines.size() - 1)
			{
				outFile.println();
			}
		}
		
		//terminate program
		System.out.printf("\n\nProgram Terminated!\n\n");
		
		//close all resource leaks
		inFile.close();
		outFile.close();
		keyboard.close();
		
	}
	
}
