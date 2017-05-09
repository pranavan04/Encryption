/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sss;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Piranavan
 */
public class SSS {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Key aesKey=null;
        Cipher cipher=null;
        byte[] encrypted = null;
        String con = "y";
        while(con.equals("y"))
        {
        String everything="";
        Scanner sc = new Scanner(System.in);
        System.out.println("Encrypt - E");
        System.out.println("Decrypt - D");
        String opt = sc.nextLine();
        
        try
        {
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            aesKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher = Cipher.getInstance("AES");
        }
        catch (Exception ex) {
        }
        
        if(opt.equals("E"))
        {
            System.out.println("Insert file parth to encrypt : ");
            String inputfile = sc.nextLine();
            System.out.println("Enter destination parth : ");
            String dp = sc.nextLine();
            System.out.println("Enter Password : ");
            String pw = sc.nextLine();
            BufferedReader br = new BufferedReader(new FileReader(inputfile));
            
            
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                everything = sb.toString();
                everything=pw+" "+everything;
                //System.out.println(everything);
            }       
            catch (IOException ex) {
                Logger.getLogger(SSS.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
                // encrypt the text
                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                encrypted = cipher.doFinal(everything.getBytes());
                //System.out.println(new String(encrypted));
                PrintWriter writer = new PrintWriter(dp, "UTF-8");
                writer.println(new String(encrypted));
                writer.close();
                System.out.println("Encrypted sucessfully!");
            }
            catch(Exception e) 
            {
            }
        }
        else if(opt.equals("D"))
        {
            System.out.println("Insert file parth to decrypt : ");
            String inputfile = sc.nextLine();
            System.out.println("Enter destination parth : ");
            String dp = sc.nextLine();
            System.out.println("Enter Password : ");
            String pw = sc.nextLine();
            
            BufferedReader br = new BufferedReader(new FileReader(inputfile));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                everything = sb.toString();
                //System.out.println(everything);
            }       
            catch (IOException ex) {
                Logger.getLogger(SSS.class.getName()).log(Level.SEVERE, null, ex);
            }
            String decrypted="";
            try{
                // decrypt the text
                cipher.init(Cipher.DECRYPT_MODE, aesKey);
                decrypted = new String(cipher.doFinal(encrypted));
                //System.out.println(decrypted);
            }
            catch(Exception e) 
            {
                System.out.println(e);
            }
            String pws[] = decrypted.split(" ");
            String pswd = pws[0];
            decrypted="";
            for(int x=1;x<pws.length;x++)
            {
                decrypted=decrypted+pws[x];
            }
            
            if(pswd.equals(pw))
            {
            PrintWriter writer = new PrintWriter(dp, "UTF-8");
            writer.println(decrypted);
            writer.close();
             System.out.println("Decrypted sucessfully!");
            }
            else
            {
                System.out.println("Password Incorect ");
            }
        }
        
        System.out.println("Do you want to continue (y/n) : ");
        con = sc.nextLine();
        }
        

           
    
    }
    
}
