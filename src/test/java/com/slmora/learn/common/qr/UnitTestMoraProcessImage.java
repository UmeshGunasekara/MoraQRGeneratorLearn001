/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 6:41 PM
 */
package com.slmora.learn.common.qr;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This Class created for Testing MoraProcessImage
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 6:41 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.qr.MoraProcessImage")
public class UnitTestMoraProcessImage
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestMoraProcessImage.class);

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
    @Tag("IMAGE")
    @Tag("RESIZE")
    @DisplayName("Test MoraProcessImage.resizeImage()")
    public void test001(){

        File inFile = new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\srichatplayqr.png");

        // The path where the image will get saved
        String OutPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        try {
            moraProcessImage.printImage(OutPath, moraProcessImage.getResizedBufferedImageWithHint(inFile,10000,10000), "PNG");
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

        System.out.println("Image Resized!!! ");
    }

    /**
     * This method runs createBasicQR() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("IMAGE")
    @Tag("MERGE")
    @DisplayName("Test MoraProcessImage.mergeImage()")
    public void test002(){

        File innerImageFile = new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\milogo200.png");
        File outerImageFile = new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\miqr.png");


        // The path where the image will get saved
        String OutPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        try {
//            moraProcessImage.printImage(OutPath, moraProcessImage.getResizedBufferedImageWithHint(inFile,10000,10000), "PNG");
            moraProcessImage.mergeImage(innerImageFile, outerImageFile, OutPath);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

        System.out.println("Image Merged!!! ");
    }
}
