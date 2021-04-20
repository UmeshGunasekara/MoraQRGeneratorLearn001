package com.slmora.learn.app.qrgenerate.test.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.spec.KeySpec;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by A.U.D.M.Gunasekara on 2016-07-11.
 */
public class MoraXiaomiQR {
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public MoraXiaomiQR() throws Exception {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }






    public static void main(String args[]){
//        MoraXiaomiQR.makeQR("TestMora","999/99999/9999999999");

        MoraXiaomiQR factory= null;
        try {
            factory = new MoraXiaomiQR();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String target="999/99999/9999999999";
        String encrypted=factory.encrypt(target);

        String qrValue="Xiaomi.lk/"+encrypted;
        File miLogo=new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\milogo.png");
        File srichatLogo=new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\srichatlogo.png");
        File srichatQR=new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\srichatplayqr.png");
        File lineimg=new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\line.png");

        File outF=new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\out.png");

        BufferedImage backgroud=factory.setBackground(qrValue);

        BufferedImage deviceQRbuf=factory.makeQRBuf(qrValue,2000,255,74,0);
        factory.printImage("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\miqr.png",deviceQRbuf,"PNG");
        BufferedImage milogobuf=factory.resizeImageWithHint(miLogo,200,200);
        factory.printImage("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\milogo200.png",milogobuf,"PNG");
        BufferedImage devicefinalQRbuf=factory.mergeImageBuf(milogobuf,deviceQRbuf);

        BufferedImage backgroudmerge01=factory.mergeImageBuf(devicefinalQRbuf,backgroud,0,0);

        BufferedImage backgroudmerge02=factory.mergeImageBufFile(srichatLogo,backgroudmerge01,2000,0);

        BufferedImage backgroudmerge03=factory.mergeImageBufFile(srichatQR,backgroudmerge02,4000,0);

        try {
            ImageIO.write(backgroudmerge03, "png", outF);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makeQR(String outPutFileName,String sourceText){
        String myCodeText = sourceText;
        String filePath = "F:/"+outPutFileName+".png";
        int size = 2000;
        String fileType = "png";
        File myFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);


            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(4*CrunchifyWidth, CrunchifyWidth+300,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 4*CrunchifyWidth, CrunchifyWidth+300);
            graphics.setColor(new Color(255,74,0));

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            Font font = new Font("Serif", Font.PLAIN, 50);
            graphics.setFont(font);
            graphics.setColor(new Color(0,0,0));
            graphics.drawString(sourceText, 50, 2100);


            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
    }

    public void makeQR(String outPutFileName,String sourceText, int imgSize, int r, int g, int b){
        String myCodeText = sourceText;
        String filePath = "D:/"+outPutFileName+".png";
        int size = imgSize;
        String fileType = "png";
        File myFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);


            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(new Color(r,g,b));

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nQR code created.");
    }

    public BufferedImage makeQRBuf(String sourceText, int imgSize, int r, int g, int b){
        String myCodeText = sourceText;
        int size = imgSize;
        BufferedImage image=null;
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);


            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
//            graphics.setColor(new Color(255,74,0));
            graphics.setColor(new Color(r,g,b));

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

//            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } /*catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("\n\nQR code created.");
        return image;
    }

    public void mergeImage(){
        File inner = new File("F:/a1.png");
        File outter = new File("F:/a2.png");

        try {

            BufferedImage biInner = ImageIO.read(inner);
            BufferedImage biOutter = ImageIO.read(outter);

            System.out.println(biInner);
            System.out.println(biOutter);

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();

            ImageIO.write(biOutter, "PNG", new File("F:/Outter.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mergeImage(File innerFile,File outterFile){
        File inner = innerFile;//new File("D:/a1.png");
        File outter = outterFile;//new File("D:/a2.png");

        try {

            BufferedImage biInner = ImageIO.read(inner);
            BufferedImage biOutter = ImageIO.read(outter);

            System.out.println(biInner);
            System.out.println(biOutter);

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();

            ImageIO.write(biOutter, "PNG", new File("F:/Outter.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mergeImage(BufferedImage biInnerBuf,BufferedImage biOutterBuf){
        try {

            BufferedImage biInner = biInnerBuf;
            BufferedImage biOutter = biOutterBuf;

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
//            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();


            ImageIO.write(biOutter, "PNG", new File("F:/Outter.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage mergeImageBuf(BufferedImage biInnerBuf,BufferedImage biOutterBuf){
        BufferedImage biOutter=null;
        try {

            BufferedImage biInner = biInnerBuf;
            biOutter = biOutterBuf;

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
//            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();


//            ImageIO.write(biOutter, "PNG", new File("D:/Outter.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return biOutter;
    }

    public void mergeImage(BufferedImage biInnerBuf,BufferedImage biOutterBuf,File file, int x, int y){
        try {

            BufferedImage biInner = biInnerBuf;
            BufferedImage biOutter = biOutterBuf;

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
//            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
//            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();


            ImageIO.write(biOutter, "PNG", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mergeImage(File innerFile,BufferedImage biOutterBuf,File file, int x, int y){
        File inner = innerFile;
        try {

            BufferedImage biInner = ImageIO.read(inner);
            BufferedImage biOutter = biOutterBuf;

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
//            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
//            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();


            ImageIO.write(biOutter, "PNG", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage mergeImageBufFile(File innerFile,BufferedImage biOutterBuf, int x, int y){
        BufferedImage biOutter=null;
        File inner = innerFile;
        try {

            BufferedImage biInner = ImageIO.read(inner);
            biOutter = biOutterBuf;

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
//            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
//            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();


//            ImageIO.write(biOutter, "PNG", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return biOutter;
    }

    public BufferedImage mergeImageBuf(BufferedImage biInnerBuf,BufferedImage biOutterBuf, int x, int y){
        BufferedImage biOutter=null;
        try {

            BufferedImage biInner = biInnerBuf;
            biOutter = biOutterBuf;

            Graphics2D g = biOutter.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//            int x = (biOutter.getWidth() - biInner.getWidth()) / 2;
//            int y = (biOutter.getHeight() - biInner.getHeight()) / 2;
//            System.out.println(x + "x" + y);
            g.drawImage(biInner, x, y, null);
            g.dispose();


//            ImageIO.write(biOutter, "PNG", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return biOutter;
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH,int IMG_HEIGHT){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, int IMG_WIDTH,int IMG_HEIGHT){

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public BufferedImage resizeImageWithHint(File file, int IMG_WIDTH,int IMG_HEIGHT){

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public void printImage(String path, BufferedImage image, String format){
        File file=new File(path);
        try {
            ImageIO.write(image, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage setBackground(String sourceText){
        BufferedImage image = new BufferedImage(6000, 2400, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 6000, 2200);

//        Font font1 = new Font("Serif", Font.PLAIN, 200);
        Font font1 = new Font("Calibri", Font.PLAIN, 200);
        graphics.setFont(font1);
        graphics.setColor(new Color(0,0,0));
//        graphics.drawString("_____________________________________", 50, 2000);
        graphics.drawString(sourceText, 50, 2150);

        Font font = new Font("Calibri", Font.PLAIN, 200);
        graphics.setFont(font);
        graphics.setColor(new Color(255,255,255));
        graphics.drawString("For Xiaomi Registration", 50, 2350);
        graphics.drawString("Install Srichat", 4050, 2350);

        return image;
    }
}
