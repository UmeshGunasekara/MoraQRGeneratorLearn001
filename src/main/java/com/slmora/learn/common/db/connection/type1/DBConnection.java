/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/20/2020 1:06 PM
 */
package com.slmora.learn.common.db.connection.type1;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.slmora.learn.common.util.MoraAccessProperties;
import lombok.Data;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This Class created for get JDBC connection
 *
 * @Author: SLMORA
 * @DateTime: 9/20/2020 1:06 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/20/2020      SLMORA                Initial Code
 */
@Data
public class DBConnection
{
    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;

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
    public static Connection getConnection()
            throws
            ClassNotFoundException,
            SQLException
    {
        setAttribute();
        Class.forName(CONNECTION_CLASS_NAME);
        Connection connection = DriverManager.getConnection(getFullConnectionURL(),CONNECTION_USER,CONNECTION_PASSWORD);
        return connection;
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
    public static MysqlDataSource getMysqlDataSource()
    {
        setAttribute();
        MysqlDataSource dataSource =  new MysqlDataSource();
        dataSource.setURL(getFullConnectionURL());
        dataSource.setUser(CONNECTION_USER);
        dataSource.setPassword(CONNECTION_PASSWORD);
        return dataSource;
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
    public static BasicDataSource getApacheCommonsBasicDataSource()
    {
        setAttribute();
        BasicDataSource dataSource =  new BasicDataSource();
        dataSource.setUrl(getFullConnectionURL());
        dataSource.setUsername(CONNECTION_USER);
        dataSource.setPassword(CONNECTION_PASSWORD);
        return dataSource;
    }

    /**
     * Read datasource.properties and assign values
     *
     * @return String Object will return with requested property or null
     * @throws ClassNotFoundException,SQLException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public static  Connection getConnection(String className,String uRL,String userName,String password)
            throws
            ClassNotFoundException,
            SQLException
    {
        Class.forName(className);
        Connection connection = DriverManager.getConnection(uRL,userName,password);
        return connection;
    }

    public static String getConnectionHost()
    {
        return CONNECTION_HOST;
    }

    public static void setConnectionHost(String connectionHost)
    {
        CONNECTION_HOST = connectionHost;
    }

    public static String getConnectionPort()
    {
        return CONNECTION_PORT;
    }

    public static void setConnectionPort(String connectionPort)
    {
        CONNECTION_PORT = connectionPort;
    }

    public static String getConnectionDatabase()
    {
        return CONNECTION_DATABASE;
    }

    public static void setConnectionDatabase(String connectionDatabase)
    {
        CONNECTION_DATABASE = connectionDatabase;
    }

    public static String getConnectionUser()
    {
        return CONNECTION_USER;
    }

    public static void setConnectionUser(String connectionUser)
    {
        CONNECTION_USER = connectionUser;
    }

    public static String getConnectionPassword()
    {
        return CONNECTION_PASSWORD;
    }

    public static void setConnectionPassword(String connectionPassword)
    {
        CONNECTION_PASSWORD = connectionPassword;
    }

    public static String getConnectionClassName()
    {
        return CONNECTION_CLASS_NAME;
    }

    public static void setConnectionClassName(String connectionClassName)
    {
        CONNECTION_CLASS_NAME = connectionClassName;
    }

    public static String getConnectionPropertyString()
    {
        return CONNECTION_PROPERTY_STRING;
    }

    public static void setConnectionPropertyString(String connectionPropertyString)
    {
        CONNECTION_PROPERTY_STRING = connectionPropertyString;
    }
}
