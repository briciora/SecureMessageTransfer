import java.io.Console;  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bob
{
	public static void main(String[] args)
	{
		try
		{
			//reads message from ctext and stores it in a string
			String fileName = "ctext.txt";
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String message = br.readLine();
			
			System.out.println(message);
		
			//decryption
	    	String decryptedString = AES.decrypt(message);

	    	//print message to command line
	        System.out.println(decryptedString);
    	}
    	catch(IOException i)
    	{
    		i.printStackTrace(); 
    	}
    }

}