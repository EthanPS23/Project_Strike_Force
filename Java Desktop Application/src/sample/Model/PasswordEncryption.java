// Author: Chris Potvin
// Date: May 1st, 2019
// About: This is the password encryption class that is using MD5 to hash the password form the login pane on the controller class.
// this class is called in the login method.

package sample.Model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {

    public static String MD5(String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        }
    }
