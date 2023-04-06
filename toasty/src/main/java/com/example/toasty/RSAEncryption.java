package com.example.toasty;

import android.util.Base64;

import androidx.annotation.Nullable;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryption {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    /**
     * Initializes RSA public or private keys for encryption or decryption.
     *
     * @param isEncryption true if the key is for encryption, false if it is for decryption
     * @param _key the key string (public key if isEncryption is true, private key if isEncryption is false)
     */
    public void initializeRSAKeys(Boolean isEncryption, String _key){
        try{
            if(isEncryption){
                String pubKey = _key;
                String regex="-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?";
                pubKey = pubKey.replaceAll(regex, "");
                X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(pubKey));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                publicKey = keyFactory.generatePublic(keySpecPublic);
            }
            else{
                String privKey = _key;
                String regex="-+BEGIN RSA PRIVATE KEY-+\\r?\\n|-+END RSA PRIVATE KEY-+\\r?\\n?";
                privKey = privKey.replaceAll(regex, "");
                PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(privKey));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                privateKey = keyFactory.generatePrivate(keySpecPrivate);
            }

        }catch (Exception e){
            System.err.println("Error initializing RSA keys: " + e.getMessage());
        }
    }



    /**
     * Encrypts the given message using the RSA encryption algorithm.
     *
     * @param message the message to encrypt
     * @return the encrypted message as a string
     * @throws Exception if there is an error during encryption
     */
    public String encryptData(String message) throws Exception {
        try{
            byte[] messageToBytes = message.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(messageToBytes);
            return encode(encryptedBytes);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Error during encryption: " + e.getMessage(), e);
        }
    }

    /**
     * Encrypts the given message using the RSA decryption algorithm.
     *
     * @param encryptedMessage the message to decrypt
     * @return the decrypted message as a string
     * @throws Exception if there is an error during encryption
     */
    public String decryptData(String encryptedMessage) throws Exception {
        try {
            byte[] encryptedBytes = decode(encryptedMessage);
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, "UTF8");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Error during decryption: " + e.getMessage(), e);
        }

    }

    private static String encode(byte[] data) {
        return Base64.encodeToString(data,Base64.DEFAULT);
    }
    private static byte[] decode(String data) {
        return Base64.decode(data,Base64.DEFAULT);
    }

}
