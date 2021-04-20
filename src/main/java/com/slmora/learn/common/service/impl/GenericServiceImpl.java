/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:16 AM
 */
package com.slmora.learn.common.service.impl;

import com.slmora.learn.common.dao.GenericDao;
import com.slmora.learn.common.service.GenericService;

import java.util.List;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:16 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/25/2020      SLMORA                Initial Code
 */
public abstract class GenericServiceImpl <K, T> implements GenericService<K, T>
{
    private GenericDao<K, T> genericDao;

    public GenericServiceImpl(GenericDao<K, T> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Override
    public K add(T entity) {
        return genericDao.add(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    @Override
    public T getByKey(K key) {
        return genericDao.getByKey(key);
    }

    @Override
    public List<T> getAll() {
        return genericDao.getAll();
    }
}
