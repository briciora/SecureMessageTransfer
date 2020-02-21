import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.Mac;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.*;
import java.lang.String;

public class HMAC
{

    private static String bytesToHex(byte[] hashInBytes) 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) 
        {
            sb.append(Integer.toString((hashInBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
	public static void generateKey()
	{
		try
        {
            //generate key
            String algorithm = "HMACSHA256";
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            SecretKey skey = kgen.generateKey();

            //save the key in a file 
            String keyFile = "key";
            FileOutputStream out = new FileOutputStream(keyFile);
            byte[] key = skey.getEncoded();
            out.write(key);
            out.close();
        }
        catch(Exception except)
        {
            except.printStackTrace(); 
        }
	}

    public static String HMAC_Calc(String message)
    {
		try
        {
            //Generate Key
            //HMAC.generateKey();

            // decode encoded string
            Path fileLocation = Paths.get("key"); 
            byte[] key = Files.readAllBytes(fileLocation);

            // rebuild key using SecretKeySpec
            SecretKey sk = new SecretKeySpec(key, "HMACSHA256");

            Mac mac = Mac.getInstance("HMACSHA256");
            mac.init(sk);
            return bytesToHex(mac.doFinal(message.getBytes()));
            
        }

        catch(Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }

        return null;
    }

    public static String Verify(String message, String code)
    {
        try
        {
            // decode encoded string
            Path fileLocation = Paths.get("key"); 
            byte[] key = Files.readAllBytes(fileLocation);

            // rebuild key using SecretKeySpec
            SecretKey sk = new SecretKeySpec(key, "HMACSHA256");

            Mac mac = Mac.getInstance("HMACSHA256");
            mac.init(sk);

            String hmac = bytesToHex(mac.doFinal(message.getBytes()));

            if(hmac.equals(code))
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