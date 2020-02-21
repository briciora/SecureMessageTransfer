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
		
		//encryption
    	BigInteger signature = RSA.createSignature(message);
    	System.out.println(signature);
	    
	    //clear sigtext file
	    File file = new File("sigtext.txt"); 
	    if (file.exists()) 
	    	{   
	    		//delete if exists    
	    		file.delete(); 
	    	}

		//writes the sting to the sigtext.txt file
		try (FileWriter f = new FileWriter("sigtext.txt", true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b);) 
		{ 
			p.println(message);
			p.println(signature); 
		} 
		catch (IOException i) 
		{ 
			i.printStackTrace(); 
		} 
	}
} 