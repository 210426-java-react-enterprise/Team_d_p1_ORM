/*
 * File: UpdateBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:23 PM
 * Last Modified: 5/14/21, 11:23 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;
import com.revature.util.datasource.ConnectionFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateBuilder extends StatementBuilder{

    private Repo repo;

    public UpdateBuilder(){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.UPDATE;
        repo = new Repo(conn);
    }

    public UpdateBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.UPDATE;
        this.repo = repo;
    }

    public ResultSet buildUpdateStatement(ColumnFieldType[] fieldsData,ColumnFieldType updateConditionFieldNames) throws SQLException {
        StringBuilder sql = new StringBuilder().append("update ").append(updateConditionFieldNames.getTableName()).append(" set ");
        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName()).append(" = ?, ");        }
        sql.replace(sql.length()-1,sql.length()," where ");
        sql.append(updateConditionFieldNames.getColumnName())
                .append(" = ")
                .append(updateConditionFieldNames.getDefaultValue().toString());
        System.out.println(sql);
        sqlStatement = conn.prepareStatement(sql.toString());
        sqlStatement = parseTypeData(sqlStatement,fieldsData);
//        TODO call to repo, not in this branch itself, need to refactor to include it.
        return repo.statementExecute(sqlStatement);
    }
}
