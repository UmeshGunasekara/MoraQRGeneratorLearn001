/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/24/2020 12:12 PM
 */
package com.slmora.learn.common.hibernate;

import com.slmora.learn.common.util.MoraAccessProperties;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/24/2020 12:12 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/24/2020      SLMORA                Initial Code
 */
public class HibernateAnoUtil
{
    final static Logger LOGGER = LogManager.getLogger(HibernateAnoUtil.class);

    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;
    private static String HIBERNATE_DIALECT;
    private static String HIBERNATE_SHOW_SQL;
    private static String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS;
    private static String HIBERNATE_HBM2DDL_AUTO;
    private static String HIBERNATE_POOL_SIZE;
    private static String HIBERNATE_DBCP_INITIAL_SIZE;
    private static String HIBERNATE_DBCP_MAX_TOTAL;
    private static String HIBERNATE_DBCP_MAX_IDLE;
    private static String HIBERNATE_DBCP_MIN_IDLE;
    private static String HIBERNATE_DBCP_MAX_WITH_MILLIS;
    private static String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
    private static String HIBERNATE_CACHE_USE_QUERY_CACHE;
    private static String HIBERNATE_CACHE_REGION_FACTORY_CLASS;

    private static StandardServiceRegistry REGISTRY;
    private static SessionFactory SESSION_FACTORY;

    private HibernateAnoUtil(){}

