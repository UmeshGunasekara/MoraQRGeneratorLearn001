/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/19/2020 12:37 PM
 */
package com.slmora.learn.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Class created for unit testing com.slmora.learn.common.util.MoraAccessProperties
 *
 * @Author: SLMORA
 * @DateTime: 9/19/2020 12:37 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/19/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.util.MoraAccessProperties")
public class UnitTestMoraAccessProperties
{
    TestInfo testInfo;
    TestReporter testReporter;
    final static Logger LOGGER = LogManager.getLogger(UnitTestMoraAccessProperties.class);

    MoraAccessProperties accessProperties;

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
     * @param testInfo, testReporter to inject reporting information
     * */
    @BeforeEach
    public void beforeEachInit(TestInfo testInfo, TestReporter testReporter)
    {
        this.testInfo =  testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + "with tags " + testInfo.getTags() + "\n");

        accessProperties = new MoraAccessProperties();

//        this.TEST_FILE_PATH = Paths.get("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJavaFileAccess\\MOD_1.txt");
//        this.TEST_FILE_LOCATION = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJavaFileAccess\\MOD_1.txt";
//        this.TEST_OUT_PUT_STRING = "hi Hello, world1!\nHello, world2!\nhi Hello, world10!";
//        this.TEST_OUT_PUT_STRING_FILTER = "HI HELLO, WORLD1!\nHI HELLO, WORLD0005!";
//        this.TEST_OUT_PUT_LIST = new ArrayList();
//        this.TEST_OUT_PUT_LIST.add("hi Hello, world1!");
//        this.TEST_OUT_PUT_LIST.add("Hello, world2!");
//        this.TEST_OUT_PUT_LIST.add("hi Hello, world10!");
//        this.TEST_OUT_PUT_LIST_FILTER = new ArrayList();
//        this.TEST_OUT_PUT_LIST_FILTER.add("HI HELLO, WORLD1!");
//        this.TEST_OUT_PUT_LIST_FILTER.add("HI HELLO, WORLD0005!");
//        this.FILE_ACCESS = new MoraFileAccess();
    }

    /**
     * Runs this method after each test execution
     * */
    @AfterEach
    private void afterEachEnd()
    {
        System.out.println("Test Completed "+testInfo.getDisplayName());
        System.out.println("After Each Clean Test........");
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("READ")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read getPropertyFromResource(String propertyFileName, String propertyRef)")
    public void givenPropertyFileFromResource(){
        assertEquals(
                "mora@slmora.com",
                accessProperties.getPropertyFromResource("constant.properties","MORA.CONSTANT.TEST_EMAIL"));
    }

    /**
     * This method runs getPropertyFromResourceWithPath(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
//    @Test
//    @Tag("READ")
//    @Tag("RESOURCE")
//    @DisplayName("Test Property File Read getPropertyFromResourceWithPath(String propertyFileName, String propertyRef)")
//    public void givenPropertyFileFromResourceWithPath(){
//        assertEquals(
//                "mora@slmora.com",
//                accessProperties.getPropertyFromResourceWithPath("constant.properties","MORA.CONSTANT.TEST_EMAIL"));
//    }

    /**
     * This method runs getPropertyFromResourceWithPath(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("READ")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read getPropertyFromResourceWithPath(String propertyFileName, String propertyRef)")
    public void givenPropertyFileFromResourceWithPath(){
        assertEquals(
                "mora@slmora.com",
                accessProperties.getPropertyFromPath(
                        getClass()
                                .getClassLoader()
                                .getResource("constant.properties")
                                .getPath(),
                        "MORA.CONSTANT.TEST_EMAIL"));
    }

    /**
     * This method runs getPropertyFromPath(String propertyFilePath, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("READ")
    @Tag("PATH")
    @DisplayName("Test Property File Read getPropertyFromPath(String propertyFilePath, String propertyRef)")
    public void givenPropertyFileFromPath(){
        assertEquals(
                "mora@slmora.com",
                accessProperties.getPropertyFromPath("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraHibernateLearn001\\src\\main\\resources\\constant.properties",
                        "MORA.CONSTANT.TEST_EMAIL"));
    }

    /**
     * This method runs setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment)
     * methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("WRIGHT")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment)")
    public void setPropertyFromPath(){
        assertAll(
                () -> assertNotEquals(
                        "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraHibernateLearn001\\src\\main\\resources\\constant.properties",
                        getClass()
                                .getClassLoader()
                                .getResource("constant.properties")
                                .getPath()
                ),
                () -> assertEquals(
                        1,
                        accessProperties.setPropertyFromPath(
                                "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraHibernateLearn001\\src\\main\\resources\\constant.properties",
                                "MORA.CONSTANT.TEST",
                                "mora@#12346",
                                "Test Comment").intValue()
                ),
                () -> assertEquals(
                        1,
                        accessProperties.setPropertyFromPath(
                                getClass()
                                        .getClassLoader()
                                        .getResource("constant.properties")
                                        .getPath(),
                                "MORA.CONSTANT.TEST",
                                "mora@#12345",
                                "Test Comment").intValue()
                )
        );
    }

    /**
     * This method runs getPropertyFromResourceWithPath(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("READ")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read getPropertyFromResourceWithPath(String propertyFileName, String propertyRef)")
    public void givenXmlPropertyFileFromResourceWithPath(){
        assertEquals(
                "mora@slmora.com",
                accessProperties.getXmlPropertyFromPath(
                        getClass()
                                .getClassLoader()
                                .getResource("constantxml.xml")
                                .getPath(),
                        "MORA.CONSTANT.TEST_EMAIL"));
    }

    /**
     * This method runs setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment)
     * methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("WRIGHT")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment)")
    public void setXmlPropertyFromPath(){
        assertAll(
                () -> assertNotEquals(
                        "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraHibernateLearn001\\src\\main\\resources\\constantxml.xml",
                        getClass()
                                .getClassLoader()
                                .getResource("constantxml.xml")
                                .getPath()
                ),
                () -> assertEquals(
                        1,
                        accessProperties.setXmlPropertyFromPath(
                                "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraHibernateLearn001\\src\\main\\resources\\constantxml.xml",
                                "MORA.CONSTANT.TEST",
                                "mora@#12346",
                                "Test Comment").intValue()
                )
//                () -> assertEquals(
//                        1,
//                        accessProperties.setXmlPropertyFromPath(
//                                getClass()
//                                        .getClassLoader()
//                                        .getResource("constantxml.xml")
//                                        .getPath(),
//                                "MORA.CONSTANT.TEST",
//                                "mora@#12345",
//                                "Test Comment").intValue()
//                )
        );
    }

    /**
     * This method runs setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment)
     * methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("WRIGHT")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment)")
    public void createXmlPropertyFromPath(){
        assertEquals(
                1,
                accessProperties.createXmlPropertyFromPath(
                        "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraHibernateLearn001\\src\\main\\resources\\constantxml.xml",
//                        getClass()
//                                .getClassLoader()
//                                .getResource("constantxml.xml")
//                                .getPath(),
                        "MORA.CONSTANT.TEST",
                        "mora@#1234",
                        "Test Comment").intValue()
        );
    }
}
