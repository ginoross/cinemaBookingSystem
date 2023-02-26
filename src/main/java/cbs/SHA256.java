package cbs;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    //hashing method that takes a string as input and cryptographically hashes it
    public static byte[] getSHABytes(String input) throws NoSuchAlgorithmException{

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));

    }

    public static String toHexString(byte[] hash){
        BigInteger num = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(num.toString(16));

        while(hexString.length()<64){
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }


}
