/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author MinhDuc
 */
public class Utils {
    public static String encryptBySHA256(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (password != null && (!password.trim().equals(""))) {
            //Encryted password by using SHA-256
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            //get hash value 
            byte[] hashedPassword = msgDigest.digest(password.getBytes("UTF-8"));
            String valueHashed = DatatypeConverter.printHexBinary(hashedPassword);
            return valueHashed;
        }
        return null;
    }
}
