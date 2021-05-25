/*
 * File: QueryBuilder
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
import java.util.Arrays;
import java.util.List;

public class QueryBuilder extends StatementBuilder{

    private Repo repo;

    public QueryBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection();
        type = StatementType.SELECT;
        this.repo = repo;
    }

    public ResultSet buildSelectStatement(ColumnFieldType conditionFieldName) throws SQLException {
        StringBuilder sql = new StringBuilder().append("select * from ").append(conditionFieldName.getTableName());
        Object valueToSearchFor = conditionFieldName.getDefaultValue();
        if(valueToSearchFor==null) {
            sqlStatement = conn.prepareStatement(sql.toString());
        }else {
            sql.append(" where ")
            .append(conditionFieldName.getColumnName())
            .append(" = ?");
            sqlStatement = conn.prepareStatement(sql.toString());
            sqlStatement = parseTypeData(sqlStatement, new ColumnFieldType[]{conditionFieldName});
        }
        System.out.println(sql);
        return repo.queryExecute(sqlStatement);
    }
    public ResultSet buildSelectStatementWithMultipleConditions(String tableName, ColumnFieldType... conditionFieldName) throws SQLException {
        if(conditionFieldName.length==1) {
            return buildSelectStatement(conditionFieldName[0]);
        }else {
            StringBuilder sql = new StringBuilder().append("select * from ").append(tableName);
            sql.append(" where ");
            for(ColumnFieldType columnFieldType:conditionFieldName){
                sql.append(columnFieldType.getColumnName())
                        .append(" = ?")
                .append(" and ");

            }
            sql.replace(sql.length()-5, sql.length(), "");
            sqlStatement = conn.prepareStatement(sql.toString());
            sqlStatement = parseTypeData(sqlStatement, conditionFieldName);

        }
        System.out.println(sqlStatement);
        return repo.queryExecute(sqlStatement);
    }

    // TODO need to process conditionalFieldNames
    @Override
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        List<ColumnFieldType> conditionalFieldTypes = new ArrayList<>();
        processConditionStatements(tableConfig,conditionalFieldTypes,conditionalFieldNames);
        return buildSelectStatementWithMultipleConditions(tableConfig.getTableName(),conditionalFieldTypes.toArray(new ColumnFieldType[0]));
    }
}





