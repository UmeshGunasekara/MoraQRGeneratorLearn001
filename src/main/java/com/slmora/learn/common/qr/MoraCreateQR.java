/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:23 PM
 */
package com.slmora.learn.common.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;

/**
 * This Class created for create QR
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:23 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021        SLMORA                Initial Code
 */
public class MoraCreateQR
{
    final static Logger LOGGER = LogManager.getLogger(MoraCreateQR.class);

    // Function to create the QR code
    /**
     * Method to create the Basic QR code
     * @param data
     * @param path
     * @param charset
     * @param hashMap
     * @param height
     * @param width
     * */
    public static void createBasicQR(String data, String path, String charset, Map hashMap, int height, int width)
    throws WriterException, IOException
    {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
        LOGGER.info("QR Code Generated!!!.");
        System.out.println("\nQR Code Generated!!!.");
    }

    // Function to read the QR file
    /**
     * Method to read the Basic QR code
     * @param path
     * @param charset
     * @param hashMap
     * */
    public static String readBasicQR(String path, String charset, Map hashMap)
    throws FileNotFoundException, IOException, NotFoundException
    {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer( new BufferedImageLuminanceSource( ImageIO.read( new FileInputStream(path)))));
        Result result= new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }

    /**
     * Method to generate the QR code
     * @param outQRFile
     * @param qrSourceText
     * @param imgSize
     * @param r
     * @param g
     * @param b
     * */
    public void createMoraQR(File outQRFile, String qrSourceText, int imgSize, int r, int g, int b)
    throws IOException, WriterException
    {
//        //put outPutFilePath with .png extention
//        String fileType = "png";
//
//        Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
//        hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");
//        // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
//        hintMap.put(EncodeHintType.MARGIN, 1);
//        /* default = 4 */
//        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(qrSourceText, BarcodeFormat.QR_CODE, imgSize, imgSize, hintMap);
//
//        // Make the BufferedImage that are to hold the QRCode
//        int matrixWidth = bitMatrix.getWidth();
//        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
//        image.createGraphics();
//
//        Graphics2D graphics = (Graphics2D) image.getGraphics();
//        graphics.setColor(Color.WHITE);
//        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
//
//        // Paint and save the image using the ByteMatrix
//        graphics.setColor(new Color(r, g, b));
//
//        for (int i = 0; i < matrixWidth; i++) {
//            for (int j = 0; j < matrixWidth; j++) {
//                if (bitMatrix.get(i, j)) {
//                    graphics.fillRect(i, j, 1, 1);
//                }
//            }
//        }
//
//        ImageIO.write(image, fileType, outQRFile);
//        LOGGER.info("QR Code Generated!!!.");
//        System.out.println("\nQR Code Generated!!!.");

        Optional<BufferedImage> bufferedQRImage = getBufferedQRImage(qrSourceText, imgSize, r, g, b);
        MoraProcessImage moraProcessImage = new MoraProcessImage();
        if(bufferedQRImage.isPresent()){
//            ImageIO.write(bufferedQRImage.get(), "PNG", outQRFile);
            moraProcessImage.printImage(outQRFile, bufferedQRImage.get(), "PNG");
        }else{
            LOGGER.info("QR Code Can not generate.");
            System.out.println("\nSomething happened, QR Code Can not generate..");
        }
    }

    /**
     * Method to generate the QR code
     * @param outQRFilePath
     * @param qrSourceText
     * @param imgSize
     * @param r
     * @param g
     * @param b
     * */
    public void createMoraQR(String outQRFilePath, String qrSourceText, int imgSize, int r, int g, int b)
            throws IOException, WriterException
    {
        Optional<BufferedImage> bufferedQRImage = getBufferedQRImage(qrSourceText, imgSize, r, g, b);
        MoraProcessImage moraProcessImage = new MoraProcessImage();
        if(bufferedQRImage.isPresent()){
            moraProcessImage.printImage(outQRFilePath, bufferedQRImage.get(), "PNG");
        }else{
            LOGGER.info("QR Code Can not generate.");
            System.out.println("\nSomething happened, QR Code Can not generate..");
        }
    }

    /**
     * Method to generate the QR code
     * @param qrSourceText
     * @param imgSize
     * @param r
     * @param g
     * @param b
     * */
    public Optional<BufferedImage> getBufferedQRImage(String qrSourceText, int imgSize, int r, int g, int b) throws
            WriterException
    {
        BufferedImage image = null;
        Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
        hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");
        // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
        hintMap.put(EncodeHintType.MARGIN, 1);
        /* default = 4 */
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrSourceText, BarcodeFormat.QR_CODE, imgSize, imgSize, hintMap);

        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = bitMatrix.getWidth();
        image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);

        // Paint and save the image using the ByteMatrix
        graphics.setColor(new Color(r, g, b));

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        LOGGER.info("QR Code Generated!!!.");
        System.out.println("\nQR code created.");
        return Optional.ofNullable(image);
    }

    //Orange Color QR with width background additional 3 times with like QR and mention the QR text bellow QR
    /**
     * Method to generate the QR code
     * @param outQRFile
     * @param qrSourceText
     * */
    public void createSpecialQR1(File outQRFile, String qrSourceText)
    throws WriterException, IOException
    {
        //Set QR size as 2000
        int size = 2000;
        //put outPutFilePath with .png extention
        String fileType = "png";

        Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, "UTF-8");
        // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
        hintMap.put(EncodeHintType.MARGIN, 1);
        /* default = 4 */
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrSourceText, BarcodeFormat.QR_CODE, size, size, hintMap);

        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = bitMatrix.getWidth();
        //Set image with as 4 times QR with and add 300 pix for height
        BufferedImage image = new BufferedImage(4 * matrixWidth, matrixWidth + 300, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        // set Color.WHITE for additional space also
        graphics.fillRect(0, 0, 4 * matrixWidth, matrixWidth + 300);
//        graphics.fillRect(0, 0, matrixWidth, matrixWidth); // this make additional space as black

        //Set QR foreground color as orange
        graphics.setColor(new Color(255, 74, 0));

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        //Add QR text bellow QR
        Font font = new Font("Serif", Font.PLAIN, 50);
        graphics.setFont(font);
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawString(qrSourceText, 50, 2100);

        ImageIO.write(image, fileType, outQRFile);
        System.out.println("\nQR Code Generated!!!.");
    }

    //For Xiaomi QR

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

}
