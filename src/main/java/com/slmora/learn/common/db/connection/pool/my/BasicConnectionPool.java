/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:56 AM
 */
package com.slmora.learn.common.db.connection.pool.my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/15/2020 12:56 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/15/2020      SLMORA                Initial Code
 */
public class BasicConnectionPool
        implements ConnectionPool
{
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    public static BasicConnectionPool create(
            String url,
            String user,
            String password)
            throws SQLException
    {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new BasicConnectionPool(url, user, password, pool);
    }

    public BasicConnectionPool(
            String url,
            String user,
            String password,
            List<Connection> connectionPool)
    {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    private static Connection createConnection(
            String url,
            String user,
            String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Connection getConnection()
    {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;

//        if (connectionPool.isEmpty()) {
//            if (usedConnections.size() < MAX_POOL_SIZE) {
//                connectionPool.add(createConnection(url, user, password));
//            } else {
//                throw new RuntimeException(
//                        "Maximum pool size reached, no available connections!");
//            }
//        }
//
//        Connection connection = connectionPool
//                .remove(connectionPool.size() - 1);
//
//        if(!connection.isValid(MAX_TIMEOUT)){
//            connection = createConnection(url, user, password);
//        }
//
//        usedConnections.add(connection);
//        return connection;
    }

    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }

    @Override
    public boolean releaseConnection(Connection connection)
    {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
