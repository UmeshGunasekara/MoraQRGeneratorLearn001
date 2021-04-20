/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/14/2020 11:56 PM
 */
package com.slmora.learn.common.db.connection.pool;

import com.slmora.learn.common.util.MoraAccessProperties;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/14/2020 11:56 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/14/2020      SLMORA                Initial Code
 */
public class DBCPConnectionPool
{
    final static Logger LOGGER = LogManager.getLogger(DBCPConnectionPool.class);

    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;

    private static GenericObjectPool G_POOL = null;

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

    @SuppressWarnings("unused")
    public DataSource setUpPool()
            throws
            ClassNotFoundException
    {
        setAttribute();
        Class.forName(CONNECTION_CLASS_NAME);

        // Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
        G_POOL = new GenericObjectPool();
        G_POOL.setMaxActive(5);

        // Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(getFullConnectionURL(), CONNECTION_USER, CONNECTION_PASSWORD);

        // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory,
                G_POOL,
                null,
                null,
                false,
                true);
        return new PoolingDataSource(G_POOL);
    }

    public GenericObjectPool getConnectionPool() {
        return G_POOL;
    }

    // This Method Is Used To Print The Connection Pool Status
    public void printDbStatus() {
        System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }
}
