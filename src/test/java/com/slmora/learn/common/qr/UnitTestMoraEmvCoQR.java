/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:44 PM
 */
package com.slmora.learn.common.qr;

import com.google.zxing.WriterException;
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

import java.io.IOException;

/**
 * This Class created for testing MoraEmvCoQR
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:44 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.qr.MoraEmvCoQR")
public class UnitTestMoraEmvCoQR
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestMoraCreateQR.class);

    MoraEmvCoQR moraEmvCoQR;
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

        moraEmvCoQR = new MoraEmvCoQR();

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
    @Tag("CRC")
    @Tag("EMVCO")
    @Tag("VALIDATE")
    @DisplayName("Test MoraEmvCoQR.reValidateEmvCoQR()")
    public void test001(){

        String payload = "00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore630443F4";

        String ourQRPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        try {
            moraEmvCoQR.reValidateEmvCoQR(payload, ourQRPath);
        } catch (IOException | WriterException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * This method runs createBasicQR() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("CRC")
    @Tag("EMVCO")
    @Tag("VALIDATE")
    @DisplayName("Test MoraEmvCoQR.reValidateEmvCoQR()")
    public void test002(){

        String payload = "00020101021126440010com.mifave0126https://myfave.com/qr/511730630010com.alipay0145https://qr.alipay.com/ocx00500vovy3fhtvuddmd651800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802LK5911DRAGOn BOWL6009SingapOre630443F4";

        String ourQRPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        try {
            moraEmvCoQR.reValidateEmvCoQR(payload, ourQRPath);
        } catch (IOException | WriterException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * This method runs createBasicQR() methods in MoraCreateQR class
     * This will analyze the generated QR
     * */
    @Test
    @Tag("CRC")
    @Tag("EMVCO")
    @Tag("VALIDATE")
    @DisplayName("Test MoraEmvCoQR.reValidateEmvCoQR()")
    public void test003(){

        String payload = "0002010102120216464946864986670226320028164540000000010000010000000030630010com.alipay0145https://qr.alipay.com/ocx08378ktaghmyy4spyy3c5204541153031445406150.525502015802LK5911JEWEL QUDSI6007Colombo621207084010000063044F34";

        String ourQRPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\test\\demo.png";

        try {
            moraEmvCoQR.reValidateEmvCoQR(payload, ourQRPath);
        } catch (IOException | WriterException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }
}
