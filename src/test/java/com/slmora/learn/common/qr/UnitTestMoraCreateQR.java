/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 4:00 PM
 */
package com.slmora.learn.common.qr;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * This Class created for Test MoraCreateQR
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 4:00 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.qr.MoraCreateQR")
public class UnitTestMoraCreateQR
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestMoraCreateQR.class);

    MoraCreateQR moraCreateQR;
    MoraProcessImage moraProcessImage;
    long startTime;
    long endTime;

    /**
     * Runs this method before initialize this test class
     * */
    @BeforeAll
    public static void beforeAllInit()
    {
        System.out.println("Before all initialized .......");
    }

    /**
     * Runs this method before each test execution
     * @param testInfo to inject reporting information
     * @param testReporter to inject reporting information
     * */
    @BeforeEach
    public void beforeEachInit(TestInfo testInfo, TestReporter testReporter)
    {
        this.testInfo =  testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + "with tags " + testInfo.getTags() + "\n");

        moraCreateQR = new MoraCreateQR();
        moraProcessImage = new MoraProcessImage();

        System.out.println("Programme Start--------------------------------------------------------------------------");
        startTime = System.nanoTime();
    }

    /**
     * Runs this method after each test execution
     * */
    @AfterEach
    private void afterEachEnd()
    {
        endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End----------------------------------------------------------------------------");

        System.out.println("*****************************************************************************************");
        System.out.println("**** Test Completed : " + testInfo.getDisplayName());
        System.out.println("**** Elapsed TIme : " + ELAPSED_TIME + " ns");
        System.out.println("*****************************************************************************************");
        LOGGER.info("Elapsed TIme for " + testInfo.getDisplayName() + " : " + ELAPSED_TIME);
    }

    /**
     * This method runs After all Test Cases execution
     * This must be static because of this will execute after instance destroyed
     * */
    @AfterAll
    public static void AfterAllInit()
    {
        System.out.println("After All Initiated........");
    }

    /**
     * This method runs createBasicQR() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("QR")
    @Tag("GENERATE")
    @DisplayName("Test MoraCreateQR.createBasicQR()")
    public void test001(){

        // The data that the QR code will contain
        String data = "http://slmora.com";

        // The path where the image will get saved
        String path = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap= new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        try {
            moraCreateQR.createBasicQR(data, path, charset, hashMap, 200, 200);
        } catch (WriterException | IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        System.out.println("QR Code Generated!!! ");
    }

    /**
     * This method runs readBasicQR() methods in MoraCreateQR class
     * This will analyze the read QR
     * */
    @Test
    @Tag("QR")
    @Tag("READ")
    @DisplayName("Test MoraCreateQR.readBasicQR()")
    public void test002(){

        // The data that the QR code will contain
        String data = "http://slmora.com";

        // The path where the image will get saved
        String path = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap= new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        String reslt = null;
        try {
            reslt = moraCreateQR.readBasicQR(path, charset, hashMap);
            System.out.println("QRCode output: "+reslt);
        } catch (IOException | NotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        Assert.assertEquals(data,reslt);
    }

    /**
     * This method runs createMoraQR() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("QR")
    @Tag("GENERATE")
    @DisplayName("Test MoraCreateQR.createMoraQR()")
    public void test003(){

        // The data that the QR code will contain
        String data = "http://slmora.com";

        // The path where the image will get saved
        String path = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        File outQRFile = new File(path);
        File parent = outQRFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        // Create the QR code and save
        // in the specified folder
        try {
            moraCreateQR.createMoraQR(outQRFile, data, 1000,0,0,0);
        } catch (WriterException | IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * This method runs createSpecialQR1() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("QR")
    @Tag("GENERATE")
    @DisplayName("Test MoraCreateQR.createSpecialQR1()")
    public void test004(){

        // The data that the QR code will contain
        String data = "http://slmora.com";

        // The path where the image will get saved
        String path = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        File outQRFile = new File(path);
        File parent = outQRFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        // Create the QR code and save
        // in the specified folder
        try {
            moraCreateQR.createSpecialQR1(outQRFile, data);
        } catch (WriterException | IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * This method runs getBufferedQRImage() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("QR")
    @Tag("GENERATE")
    @DisplayName("Test MoraCreateQR.getBufferedQRImage()")
    public void test005(){

        // The data that the QR code will contain
        String data = "http://slmora.com";

        // The path where the image will get saved
        String path = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        File outQRFile = new File(path);
        File parent = outQRFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        // Create the QR code and save
        // in the specified folder
        try {
            Optional<BufferedImage> bufferedQRImage = moraCreateQR.getBufferedQRImage(data, 1000,0,0,0);
            if(bufferedQRImage.isPresent()){
                ImageIO.write(bufferedQRImage.get(), "PNG", outQRFile);
            }else{
                LOGGER.info("QR Code Can not generate.");
                System.out.println("\nSomething happened, QR Code Can not generate..");
            }
        } catch (WriterException | IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * This method runs getBufferedQRImage() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("QR")
    @Tag("GENERATE")
    @DisplayName("Test MoraCreateQR.getBufferedQRImage() and MoraProcessImage.printImage()")
    public void test006(){

        // The data that the QR code will contain
        String data = "00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore630443F4";

        // The path where the image will get saved
        String path = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        // Create the QR code and save
        // in the specified folder
        try {
            Optional<BufferedImage> bufferedQRImage = moraCreateQR.getBufferedQRImage(data, 1000,0,0,0);
            if(bufferedQRImage.isPresent()){
                moraProcessImage.printImage(path, bufferedQRImage.get(), "PNG");
            }else{
                LOGGER.info("QR Code Can not generate.");
                System.out.println("\nSomething happened, QR Code Can not generate..");
            }
        } catch (WriterException | IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }
}
