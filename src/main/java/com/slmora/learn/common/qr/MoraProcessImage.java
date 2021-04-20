/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 6:24 PM
 */
package com.slmora.learn.common.qr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This Class created for Process Image
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 6:24 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
public class MoraProcessImage
{

    /**
     * Method to generate the QR code
     * @param outPath
     * @param bufferedImage
     * @param imgFormat
     * */
    public void printImage(String outPath, BufferedImage bufferedImage, String imgFormat) throws IOException
    {
        File file=new File(outPath);
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        ImageIO.write(bufferedImage, imgFormat, file);
    }

    /**
     * Method to generate the QR code
     * @param outFile
     * @param bufferedImage
     * @param imgFormat
     * */
    public void printImage(File outFile, BufferedImage bufferedImage, String imgFormat) throws IOException
    {
        File parent = outFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        ImageIO.write(bufferedImage, imgFormat, outFile);
    }

    // get resized BufferedImage without Hint
    /**
     * Method to generate the QR code
     * @param originalBufferedImage
     * @param type
     * @param imgWidth
     * @param imgHeight
     * */
    public BufferedImage getResizedBufferedImage(BufferedImage originalBufferedImage, int type, int imgWidth,int imgHeight){
        BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalBufferedImage, 0, 0, imgWidth, imgHeight, null);
        g.dispose();

