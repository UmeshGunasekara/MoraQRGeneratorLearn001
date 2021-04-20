/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmora.learn.app.qrgenerate.test.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.sql.DataSource;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author A.U.D.M.Gunasekara
 */
public class MoraProtect {
    
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    private byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    private SecretKey key;

    public MoraProtect() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        myEncryptionKey = "MORAMORAMORAMORAMORAMORA";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    private void setEncriptConfiguration(String myKey) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        int keySize=myKey.length();
        if(24>keySize){
            String mora="MORA";
            while(24>keySize){
                myKey+=mora;
                keySize=myKey.length();
            }
        }
        myEncryptionKey = myKey;
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    private String encrypt(String unencryptedString) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
        String encryptedString = null;
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = new String(Base64.encodeBase64(encryptedText));
        return encryptedString;
    }

    private String[] getKeys(String incKey){
        boolean checkCode=incKey.contains("$");
        if(checkCode){
            int cutter=incKey.indexOf("$");
            String supportKey=incKey.substring(0, cutter);
            String enKey=incKey.substring(cutter+1);
            String out[]={supportKey,enKey};
            return out;
        }else{
            return null;
        }
    }

    public boolean checkUser(String incKey,String inputPassword, String tablePassword) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException{
        boolean ok=false;
        if(tablePassword.equals(getEncriptedPassword(inputPassword, incKey))){
            ok=true;
        }
        return ok;
    }

    public String passwordEncript(String password,String incKey) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException{
        String encriptedPassword=null;
        encriptedPassword=getEncriptedPassword(password, incKey);
        return encriptedPassword;
    }

    private String getEncriptedPassword(String password,String incKey) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException{
        String encriptedPassword=null;
//        String sql="SELECT item_name FROM mi_mora_item WHERE idf="+userCat+";";
//        String incKey=jdbcTemplate.queryForObject(sql, String.class);
        String keys[]=getKeys(incKey);
        if(keys!=null){
            setEncriptConfiguration(keys[1]);
            String tempPass=encrypt(password);
            tempPass+=keys[0];
            encriptedPassword=encrypt(tempPass);
        }else{
            return null;
        }
        return encriptedPassword;
    }

}
