/*
 * File: DeleteBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:24 PM
 * Last Modified: 5/14/21, 11:24 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

import com.revature.repos.Repo;
import com.revature.util.datasource.ConnectionFactory;

import java.sql.ResultSet;

public class DeleteBuilder extends StatementBuilder{

    private Repo repo;

    public DeleteBuilder(){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.DELETE;
        repo = new Repo(conn);
    }

    public ResultSet buildDeleteStatement(){
        StringBuilder sql = new StringBuilder();
        return null;
    }


}
