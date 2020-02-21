import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.io.Console;  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class RSA
{

	public static void generatePublicKey()
	{
		//create random number
		SecureRandom range = new SecureRandom();

		//make p and q
		BigInteger p, q;

		//make primes
		p = BigInteger.probablePrime(1024, range);
        q = BigInteger.probablePrime(1024, range);

        //p*q
        BigInteger n = p.multiply(q);

        //euler's
        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);
        BigInteger euler = pMinus1.multiply(qMinus1);

        //find relatively prime number (e) to eulers
        boolean relativelyPrime = false;
        BigInteger e = BigInteger.probablePrime(512, range);
        while(!relativelyPrime)
        {
            e = BigInteger.probablePrime(512, new SecureRandom());
            if((euler.gcd(e)).equals(BigInteger.ONE))
            {
                relativelyPrime = true;
            }
        }

        //d*e = 1 mod phi(n)
        BigInteger d = e.modInverse(euler);

        //public key = (e, n)
        //private key = (d, n)
        //write this key to a file
        try
        {
            File file = new File("key");
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(e.toString());
            writer.write("\n");
            writer.write(d.toString());
            writer.write("\n");
            writer.write(n.toString());
            writer.write("\n");
            writer.flush();
            writer.close();
        }
        catch(Exception except)
        {
            except.printStackTrace(); 
        }
	}

    public static BigInteger createSignature(String message)
    {
		try
        {
            //generates key
            //RSA.generatePublicKey();

            //System.out.println(message);

            //reads key from file
            String fileName = "key";
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String readLine;
            ArrayList<String> keys = new ArrayList<String>();
            int i = 0; 
            while ((readLine = br.readLine()) != null) 
            {
                keys.add(readLine);
                i++;
            }

            String e = keys.get(0);
            //System.out.println(e);
            String d = keys.get(1);
            //System.out.println(d);
            String n = keys.get(2);
            //System.out.println(n);

            //creates big ints
            BigInteger dd = new BigInteger(d);
            BigInteger nn = new BigInteger(n);

            //string to big int 
            byte[] messageBytes = message.getBytes();
            BigInteger m = new BigInteger(messageBytes);

            //encryption
            BigInteger s;
            s = m.modPow(dd, nn);

            return s;
        }

        catch(Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }

        return null;
    }

    public static String verifySignature(String message, String signature)
    {
        try
        {
            //reads key from file
            String fileName = "key";
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String readLine;
            ArrayList<String> keys = new ArrayList<String>();
            int i = 0; 
            while ((readLine = br.readLine()) != null) 
            {
                keys.add(readLine);
                i++;
            }

            String e = keys.get(0);
            //System.out.println(e);
            String d = keys.get(1);
            //System.out.println(d);
            String n = keys.get(2);
            //System.out.println(n);

            //creates big int
            BigInteger ee = new BigInteger(e);
            BigInteger nn = new BigInteger(n);
            BigInteger s = new BigInteger(signature);
            //System.out.println(s);
  
            //string to big int 
            byte[] messageBytes = message.getBytes();
            BigInteger mess = new BigInteger(messageBytes);
            //System.out.println(mess);

            //verify
            BigInteger m;
            m = s.modPow(ee, nn);
            //System.out.println(m);

            if(m.compareTo(mess) == 0)
            {
                return "The verification succeeded!";
            }
            else
            {
                return "The verification failed.";
            }

        }

        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
     
        
}