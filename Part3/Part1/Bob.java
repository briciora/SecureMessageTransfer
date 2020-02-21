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
		
			//time 100 executions
			String decryptedString = "";
			double startTime = System.nanoTime();
			for(int i = 0; i < 100; i++)
			{
				decryptedString = AES.decrypt(message);
			}
			double endTime = System.nanoTime();
			double average = ((endTime - startTime) / 100);

			//print message to command line
		    System.out.println(decryptedString);
		    
	    	System.out.println("The average decryption time is " + average + " nanoseconds.");

	    }
	    catch(IOException ie)
	    {
	    	ie.printStackTrace(); 
	    }
    }

}