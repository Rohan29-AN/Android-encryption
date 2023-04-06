package com.example.toasty;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

    private String _key,_iv;

    public AESEncryption(String _key,String _iv){
        this._key=_key;
        this._iv=_iv;
    }

    /***
     * @param value data to encrypted
     * @return value crypted with the AES Algorithm**/

    public byte[] EncryptAES(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] _keyBytes=this._key.getBytes();
        byte[] _ivBytes=this._iv.getBytes();
        SecretKeySpec secretKey=new SecretKeySpec(_keyBytes,"AES");
        IvParameterSpec ivParameterSpec=new IvParameterSpec(_ivBytes);
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivParameterSpec);
        return cipher.doFinal(value.getBytes());
       // return android.util.Base64.encodeToString(cipher.doFinal(value.getBytes()), android.util.Base64.DEFAULT);
    }

    /***
     * @param data data to decrypted
     * @return value decrypted with the AES Algorithm**/
    public byte[] DecryptAES(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] _ivBytes=this._iv.getBytes();
        byte[] _keyBytes=this._key.getBytes();
        byte[] _dataBytes=data.getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(_ivBytes);
        // Hash key.
        SecretKeySpec secretKeySpec = new SecretKeySpec(_keyBytes, "AES");
        // Decrypt
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/NoPadding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipherDecrypt.doFinal(_dataBytes);
    }


}
