/*
 * File: ConnectionFactory
 * Team_d_p1_ORM
 * Date created: 5/15/21, 12:53 AM
 * Last Modified: 5/15/21, 12:53 AM
 * Created by: Wezley Singleton
 */

package com.revature.util.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Connection factory.
 */
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

    /**
     * Gets the only instance allowed of this class.
     *
     * @return the instance of this class
     */
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    /**
     * Generates a connection.
     *
     * @return the connection generated
     */
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

    /**
     * Sets up the information needed to generate a connection to a database.
     *
     * @param hostURL    the host url to the database
     * @param username   the username to the database
     * @param password   the password to the database
     * @param schemaName the schema name of the database
     */
// Entry point into setting the connection for the Database for the ORM from the WebApp, setup this way because if there is time, easy to change to allow for
    // multiple connections based on a preface call
    public static void setConnection(String hostURL, String username, String password,String schemaName){
        String processedHostURL = "jdbc:postgresql://"+hostURL+"/postgres?currentSchema="+schemaName;
        props.put("host-url",processedHostURL);
        props.put("username", username);
        props.put("password",password);
    }

}
