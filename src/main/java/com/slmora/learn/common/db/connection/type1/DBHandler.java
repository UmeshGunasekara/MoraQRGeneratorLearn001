/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/20/2020 1:06 PM
 */
package com.slmora.learn.common.db.connection.type1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 9/20/2020 1:06 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/20/2020      SLMORA                Initial Code
 */
public class DBHandler
{
    /**
     * Read datasource.properties and assign values
     *
     * @return String Object will return with requested property or null
     * @throws ClassNotFoundException,SQLException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public static int setData(Connection connection, String query)
            throws SQLException
    {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        statement.close();
        return result;
    }

    /**
     * Read datasource.properties and assign values
     *
     * @return String Object will return with requested property or null
     * @throws ClassNotFoundException,SQLException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public static ResultSet getData(Connection connection, String query)
            throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
//        statement.close(); // Cant Use it in here
        return resultSet;
    }

}
