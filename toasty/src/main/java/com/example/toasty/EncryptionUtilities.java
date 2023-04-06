package com.example.toasty;

import android.util.Base64;

public class EncryptionUtilities {

    /**
     * @param isEncryption true if the key is for encryption, false if it is for decryption
     * @param value word to encrypt or decrypt
     * @param key the key string (public key if isEncryption is true, private key if isEncryption is false)
     * @return resultat This method returns a string if decryption or encryption goes well and null if a problem occurs
     */
    public static String EncryptOrDecryptRSA(Boolean isEncryption,String value,String key){
        String resultat="";
        try{
            RSAEncryption rsaEncryption=new RSAEncryption();
            rsaEncryption.initializeRSAKeys(isEncryption,key);
            if(isEncryption) {
                resultat=rsaEncryption.encryptData(value);
            }
            else{
                resultat=rsaEncryption.decryptData(value);
            }
            return resultat;
        }
        catch (Exception e){
            System.out.println("A problem has occurred"+e.getMessage());
            return null;
        }
    }


    public static String encode(byte[] data) {
        return Base64.encodeToString(data,Base64.DEFAULT);
    }
    public static byte[] decode(String data) {
        return Base64.decode(data,Base64.DEFAULT);
    }

}
