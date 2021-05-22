/*
 * File: DeleteBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:24 PM
 * Last Modified: 5/14/21, 11:24 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

import com.revature.configurations.TableConfig;
import com.revature.exception.ImproperConfigurationException;
import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import com.revature.util.datasource.ConnectionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteBuilder extends StatementBuilder{

    private Repo repo;

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

    public ResultSet buildDeleteStatementWithMultipleConditions(String tableName, ColumnFieldType... conditionFieldName) throws SQLException {
        if(conditionFieldName.length==1) {
            return buildDeleteStatement(conditionFieldName[0]);
        }else {
            StringBuilder sql = new StringBuilder().append("delete from ").append(tableName);
            sql.append(" where ");
            for(ColumnFieldType columnFieldType:conditionFieldName) {
                sql.append(columnFieldType.getColumnName()).append(" = ?");
            }
                sqlStatement = conn.prepareStatement(sql.toString());
                sqlStatement = parseTypeData(sqlStatement, conditionFieldName);
        }
        System.out.println(sqlStatement);
        return repo.queryExecute(sqlStatement);
    }

    @Override
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws ImproperConfigurationException, SQLException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        List<ColumnFieldType> conditionalFieldTypes = new ArrayList<>();
        processConditionStatements(tableConfig,conditionalFieldTypes,conditionalFieldNames);
        return buildDeleteStatementWithMultipleConditions(tableConfig.getTableName(),conditionalFieldTypes.toArray(new ColumnFieldType[0]));
    }
}
