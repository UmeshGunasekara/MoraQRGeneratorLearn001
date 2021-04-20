/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/8/2020 12:43 AM
 */
package com.slmora.learn.common.db.connection.type1;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.UUID;


/**
 * This Class created for testing com.slmora.learn.common.db.connection.type1.DBConnection
 *
 * @Author: SLMORA
 * @DateTime: 10/8/2020 12:43 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/8/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.db.connection.type1.DBConnection")
public class UnitTestDBConnection
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestDBConnection.class);

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

    }

    /**
     * Runs this method after each test execution
     * */
    @AfterEach
    private void afterEachEnd()
    {
        System.out.println("Test Completed "+testInfo.getDisplayName());
        System.out.println("After Each Clean Test........");
        System.out.println("Elapsed TIme : " + ELAPSED_TIME + " ns");
        LOGGER.info("Elapsed TIme for "+testInfo.getDisplayName()+" : " +ELAPSED_TIME);
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
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties")
    public void testGetConnectionWithPropertiesInsertData(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "INSERT INTO SB_USER " +
                "(user_id," +
                " user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
                "(1, " +
                "'probisys@boswingroup.com', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys Probisys', " +
                "'System', " +
                "'Male', " +
                "'2018-05-20', " +
                "0, " +
                "'2018-05-20 00:00:01', " +
                "'0.0.0.0', " +
                "0, " +
                "'111111111V', " +
                "'Sri Lanka', " +
                "NULL, " +
                "'0768250515', " +
                "'0711233000', " +
                "'0768250515', " +
                "'0768250515', " +
                "1, " +
                "'super', " +
                "1, " +
                "1, " +
                "'This is super user related details', " +
                "'PROBISYS$MORA', " +
                "1, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL, " +
                "1);";
        try (Connection connection = DBConnection.getConnection();
             Statement stm = connection.createStatement()){
            int res = stm.executeUpdate(testQuery);
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties in Mysql Data Source")
    public void testGetConnectionWithPropertiesInsertDataWithMysqlDataSource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        DataSource dataSource = DBConnection.getMysqlDataSource();
        String testQuery = "INSERT INTO SB_USER " +
                "(user_id," +
                " user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
                "(2, " +
                "'probisys@boswingroup.com', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys Probisys', " +
                "'System', " +
                "'Male', " +
                "'2018-05-20', " +
                "0, " +
                "'2018-05-20 00:00:01', " +
                "'0.0.0.0', " +
                "0, " +
                "'111111111V', " +
                "'Sri Lanka', " +
                "NULL, " +
                "'0768250515', " +
                "'0711233000', " +
                "'0768250515', " +
                "'0768250515', " +
                "1, " +
                "'super', " +
                "1, " +
                "1, " +
                "'This is super user related details', " +
                "'PROBISYS$MORA', " +
                "1, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL, " +
                "1);";
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()){
            int res = stm.executeUpdate(testQuery);
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties in Apache Basic Data Source")
    public void testGetConnectionWithPropertiesInsertDataWithApacheBasicDataSource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        DataSource dataSource = DBConnection.getApacheCommonsBasicDataSource();
        String testQuery = "INSERT INTO SB_USER " +
                "(user_id," +
                " user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
                "(3, " +
                "'probisys@boswingroup.com', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys Probisys', " +
                "'System', " +
                "'Male', " +
                "'2018-05-20', " +
                "0, " +
                "'2018-05-20 00:00:01', " +
                "'0.0.0.0', " +
                "0, " +
                "'111111111V', " +
                "'Sri Lanka', " +
                "NULL, " +
                "'0768250515', " +
                "'0711233000', " +
                "'0768250515', " +
                "'0768250515', " +
                "1, " +
                "'super', " +
                "1, " +
                "1, " +
                "'This is super user related details', " +
                "'PROBISYS$MORA', " +
                "1, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL, " +
                "1);";
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()){
            int res = stm.executeUpdate(testQuery);
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Direct Connection")
    public void testDirectConnectionInsertData(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "INSERT INTO SB_USER " +
                "(user_id," +
                " user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
                "(4, " +
                "'probisys@boswingroup.com', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys Probisys', " +
                "'System', " +
                "'Male', " +
                "'2018-05-20', " +
                "0, " +
                "'2018-05-20 00:00:01', " +
                "'0.0.0.0', " +
                "0, " +
                "'111111111V', " +
                "'Sri Lanka', " +
                "NULL, " +
                "'0768250515', " +
                "'0711233000', " +
                "'0768250515', " +
                "'0768250515', " +
                "1, " +
                "'super', " +
                "1, " +
                "1, " +
                "'This is super user related details', " +
                "'PROBISYS$MORA', " +
                "1, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL, " +
                "1);";

//        String cs = "jdbc:mysql://localhost:3307/sample_db_v001?useSSL=false";
        String connectionString = "jdbc:mysql://localhost:3307/sample_db_v001?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String connectionUser = "root";
        String connectionPassword = "root";
        try (Connection connection = DriverManager.getConnection(
                connectionString,
                connectionUser,
                connectionPassword);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(testQuery);
        } catch (SQLException ex) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
            ex.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Inputs")
    public void testGetConnectionWithInputsInsertData(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "INSERT INTO SB_USER " +
                "(user_id," +
                " user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
                "(5, " +
                "'probisys@boswingroup.com', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys Probisys', " +
                "'System', " +
                "'Male', " +
                "'2018-05-20', " +
                "0, " +
                "'2018-05-20 00:00:01', " +
                "'0.0.0.0', " +
                "0, " +
                "'111111111V', " +
                "'Sri Lanka', " +
                "NULL, " +
                "'0768250515', " +
                "'0711233000', " +
                "'0768250515', " +
                "'0768250515', " +
                "1, " +
                "'super', " +
                "1, " +
                "1, " +
                "'This is super user related details', " +
                "'PROBISYS$MORA', " +
                "1, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL, " +
                "1);";
        String connectionString = "jdbc:mysql://localhost:3307/sample_db_v001?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String connectionUser = "root";
        String connectionPassword = "root";
        String connectionClassname = "com.mysql.cj.jdbc.Driver";
        try (Connection connection = DBConnection.getConnection(
                connectionClassname,
                connectionString,
                connectionUser,
                connectionPassword);
             Statement stm = connection.createStatement()){
            int res = stm.executeUpdate(testQuery);
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties")
    public void testGetConnectionWithPropertiesInsertDataWithSetData(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "INSERT INTO SB_USER " +
                "(user_id," +
                " user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
                "(6, " +
                "'probisys@boswingroup.com', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys', " +
                "'Probisys Probisys', " +
                "'System', " +
                "'Male', " +
                "'2018-05-20', " +
                "0, " +
                "'2018-05-20 00:00:01', " +
                "'0.0.0.0', " +
                "0, " +
                "'111111111V', " +
                "'Sri Lanka', " +
                "NULL, " +
                "'0768250515', " +
                "'0711233000', " +
                "'0768250515', " +
                "'0768250515', " +
                "1, " +
                "'super', " +
                "1, " +
                "1, " +
                "'This is super user related details', " +
                "'PROBISYS$MORA', " +
                "1, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL, " +
                "1);";
        try (Connection connection = DBConnection.getConnection()){
            int res = DBHandler.setData(connection,testQuery);
            System.out.println(res);
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Retrieve Data Using Statement in Get Connection With Properties")
    public void testGetConnectionWithPropertiesRetrieveData(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "SELECT * FROM SB_USER WHERE user_id = 1";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(testQuery)){
            while (resultSet.next()){
                System.out.println("User ID : "+resultSet.getInt("user_id"));
                System.out.println("User Email : "+resultSet.getString(2));
                System.out.println("User Avatar Name : "+resultSet.getString("user_avatar_name"));
                System.out.println("User First Name : "+resultSet.getString("user_first_name"));
                System.out.println("User Last Name : "+resultSet.getString("user_last_name"));
                System.out.println("User Full Name : "+resultSet.getString("user_full_name"));
                System.out.println("User Title : "+resultSet.getString("user_title"));
                System.out.println("User Sex : "+resultSet.getString("user_sex"));
                System.out.println("User Email : "+resultSet.getDate("user_birthday"));
                System.out.println("User Rank Point : "+resultSet.getInt(10));
                System.out.println("User Register Date Time : "+resultSet.getString("user_reg_date_time"));
                System.out.println("User Register IP : "+resultSet.getString("user_reg_ip"));
                System.out.println("User Rank : "+resultSet.getString("user_rank"));
                System.out.println("User NIC : "+resultSet.getString("user_nic"));
                System.out.println("User Nationality : "+resultSet.getString("user_nationality"));
                System.out.println("User Passport Number : "+resultSet.getString("user_passport_no"));
                System.out.println("User Contact Number 01 : "+resultSet.getString("user_contact_no_01"));
                System.out.println("User Contact Number 02 : "+resultSet.getString("user_contact_no_02"));
                System.out.println("User Mobile : "+resultSet.getString("user_mobile"));
                System.out.println("User Fax Number : "+resultSet.getString("user_fax_no"));
                System.out.println("User Speciality : "+resultSet.getString("user_speciality"));
                System.out.println("User Flag : "+resultSet.getString("user_flag"));
                System.out.println("User Email Verified : "+resultSet.getString("user_is_email_verified"));
                System.out.println("User Mobile Verified : "+resultSet.getString("user_is_mobile_verified"));
                System.out.println("User Note : "+resultSet.getString("user_note"));
                System.out.println("User Protect Item : "+resultSet.getString("user_protect_item"));
                System.out.println("User Active Status : "+resultSet.getString("user_active_status"));
                System.out.println("Raw Last Update Date Time : "+resultSet.getTimestamp("raw_last_update_date_time"));
                System.out.println("Raw Lat Update Log ID : "+resultSet.getString("raw_last_update_log_id"));
                System.out.println("Raw Update User Account ID : "+resultSet.getString("update_user_account_id"));
                System.out.println("Raw Show Status : "+resultSet.getString("raw_show_status"));
                System.out.println("Raw Update Status : "+resultSet.getString("raw_delete_status"));
                System.out.println("Raw Active Status : "+resultSet.getString("raw_active_status"));
                System.out.println("Extra 01 : "+resultSet.getString("extra_01"));
                System.out.println("Extra 02 : "+resultSet.getString("extra_02"));
                System.out.println("Extra 03 : "+resultSet.getString("extra_03"));
                System.out.println("User Category Id : "+resultSet.getInt("user_category_id"));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Retrieve Data Using Statement in Get Connection With Properties and Get Data")
    public void testGetConnectionWithPropertiesRetrieveDataWithGetData(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "SELECT * FROM SB_USER WHERE user_id = 1";
        try (Connection connection = DBConnection.getConnection();
             ResultSet resultSet = DBHandler.getData(connection,testQuery)){
            while (resultSet.next()){
                System.out.println("User ID : "+resultSet.getInt("user_id"));
                System.out.println("User Email : "+resultSet.getString(2));
                System.out.println("User Avatar Name : "+resultSet.getString("user_avatar_name"));
                System.out.println("User First Name : "+resultSet.getString("user_first_name"));
                System.out.println("User Last Name : "+resultSet.getString("user_last_name"));
                System.out.println("User Full Name : "+resultSet.getString("user_full_name"));
                System.out.println("User Title : "+resultSet.getString("user_title"));
                System.out.println("User Sex : "+resultSet.getString("user_sex"));
                System.out.println("User Email : "+resultSet.getDate("user_birthday"));
                System.out.println("User Rank Point : "+resultSet.getInt(10));
                System.out.println("User Register Date Time : "+resultSet.getString("user_reg_date_time"));
                System.out.println("User Register IP : "+resultSet.getString("user_reg_ip"));
                System.out.println("User Rank : "+resultSet.getString("user_rank"));
                System.out.println("User NIC : "+resultSet.getString("user_nic"));
                System.out.println("User Nationality : "+resultSet.getString("user_nationality"));
                System.out.println("User Passport Number : "+resultSet.getString("user_passport_no"));
                System.out.println("User Contact Number 01 : "+resultSet.getString("user_contact_no_01"));
                System.out.println("User Contact Number 02 : "+resultSet.getString("user_contact_no_02"));
                System.out.println("User Mobile : "+resultSet.getString("user_mobile"));
                System.out.println("User Fax Number : "+resultSet.getString("user_fax_no"));
                System.out.println("User Speciality : "+resultSet.getString("user_speciality"));
                System.out.println("User Flag : "+resultSet.getString("user_flag"));
                System.out.println("User Email Verified : "+resultSet.getString("user_is_email_verified"));
                System.out.println("User Mobile Verified : "+resultSet.getString("user_is_mobile_verified"));
                System.out.println("User Note : "+resultSet.getString("user_note"));
                System.out.println("User Protect Item : "+resultSet.getString("user_protect_item"));
                System.out.println("User Active Status : "+resultSet.getString("user_active_status"));
                System.out.println("Raw Last Update Date Time : "+resultSet.getTimestamp("raw_last_update_date_time"));
                System.out.println("Raw Lat Update Log ID : "+resultSet.getString("raw_last_update_log_id"));
                System.out.println("Raw Update User Account ID : "+resultSet.getString("update_user_account_id"));
                System.out.println("Raw Show Status : "+resultSet.getString("raw_show_status"));
                System.out.println("Raw Update Status : "+resultSet.getString("raw_delete_status"));
                System.out.println("Raw Active Status : "+resultSet.getString("raw_active_status"));
                System.out.println("Extra 01 : "+resultSet.getString("extra_01"));
                System.out.println("Extra 02 : "+resultSet.getString("extra_02"));
                System.out.println("Extra 03 : "+resultSet.getString("extra_03"));
                System.out.println("User Category Id : "+resultSet.getInt("user_category_id"));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Retrieve Data Using Prepared Statement in Get Connection With Properties")
    public void testGetConnectionWithPropertiesRetrieveDataInPreparedStatement(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery = "SELECT * FROM SB_USER WHERE user_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery)
        ){
            preparedStatement.setInt(1,2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("User ID : "+resultSet.getInt("user_id"));
                System.out.println("User Email : "+resultSet.getString(2));
                System.out.println("User Avatar Name : "+resultSet.getString("user_avatar_name"));
                System.out.println("User First Name : "+resultSet.getString("user_first_name"));
                System.out.println("User Last Name : "+resultSet.getString("user_last_name"));
                System.out.println("User Full Name : "+resultSet.getString("user_full_name"));
                System.out.println("User Title : "+resultSet.getString("user_title"));
                System.out.println("User Sex : "+resultSet.getString("user_sex"));
                System.out.println("User Email : "+resultSet.getDate("user_birthday"));
                System.out.println("User Rank Point : "+resultSet.getInt(10));
                System.out.println("User Register Date Time : "+resultSet.getString("user_reg_date_time"));
                System.out.println("User Register IP : "+resultSet.getString("user_reg_ip"));
                System.out.println("User Rank : "+resultSet.getString("user_rank"));
                System.out.println("User NIC : "+resultSet.getString("user_nic"));
                System.out.println("User Nationality : "+resultSet.getString("user_nationality"));
                System.out.println("User Passport Number : "+resultSet.getString("user_passport_no"));
                System.out.println("User Contact Number 01 : "+resultSet.getString("user_contact_no_01"));
                System.out.println("User Contact Number 02 : "+resultSet.getString("user_contact_no_02"));
                System.out.println("User Mobile : "+resultSet.getString("user_mobile"));
                System.out.println("User Fax Number : "+resultSet.getString("user_fax_no"));
                System.out.println("User Speciality : "+resultSet.getString("user_speciality"));
                System.out.println("User Flag : "+resultSet.getString("user_flag"));
                System.out.println("User Email Verified : "+resultSet.getString("user_is_email_verified"));
                System.out.println("User Mobile Verified : "+resultSet.getString("user_is_mobile_verified"));
                System.out.println("User Note : "+resultSet.getString("user_note"));
                System.out.println("User Protect Item : "+resultSet.getString("user_protect_item"));
                System.out.println("User Active Status : "+resultSet.getString("user_active_status"));
                System.out.println("Raw Last Update Date Time : "+resultSet.getTimestamp("raw_last_update_date_time"));
                System.out.println("Raw Lat Update Log ID : "+resultSet.getString("raw_last_update_log_id"));
                System.out.println("Raw Update User Account ID : "+resultSet.getString("update_user_account_id"));
                System.out.println("Raw Show Status : "+resultSet.getString("raw_show_status"));
                System.out.println("Raw Update Status : "+resultSet.getString("raw_delete_status"));
                System.out.println("Raw Active Status : "+resultSet.getString("raw_active_status"));
                System.out.println("Extra 01 : "+resultSet.getString("extra_01"));
                System.out.println("Extra 02 : "+resultSet.getString("extra_02"));
                System.out.println("Extra 03 : "+resultSet.getString("extra_03"));
                System.out.println("User Category Id : "+resultSet.getInt("user_category_id"));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Retrieve Data Using Prepared Statement in Get Connection With Properties with multiple quries")
    public void testMultipleQueryExecution(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testQuery = "SELECT user_id FROM SB_USER WHERE user_id = ?; SELECT user_id FROM SB_USER WHERE user_id = ?;";
        DBConnection.setConnectionPropertyString("allowMultiQueries=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery)
        ){
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,2);

            boolean isResult = preparedStatement.execute();
            do {
                try (ResultSet resultSet = preparedStatement.getResultSet()){
                    while (resultSet.next()) {
                        System.out.println("User ID : " + resultSet.getInt("user_id"));
//                System.out.println("User Email : "+resultSet.getString(2));
//                System.out.println("User Avatar Name : "+resultSet.getString("user_avatar_name"));
//                System.out.println("User First Name : "+resultSet.getString("user_first_name"));
//                System.out.println("User Last Name : "+resultSet.getString("user_last_name"));
//                System.out.println("User Full Name : "+resultSet.getString("user_full_name"));
//                System.out.println("User Title : "+resultSet.getString("user_title"));
//                System.out.println("User Sex : "+resultSet.getString("user_sex"));
//                System.out.println("User Email : "+resultSet.getDate("user_birthday"));
//                System.out.println("User Rank Point : "+resultSet.getInt(10));
//                System.out.println("User Register Date Time : "+resultSet.getString("user_reg_date_time"));
//                System.out.println("User Register IP : "+resultSet.getString("user_reg_ip"));
//                System.out.println("User Rank : "+resultSet.getString("user_rank"));
//                System.out.println("User NIC : "+resultSet.getString("user_nic"));
//                System.out.println("User Nationality : "+resultSet.getString("user_nationality"));
//                System.out.println("User Passport Number : "+resultSet.getString("user_passport_no"));
//                System.out.println("User Contact Number 01 : "+resultSet.getString("user_contact_no_01"));
//                System.out.println("User Contact Number 02 : "+resultSet.getString("user_contact_no_02"));
//                System.out.println("User Mobile : "+resultSet.getString("user_mobile"));
//                System.out.println("User Fax Number : "+resultSet.getString("user_fax_no"));
//                System.out.println("User Speciality : "+resultSet.getString("user_speciality"));
//                System.out.println("User Flag : "+resultSet.getString("user_flag"));
//                System.out.println("User Email Verified : "+resultSet.getString("user_is_email_verified"));
//                System.out.println("User Mobile Verified : "+resultSet.getString("user_is_mobile_verified"));
//                System.out.println("User Note : "+resultSet.getString("user_note"));
//                System.out.println("User Protect Item : "+resultSet.getString("user_protect_item"));
//                System.out.println("User Active Status : "+resultSet.getString("user_active_status"));
//                System.out.println("Raw Last Update Date Time : "+resultSet.getTimestamp("raw_last_update_date_time"));
//                System.out.println("Raw Lat Update Log ID : "+resultSet.getString("raw_last_update_log_id"));
//                System.out.println("Raw Update User Account ID : "+resultSet.getString("update_user_account_id"));
//                System.out.println("Raw Show Status : "+resultSet.getString("raw_show_status"));
//                System.out.println("Raw Update Status : "+resultSet.getString("raw_delete_status"));
//                System.out.println("Raw Active Status : "+resultSet.getString("raw_active_status"));
//                System.out.println("Extra 01 : "+resultSet.getString("extra_01"));
//                System.out.println("Extra 02 : "+resultSet.getString("extra_02"));
//                System.out.println("Extra 03 : "+resultSet.getString("extra_03"));
//                System.out.println("User Category Id : "+resultSet.getInt("user_category_id"));
                    }
                    isResult = preparedStatement.getMoreResults();
                }
            }while (isResult);
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Column Headers")
    public void testColumnHeaders(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery = "SELECT * FROM SB_USER WHERE user_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery)
        ){
            preparedStatement.setInt(1,2);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                System.out.println(metaData.getColumnName(1)+" : "+resultSet.getInt("user_id"));
                System.out.println(metaData.getColumnName(2)+" : "+resultSet.getString(2));
                System.out.println(metaData.getColumnName(3)+" : "+resultSet.getString("user_avatar_name"));
                System.out.println(metaData.getColumnName(4)+" : "+resultSet.getString("user_first_name"));
                System.out.println(metaData.getColumnName(5)+" : "+resultSet.getString("user_last_name"));
                System.out.println(metaData.getColumnName(6)+" : "+resultSet.getString("user_full_name"));
                System.out.println(metaData.getColumnName(7)+" : "+resultSet.getString("user_title"));
                System.out.println(metaData.getColumnName(8)+" : "+resultSet.getString("user_sex"));
                System.out.println("User Email : "+resultSet.getDate("user_birthday"));
                System.out.println("User Rank Point : "+resultSet.getInt(10));
                System.out.println("User Register Date Time : "+resultSet.getString("user_reg_date_time"));
                System.out.println("User Register IP : "+resultSet.getString("user_reg_ip"));
                System.out.println("User Rank : "+resultSet.getString("user_rank"));
                System.out.println("User NIC : "+resultSet.getString("user_nic"));
                System.out.println("User Nationality : "+resultSet.getString("user_nationality"));
                System.out.println("User Passport Number : "+resultSet.getString("user_passport_no"));
                System.out.println("User Contact Number 01 : "+resultSet.getString("user_contact_no_01"));
                System.out.println("User Contact Number 02 : "+resultSet.getString("user_contact_no_02"));
                System.out.println("User Mobile : "+resultSet.getString("user_mobile"));
                System.out.println("User Fax Number : "+resultSet.getString("user_fax_no"));
                System.out.println("User Speciality : "+resultSet.getString("user_speciality"));
                System.out.println("User Flag : "+resultSet.getString("user_flag"));
                System.out.println("User Email Verified : "+resultSet.getString("user_is_email_verified"));
                System.out.println("User Mobile Verified : "+resultSet.getString("user_is_mobile_verified"));
                System.out.println("User Note : "+resultSet.getString("user_note"));
                System.out.println("User Protect Item : "+resultSet.getString("user_protect_item"));
                System.out.println("User Active Status : "+resultSet.getString("user_active_status"));
                System.out.println("Raw Last Update Date Time : "+resultSet.getTimestamp("raw_last_update_date_time"));
                System.out.println("Raw Lat Update Log ID : "+resultSet.getString("raw_last_update_log_id"));
                System.out.println("Raw Update User Account ID : "+resultSet.getString("update_user_account_id"));
                System.out.println("Raw Show Status : "+resultSet.getString("raw_show_status"));
                System.out.println("Raw Update Status : "+resultSet.getString("raw_delete_status"));
                System.out.println("Raw Active Status : "+resultSet.getString("raw_active_status"));
                System.out.println("Extra 01 : "+resultSet.getString("extra_01"));
                System.out.println("Extra 02 : "+resultSet.getString("extra_02"));
                System.out.println("Extra 03 : "+resultSet.getString("extra_03"));
                System.out.println("User Category Id : "+resultSet.getInt("user_category_id"));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Get Auto Generated Keys")
    public void testGetAutoGeneratedKeys(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery = "INSERT INTO SB_USER " +
//                "(user_id," +
                " (user_email," +
                " user_avatar_name," +
                " user_first_name," +
                " user_last_name," +
                " user_full_name," +
                " user_title," +
                " user_sex," +
                " user_birthday," +
                " user_rank_point," +
                " user_reg_date_time," +
                " user_reg_ip," +
                " user_rank," +
                " user_nic," +
                " user_nationality," +
                " user_passport_no," +
                " user_contact_no_01," +
                " user_contact_no_02," +
                " user_mobile," +
                " user_fax_no," +
                " user_speciality," +
                " user_flag," +
                " user_is_email_verified," +
                " user_is_mobile_verified," +
                " user_note," +
                " user_protect_item," +
                " user_active_status," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " user_category_id)" +
                "VALUES " +
//                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?);";
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery,Statement.RETURN_GENERATED_KEYS)){
//            preparedStatement.setInt(1,6);
            preparedStatement.setString(1,"probisys@boswingroup.com");
            preparedStatement.setString(2,"Probisys");
            preparedStatement.setString(3,"Probisys");
            preparedStatement.setString(4,"Probisys");
            preparedStatement.setString(5,"Probisys Probisys");
            preparedStatement.setString(6,"System");
            preparedStatement.setString(7,"Male");
            preparedStatement.setDate(8,Date.valueOf("2018-05-20"));
            preparedStatement.setInt(9,0);
            preparedStatement.setTimestamp(10,Timestamp.valueOf("2018-05-20 00:00:01"));
            preparedStatement.setString(11,"0.0.0.0");
            preparedStatement.setInt(12,0);
            preparedStatement.setString(13,"111111111V");
            preparedStatement.setString(14,"Sri Lanka");
            preparedStatement.setString(15,null);
            preparedStatement.setString(16,"0768250515");
            preparedStatement.setString(17,"0711233000");
            preparedStatement.setString(18,"0768250515");
            preparedStatement.setString(19,"0768250515");
            preparedStatement.setInt(20,1);
            preparedStatement.setString(21,"super");
            preparedStatement.setInt(22,1);
            preparedStatement.setInt(23,1);
            preparedStatement.setString(24,"This is super user related details");
            preparedStatement.setString(25,"PROBISYS$MORA");
            preparedStatement.setInt(26,1);
            preparedStatement.setTimestamp(27,Timestamp.valueOf("2018-05-20 00:00:01"));
            preparedStatement.setInt(28,1);
            preparedStatement.setInt(29,1);
            preparedStatement.setInt(30,1);
            preparedStatement.setInt(31,1);
            preparedStatement.setInt(32,1);
            preparedStatement.setInt(33,1);
            preparedStatement.setString(34,null);
            preparedStatement.setString(35,null);
            preparedStatement.setString(36,null);
            preparedStatement.setInt(37,1);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.first()){
//                System.out.println("The ID of new user: "+ resultSet.getLong(1));
//            }
            while (resultSet.next()){
//                System.out.printf("The ID of new user: %d", resultSet.getLong(1));
                System.out.println("The ID of new user: "+ resultSet.getLong(1));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Get Auto Generated Keys")
    public void testInsertImageFromResource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ClassLoader classLoader = getClass().getClassLoader();
        File imageFile = new File(classLoader.getResource("ClipartKey.png").getFile());
        System.out.println("File Length : "+imageFile.length());
        ResultSet resultSet = null;
        String testQuery = "INSERT INTO SB_IMAGE " +
//                "(image_id," +
                "(image_note," +
                " image_salt," +
                " image_salt_code," +
                " image_is_salted," +
                " image_description," +
                " image_name," +
                " image_path," +
                " image_blob," +
                " image_code," +
                " image_height," +
                " image_width," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " image_cat_id," +
                " image_type_id)" +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery,Statement.RETURN_GENERATED_KEYS);
             InputStream inputStream = new FileInputStream(imageFile)){
//            preparedStatement.setInt(1,6);
            preparedStatement.setString(1,null);
            preparedStatement.setString(2,"PROBISYS$MORA");
            preparedStatement.setInt(3,1000);
            preparedStatement.setInt(4,0);
            preparedStatement.setString(5,"Sample Image for Testing");
            preparedStatement.setString(6,"my_pof");
            preparedStatement.setString(7,"D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJDBCLearn001\\src\\main\\resources\\ClipartKey.png");
            preparedStatement.setBinaryStream(8, inputStream, imageFile.length());
            preparedStatement.setInt(9,1);
            preparedStatement.setInt(10,200);
            preparedStatement.setInt(11,100);
            preparedStatement.setTimestamp(12,new Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(13,1);
            preparedStatement.setInt(14,1);
            preparedStatement.setInt(15,1);
            preparedStatement.setInt(16,1);
            preparedStatement.setInt(17,1);
            preparedStatement.setInt(18,1);
            preparedStatement.setString(19,null);
            preparedStatement.setString(20,null);
            preparedStatement.setString(21,null);
            preparedStatement.setInt(22,1);
            preparedStatement.setInt(23,1);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                System.out.println("The ID of new image: "+ resultSet.getLong(1));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Get Auto Generated Keys")
    public void testInsertImageFromPath(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ClassLoader classLoader = getClass().getClassLoader();
        String imageFilePath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJDBCLearn001\\image\\ClipartKey.png";
        File imageFile = new File(imageFilePath);
        System.out.println("File Length : "+imageFile.length());
        ResultSet resultSet = null;
        String testQuery = "INSERT INTO SB_IMAGE " +
//                "(image_id," +
                "(image_note," +
                " image_salt," +
                " image_salt_code," +
                " image_is_salted," +
                " image_description," +
                " image_name," +
                " image_path," +
                " image_blob," +
                " image_code," +
                " image_height," +
                " image_width," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03," +
                " image_cat_id," +
                " image_type_id)" +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery,Statement.RETURN_GENERATED_KEYS);
             InputStream inputStream = new FileInputStream(imageFile)){
//            preparedStatement.setInt(1,6);
            preparedStatement.setString(1,null);
            preparedStatement.setString(2,"PROBISYS$MORA");
            preparedStatement.setInt(3,1000);
            preparedStatement.setInt(4,0);
            preparedStatement.setString(5,"Sample Image for Testing");
            preparedStatement.setString(6,"my_pof");
            preparedStatement.setString(7,imageFilePath);
            preparedStatement.setBinaryStream(8, inputStream, imageFile.length());
//            preparedStatement.setBinaryStream(8, inputStream, inputStream.available());
            preparedStatement.setInt(9,1);
            preparedStatement.setInt(10,200);
            preparedStatement.setInt(11,100);
            preparedStatement.setTimestamp(12,new Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(13,1);
            preparedStatement.setInt(14,1);
            preparedStatement.setInt(15,1);
            preparedStatement.setInt(16,1);
            preparedStatement.setInt(17,1);
            preparedStatement.setInt(18,1);
            preparedStatement.setString(19,null);
            preparedStatement.setString(20,null);
            preparedStatement.setString(21,null);
            preparedStatement.setInt(22,1);
            preparedStatement.setInt(23,1);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                System.out.println("The ID of new image: "+ resultSet.getLong(1));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Column Headers")
    public void testRetrieveImage(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String outImageFolder = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraJDBCLearn001\\image\\";
        ResultSet resultSet = null;
        String testQuery = "SELECT * FROM SB_Image WHERE image_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(testQuery)
        ){
            preparedStatement.setInt(1,2);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                String outImagePath = outImageFolder+resultSet.getString("image_name")+resultSet.getInt("image_id")+".png";
                Blob imageBlob = resultSet.getBlob("image_blob");
                try (OutputStream outputStream = new FileOutputStream(outImagePath)){
                    outputStream.write(imageBlob.getBytes(1, (int) imageBlob.length()),0,(int) imageBlob.length());
                } catch (FileNotFoundException e) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                    e.printStackTrace();
                } catch (IOException e) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties")
    public void testTransactionSupport(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testInsertQuery = "INSERT INTO SB_USER_CATEGORY " +
                "(user_category_name," +
                " user_category_code," +
                " user_category_is_default," +
                " user_category_note," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03)" +
                "VALUES " +
                "('Test Categoty', " +
                "1, " +
                "0, " +
                "null, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL);";
        String testUpdateQuery1 = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = 'Test Category 4'," +
                " user_category_code = 1," +
                " user_category_is_default = 1," +
                " raw_last_update_date_time = '2020-05-20 00:00:01'," +
                " raw_last_update_log_id = 2," +
                " update_user_account_id = 2," +
                " raw_show_status = 0 " +
                "WHERE user_category_id = 1;";
        String testUpdateQuery2 = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = 'Test Category 5'," +
                " user_category_code = 3," +
                " raw_last_update_date_time = '2019-05-20 00:00:01'," +
                " raw_last_update_log_id = 2," +
                " update_user_account_id = 2," +
                " raw_show_status = 0 " +
                "WHERE user_category_id = 3;";
        String testDeleteQuery = "DELETE FROM SB_USER_CATEGORY WHERE user_category_id = 4;";
        try (Connection connection = DBConnection.getConnection()){
            connection.setAutoCommit(false);
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(testInsertQuery);
                statement.executeUpdate(testUpdateQuery1);
                statement.executeUpdate(testUpdateQuery2);
                statement.executeUpdate(testDeleteQuery);

//                try{
//                    connection.rollback();
//                } catch (SQLException ex1) {
//                    LOGGER.error(ExceptionUtils.getFullStackTrace(ex1));
//                    ex1.printStackTrace();
//                }

                connection.commit();

            } catch (SQLException ex) {
                try{
                    connection.rollback();
                } catch (SQLException ex1) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(ex1));
                    ex1.printStackTrace();
                }
                LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties")
    public void testBatchUpdate(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testInsertQuery = "INSERT INTO SB_USER_CATEGORY " +
                "(user_category_name," +
                " user_category_code," +
                " user_category_is_default," +
                " user_category_note," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status," +
                " extra_01," +
                " extra_02," +
                " extra_03)" +
                "VALUES " +
                "('Test Categoty', " +
                "1, " +
                "0, " +
                "null, " +
                "'2018-05-20 00:00:01', " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "1, " +
                "NULL, " +
                "NULL, " +
                "NULL);";
        String testUpdateQuery1 = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = 'Test Category 4'," +
                " user_category_code = 1," +
                " user_category_is_default = 1," +
                " raw_last_update_date_time = '2020-05-20 00:00:01'," +
                " raw_last_update_log_id = 2," +
                " update_user_account_id = 2," +
                " raw_show_status = 0 " +
                "WHERE user_category_id = 1;";
        String testUpdateQuery2 = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = 'Test Category 5'," +
                " user_category_code = 3," +
                " raw_last_update_date_time = '2019-05-20 00:00:01'," +
                " raw_last_update_log_id = 2," +
                " update_user_account_id = 2," +
                " raw_show_status = 0 " +
                "WHERE user_category_id = 3;";
        String testDeleteQuery = "DELETE FROM SB_USER_CATEGORY WHERE user_category_id = 4;";
        try (Connection connection = DBConnection.getConnection()){
            connection.setAutoCommit(false);
            try(Statement statement = connection.createStatement()){
                statement.addBatch(testInsertQuery);
                statement.addBatch(testUpdateQuery1);
                statement.addBatch(testUpdateQuery2);
                statement.addBatch(testDeleteQuery);

                int results[] = statement.executeBatch();

                connection.commit();

                Arrays.stream(results).forEach(System.out::println);

            } catch (SQLException ex) {
                try{
                    connection.rollback();
                } catch (SQLException ex1) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(ex1));
                    ex1.printStackTrace();
                }
                LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Insert Data Using Statement in Get Connection With Properties")
    public void testBatchUpdateWithPreparedStatement(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String [] userCategories = {"Test1", "Test2", "Test3"};
        int [] userCategoryCodes = {100, 200, 300};
        String testInsertQuery = "INSERT INTO SB_USER_CATEGORY " +
                "(user_category_name," +
                " user_category_code," +
                " user_category_is_default," +
                " raw_last_update_date_time," +
                " raw_last_update_log_id," +
                " update_user_account_id," +
                " raw_show_status," +
                " raw_update_status," +
                " raw_delete_status," +
                " raw_active_status)" +
                "VALUES " +
                "(?, ?, 1, ?, 1, 1, 1, 1, 1, 1);";
        try (Connection connection = DBConnection.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(testInsertQuery)){
                for(int i= 0; i < userCategories.length; i++){
                    preparedStatement.setString(1, userCategories[i]);
                    preparedStatement.setInt(2, userCategoryCodes[i]);
                    preparedStatement.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.addBatch();
                }
                int results[] = preparedStatement.executeBatch();

                connection.commit();

                Arrays.stream(results).forEach(System.out::println);

            } catch (SQLException ex) {
                try{
                    connection.rollback();
                } catch (SQLException ex1) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(ex1));
                    ex1.printStackTrace();
                }
                LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Column Headers")
    public void testDatabaseHeaders(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        try (Connection connection = DBConnection.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Driver Name: "+metaData.getDriverName());
            System.out.println("Driver Version: "+metaData.getDriverVersion());
            System.out.println("UserName: "+metaData.getUserName());
            System.out.println("Database Product Name: "+metaData.getDatabaseProductName());
            System.out.println("Database Product Version: "+metaData.getDatabaseProductVersion());

            String tableTypes[]={"TABLE", "VIEW"};
            resultSet = metaData.getTables(null, null, null, tableTypes);
            while (resultSet.next()){
                System.out.println(resultSet.getString(3));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Column Headers")
    public void testCallStoredProcedure(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery = "CALL add_user_category(?, ?)";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(testQuery)
        ){
            callableStatement.setString(1,"Test Call Mora");
            callableStatement.setInt(2,1);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                System.out.println("Inserted ID: "+resultSet.getInt(1));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Column Headers")
    public void testCallFunction(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery = "SELECT getUnixTimestampInMilliseconds()";
        String testQuery2 = "? = CALL get_ordered_uuid(?)";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
        ){
            resultSet = statement.executeQuery(testQuery);
            while (resultSet.next()){
                System.out.println("Time Stamp: "+resultSet.getBigDecimal(1));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Column Headers")
    public void testCallFunction2(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        String testQuery = "SELECT getUnixTimestampInMilliseconds()";
        String testQuery2 = "SELECT get_ordered_uuid(?)";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(testQuery2)
        ){
            UUID uuid = UUID.randomUUID();
            callableStatement.setString(1,uuid.toString());
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                System.out.println("Ordered UUID: "+resultSet.getString(1));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
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
