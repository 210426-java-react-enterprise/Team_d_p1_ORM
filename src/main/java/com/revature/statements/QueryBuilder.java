/*
 * File: QueryBuilder
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

public class QueryBuilder extends StatementBuilder{

    private Repo repo;

    public QueryBuilder(){
        conn = ConnectionFactory.getInstance().getConnection();
        type = StatementType.SELECT;
        repo = new Repo(conn);
    }

    public ResultSet buildSelectStatement(ColumnFieldType conditionFieldName) throws SQLException {
        StringBuilder sql = new StringBuilder().append("select * from ").append(conditionFieldName.getTableName());
        Object valueToSearchFor = conditionFieldName.getDefaultValue();
        if(valueToSearchFor==null) {
            sqlStatement= conn.prepareStatement(sql.toString());
        }else {
            sql.append(" where ")
            .append(conditionFieldName.getColumnName())
            .append(" = ?");
            conn.prepareStatement(sql.toString());
            sqlStatement = parseTypeData(sqlStatement, new ColumnFieldType[]{conditionFieldName});
        }
        System.out.println(sql);
//        TODO call to repo, not in this branch itself, need to refactor to include it.
        return repo.queryExecute(sqlStatement);
    }
}





