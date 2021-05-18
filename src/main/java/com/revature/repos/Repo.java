/*
 * File: Repo
 * Team_d_p1_ORM
 * Date created: 5/15/21, 1:09 AM
 * Last Modified: 5/15/21, 1:09 AM
 * Created by: Nicholas Recino
 */

package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Repo <T,ID>{
    Connection conn;

    public Repo(Connection conn){
        this.conn = conn;
    }

    public ResultSet statementExecute(PreparedStatement statement) {
        ResultSet rs = null;
        try {
            int rowInserted = statement.executeUpdate();
            if (rowInserted != 0) {
                rs = statement.getGeneratedKeys();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }


    public ResultSet queryExecute(PreparedStatement statement){
        ResultSet rs = null;
        try{
            rs = statement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

    public Connection getRepoConnection(){
        return conn;
    }

}
