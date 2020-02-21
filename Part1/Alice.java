import java.io.Console;  
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class Alice
{ 
	public static void main(String[] args) 
	{
		// //only done once and then removed once they have been saved to files
		// AES.generateSecretKey();
		// AES.generateIV();
		
		//takes in message string from the command line
		String message = args[0];
		
		//encryption
    	String encryptedString = AES.encrypt(message);
    	System.out.println(encryptedString);
	    
	    //clear ctext file
	    File file = new File("ctext.txt"); 
	    if (file.exists()) 
	    	{   
	    		//delete if exists    
	    		file.delete(); 
	    	}

		//writes the sting to the ctext.txt file
		try (FileWriter f = new FileWriter("ctext.txt", true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b);) 
		{ 
			p.println(encryptedString); 
		} 
		catch (IOException i) 
		{ 
			i.printStackTrace(); 
		} 
	}
} 