/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:46 AM
 */
package com.slmora.learn.common.db.connection.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.slmora.learn.common.util.MoraAccessProperties;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:46 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/15/2020      SLMORA                Initial Code
 */
public class C3poDataSource
{
    final static Logger LOGGER = LogManager.getLogger(C3poDataSource.class);

    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;

    private static ComboPooledDataSource COMBO_POOLED_DATA_SOURCE = new ComboPooledDataSource();

    private C3poDataSource(){ }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    private static void setAttribute(){
        Properties properties = new MoraAccessProperties().getAllPropertiesFromResource("datasource.properties");
        if(CONNECTION_HOST == null || CONNECTION_HOST.isBlank()){
            CONNECTION_HOST = properties.getProperty("MORA.DATASOURCE.HOST");
        }
        if(CONNECTION_PORT == null || CONNECTION_PORT.isBlank()){
            CONNECTION_PORT = properties.getProperty("MORA.DATASOURCE.PORT");
        }
        if(CONNECTION_DATABASE == null || CONNECTION_DATABASE.isBlank()){
            CONNECTION_DATABASE = properties.getProperty("MORA.DATASOURCE.DATABASE");
        }
        if(CONNECTION_USER == null || CONNECTION_USER.isBlank()){
            CONNECTION_USER = properties.getProperty("MORA.DATASOURCE.USER");
        }
        if(CONNECTION_PASSWORD == null || CONNECTION_PASSWORD.isBlank()){
            CONNECTION_PASSWORD = properties.getProperty("MORA.DATASOURCE.PASSWORD");
        }
        if(CONNECTION_CLASS_NAME == null || CONNECTION_CLASS_NAME.isBlank()){
            CONNECTION_CLASS_NAME = properties.getProperty("MORA.DATASOURCE.CLASS");
        }
        if(CONNECTION_PROPERTY_STRING == null || CONNECTION_PROPERTY_STRING.isBlank()){
            CONNECTION_PROPERTY_STRING = properties.getProperty("MORA.DATASOURCE.PROPERTY_STRING");
        }
    }

    /**
     * create full connection URL using assigned attributes
     *
     * @return String Object will return with full URl
     * @apiNote Rcreate full connection URL using assigned attributes
     * @Note If CONNECTION_PORT is null set default port
     */
    private static String getFullConnectionURL(){
        if(null == CONNECTION_PORT){
            return "jdbc:mysql://"+ CONNECTION_HOST +":3306/"+CONNECTION_DATABASE+"?"+CONNECTION_PROPERTY_STRING;
        }else{
            return "jdbc:mysql://"+ CONNECTION_HOST +":"+CONNECTION_PORT+"/"+CONNECTION_DATABASE+"?"+CONNECTION_PROPERTY_STRING;
        }
    }

    /**
     * Assign property values from datasource.properties and create JDBC connection in class initialization
     *
     * @Note don't want include this key word with constant because of they are static
     */
    static{
        setAttribute();
        try {
            COMBO_POOLED_DATA_SOURCE.setDriverClass(CONNECTION_CLASS_NAME);
            COMBO_POOLED_DATA_SOURCE.setJdbcUrl(getFullConnectionURL());
            COMBO_POOLED_DATA_SOURCE.setUser(CONNECTION_USER);
            COMBO_POOLED_DATA_SOURCE.setPassword(CONNECTION_PASSWORD);
        } catch (PropertyVetoException ex) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
            ex.printStackTrace();
        }

    }

    /**
     * Create JDBC connection object with assigned values
     *
     * @return Connection Object will return with assigned values in datasource.properties
     * @throws ClassNotFoundException,SQLException with JDBC class notfound or SQL connection issue
     * @apiNote Read assigned connection values and create a Connection Object
     * @Note The getConnection has overloaded with
     * getConnection(),
     * getConnection(String className,String uRL,String userName,String password)
     * and these methods are static so will work as singleton
     */
    public synchronized static Connection getConnection() throws SQLException
    {
        return COMBO_POOLED_DATA_SOURCE.getConnection();
    }

    public static void printDbStatus() {
        try {
            System.out.println("Max.: " + COMBO_POOLED_DATA_SOURCE.getNumConnections() + "; Active: " + COMBO_POOLED_DATA_SOURCE.getNumBusyConnections() + "; Idle: " + COMBO_POOLED_DATA_SOURCE.getNumIdleConnections());
        } catch (SQLException throwables) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwables));
            throwables.printStackTrace();
        }
    }
}
