/*
 * File: UpdateBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:23 PM
 * Last Modified: 5/14/21, 11:23 PM
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

public class UpdateBuilder extends StatementBuilder{

    private Repo repo;

    public UpdateBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection();
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

        return repo.statementExecute(sqlStatement);
    }
    public ResultSet buildUpdateStatementWithMultipleConditions(String tableName,ColumnFieldType[] fieldsData,ColumnFieldType... updateConditionFieldNames) throws SQLException {
        StringBuilder sql = new StringBuilder().append("update ").append(tableName).append(" set ");
        if(updateConditionFieldNames.length==1){
            return buildUpdateStatement(fieldsData,updateConditionFieldNames[0]);
        }
        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName()).append(" = ?, ");
        }
        sql.replace(sql.length()-1,sql.length()," where ");
        for(ColumnFieldType fieldType:updateConditionFieldNames){
            sql.append(fieldType.getColumnName())
                    .append(" = ")
                    .append(fieldType.getDefaultValue().toString());
        }
        sqlStatement = conn.prepareStatement(sql.toString());
        sqlStatement = parseTypeData(sqlStatement,fieldsData);
        return repo.statementExecute(sqlStatement);
    }

    @Override
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        List<ColumnFieldType> conditionalFieldTypes = new ArrayList<>();
        processConditionStatements(tableConfig,conditionalFieldTypes,conditionalFieldNames);
        return buildUpdateStatementWithMultipleConditions(tableConfig.getTableName(), tableConfig.getFieldTypes().toArray(new ColumnFieldType[0]),conditionalFieldTypes.toArray(new ColumnFieldType[0]));
    }
}
