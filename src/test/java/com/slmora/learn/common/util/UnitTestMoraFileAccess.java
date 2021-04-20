/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 5/25/2020 6:17 PM
 */
package com.slmora.learn.common.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Test Class created for testing com.slmora.morajavafileaccess.MoraFileAccess
 *
 * @Author: SLMORA
 * @DateTime: 5/25/2020 6:17 PM
 * <p>
 * Version      Date            Editor              Note
 * ---------    ----------      ----------------    --------------------------------------------------------------------
 * 1.0          5/25/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.morajavafileaccess.MoraFileAccess")
public class UnitTestMoraFileAccess
{
    TestInfo testInfo;
    TestReporter testReporter;
    final static Logger LOGGER = LogManager.getLogger(MoraFileAccess.class);

    Path TEST_FILE_PATH;
    String TEST_FILE_LOCATION;
    String TEST_OUT_PUT_STRING;
    String TEST_OUT_PUT_STRING_FILTER;
    List<String> TEST_OUT_PUT_LIST;
    List<String> TEST_OUT_PUT_LIST_FILTER;
    MoraFileAccess FILE_ACCESS;


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

        this.TEST_FILE_PATH = Paths.get("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJavaFileAccess\\MOD_1.txt");
        this.TEST_FILE_LOCATION = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJavaFileAccess\\MOD_1.txt";
        this.TEST_OUT_PUT_STRING = "hi Hello, world1!\nHello, world2!\nhi Hello, world10!";
        this.TEST_OUT_PUT_STRING_FILTER = "HI HELLO, WORLD1!\nHI HELLO, WORLD0005!";
        this.TEST_OUT_PUT_LIST = new ArrayList();
        this.TEST_OUT_PUT_LIST.add("hi Hello, world1!");
        this.TEST_OUT_PUT_LIST.add("Hello, world2!");
        this.TEST_OUT_PUT_LIST.add("hi Hello, world10!");
        this.TEST_OUT_PUT_LIST_FILTER = new ArrayList();
        this.TEST_OUT_PUT_LIST_FILTER.add("HI HELLO, WORLD1!");
        this.TEST_OUT_PUT_LIST_FILTER.add("HI HELLO, WORLD0005!");
        this.FILE_ACCESS = new MoraFileAccess();
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
     * This method runs all print methods in MoraFileAccess class
     * printFileFullContentUsingStream(String filePath)
     * printFileFullContentUsingBufferedReader(String filePath)
     * printFileFullContentUsingBufferedReaderFileReader(String filePath)
     * printFileFullContentToListUsingScanner(String filePath)
     * All these methods are void methods
     * */
    @Nested
    @DisplayName("Test All Print Methods")
    class TestPrintMethods{
        /**
         * This method runs printFileFullContentUsingStream(String filePath) methods in MoraFileAccess class
         * This id void method
         * */
        @Test
        @Tag("READ")
        @Tag("PRINT")
        @DisplayName("Test printFileFullContentUsingStream(String filePath)")
        public void testPrintMethod01(){
            FILE_ACCESS.printFileFullContentUsingStream(TEST_FILE_LOCATION);
        }

        /**
         * This method runs printFileFullContentUsingBufferedReader(String filePath) methods in MoraFileAccess class
         * This id void method
         * */
        @Test
        @Tag("READ")
        @Tag("PRINT")
        @DisplayName("Test printFileFullContentUsingBufferedReader(String filePath)")
        public void testPrintMethod02(){
            FILE_ACCESS.printFileFullContentUsingBufferedReader(TEST_FILE_LOCATION);
        }

        /**
         * This method runs printFileFullContentUsingBufferedReaderFileReader(String filePath) methods in MoraFileAccess class
         * This id void method
         * */
        @Test
        @Tag("READ")
        @Tag("PRINT")
        @DisplayName("Test printFileFullContentUsingBufferedReaderFileReader(String filePath)")
        public void testPrintMethod03(){
            FILE_ACCESS.printFileFullContentUsingBufferedReaderFileReader(TEST_FILE_LOCATION);
        }

