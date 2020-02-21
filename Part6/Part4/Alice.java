import java.io.Console;  
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.math.BigInteger;

public class Alice  
{ 
	public static void main(String[] args) 
	{
		//takes in message string from the command line
		String message = args[0];
		
		//time 100 executions
		String encryptedString = "";
		double startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
    		encryptedString = HMAC.HMAC_Calc(message);
    	}
    	double endTime = System.nanoTime();
    	double average = ((endTime - startTime) / 100);
    	System.out.println(encryptedString);
    	System.out.println("The average HMAC generation time is " + average + " nanoseconds.");
    	
	    //clear mactext file
	    File file = new File("mactext.txt"); 
	    if (file.exists()) 
	    	{   
	    		//delete if exists    
	    		file.delete(); 
	    	}

		//writes the sting to the mactext.txt file
		try (FileWriter f = new FileWriter("mactext.txt", true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b);) 
		{ 
			p.println(message);
			p.println(encryptedString); 
		} 
		catch (IOException i) 
		{ 
			i.printStackTrace(); 
		} 
	}
} 