        return resizedImage;
    }

    // get resized BufferedImage with Hint, have to provide type
    /**
     * Method to generate the QR code
     * @param originalBufferedImage
     * @param type
     * @param imgWidth
     * @param imgHeight
     * */
    public BufferedImage getResizedBufferedImageWithHint(BufferedImage originalBufferedImage, int type, int imgWidth,int imgHeight){

        BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalBufferedImage, 0, 0, imgWidth, imgHeight, null);
        g.dispose();

        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    /**
     * Method to generate the QR code
     * @param inputImageFile
     * @param imgWidth
     * @param imgHeight
     * */
    public BufferedImage getResizedBufferedImageWithHint(File inputImageFile, int imgWidth, int imgHeight) throws IOException
    {

        BufferedImage originalImage = ImageIO.read(inputImageFile);
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

//        BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
//        Graphics2D g = resizedImage.createGraphics();
//
//        g.drawImage(originalImage, 0, 0, imgWidth, imgHeight, null);
//        g.dispose();
//        g.setComposite(AlphaComposite.Src);
//        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return getResizedBufferedImageWithHint(originalImage, type, imgWidth, imgHeight);
    }

    /**
     * Method to generate the QR code
     * @param innerFilePath
     * @param outerFilePath
     * @param outImagePath
     * */
    public void mergeImage(String innerFilePath,String outerFilePath, String outImagePath) throws IOException
    {
        BufferedImage innerBufferedImage = ImageIO.read(new File(innerFilePath));
        BufferedImage outerBufferedImage = ImageIO.read(new File(outerFilePath));

        System.out.println(innerBufferedImage);
        System.out.println(outerBufferedImage);

//        Graphics2D g = outerBufferedImage.createGraphics();
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//        int x = (outerBufferedImage.getWidth() - innerBufferedImage.getWidth()) / 2;
//        int y = (outerBufferedImage.getHeight() - innerBufferedImage.getHeight()) / 2;
//        System.out.println(x + "x" + y);
//        g.drawImage(innerBufferedImage, x, y, null);
//        g.dispose();
//
////        ImageIO.write(outerBufferedImage, "PNG", new File("F:/Outter.png"));
//        printImage(outImagePath, outerBufferedImage, "PNG");

        mergeImage(innerBufferedImage, outerBufferedImage, outImagePath);
    }

    /**
     * Method to generate the QR code
     * @param innerFile
     * @param outerFile
     * @param outImagePath
     * */
    public void mergeImage(File innerFile,File outerFile, String outImagePath) throws IOException
    {
        BufferedImage innerBufferedImage = ImageIO.read(innerFile);
        BufferedImage outerBufferedImage = ImageIO.read(outerFile);

        System.out.println(innerBufferedImage);
        System.out.println(outerBufferedImage);

//        Graphics2D g = outerBufferedImage.createGraphics();
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//        int x = (outerBufferedImage.getWidth() - innerBufferedImage.getWidth()) / 2;
//        int y = (outerBufferedImage.getHeight() - innerBufferedImage.getHeight()) / 2;
//        System.out.println(x + "x" + y);
//        g.drawImage(innerBufferedImage, x, y, null);
//        g.dispose();
//
////        ImageIO.write(outerBufferedImage, "PNG", new File("F:/Outter.png"));
//        printImage(outImagePath, outerBufferedImage, "PNG");

        mergeImage(innerBufferedImage, outerBufferedImage, outImagePath);
    }

    /**
     * Method to generate the QR code
     * @param innerBufferedImage
     * @param outerBufferedImage
     * @param outImagePath
     * */
    public void mergeImage(BufferedImage innerBufferedImage,BufferedImage outerBufferedImage, String outImagePath) throws IOException
    {
//        Graphics2D g = outerBufferedImage.createGraphics();
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//        int x = (outerBufferedImage.getWidth() - innerBufferedImage.getWidth()) / 2;
//        int y = (outerBufferedImage.getHeight() - innerBufferedImage.getHeight()) / 2;
////            System.out.println(x + "x" + y);
//        g.drawImage(innerBufferedImage, x, y, null);
//        g.dispose();
//
////        ImageIO.write(outerBufferedImage, "PNG", new File("F:/Outter.png"));
//        printImage(outImagePath, mergeBufferedImage(innerBufferedImage, outerBufferedImage, 1f), "PNG");
        printImage(outImagePath, getMergedBufferedImage(innerBufferedImage, outerBufferedImage, 0.9f), "PNG");
    }

    /**
     * Method to generate the QR code
     * @param innerBufferedImage
     * @param outerBufferedImage
     * @param outFile
     * @param x
     * @param y
     * */
    public void mergeImage(BufferedImage innerBufferedImage,BufferedImage outerBufferedImage,File outFile, int x, int y)
    throws IOException
    {
//        Graphics2D g = outerBufferedImage.createGraphics();
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//        g.drawImage(innerBufferedImage, x, y, null);
//        g.dispose();
//
//        ImageIO.write(outerBufferedImage, "PNG", outFile);
        printImage(outFile, getMergedBufferedImage(innerBufferedImage, outerBufferedImage, 0.9f, x, y), "PNG");
    }

    /**
     * Method to generate the QR code
     * @param innerFile
     * @param outerBufferedImage
     * @param outFile
     * @param x
     * @param y
     * */
    public void mergeImage(File innerFile,BufferedImage outerBufferedImage,File outFile, int x, int y) throws
            IOException
    {

        BufferedImage innerBufferedImage = ImageIO.read(innerFile);

//        Graphics2D g = outerBufferedImage.createGraphics();
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//
//        g.drawImage(innerBufferedImage, x, y, null);
//        g.dispose();
//
//        ImageIO.write(outerBufferedImage, "PNG", outFile);
        printImage(outFile, getMergedBufferedImage(innerBufferedImage, outerBufferedImage, 1f, x, y), "PNG");

    }

    /**
     * Method to generate the QR code
     * @param innerBufferedImage
     * @param outerBufferedImage
     * @param alpha
     * */
    public BufferedImage getMergedBufferedImage(BufferedImage innerBufferedImage,BufferedImage outerBufferedImage, float alpha){

//        Graphics2D g = outerBufferedImage.createGraphics();
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        int x = (outerBufferedImage.getWidth() - innerBufferedImage.getWidth()) / 2;
        int y = (outerBufferedImage.getHeight() - innerBufferedImage.getHeight()) / 2;
//        g.drawImage(innerBufferedImage, x, y, null);
//        g.dispose();
//
//        return outerBufferedImage;
        return getMergedBufferedImage(innerBufferedImage, outerBufferedImage, alpha, x, y);
    }

    /**
     * Method to generate the QR code
     * @param innerFile
     * @param outerBufferedImage
     * @param x
     * @param y
     * */
    public BufferedImage getMergedBufferedImage(File innerFile,BufferedImage outerBufferedImage, int x, int y)
    throws IOException
    {
        BufferedImage innerBufferedImage = ImageIO.read(innerFile);
        return getMergedBufferedImage(innerBufferedImage,outerBufferedImage, 1f, x, y);
    }

    /**
     * Method to generate the QR code
     * @param innerBufferedImage
     * @param outerBufferedImage
     * @param alpha
     * @param x
     * @param y
     * */
    public BufferedImage getMergedBufferedImage(BufferedImage innerBufferedImage,BufferedImage outerBufferedImage, float alpha, int x, int y){

        Graphics2D g = outerBufferedImage.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(innerBufferedImage, x, y, null);
        g.dispose();

        return outerBufferedImage;
    }

}
