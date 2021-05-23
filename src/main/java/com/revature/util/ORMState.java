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

public class ORMState {
    private static ORMState ormState;
    private final Connection conn;
    private HashMap<String,DatabaseTable<?>> databaseTables;
    public static HashMap<String, StatementBuilder> sqlBuilders;
    private Repo repo;

    private ORMState(){
        conn = ConnectionFactory.getInstance().getConnection();
        repo = new Repo(conn);
        sqlBuilders = new HashMap<>();
        sqlBuilders.put("insert",new InsertBuilder(repo));
        sqlBuilders.put("delete",new DeleteBuilder(repo));
        sqlBuilders.put("query",new QueryBuilder(repo));
        sqlBuilders.put("update",new UpdateBuilder(repo));

    }

    public static ORMState getInstance(){
        if(ormState == null){
            ormState = new ORMState();
        }
        return ormState;
    }

    public static StatementBuilder getStatementBuilder(String builderName){
        return sqlBuilders.get(builderName);
    }

    public Connection getConnection(){
        return conn;
    }



}
