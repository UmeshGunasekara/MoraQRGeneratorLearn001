/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/26/2020 4:57 PM
 */
package com.slmora.learn.common.util;

import com.slmora.learn.common.db.connection.pool.HikariCPDataSource;
import com.slmora.learn.common.db.connection.type1.DBConnection;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This Class created for unit testing com.slmora.learn.common.util.UuidUtilities
 *
 * @Author: SLMORA
 * @DateTime: 9/26/2020 4:57 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/26/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.util.UuidUtilities")
public class UnitTestUuidUtilities
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestUuidUtilities.class);

    UuidUtilities utilities;

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

        utilities = new UuidUtilities();

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
     * This method runs printUUIDDetails(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test properties of randomly generated UUID from UUID.randomUUID()")
    public void analyzeRandomUUID(){
        UUID uuid = UUID.randomUUID();
        utilities.printUUIDDetails(uuid);
    }

    /**
     * This nested operations will test UUID constructor with different scenarios
     * */
    @Nested
    @DisplayName("Test UUID Constructor")
    class TestUUIDConstructor{

        /**
         * This method runs printUUIDDetails(UUID uuid) methods in UuidUtilities class
         * This will analyze the generated UUID from UUID constructor with 2 primitive values
         * */
        @Test
        @Tag("ANALYSE")
        @Tag("PRINT")
        @DisplayName("Test properties of randomly generated UUID from UUID(2,3)")
        public void analyzeUUIDConstructorForPremitives(){
            UUID uuid = new UUID(2,3);
            utilities.printUUIDDetails(uuid);
        }

        /**
         * This method runs printUUIDDetails(UUID uuid) methods in UuidUtilities class
         * This will analyze the generated UUID from UUID constructor with zero values
         * */
        @Test
        @Tag("ANALYSE")
        @Tag("PRINT")
        @DisplayName("Test properties of randomly generated UUID from UUID(0,0)")
        public void analyzeUUIDConstructorForZeros(){
            UUID uuid = new UUID(0,0);
            utilities.printUUIDDetails(uuid);
        }

        /**
         * This method runs printUUIDDetails(UUID uuid) methods in UuidUtilities class
         * This will analyze the generated UUID from UUID constructor with generated long
         * */
        @Test
        @Tag("ANALYSE")
        @Tag("PRINT")
        @DisplayName("Test properties of randomly generated UUID from UUID()")
        public void analyzeUUIDConstructor(){
            long msb = System.currentTimeMillis();
            long lsb = System.currentTimeMillis();
            UUID uuid = new UUID(msb,lsb);
            utilities.printUUIDDetails(uuid);
        }
    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testRemoveHyphens(){
        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String outString = utilities.removeHyphens(uuid);
        assertEquals("123e4567e89b12d3a456426614174000",outString);
    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testOrderedUUIDString(){
        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String outString = utilities.getOrderedUUIDString(uuid);
        assertEquals("12d3e89b123e4567a456426614174000",outString);
    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testByteArrangements(){
        //https://mkyong.com/java/how-do-convert-byte-array-to-string-in-java/
        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String outString = utilities.getOrderedUUIDString(uuid);
        System.out.println(outString);
        byte [] byteUUID1 = outString.getBytes();
        byte [] byteUUID2 = Base64.getDecoder().decode(outString);
//        for (byte b : byteUUID1) {
//            System.out.print(b);
//            System.out.print(" | ");
//        }
//        System.out.println();
        System.out.println("byteUUID1 : "+Arrays.toString(byteUUID1));
        System.out.println("byteUUID2 : "+Arrays.toString(byteUUID2));
        String byteUUID1StringFromCharset = new String(byteUUID1, StandardCharsets.UTF_8);
        System.out.println("byteUUID1StringFromCharset : "+byteUUID1StringFromCharset);
        String byteUUID2StringFromBase64 = Base64.getEncoder().encodeToString(byteUUID2);
        System.out.println("byteUUID1StringFromBase64 : "+byteUUID2StringFromBase64);
        String byteUUID2StringFromCharset = new String(byteUUID2, StandardCharsets.UTF_8);
        System.out.println("byteUUID2StringFromCharset : "+byteUUID2StringFromCharset);
        String byteUUID1StringFromBase64 = Base64.getEncoder().encodeToString(byteUUID1);
        System.out.println("byteUUID1StringFromBase64 : "+byteUUID1StringFromBase64);
    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testMySQLHexUnHex(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery2 = "SELECT HEX(?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(testQuery2)
        ){
            UUID uuid = UUID.randomUUID();
            callableStatement.setString(1,"1");
//            callableStatement.setString(1,"123e4567-e89b-12d3-a456-426614174000");
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                String res = resultSet.getString(1);
                System.out.println("Hex String "+res);
            }
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }finally {
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");

    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testHexConversion(){
        String s = "ffffffffffffffffffffffffffffffff";
        byte [] sb =s.getBytes();

        char[] result1 = Hex.encodeHex(sb);

        System.out.println(result1);

        try {
            byte[] s1 = Hex.decodeHex(s);
            String ss1 = new String(s1, StandardCharsets.UTF_8);
            System.out.println(ss1);
            char[] s2 = Hex.encodeHex(s1);
            System.out.println(s2);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testHexUUIDForApacheCommons(){
        HexUtils hexUtils = new HexUtils();
        UuidUtilities utilities = new UuidUtilities();
        UUID uuid = UUID.randomUUID();

        String userId = hexUtils.convertStringToUnHexWithApacheCommons(utilities.getOrderedUUIDString(uuid));

        System.out.println("UUID : "+uuid.toString());
        System.out.println("Ordered UUID String : "+utilities.getOrderedUUIDString(uuid));
        System.out.println("Apache Commons Generated UNHEX String : "+userId);
        System.out.println("Length of Apache Commons Generated UNHEX String : "+userId.length());
    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testMySQLUuidToBin(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery2 = "SELECT UuidToBin(?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(testQuery2)
        ){
            UUID uuid = UUID.randomUUID();
            callableStatement.setString(1,uuid.toString());
//            callableStatement.setString(1,"123e4567-e89b-12d3-a456-426614174000");
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();
            System.out.println("uuid : "+uuid.toString());
            while (resultSet.next()){
                String res = resultSet.getString(1);
                System.out.println("UNHEX String : "+res);
                System.out.println("Length of UNHEX String : "+res.length());
            }
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }finally {
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");

    }

    /**
     * This method runs removeHyphens(UUID uuid) methods in UuidUtilities class
     * This will analyze the generated UUID from UUID.randomUUID()
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test removeHyphens(UUID uuid) with 123e4567-e89b-12d3-a456-426614174000")
    public void testMySQLUUID_TO_BIN(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery2 = "SELECT UUID_TO_BIN(?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(testQuery2)
        ){
            UUID uuid = UUID.randomUUID();
            callableStatement.setString(1,uuid.toString());
//            callableStatement.setString(1,"123e4567-e89b-12d3-a456-426614174000");
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();
            System.out.println("uuid : "+uuid.toString());
            while (resultSet.next()){
                String res = resultSet.getString(1);
                System.out.println("UNHEX String : "+res);
                System.out.println("Length of UNHEX String : "+res.length());
            }
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }finally {
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");

    }
}
