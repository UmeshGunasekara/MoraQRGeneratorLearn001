/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/23/2020 10:56 PM
 */
package com.slmora.learn.common.hibernate;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/23/2020 10:56 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/23/2020      SLMORA                Initial Code
 */
public class HibernateEntityManagerUtil
{
    final static Logger LOGGER = LogManager.getLogger(HibernateEntityManagerUtil.class);

    private static EntityManagerFactory ENTITY_MANAGER = null;

    private HibernateEntityManagerUtil(){}

    public static EntityManagerFactory getEntityManagerFactory() throws Throwable{
        if(ENTITY_MANAGER == null){
            try {
                ENTITY_MANAGER = Persistence.createEntityManagerFactory("persistencecfg");
            }catch (Exception e){
                LOGGER.error(ExceptionUtils.getFullStackTrace(e));
                e.printStackTrace();
            }
        }
        return ENTITY_MANAGER;
    }

    public static void shutdown(){
        if(ENTITY_MANAGER.isOpen()){
            ENTITY_MANAGER.close();
        }
    }

}