    /**
     * Assign property values from datasource.properties and create JDBC connection in class initialization
     *
     * @Note don't want include this key word with constant because of they are static
     */
    static{
        setDtaSourceAttributes();
        setHibernateAttributes();
    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    public static SessionFactory getSessionFactory() throws Throwable{
        if(SESSION_FACTORY == null){
            try {
                Configuration configuration=new Configuration();

                Properties settings=new Properties();
                settings.put(Environment.DRIVER,CONNECTION_CLASS_NAME);
                settings.put(Environment.URL,getFullConnectionURL());
                settings.put(Environment.USER,CONNECTION_USER);
                settings.put(Environment.PASS,CONNECTION_PASSWORD);
                settings.put(Environment.DIALECT,HIBERNATE_DIALECT);
                settings.put(Environment.SHOW_SQL,HIBERNATE_SHOW_SQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS);
                settings.put(Environment.HBM2DDL_AUTO,HIBERNATE_HBM2DDL_AUTO);
                settings.put(Environment.POOL_SIZE,HIBERNATE_POOL_SIZE);
                settings.put(Environment.USE_SECOND_LEVEL_CACHE,HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE);
                settings.put(Environment.USE_QUERY_CACHE,HIBERNATE_CACHE_USE_QUERY_CACHE);
                settings.put(Environment.CACHE_REGION_FACTORY,HIBERNATE_CACHE_REGION_FACTORY_CLASS);

                configuration.setProperties(settings);
//                configuration.addAnnotatedClass(SBAddress02.class);

                REGISTRY = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                SESSION_FACTORY=configuration.buildSessionFactory(REGISTRY);

            }catch (Exception e){
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
                if(REGISTRY != null){
                    StandardServiceRegistryBuilder.destroy(REGISTRY);
                }
            }
        }
        return SESSION_FACTORY;
    }


    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    public static void shutdown(){
        if(REGISTRY != null){
            StandardServiceRegistryBuilder.destroy(REGISTRY);
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
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    private static void setDtaSourceAttributes(){
        try {
            Properties dataSourceProperties = new MoraAccessProperties().getAllPropertiesFromResource(
                    "datasource.properties");
            if (CONNECTION_HOST == null || CONNECTION_HOST.isBlank()) {
                CONNECTION_HOST = dataSourceProperties.getProperty("MORA.DATASOURCE.HOST");
            }
            if (CONNECTION_PORT == null || CONNECTION_PORT.isBlank()) {
                CONNECTION_PORT = dataSourceProperties.getProperty("MORA.DATASOURCE.PORT");
            }
            if (CONNECTION_DATABASE == null || CONNECTION_DATABASE.isBlank()) {
                CONNECTION_DATABASE = dataSourceProperties.getProperty("MORA.DATASOURCE.DATABASE");
            }
            if (CONNECTION_USER == null || CONNECTION_USER.isBlank()) {
                CONNECTION_USER = dataSourceProperties.getProperty("MORA.DATASOURCE.USER");
            }
            if (CONNECTION_PASSWORD == null || CONNECTION_PASSWORD.isBlank()) {
                CONNECTION_PASSWORD = dataSourceProperties.getProperty("MORA.DATASOURCE.PASSWORD");
            }
            if (CONNECTION_CLASS_NAME == null || CONNECTION_CLASS_NAME.isBlank()) {
                CONNECTION_CLASS_NAME = dataSourceProperties.getProperty("MORA.DATASOURCE.CLASS");
            }
            if (CONNECTION_PROPERTY_STRING == null || CONNECTION_PROPERTY_STRING.isBlank()) {
                CONNECTION_PROPERTY_STRING = dataSourceProperties.getProperty("MORA.DATASOURCE.PROPERTY_STRING");
            }
        }catch (Exception e){
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
    private static void setHibernateAttributes(){
        try {
            Properties hibernateProperties = new MoraAccessProperties().getAllPropertiesFromResource("datasource.hibernate.properties");
            if(HIBERNATE_DIALECT == null || HIBERNATE_DIALECT.isBlank()){
                HIBERNATE_DIALECT = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DIALECT");
            }
            if(HIBERNATE_SHOW_SQL == null || HIBERNATE_SHOW_SQL.isBlank()){
                HIBERNATE_SHOW_SQL = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.SHOW_SQL");
            }
            if(HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS == null || HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS.isBlank()){
                HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CURRENT_SESSION_CONTEXT_CLASS");
            }
            if(HIBERNATE_HBM2DDL_AUTO == null || HIBERNATE_HBM2DDL_AUTO.isBlank()){
                HIBERNATE_HBM2DDL_AUTO = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HBM2DDL_AUTO");
            }
            if(HIBERNATE_POOL_SIZE == null || HIBERNATE_POOL_SIZE.isBlank()){
                HIBERNATE_POOL_SIZE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.POOL_SIZE");
            }
            if(HIBERNATE_DBCP_INITIAL_SIZE == null || HIBERNATE_DBCP_INITIAL_SIZE.isBlank()){
                HIBERNATE_DBCP_INITIAL_SIZE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DBCP.INITIAL_SIZE");
            }
            if(HIBERNATE_DBCP_MAX_TOTAL == null || HIBERNATE_DBCP_MAX_TOTAL.isBlank()){
                HIBERNATE_DBCP_MAX_TOTAL = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DBCP.MAX_TOTAL");
            }
            if(HIBERNATE_DBCP_MAX_IDLE == null || HIBERNATE_DBCP_MAX_IDLE.isBlank()){
                HIBERNATE_DBCP_MAX_IDLE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DBCP.MAX_IDLE");
            }
            if(HIBERNATE_DBCP_MIN_IDLE == null || HIBERNATE_DBCP_MIN_IDLE.isBlank()){
                HIBERNATE_DBCP_MIN_IDLE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DBCP.MIN_IDLE");
            }
            if(HIBERNATE_DBCP_MAX_WITH_MILLIS == null || HIBERNATE_DBCP_MAX_WITH_MILLIS.isBlank()){
                HIBERNATE_DBCP_MAX_WITH_MILLIS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DBCP.MAX_WITH_MILLIS");
            }
            if(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE == null || HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE.isBlank()){
                HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CACHE.USE_SECOND_LEVEL_CACHE");
            }
            if(HIBERNATE_CACHE_USE_QUERY_CACHE == null || HIBERNATE_CACHE_USE_QUERY_CACHE.isBlank()){
                HIBERNATE_CACHE_USE_QUERY_CACHE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CACHE.USE_QUERY_CACHE");
            }
            if(HIBERNATE_CACHE_REGION_FACTORY_CLASS == null || HIBERNATE_CACHE_REGION_FACTORY_CLASS.isBlank()){
                HIBERNATE_CACHE_REGION_FACTORY_CLASS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CACHE.REGION.FACTORY_CLASS");
            }
        }catch (Exception e){
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

}
