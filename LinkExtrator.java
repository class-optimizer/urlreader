package ver1;

/**
 * Code extracts links to courses from the York University Main Site and puts it into 
 * a data.txt file. This piece of software was designed to test the behavior the York
 * website and extract the basic course information. 
 * 
 * Note that the courses webpage needs to be downloaded before hand. This software
 * accesses the links locally. Future releases will access the York Website directly
 * 
 * Group Members: Chris Kerley, Roopdeep Samanta, Charley Hooper and Carlos Lau
 * Feb 21, 2013.
 * York University
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;


public class LinkExtrator {
	
	
	
	public static void main (String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(System.in);
		PrintStream output = System.out;
		StringBuffer linkSB = new StringBuffer();
		Scanner fileInput = new Scanner(new File("York University Courses Website - Course View List.htm"));
		
		File fileOutput = new File("data.txt");
		PrintWriter out = new PrintWriter(new FileOutputStream(fileOutput));
		
		String temp;
		boolean capture=false;
		
		List<String> hrefLinks = new ArrayList<String>();
		List<String> courseLinks = new ArrayList<String>();
		
		for ( ; fileInput.hasNextLine(); )
		{
			temp = fileInput.nextLine();

			for (int i = 0 ; i<temp.length(); i++)
			{
				char c = temp.charAt(i);
				
				if (c=='<')
				{
					capture = true;
				}
				
				if (c=='>')
				{
					capture = false;
				
					if(linkSB.toString().contains("a href="))
					{
						hrefLinks.add(linkSB.toString());
					}
					linkSB.delete(0,linkSB.length());
				}
				
				if (capture==true && c!='<')
				{					
					linkSB.append(c);
				}
			}//end for iterator
			


			
		}//end for hasNextLine
		fileInput.close();
		input.close();
			
		//Clean up the hrefLinks array;
		String temp1;
		for (String iterator : hrefLinks)
		{
			temp1 = iterator;
			
			if(temp1.contains("https://w2"))
			{
				courseLinks.add(temp1.substring(8,temp1.length()-1));
			}
		}
		//review the course links
		for (String iterator : courseLinks)
		{
			output.println (iterator);
			out.println (iterator);
		}
		
		out.close();
		
	}//end main
	
}
