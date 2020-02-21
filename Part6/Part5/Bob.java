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
            int count = 0; 
            while ((readLine = br.readLine()) != null) 
            {
                message_signature.add(readLine);
                count++;
            }

            String message = message_signature.get(0);
            String signature = message_signature.get(1);
			
            //time 100 executions
            String verification = "";
            double startTime = System.nanoTime();
            for(int i = 0; i < 100; i++)
            {
                verification = RSA.verifySignature(message, signature);
            }
            double endTime = System.nanoTime();
            double average = ((endTime - startTime) / 100);

            //print message to command line
            System.out.println(verification);
            
            System.out.println("The average verification time is " + average + " nanoseconds.");
    	}
    	catch(IOException i)
    	{
    		i.printStackTrace(); 
    	}
    }

}