package com.slmora.learn.app.qrgenerate.test.test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by A.U.D.M.Gunasekara on 2016-07-22.
 */
public class MoraCreateQR {
    public static void main(String[] args) {
        MoraCreateQR moraCreateQR=new MoraCreateQR();
        System.out.println("Hello world");
        String txt=moraCreateQR.testEncription("abc@$123");
        System.out.println("Encripted data :"+txt);
        MoraQR moraQR=new MoraQR();
        
        String filePath="D:/test/makeQR01.png";
        File theDir = new File(filePath);
        File parent = theDir.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        
        moraQR.makeQR(theDir,"BOSWIN :"+txt,100,0,0,0);

    }

    private String testEncription(String pass){
        String tpass=pass;
        try {
            MoraProtect protect=new MoraProtect();
            tpass=protect.passwordEncript(tpass,"MORA$XIAOMIADMIN");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return tpass;
    }
}
