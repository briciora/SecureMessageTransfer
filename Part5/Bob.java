import java.io.Console;  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bob
{
	public static void main(String[] args)
	{
		try
		{
			//reads message from sigtext and stores it in a string
			String fileName = "sigtext.txt";
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
            String readLine;
            ArrayList<String> message_signature = new ArrayList<String>();
            int i = 0; 
            while ((readLine = br.readLine()) != null) 
            {
                message_signature.add(readLine);
                i++;
            }

            String message = message_signature.get(0);
            String signature = message_signature.get(1);

			//decryption
	    	String verification = RSA.verifySignature(message, signature);

	    	//print message to command line
	        System.out.println(verification);
    	}
    	catch(IOException i)
    	{
    		i.printStackTrace(); 
    	}
    }

}