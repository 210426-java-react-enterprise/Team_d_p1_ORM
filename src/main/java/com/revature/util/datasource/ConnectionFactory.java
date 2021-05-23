/*
 * File: ConnectionFactory
 * Team_d_p1_ORM
 * Date created: 5/15/21, 12:53 AM
 * Last Modified: 5/15/21, 12:53 AM
 * Created by: Wezley Singleton
 */

package com.revature.util.datasource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private static Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public Connection getConnection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }
    // Entry point into setting the connection for the Database for the ORM from the WebApp, setup this way because if there is time, easy to change to allow for
    // multiple connections based on a preface call
    public static void setConnection(String hostURL, String username, String password,String schemaName){
        String processedHostURL = "jdbc:postgresql://"+hostURL+"/postgres?currentSchema="+schemaName;
        props.put("host-url",processedHostURL);
        props.put("username", username);
        props.put("password",password);
    }

}
