/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmora.learn.common.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Umesh Gunasekara
 */
public class MoraCreateQROld
{
//    public BufferedImage setBackground(String sourceText){
//        BufferedImage image = new BufferedImage(910, 300, BufferedImage.TYPE_INT_RGB);
//        image.createGraphics();
//        Graphics2D graphics = (Graphics2D) image.getGraphics();
//        graphics.setColor(Color.WHITE);
//        graphics.fillRect(0, 0, 910, 275);
//
////        Font font1 = new Font("Serif", Font.PLAIN, 200);
//        Font font1 = new Font("Calibri", Font.PLAIN, 20);
//        graphics.setFont(font1);
//        graphics.setColor(new Color(0,0,0));
////        graphics.drawString("_____________________________________", 50, 2000);
//        graphics.drawString(sourceText, 5, 268);
//
//        Font font = new Font("Calibri", Font.PLAIN, 20);
//        graphics.setFont(font);
//        graphics.setColor(new Color(255,255,255));
//        graphics.drawString("For Xiaomi Registration", 5, 293);
//        graphics.drawString("Install Srichat", 608, 293);
//
//        return image;
//    }

    public BufferedImage setBackground(String sourceText){
        BufferedImage image = new BufferedImage(300, 370, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 300, 330);

//        Font font1 = new Font("Serif", Font.PLAIN, 200);
        Font font1 = new Font("Calibri", Font.PLAIN, 28);
        graphics.setFont(font1);
        graphics.setColor(new Color(0,0,0));
//        graphics.drawString("_____________________________________", 50, 2000);
        graphics.drawString(sourceText, 10, 318);

        Font font = new Font("Calibri", Font.PLAIN, 28);
        graphics.setFont(font);
        graphics.setColor(new Color(255,255,255));
        graphics.drawString("For Xiaomi Registration", 10, 360);

        return image;
    }

    public BufferedImage makeQRBuf(String sourceText, int imgSize, int r, int g, int b) {
        String myCodeText = sourceText;
        int size = imgSize;
        BufferedImage image = null;
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1);
            /* default = 4 */
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
//            image = new BufferedImage(4 * CrunchifyWidth, CrunchifyWidth + 300,
//                    BufferedImage.TYPE_INT_RGB);
//            image.createGraphics();
//
//            Graphics2D graphics = (Graphics2D) image.getGraphics();
//            graphics.setColor(Color.WHITE);
//            graphics.fillRect(0, 0, 4 * CrunchifyWidth, CrunchifyWidth + 300);
//            graphics.setColor(new Color(255,74,0));
            graphics.setColor(new Color(r, g, b));

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
        }
        /*catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("\n\nQR code created.");
        return image;
    }

    public BufferedImage mergeImageBuf(BufferedImage biInnerBuf, BufferedImage biOutterBuf, int x, int y) {
        BufferedImage biOutter = null;
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

    public BufferedImage mergeImageBufFile(File innerFile, BufferedImage biOutterBuf, int x, int y) {
        BufferedImage biOutter = null;
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

    public BufferedImage resizeImageWithHint(File file, int IMG_WIDTH, int IMG_HEIGHT) {

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

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

    public BufferedImage mergeImageBuf(BufferedImage biInnerBuf, BufferedImage biOutterBuf) {
        BufferedImage biOutter = null;
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
}
