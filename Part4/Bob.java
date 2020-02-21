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
			//reads message from mactext and stores it in a string
			String fileName = "mactext.txt";
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
            String readLine;
            ArrayList<String> message_HMAC = new ArrayList<String>();
            int i = 0; 
            while ((readLine = br.readLine()) != null) 
            {
                message_HMAC.add(readLine);
                i++;
            }

            String message = message_HMAC.get(0);
            String code = message_HMAC.get(1);

			//decryption
	    	String decryptedString = HMAC.Verify(message, code);

	    	//print message to command line
	        System.out.println(decryptedString);
    	}
    	catch(IOException i)
    	{
    		i.printStackTrace(); 
    	}
    }

}