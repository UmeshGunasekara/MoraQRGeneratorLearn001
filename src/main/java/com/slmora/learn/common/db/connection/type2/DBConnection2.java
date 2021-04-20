/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/20/2020 2:26 PM
 */
package com.slmora.learn.common.db.connection.type2;

import com.slmora.learn.common.util.MoraAccessProperties;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This Class created for get singleton JDBC connection
 *
 * @Author: SLMORA
 * @DateTime: 9/20/2020 2:26 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/20/2020      SLMORA                Initial Code
 */
public class DBConnection2
{
    final static Logger LOGGER = LogManager.getLogger(DBConnection2.class);

    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;
    private static Connection DB_CONNECTION;

    private DBConnection2(){ }

    /**
     * Assign property values from datasource.properties and create JDBC connection in class initialization
     *
     * @Note don't want include this key word with constant because of they are static
     */
    static{
        setAttribute();
        try {
            Class.forName(CONNECTION_CLASS_NAME);
            DB_CONNECTION = DriverManager.getConnection(getFullConnectionURL(),CONNECTION_USER,CONNECTION_PASSWORD);
        } catch (ClassNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }

    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    private static void setAttribute(){
        Properties properties = new MoraAccessProperties().getAllPropertiesFromResource("datasource.properties");
        CONNECTION_HOST = properties.getProperty("MORA.DATASOURCE.HOST");
        CONNECTION_PORT = properties.getProperty("MORA.DATASOURCE.PORT");
        CONNECTION_DATABASE = properties.getProperty("MORA.DATASOURCE.DATABASE");
        CONNECTION_USER = properties.getProperty("MORA.DATASOURCE.USER");
        CONNECTION_PASSWORD = properties.getProperty("MORA.DATASOURCE.PASSWORD");
        CONNECTION_CLASS_NAME = properties.getProperty("MORA.DATASOURCE.CLASS");
        CONNECTION_PROPERTY_STRING = properties.getProperty("MORA.DATASOURCE.PROPERTY_STRING");
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
    public synchronized static Connection getConnection(){
        return DB_CONNECTION;
    }
}
