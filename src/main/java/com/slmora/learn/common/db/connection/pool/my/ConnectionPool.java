/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:59 AM
 */
package com.slmora.learn.common.db.connection.pool.my;

import java.sql.Connection;

/**
 * This Interface created for
 *
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:59 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/15/2020      SLMORA                Initial Code
 */
public interface ConnectionPool
{
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