        /**
         * This method runs printFileFullContentToListUsingScanner(String filePath) methods in MoraFileAccess class
         * This id void method
         * */
        @Test
        @Tag("READ")
        @Tag("PRINT")
        @DisplayName("Test printFileFullContentToListUsingScanner(String filePath)")
        public void testPrintMethod04(){
            FILE_ACCESS.printFileFullContentToListUsingScanner(TEST_FILE_LOCATION);
        }
    }

    /**
     * This method runs all get file content in to List Object methods in MoraFileAccess class
     * getFileFullContentToListUsingStream(String filePath)
     * getFileFullContentInUTF8ToListUsingStream(String filePath)
     * getFilteredFileContentToListUsingStream(String filePath, String startsWith, String numberReplace)
     * getFileFullContentToListUsingBufferedReader(String filePath)
     * getFilteredFileContentToListUsingBufferedReader(String filePath, String startsWith, String numberReplace)
     * getFileFullContentToListUsingScanner(String filePath)
     * All these methods return List<String>
     * */
    @Nested
    @DisplayName("Test All Read for List Methods")
    class TestGetList{

        /**
         * This method runs getFileFullContentToListUsingStream(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_LIST
         * */
        @Test
        @Tag("READ")
        @Tag("LIST")
        @DisplayName("Test getFileFullContentToListUsingStream(String filePath)")
        public void testGetListMethod01(){
            assertIterableEquals(TEST_OUT_PUT_LIST, FILE_ACCESS.getFileFullContentToListUsingStream(TEST_FILE_LOCATION));
        }

        /**
         * This method runs getFileFullContentInUTF8ToListUsingStream(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_LIST
         * */
        @Test
        @Tag("READ")
        @Tag("LIST")
        @DisplayName("Test getFileFullContentInUTF8ToListUsingStream(String filePath)")
        public void testGetListMethod02(){
            assertIterableEquals(TEST_OUT_PUT_LIST, FILE_ACCESS.getFileFullContentInUTF8ToListUsingStream(TEST_FILE_LOCATION));
        }

        /**
         * This method runs getFilteredFileContentToListUsingStream(String filePath, String startsWith, String numberReplace) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_LIST_FILTER
         * */
        @Test
        @Tag("READ")
        @Tag("LIST")
        @Tag("FILTER")
        @DisplayName("Test getFilteredFileContentToListUsingStream(String filePath, String startsWith, String numberReplace)")
        public void testGetListMethod03(){
            assertIterableEquals(TEST_OUT_PUT_LIST_FILTER, FILE_ACCESS.getFilteredFileContentToListUsingStream(TEST_FILE_LOCATION, "Hello", "5"));
        }

        /**
         * This method runs getFileFullContentToListUsingBufferedReader(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_LIST
         * */
        @Test
        @Tag("READ")
        @Tag("LIST")
        @DisplayName("Test getFileFullContentToListUsingBufferedReader(String filePath)")
        public void testGetListMethod04(){
            assertIterableEquals(TEST_OUT_PUT_LIST, FILE_ACCESS.getFileFullContentToListUsingBufferedReader(TEST_FILE_LOCATION));
        }

        /**
         * This method runs getFilteredFileContentToListUsingBufferedReader(String filePath, String startsWith, String numberReplace) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_LIST_FILTER
         * */
        @Test
        @Tag("READ")
        @Tag("LIST")
        @Tag("FILTER")
        @DisplayName("Test getFilteredFileContentToListUsingBufferedReader(String filePath, String startsWith, String numberReplace)")
        public void testGetListMethod05(){
            assertIterableEquals(TEST_OUT_PUT_LIST_FILTER, FILE_ACCESS.getFilteredFileContentToListUsingBufferedReader(TEST_FILE_LOCATION, "Hello", "5"));
        }

        /**
         * This method runs getFileFullContentToListUsingScanner(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_LIST
         * */
        @Test
        @Tag("READ")
        @Tag("LIST")
        @DisplayName("Test getFileFullContentToListUsingScanner(String filePath)")
        public void testGetListMethod06(){
            assertIterableEquals(TEST_OUT_PUT_LIST, FILE_ACCESS.getFileFullContentToListUsingScanner(TEST_FILE_LOCATION));
        }
    }

