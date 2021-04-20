/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:02 AM
 */
package com.slmora.learn.common.dao;

import java.util.List;

/**
 * This Interface created for
 *
 * @Author: SLMORA
 * @DateTime: 10/25/2020 3:02 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/25/2020      SLMORA                Initial Code
 */
public interface GenericDao <K, T>
{
    public K add(T entity);

    public void saveOrUpdate(T entity);

    public void delete(T entity);

    public T getByKey(K key);

    public List<T> getAll();
}
