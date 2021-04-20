/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/10/2020 1:39 PM
 */
package com.slmora.learn.common.db.connection.type2;

import com.slmora.learn.common.db.connection.type1.DBConnection;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This Class created for testing com.slmora.learn.common.db.connection.type2.DBConnection2
 *
 * @Author: SLMORA
 * @DateTime: 10/10/2020 1:39 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/10/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.db.connection.type2.DBConnection")
public class UnitTestDBConnection2
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestDBConnection2.class);

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
                "(1000, " +
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
        try (Connection connection = DBConnection2.getConnection();
             Statement statement = connection.createStatement()){
            int res = statement.executeUpdate(testQuery);
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
        String testQuery = "SELECT * FROM SB_USER WHERE user_id = 2";
        try (Connection connection = DBConnection2.getConnection();
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
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }
}