    /**
     * This method runs all get file content in to single String Object methods in MoraFileAccess class
     * getFileFullContentInAllBytesToString(String filePath)
     * getFileFullContentInUTF8ToStringBuilderUsingStream(String filePath)
     * getFileFullContentInUTF8ToStringBuilderUsingReadAllLines(String filePath)
     * getFileFullContentInUTF8ToStringBuilderUsingInputStream(String filePath, String startsWith, String numberReplace)
     * getFileAllCharactersToString(String filePath)
     * readFromInputStream(InputStream inputStream)
     * getFileFullContentToStringUsingFileUtilsReadFile(String fileName)
     * getFileFullContentToStringUsingIOUtilsReadFile(String filePath)
     * getFileFullContentToStringBuilderUsingDataInputStream(String filePath)
     * getFileFullContentToStringUsingFileChanel(String filePath)
     * All these methods return String or StringBuilder Object
     * */
    @Nested
    @DisplayName("Test All Read for Single String Methods")
    class TestGetString{

        /**
         * This method runs getFileFullContentInAllBytesToString(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentInAllBytesToString(String filePath)")
        @Disabled
        public void testGetListMethod01(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS.getFileFullContentInAllBytesToString(TEST_FILE_LOCATION),
                    () -> "Expected ====> \n"+TEST_OUT_PUT_STRING+" \n but result is ====> \n"+FILE_ACCESS.getFileFullContentInAllBytesToString(TEST_FILE_LOCATION));
        }

        /**
         * This method runs getFileFullContentInUTF8ToStringBuilderUsingStream(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentInUTF8ToStringBuilderUsingStream(String filePath)")
        public void testGetListMethod02(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS
                            .getFileFullContentInUTF8ToStringBuilderUsingStream(TEST_FILE_LOCATION)
                            .toString()
                            .trim());
        }

        /**
         * This method runs getFileFullContentInUTF8ToStringBuilderUsingReadAllLines(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentInUTF8ToStringBuilderUsingReadAllLines(String filePath)")
        public void testGetListMethod03(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS
                            .getFileFullContentInUTF8ToStringBuilderUsingReadAllLines(TEST_FILE_LOCATION)
                            .toString()
                            .trim());
        }

        /**
         * This method runs getFileFullContentInUTF8ToStringBuilderUsingInputStream(String filePath, String startsWith, String numberReplace) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @Tag("FILTER")
        @DisplayName("Test getFileFullContentInUTF8ToStringBuilderUsingInputStream(String filePath, String startsWith, String numberReplace)")
        public void testGetListMethod04(){
            assertEquals(
                    TEST_OUT_PUT_STRING_FILTER,
                    FILE_ACCESS
                            .getFileFullContentInUTF8ToStringBuilderUsingInputStream(TEST_FILE_LOCATION, "Hello", "5")
                            .toString()
                            .trim());
        }

        /**
         * This method runs getFileAllCharactersToString(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileAllCharactersToString(String filePath)")
        @Disabled
        public void testGetListMethod05(){
            assertEquals(TEST_OUT_PUT_STRING, FILE_ACCESS.getFileAllCharactersToString(TEST_FILE_LOCATION));
        }

        /**
         * This method runs readFromInputStream(InputStream inputStream) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test readFromInputStream(InputStream inputStream)")
        public void testGetListMethod06(){
            try {
                assertEquals(
                        TEST_OUT_PUT_STRING,
                        FILE_ACCESS
                                .readFromInputStream(new FileInputStream(TEST_FILE_LOCATION))
                                .toString()
                                .trim());
            } catch (FileNotFoundException e) {
                LOGGER.error(ExceptionUtils.getMessage(e));
                e.printStackTrace();
            }
        }

        /**
         * This method runs getFileFullContentToStringUsingFileUtilsReadFile(String fileName) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentToStringUsingFileUtilsReadFile(String fileName)")
        @Disabled
        public void testGetListMethod07(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS
                            .getFileFullContentToStringUsingFileUtilsReadFile("MOD_1.txt"));
        }

        /**
         * This method runs getFileFullContentToStringUsingIOUtilsReadFile(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentToStringUsingIOUtilsReadFile(String filePath)")
        @Disabled
        public void testGetListMethod08(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS
                            .getFileFullContentToStringUsingIOUtilsReadFile(TEST_FILE_LOCATION));
        }

        /**
         * This method runs getFileFullContentToStringBuilderUsingDataInputStream(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentToStringBuilderUsingDataInputStream(String filePath)")
        @Disabled
        public void testGetListMethod09(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS
                            .getFileFullContentToStringBuilderUsingDataInputStream(TEST_FILE_LOCATION));
        }

        /**
         * This method runs getFileFullContentToStringUsingFileChanel(String filePath) methods in MoraFileAccess class
         * This compare for expected TEST_OUT_PUT_STRING
         * */
        @Test
        @Tag("READ")
        @Tag("SINGLE")
        @DisplayName("Test getFileFullContentToStringUsingFileChanel(String filePath)")
        @Disabled
        public void testGetListMethod10(){
            assertEquals(
                    TEST_OUT_PUT_STRING,
                    FILE_ACCESS
                            .getFileFullContentToStringUsingFileChanel(TEST_FILE_LOCATION));
        }
    }

