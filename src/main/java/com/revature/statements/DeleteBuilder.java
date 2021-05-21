/*
 * File: DeleteBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:24 PM
 * Last Modified: 5/14/21, 11:24 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import com.revature.util.datasource.ConnectionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteBuilder extends StatementBuilder{

    private Repo repo;

    public DeleteBuilder(){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.DELETE;
        repo = new Repo(conn);
    }
    public DeleteBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.DELETE;
        this.repo = repo;
    }

    public ResultSet buildDeleteStatement(ColumnFieldType deleteConditionFieldName) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ")
            .append(deleteConditionFieldName.getTableName())
            .append(" where ")
            .append(deleteConditionFieldName.getColumnName())
            .append(" = ?");
        sqlStatement = conn.prepareStatement(sql.toString());
        sqlStatement = parseTypeData(sqlStatement,new ColumnFieldType[]{deleteConditionFieldName});
        System.out.println(sqlStatement);
        return repo.statementExecute(sqlStatement);
    }


}
