package ver1;

/**
 * This code extracts information from coursepage.txt and parses the course information.
 * The output is filtered content with contains only course information and stored in a file
 * courseinfo.txt
 * 
 * This is a work-in-progress. Future releases will feature a GUI to display the course information
 * and will allow the user to select which course(s) to display on the GUI.
 * 
 * Group Members: Chris Kerley, Roopdeep Samanta, Charley Hooper and Carlos Lau
 * Feb 21, 2013.
 * York University.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class courseReader {


	
	public void read() throws IOException
	{	
		PrintStream output = System.out;
		Scanner fileInput = new Scanner(new File("coursepage.txt"));
		boolean termFlag=false;
		boolean tagFlag=false;
		
		File fileOutput = new File("courseinfo.txt");
		PrintWriter out = new PrintWriter(new FileOutputStream(fileOutput));
		
		List<String> courseTable = new ArrayList<String>();
		
		StringBuffer sb = new StringBuffer();
		
		
		for ( ; fileInput.hasNextLine(); )
		{
			String temp = fileInput.nextLine();

			
			if ( temp.contains("Term Y") || temp.contains("Term W") || temp.contains("Term W"))
			{
				termFlag=true;
			}
			
			if (termFlag)
			{

				for (int i = 0; i < temp.length() ; i++)
				{
					char c = temp.charAt(i);
					
					if (c=='<')
					{
						tagFlag = true;
						courseTable.add(sb.toString().trim());
						output.println (sb.toString().trim());
						sb.delete(0,  sb.length());
					}
					
					if (c=='>')
					{
						tagFlag = false;

					}
					
					if (!tagFlag && c!='>')
					{
						sb.append(c);
					}
					
				}// end for char iterator
			} // end if
		
		}// end for line iterator
		
		for (String iterator : courseTable)
		{
			output.println (iterator);
			out.println (iterator);
		}
		
		out.close();
		fileInput.close();
		

		
		
	}// end of method read

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		courseReader cr = new courseReader();
		try
		{
			cr.read();
		}
		catch (IOException e)
		{
			System.out.println ("Fatal error");
		}
		
	}// end of main
	
}