    /**
     * This method runs After all Test Cases execution
     * This must be static because of this will execute after instance destroyed
     * */
    @Test
    @DisplayName("Test PrintWriter.close() option to clear the file")
    @Disabled
//    @EnabledOnJre(JRE.JAVA_14)
    public void testPrintWriterClose(){
        List<String> list = new ArrayList<>();
        String s = null;
        try (Stream<String> fileStream = Files.lines(TEST_FILE_PATH)){
            new PrintWriter("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJavaFileAccess\\MOD1.txt").close();
            list = fileStream.collect(Collectors.toList());
            assertEquals(0,list.size());
        } catch (FileNotFoundException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
            System.out.println("TTS : "+ExceptionUtils.getMessage(e));
            fail(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            System.out.println("TTS : "+ExceptionUtils.getFullStackTrace(e));
            fail(e.getMessage());
            e.printStackTrace();
        }

    }

//    @Test
//    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() {
//        String expectedData = "Hello, world!";
//        MoraFileAccess fileAccess = new MoraFileAccess();
//
//        Class clazz = MoraFileAccess.class;
////        InputStream inputStream = clazz.getResourceAsStream("/MOD_1.txt");
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJavaFileAccess\\src\\main\\java\\com\\slmora\\morajavafileaccess\\MOD_1.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String data = fileAccess.readFromInputStream(inputStream);
//
//        assertEquals(data, expectedData);
//    }

    /**
     * This method runs readFromInputStream(InputStream inputStream) methods in MoraFileAccess class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("READ")
    @Tag("RESOURCE")
    @DisplayName("Test getClass().getClassLoader().getResourceAsStream()")
    public void givenFileNameAsAbsolutePathUsingClasspathFileData(){
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("MOD_1.txt");
            assertAll(
                    () -> assertEquals(
                            TEST_OUT_PUT_STRING,
                            FILE_ACCESS
                                    .readFromInputStream(inputStream)
                                    .toString()
                                    .trim()),
                    () -> assertEquals(
                            TEST_OUT_PUT_STRING,
                            FILE_ACCESS
                                    .readFromInputStream(new FileInputStream(TEST_FILE_LOCATION))
                                    .toString()
                                    .trim())
            );
    }

    /**
     * This method runs readFromInputStream(InputStream inputStream) methods in MoraFileAccess class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("READ")
    @Tag("RESOURCE")
    @DisplayName("Test Property File Read getClass().getClassLoader().getResourceAsStream()")
    public void givenPropertyFileFromResource(){
        assertEquals(
                TEST_FILE_LOCATION,
                FILE_ACCESS.getPropertyValueFromThisPropertyFile("config.properties","MORA.filepath"));
    }

    @Test
    @Disabled
    public void whenReadWithStreamTokenizer_thenCorrectTokens()
            throws IOException {
        FileReader reader = new FileReader(TEST_FILE_LOCATION);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        // token 1
        tokenizer.nextToken();
        assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
        assertEquals("hi", tokenizer.sval);

        // token 2
        tokenizer.nextToken();
        assertEquals(StreamTokenizer.TT_NUMBER, tokenizer.ttype);
        assertEquals(1, tokenizer.nval, 0.0000001);

        // token 3
        tokenizer.nextToken();
        assertEquals(StreamTokenizer.TT_EOF, tokenizer.ttype);
        reader.close();
    }

}
/**
 *
 */