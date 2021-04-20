/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:03 AM
 */
package com.slmora.learn.common.dao;

import com.slmora.learn.common.hibernate.HibernateAnoUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:03 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/25/2020      SLMORA                Initial Code
 */
public abstract class AbstractDao <K extends Serializable, T>
{
    final static Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    private final Class<T> persistentClass;
    //    @Autowired
    private SessionFactory sessionFactory;

    protected AbstractDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        try {
            sessionFactory= HibernateAnoUtil.getSessionFactory();
        } catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwable));
            throwable.printStackTrace();
        }
    }

    public AbstractDao(){
        this.persistentClass=(Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public T getByKey(K key){
        return (T)getSession().get(persistentClass, key);
    }

    public void persist(T entity){
        getSession().persist(entity);
    }

    public void delete(T entity){
        getSession().delete(entity);
    }

    public CriteriaQuery<T> createEntityCriteria(){
        return getSession().getCriteriaBuilder().createQuery(persistentClass);
    }
}
