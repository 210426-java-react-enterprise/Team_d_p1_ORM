/*
 * File: ORMState
 * Team_d_p1_ORM
 * Date created: 5/21/21, 8:54 PM
 * Last Modified: 5/21/21, 8:54 PM
 * Created by: Nicholas Recino
 */

package com.revature.util;

import com.revature.models.DatabaseTable;
import com.revature.repos.Repo;
import com.revature.statements.*;
import com.revature.util.datasource.ConnectionFactory;

import java.sql.Connection;
import java.util.HashMap;

/**
 * The type Orm state.
 */
public class ORMState {
    private static ORMState ormState;
    private final Connection conn;
    private HashMap<String,DatabaseTable<?>> databaseTables;
    /**
     * The Sql builders that are available to be used by the client side via the StatementType enum.
     */
    public static HashMap<String, StatementBuilder> sqlBuilders;

    private ORMState(){
        conn = ConnectionFactory.getInstance().getConnection();
        Repo repo = new Repo();
        sqlBuilders = new HashMap<>();
        sqlBuilders.put("insert",new InsertBuilder(repo));
        sqlBuilders.put("delete",new DeleteBuilder(repo));
        sqlBuilders.put("query",new QueryBuilder(repo));
        sqlBuilders.put("update",new UpdateBuilder(repo));

    }

    /**
     * Get instance orm state.
     *
     * @return the orm state, forced singleton
     */
    public static ORMState getInstance(){
        if(ormState == null){
            ormState = new ORMState();
        }
        return ormState;
    }

    /**
     * Get a statement builder of a specific type associated by a key name .
     *
     * @param builderName the builder name that is a key to the proper builder for a specific CRUD function
     * @return the statement builder paired to the builderName
     */
    public static StatementBuilder getStatementBuilder(String builderName){
        System.out.println("breadcrumb3");
        return sqlBuilders.get(builderName);
    }

    /**
     * Get a from the connection pool if needed.
     *
     * @return a connection to a database
     */
    public Connection getConnection(){
        return conn;
    }



}
