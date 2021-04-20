/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/15/2020 9:06 PM
 */
package com.slmora.learn.common.db.connection.pool;

import com.slmora.learn.common.util.MoraAccessProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.vibur.dbcp.ViburDBCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/15/2020 9:06 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/15/2020      SLMORA                Initial Code
 */
public class ViburDataSource
{
    final static Logger LOGGER = LogManager.getLogger(ViburDataSource.class);

    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;

    private static ViburDBCPDataSource DATA_SOURCE = new ViburDBCPDataSource();

    private ViburDataSource(){ }

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
        DATA_SOURCE.setJdbcUrl(getFullConnectionURL());
        DATA_SOURCE.setUsername(CONNECTION_USER);  // Recent versions of Vibur require a username, even though it will not be used
        DATA_SOURCE.setPassword(CONNECTION_PASSWORD);
        DATA_SOURCE.setPoolInitialSize(3);
        DATA_SOURCE.setPoolMaxSize(10);
        DATA_SOURCE.setTestConnectionQuery("SELECT 1");
        DATA_SOURCE.setDriverClassName(CONNECTION_CLASS_NAME);

        DATA_SOURCE.start();
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
        return DATA_SOURCE.getConnection();
    }

//    public static void printDbStatus() {
//        System.out.println("Max.: " + DATA_SOURCE.getMaxActive() + "; Active: " + DATA_SOURCE.getNumActive() + "; Idle: " + DATA_SOURCE.getNumIdle());
//    }
}
