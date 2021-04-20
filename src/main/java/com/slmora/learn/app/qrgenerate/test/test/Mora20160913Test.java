/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmora.learn.app.qrgenerate.test.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;

/**
 *
 * @author Umesh Gunasekara
 */
public class Mora20160913Test {

    public static void main(String[] args) {
        System.out.println("Hello World!....");

        ValidationHelp help = new ValidationHelp();
        long category = 1L;
        long model = 10L;
        long id = 10234L;

        String companyRefID = help.chooceDigits(category, 3, 3) + "/" + help.chooceDigits(model, 5, 5) + "/" + help.chooceDigits(id, 10, 10);
        String companyRefIDS = help.chooceDigits(category, 3, 3) + "$" + help.chooceDigits(model, 5, 5) + "$" + help.chooceDigits(id, 10, 10);
        String companyRef = "MORA ID:" + companyRefIDS;
        System.out.println("Company reference :" + companyRef);
        try {
            MoraProtect protect = new MoraProtect();
            String encriptedText = protect.passwordEncript(companyRef, "MORA$XIAOMIADMIN");
            System.out.println("Encripted data :" + encriptedText);

            File miLogo = new File("F:/milogo.png");
            File srichatLogo = new File("F:/srichatlogo.png");
            File srichatQR = new File("F:/srichatplayqr.png");
            File lineimg = new File("F:/line.png");

            File outF = new File("F:/output/out.png");
            File parent = outF.getParentFile();
            if (!parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            MoraCreateNewQR createQR = new MoraCreateNewQR();
            String qrText = companyRefID;
            System.out.println("QR Text :" + qrText);

            BufferedImage backgroud = createQR.setBackground(qrText);
            ImageIO.write(backgroud, "PNG", new File("F:/otherimg/backgroud.png"));

            String qrKey="BOSWIN ID:"+encriptedText;
            System.out.println("QR Key :" + qrKey);
            
            BufferedImage deviceQRbuf = createQR.makeQRBuf(qrKey, 2000, 0, 0, 0);
            ImageIO.write(deviceQRbuf, "PNG", new File("F:/otherimg/deviceQRbuf.png"));

            BufferedImage backgroudmerge01 = createQR.mergeImageBuf(deviceQRbuf, backgroud, 0, 0);
            ImageIO.write(backgroudmerge01, "PNG", new File("F:/otherimg/backgroudmerge01.png"));

            BufferedImage backgroudmerge02 = createQR.mergeImageBufFile(srichatLogo, backgroudmerge01, 2000, 0);

            BufferedImage backgroudmerge03 = createQR.mergeImageBufFile(srichatQR, backgroudmerge02, 4000, 0);
            ImageIO.write(backgroudmerge03, "PNG", new File("F:/otherimg/backgroudmerge03.png"));

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mora20160913Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
