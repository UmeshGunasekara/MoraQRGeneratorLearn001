/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:04 AM
 */
package com.slmora.learn.common.db.connection.pool;

import com.slmora.learn.common.db.connection.type1.DBConnection;
import com.slmora.learn.common.db.connection.type1.UnitTestDBConnection;
//import com.slmora.learn.common.util.HexUtils;
import com.slmora.learn.common.util.UuidUtilities;
//import com.slmora.learn.entity.jdbc.SBUser01;
//import com.slmora.learn.service.jdbc.SBUser01Service01;
//import com.slmora.learn.service.jdbc.impl.SBUser01Service01Impl;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

/**
 * This Class created for testing com.slmora.learn.common.db.connection.pool.DBCPConnectionPool
 *
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:04 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/15/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.learn.common.db.connection.pool.DBCPConnectionPool")
public class UnitTestDBCPConnectionPool
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
    @DisplayName("Test Retrieve Data Using Prepared Statement in Get Connection With Properties")
    public void testConnectionPool(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DBCPConnectionPool DBCPConnectionPool = new DBCPConnectionPool();
        String testQuery = "SELECT * FROM SB_USER WHERE user_id = ?";
        try {
            DataSource dataSource = DBCPConnectionPool.setUpPool();
            DBCPConnectionPool.printDbStatus();

            // Performing Database Operation!
            System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
            connection = dataSource.getConnection();
            DBCPConnectionPool.printDbStatus();

            preparedStatement = connection.prepareStatement(testQuery);
            preparedStatement.setInt(1,2);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
                System.out.println("User Category Id : "+resultSet.getInt("user_category_id"));;
            }
            System.out.println("\n=====Releasing Connection Object To Pool=====\n");
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }finally {
            try {
                // Closing ResultSet Object
                if(resultSet!=null){
                    resultSet.close();
                }
                // Closing PreparedStatement Object
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                // Closing Connection Object
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        }
        DBCPConnectionPool.printDbStatus();
        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End");
    }

    @Test
    @DisplayName("Test Basic Thread")
    public void testThread(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        Runnable runnable1 = () -> {
            for(int i = 0; i < 100; i++){
                System.out.println("Runnable 01 : "+i);
            }
        };
        Runnable runnable2 = () -> {
            for(int i = 0; i < 100; i++){
                System.out.println("Runnable 02 : "+i);
            }
        };
        Runnable runnable3 = () -> {
            for(int i = 0; i < 100; i++){
                System.out.println("Runnable 03 : "+i);
            }
        };
        Runnable runnable4 = () -> {
            for(int i = 0; i < 100; i++){
                System.out.println("Runnable 04 : "+i);
            }
        };
        Runnable runnable5 = () -> {
            for(int i = 0; i < 100; i++){
                System.out.println("Runnable 05 : "+i);
            }
        };
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);
        Thread t4 = new Thread(runnable4);
        Thread t5 = new Thread(runnable5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())) {
            long endTime = System.nanoTime();
            ELAPSED_TIME = endTime - startTime;
            System.out.println("Programme End");
        }
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Basic Thread")
    public void testMultiThreadedUpdateWithNormalConnection(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testUpdateQuery = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = ?," +
                " user_category_code = ?," +
                " raw_last_update_date_time = ?," +
                " raw_last_update_log_id = ?," +
                " update_user_account_id = ?," +
                " raw_show_status = ? " +
                "WHERE user_category_id = ?;";
        Runnable runnable1 = () -> {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 1");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 1 :"+ i);
            } catch (ClassNotFoundException e) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 2");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 2 :"+ i);
            } catch (ClassNotFoundException e) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable3 = () -> {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 3");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 3 :"+ i);
            } catch (ClassNotFoundException e) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable4 = () -> {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 4");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 4 :"+ i);
            } catch (ClassNotFoundException e) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable5 = () -> {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 5");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 5 :"+ i);
            } catch (ClassNotFoundException e) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        try {
            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);
            Thread t3 = new Thread(runnable3);
            Thread t4 = new Thread(runnable4);
            Thread t5 = new Thread(runnable5);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())) {
                long endTime = System.nanoTime();
                ELAPSED_TIME = endTime - startTime;
                System.out.println("Programme End");
            }
        } catch (InterruptedException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Basic Thread")
    public void testMultiThreadedUpdateWithDBCPConnectionPool(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        DBCPConnectionPool DBCPConnectionPool = new DBCPConnectionPool();
        String testUpdateQuery = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = ?," +
                " user_category_code = ?," +
                " raw_last_update_date_time = ?," +
                " raw_last_update_log_id = ?," +
                " update_user_account_id = ?," +
                " raw_show_status = ? " +
                "WHERE user_category_id = ?;";
        try {
            DataSource dataSource = DBCPConnectionPool.setUpPool();
            DBCPConnectionPool.printDbStatus();
            Runnable runnable1 = () -> {
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
                ){
                    DBCPConnectionPool.printDbStatus();
                    preparedStatement.setString(1,"Test 1");
                    preparedStatement.setInt(2,1);
                    preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.setInt(4,1);
                    preparedStatement.setInt(5,1);
                    preparedStatement.setInt(6,1);
                    preparedStatement.setInt(7,3);
                    int i = preparedStatement.executeUpdate();
                    System.out.println("runnable 1 :"+ i);
                } catch (SQLException throwables) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                    throwables.printStackTrace();
                }
            };
            Runnable runnable2 = () -> {
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
                ){
                    DBCPConnectionPool.printDbStatus();
                    preparedStatement.setString(1,"Test 2");
                    preparedStatement.setInt(2,1);
                    preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.setInt(4,1);
                    preparedStatement.setInt(5,1);
                    preparedStatement.setInt(6,1);
                    preparedStatement.setInt(7,3);
                    int i = preparedStatement.executeUpdate();
                    System.out.println("runnable 2 :"+ i);
                } catch (SQLException throwables) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                    throwables.printStackTrace();
                }
            };
            Runnable runnable3 = () -> {
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
                ){
                    DBCPConnectionPool.printDbStatus();
                    preparedStatement.setString(1,"Test 3");
                    preparedStatement.setInt(2,1);
                    preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.setInt(4,1);
                    preparedStatement.setInt(5,1);
                    preparedStatement.setInt(6,1);
                    preparedStatement.setInt(7,3);
                    int i = preparedStatement.executeUpdate();
                    System.out.println("runnable 3 :"+ i);
                } catch (SQLException throwables) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                    throwables.printStackTrace();
                }
            };
            Runnable runnable4 = () -> {
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
                ){
                    DBCPConnectionPool.printDbStatus();
                    preparedStatement.setString(1,"Test 4");
                    preparedStatement.setInt(2,1);
                    preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.setInt(4,1);
                    preparedStatement.setInt(5,1);
                    preparedStatement.setInt(6,1);
                    preparedStatement.setInt(7,3);
                    int i = preparedStatement.executeUpdate();
                    System.out.println("runnable 4 :"+ i);
                } catch (SQLException throwables) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                    throwables.printStackTrace();
                }
            };
            Runnable runnable5 = () -> {
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
                ){
                    DBCPConnectionPool.printDbStatus();
                    preparedStatement.setString(1,"Test 5");
                    preparedStatement.setInt(2,1);
                    preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.setInt(4,1);
                    preparedStatement.setInt(5,1);
                    preparedStatement.setInt(6,1);
                    preparedStatement.setInt(7,3);
                    int i = preparedStatement.executeUpdate();
                    System.out.println("runnable 5 :"+ i);
                } catch (SQLException throwables) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                    throwables.printStackTrace();
                }
            };
            Runnable runnable6 = () -> {
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
                ){
                    DBCPConnectionPool.printDbStatus();
                    preparedStatement.setString(1,"Test 6");
                    preparedStatement.setInt(2,1);
                    preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.setInt(4,1);
                    preparedStatement.setInt(5,1);
                    preparedStatement.setInt(6,1);
                    preparedStatement.setInt(7,3);
                    int i = preparedStatement.executeUpdate();
                    System.out.println("runnable 6 :"+ i);
                } catch (SQLException throwables) {
                    LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                    throwables.printStackTrace();
                }
            };

            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);
            Thread t3 = new Thread(runnable3);
            Thread t4 = new Thread(runnable4);
            Thread t5 = new Thread(runnable5);
            Thread t6 = new Thread(runnable6);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            DBCPConnectionPool.printDbStatus();
            if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())&&(!t6.isAlive())) {
                long endTime = System.nanoTime();
                ELAPSED_TIME = endTime - startTime;
                System.out.println("Programme End");
            }
        } catch (InterruptedException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Basic Thread")
    public void testMultiThreadedUpdateWithApacheCommonsDBCPDataSource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testUpdateQuery = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = ?," +
                " user_category_code = ?," +
                " raw_last_update_date_time = ?," +
                " raw_last_update_log_id = ?," +
                " update_user_account_id = ?," +
                " raw_show_status = ? " +
                "WHERE user_category_id = ?;";
        ApacheCommonsDBCPDataSource.printDbStatus();
        Runnable runnable1 = () -> {
            try (Connection connection = ApacheCommonsDBCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                ApacheCommonsDBCPDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 1");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 1 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try (Connection connection = ApacheCommonsDBCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                ApacheCommonsDBCPDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 2");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 2 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable3 = () -> {
            try (Connection connection = ApacheCommonsDBCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                ApacheCommonsDBCPDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 3");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 3 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable4 = () -> {
            try (Connection connection = ApacheCommonsDBCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                ApacheCommonsDBCPDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 4");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 4 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable5 = () -> {
            try (Connection connection = ApacheCommonsDBCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                ApacheCommonsDBCPDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 5");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 5 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable6 = () -> {
            try (Connection connection = ApacheCommonsDBCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                ApacheCommonsDBCPDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 6");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 6 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        try {
            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);
            Thread t3 = new Thread(runnable3);
            Thread t4 = new Thread(runnable4);
            Thread t5 = new Thread(runnable5);
            Thread t6 = new Thread(runnable6);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())&&(!t6.isAlive())) {
                long endTime = System.nanoTime();
                ELAPSED_TIME = endTime - startTime;
                System.out.println("Programme End");
            }
            ApacheCommonsDBCPDataSource.printDbStatus();
        } catch (InterruptedException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Basic Thread")
    public void testMultiThreadedUpdateWithC3poDataSource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testUpdateQuery = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = ?," +
                " user_category_code = ?," +
                " raw_last_update_date_time = ?," +
                " raw_last_update_log_id = ?," +
                " update_user_account_id = ?," +
                " raw_show_status = ? " +
                "WHERE user_category_id = ?;";
        C3poDataSource.printDbStatus();
        Runnable runnable1 = () -> {
            try (Connection connection = C3poDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                C3poDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 1");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 1 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try (Connection connection = C3poDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                C3poDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 2");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 2 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable3 = () -> {
            try (Connection connection = C3poDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                C3poDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 3");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 3 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable4 = () -> {
            try (Connection connection = C3poDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                C3poDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 4");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 4 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable5 = () -> {
            try (Connection connection = C3poDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                C3poDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 5");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 5 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable6 = () -> {
            try (Connection connection = C3poDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                C3poDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 6");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 6 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        try {
            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);
            Thread t3 = new Thread(runnable3);
            Thread t4 = new Thread(runnable4);
            Thread t5 = new Thread(runnable5);
            Thread t6 = new Thread(runnable6);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())&&(!t6.isAlive())) {
                long endTime = System.nanoTime();
                ELAPSED_TIME = endTime - startTime;
                System.out.println("Programme End");
            }
            C3poDataSource.printDbStatus();
        } catch (InterruptedException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Basic Thread")
    public void testMultiThreadedUpdateWithTomcatDBCPDataSource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testUpdateQuery = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = ?," +
                " user_category_code = ?," +
                " raw_last_update_date_time = ?," +
                " raw_last_update_log_id = ?," +
                " update_user_account_id = ?," +
                " raw_show_status = ? " +
                "WHERE user_category_id = ?;";
        TomcatJDBCDataSource.printDbStatus();
        Runnable runnable1 = () -> {
            try (Connection connection = TomcatJDBCDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                TomcatJDBCDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 1");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 1 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try (Connection connection = TomcatJDBCDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                TomcatJDBCDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 2");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 2 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable3 = () -> {
            try (Connection connection = TomcatJDBCDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                TomcatJDBCDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 3");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 3 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable4 = () -> {
            try (Connection connection = TomcatJDBCDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                TomcatJDBCDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 4");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 4 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable5 = () -> {
            try (Connection connection = TomcatJDBCDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                TomcatJDBCDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 5");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 5 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable6 = () -> {
            try (Connection connection = TomcatJDBCDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                TomcatJDBCDataSource.printDbStatus();
                preparedStatement.setString(1,"Test 6");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 6 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        try {
            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);
            Thread t3 = new Thread(runnable3);
            Thread t4 = new Thread(runnable4);
            Thread t5 = new Thread(runnable5);
            Thread t6 = new Thread(runnable6);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())&&(!t6.isAlive())) {
                long endTime = System.nanoTime();
                ELAPSED_TIME = endTime - startTime;
                System.out.println("Programme End");
            }
            TomcatJDBCDataSource.printDbStatus();
        } catch (InterruptedException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     * */
    @Test
    @Tag("CREATE")
    @Tag("RESOURCE")
    @DisplayName("Test Basic Thread")
    public void testMultiThreadedUpdateWithHikariCPDataSource(){
        System.out.println("Programme Start");
        long startTime = System.nanoTime();
        String testUpdateQuery = "UPDATE SB_USER_CATEGORY " +
                "SET user_category_name = ?," +
                " user_category_code = ?," +
                " raw_last_update_date_time = ?," +
                " raw_last_update_log_id = ?," +
                " update_user_account_id = ?," +
                " raw_show_status = ? " +
                "WHERE user_category_id = ?;";
        Runnable runnable1 = () -> {
            try (Connection connection = HikariCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 1");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 1 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try (Connection connection = HikariCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 2");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 2 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable3 = () -> {
            try (Connection connection = HikariCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 3");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 3 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable4 = () -> {
            try (Connection connection = HikariCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 4");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 4 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable5 = () -> {
            try (Connection connection = HikariCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 5");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 5 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        Runnable runnable6 = () -> {
            try (Connection connection = HikariCPDataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(testUpdateQuery)
            ){
                preparedStatement.setString(1,"Test 6");
                preparedStatement.setInt(2,1);
                preparedStatement.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));
                preparedStatement.setInt(4,1);
                preparedStatement.setInt(5,1);
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,3);
                int i = preparedStatement.executeUpdate();
                System.out.println("runnable 6 :"+ i);
            } catch (SQLException throwables) {
                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
                throwables.printStackTrace();
            }
        };
        try {
            Thread t1 = new Thread(runnable1);
            Thread t2 = new Thread(runnable2);
            Thread t3 = new Thread(runnable3);
            Thread t4 = new Thread(runnable4);
            Thread t5 = new Thread(runnable5);
            Thread t6 = new Thread(runnable6);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            if((!t1.isAlive())&&(!t2.isAlive())&&(!t3.isAlive())&&(!t4.isAlive())&&(!t5.isAlive())&&(!t6.isAlive())) {
                long endTime = System.nanoTime();
                ELAPSED_TIME = endTime - startTime;
                System.out.println("Programme End");
            }
        } catch (InterruptedException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

    }

//    /**
//     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
//     * This compare for expected TEST_OUT_PUT_STRING
//     * */
//    @Test
//    @Tag("CREATE")
//    @Tag("RESOURCE")
//    @DisplayName("Test Basic Thread")
//    public void testHikariCPDataSourceForHibernate(){
//        System.out.println("Programme Start");
//        long startTime = System.nanoTime();
//        UUID retunID = null;
//
//        UuidUtilities utilities = new UuidUtilities();
//        UUID uuid = UUID.randomUUID();
//
//        SBUser01 sbUser01 = SBUser01.of(
//                uuid,
//                uuid.toString(),
//                "Test First Name",
//                "Test Last Name",
//                "Test Full Name",
//                "Test User Name",
//                "Test Email",
//                "Test Address",
//                "Test Country",
//                "Test State",
//                "Test Zip",
//                new Timestamp(new java.util.Date().getTime()),
//                1,
//                1,
//                1,
//                1,
//                1,
//                1,
//                null,
//                null,
//                null);
//
//        String query = "INSERT INTO SB_USER_01 " +
//                "(extra_03, " +
//                "user_01_first_name, " +
//                "user_01_last_name, " +
//                "user_01_full_name, " +
//                "user_01_user_name, " +
//                "user_01_email, " +
//                "user_01_address, " +
//                "user_01_country, " +
//                "user_01_state, " +
//                "user_01_zip, " +
//                "raw_last_update_date_time, " +
//                "raw_last_update_log_id, " +
//                "update_user_account_id, " +
//                "raw_show_status, " +
//                "raw_update_status, " +
//                "raw_delete_status, " +
//                "raw_active_status, " +
//                "extra_01, " +
//                "extra_02, " +
//                "user_01_id)" +
//                "VALUES " +
////                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, UuidToBin(UUID()))";
//                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = HikariCPDataSource.getConnection()){
//            connection.setAutoCommit(false);
//            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
//                preparedStatement.setString(1,sbUser01.getExtra03());
//                preparedStatement.setString(2,sbUser01.getUser01FirstName());
//                preparedStatement.setString(3,sbUser01.getUser01LastName());
//                preparedStatement.setString(4,sbUser01.getUser01FullName());
//                preparedStatement.setString(5,sbUser01.getUser01UserName());
//                preparedStatement.setString(6,sbUser01.getUser01Email());
//                preparedStatement.setString(7,sbUser01.getUser01Address());
//                preparedStatement.setString(8,sbUser01.getUser01Country());
//                preparedStatement.setString(9,sbUser01.getUser01State());
//                preparedStatement.setString(10,sbUser01.getUser01Zip());
//                preparedStatement.setTimestamp(11,sbUser01.getRawLastUpdateDateTime());
//                preparedStatement.setInt(12,sbUser01.getRawLastUpdateLogId());
//                preparedStatement.setInt(13,sbUser01.getUpdateUserAccountId());
//                preparedStatement.setInt(14,sbUser01.getRawShowStatus());
//                preparedStatement.setInt(15,sbUser01.getRawUpdateStatus());
//                preparedStatement.setInt(16,sbUser01.getRawDeleteStatus());
//                preparedStatement.setInt(17,sbUser01.getRawActiveStatus());
//                preparedStatement.setString(18,sbUser01.getExtra01());
//                preparedStatement.setString(19,sbUser01.getExtra02());
////                preparedStatement.setString(20,sbUser01.getUser01Id());
//                preparedStatement.setBytes(20,utilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(sbUser01.getUser01Id()));
//                int i = preparedStatement.executeUpdate();
//                connection.commit();
//                retunID = (i > 0) ? sbUser01.getUser01Id() : retunID;
//            } catch (SQLException throwables) {
//                try{
//                    connection.rollback();
//                } catch (SQLException ex1) {
//                    LOGGER.error(ExceptionUtils.getFullStackTrace(ex1));
//                    ex1.printStackTrace();
//                }
//                LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
//                throwables.printStackTrace();
//            }
//        } catch (SQLException throwables) {
//            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
//            throwables.printStackTrace();
//        }
//        System.out.println("Added User : "+ retunID.toString());
//        long endTime = System.nanoTime();
//        ELAPSED_TIME = endTime - startTime;
//        System.out.println("Programme End");
//
//    }

//    /**
//     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
//     * This compare for expected TEST_OUT_PUT_STRING
//     * */
//    @Test
//    @Tag("CREATE")
//    @Tag("RESOURCE")
//    @DisplayName("Test Basic Thread")
//    public void testInsertDataHibernateWithService(){
//        System.out.println("Programme Start");
//        long startTime = System.nanoTime();
//        UUID retunID = null;
//
//        SBUser01Service01 sbUser01Service01 =  new SBUser01Service01Impl();
//
//        UuidUtilities utilities = new UuidUtilities();
//        UUID uuid = UUID.randomUUID();
//
//        SBUser01 sbUser01 = SBUser01.of(
//                uuid,
//                uuid.toString(),
//                "Test First Name",
//                "Test Last Name",
//                "Test Full Name",
//                "Test User Name",
//                "Test Email",
//                "Test Address",
//                "Test Country",
//                "Test State",
//                "Test Zip",
//                new Timestamp(new java.util.Date().getTime()),
//                1,
//                1,
//                1,
//                1,
//                1,
//                1,
//                null,
//                null,
//                null);
//
//        retunID = sbUser01Service01.addSBUser01(sbUser01);
//
//        System.out.println("Added User : "+ retunID.toString());
//        long endTime = System.nanoTime();
//        ELAPSED_TIME = endTime - startTime;
//        System.out.println("Programme End");
//
//    }

//    /**
//     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
//     * This compare for expected TEST_OUT_PUT_STRING
//     * */
//    @Test
//    @Tag("CREATE")
//    @Tag("RESOURCE")
//    @DisplayName("Test Basic Thread")
//    public void testRetriveDataForHibernateWithService(){
//        System.out.println("Programme Start");
//        long startTime = System.nanoTime();
//        SBUser01 sbUser01 = new SBUser01();
//        SBUser01Service01 sbUser01Service01 =  new SBUser01Service01Impl();
//
//        UuidUtilities utilities = new UuidUtilities();
//
//        sbUser01 = sbUser01Service01.getSBUser01ById(UUID.fromString("388c9b80-ce65-42c5-8137-85f73b5838a3"));
//
//        System.out.println("Added User : "+ sbUser01.getUser01FirstName());
//        long endTime = System.nanoTime();
//        ELAPSED_TIME = endTime - startTime;
//        System.out.println("Programme End");
//
//    }
}
