import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.io.Console;  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

public class AES
{
    //only has to be done once, or if the key needs to be changed
    public static void generateSecretKey()
    {
        try 
        {
            //generate secret key
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            SecretKey skey = kg.generateKey();

            //save key to file
            String keyFile = "key";
            FileOutputStream out = new FileOutputStream(keyFile);
            byte[] key = skey.getEncoded();
            out.write(key);
            out.close();
        }
        catch(Exception e)
        {
            System.out.println("Error while generating key");        
        }   
    }

    //only has to be done once, or if the key needs to be changed
    public static void generateIV()
    {
        try
        {
            //generate random initialization vector 
            SecureRandom srandom = new SecureRandom();
            byte[] iv = new byte[16]; //128 bits, 16 bytes
            srandom.nextBytes(iv);
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            //save iv to file
            String ivfile = "iv";
            FileOutputStream out = new FileOutputStream(ivfile);
            out.write(iv);
            out.close();
        }
        catch(Exception e)
        {
            System.out.println("Error while generating iv"); 
        }
    }

    public static String encrypt(String message)
    {
		try
        {
            //decoce iv from saved file
            Path fileLocation1 = Paths.get("iv"); 
            byte[] ivDecoded = Files.readAllBytes(fileLocation1);
            IvParameterSpec ivSpecDecoded = new IvParameterSpec(ivDecoded);

            //decode key from saved file
            Path fileLocation = Paths.get("key"); 
            byte[] key = Files.readAllBytes(fileLocation);

            //rebuild key using SecretKeySpec on byte array
            SecretKey sk = new SecretKeySpec(key, 0, key.length, "AES"); 

            //create cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sk, ivSpecDecoded); 

            byte[] encrypted = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }
        catch(Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String message)
    {
        try
        {
            //decoce iv from saved file
            Path fileLocation1 = Paths.get("iv"); 
            byte[] ivDecoded = Files.readAllBytes(fileLocation1);
            IvParameterSpec ivSpecDecoded = new IvParameterSpec(ivDecoded);

            //decode key from saved file
            Path fileLocation2 = Paths.get("key"); 
            byte[] key = Files.readAllBytes(fileLocation2);
            
            //rebuild key using SecretKeySpec on byte array
            SecretKey sk = new SecretKeySpec(key, 0, key.length, "AES"); 

            //create cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sk, ivSpecDecoded); 

            return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
        }

        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }    
}