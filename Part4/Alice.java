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
    	String encryptedString = HMAC.HMAC_Calc(message);
    	System.out.println(encryptedString);
	    
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