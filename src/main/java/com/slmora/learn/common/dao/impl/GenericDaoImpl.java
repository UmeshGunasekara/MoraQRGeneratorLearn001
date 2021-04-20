/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:10 AM
 */
package com.slmora.learn.common.dao.impl;

import com.slmora.learn.common.dao.GenericDao;
import com.slmora.learn.common.hibernate.HibernateAnoUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:10 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/25/2020      SLMORA                Initial Code
 */
public class GenericDaoImpl <K extends Serializable, T> implements GenericDao <K, T>
{
    final static Logger LOGGER = LogManager.getLogger(GenericDaoImpl.class);

    private final Class<? extends T> daoType;
    private Session session;

    protected GenericDaoImpl(Class<? extends T> daoType) {
        this.daoType = daoType;
    }

    public GenericDaoImpl(){
        Type t=getClass().getGenericSuperclass();
        ParameterizedType pt=(ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[1];
        try {
            session= HibernateAnoUtil.getSessionFactory().openSession();
        } catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(throwable));
            throwable.printStackTrace();
        }
    }

//    protected Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }

    protected CriteriaQuery createEntityCriteriaQuery(){
        return session.getCriteriaBuilder().createQuery(daoType);
    }

    protected Criteria createEntityCriteria() {
        return session.createCriteria(daoType);
    }

    @Override
    public K add(T entity) {
//        return (K) getSession().save(entity);
        Transaction transaction=session.beginTransaction();
        K k=(K)session.save(entity);
        transaction.commit();
        return k;
    }

    @Override
    public void saveOrUpdate(T entity) {
        Transaction transaction=session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(T entity) {
        Transaction transaction=session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public T getByKey(K key) {
        Transaction transaction=session.beginTransaction();
        T t= (T) session.get(daoType, key);
        transaction.commit();
        return t;
    }

    @Override
    public List<T> getAll() {
        return (List<T>) createEntityCriteria().list();
    }
}
