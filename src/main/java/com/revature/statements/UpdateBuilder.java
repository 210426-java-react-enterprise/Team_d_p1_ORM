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
import java.util.List;

/**
 * The type Update builder.
 */
public class UpdateBuilder extends StatementBuilder{

    private Repo repo;

    /**
     * Instantiates a new Update builder.
     *
     * @param repo the repo used to execute the statements
     */
    public UpdateBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection();
        type = StatementType.UPDATE;
        this.repo = repo;
    }

    /**
     * Builds an update statement with a singular conditional field
     *
     * @param fieldsData                the fields data that is to be updated
     * @param updateConditionFieldNames the update condition  ColumnFieldType that hold information about the field that is a condition
     * @return the result set of any generated keys from the execution of the statement
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
    public ResultSet buildUpdateStatement(ColumnFieldType[] fieldsData,ColumnFieldType updateConditionFieldNames) throws SQLException {
        StringBuilder sql = new StringBuilder().append("update ").append(updateConditionFieldNames.getTableName()).append(" set ");
        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName()).append(" = ?, ");        }
        sql.replace(sql.length()-1,sql.length()," where ");
        sql.append(updateConditionFieldNames.getColumnName())
                .append(" = ")
                .append(updateConditionFieldNames.getDefaultValue().toString());
        System.out.println(sql);
        sqlStatement = conn.prepareStatement(sql.toString(),keysToReturn);
        sqlStatement = parseTypeData(sqlStatement,fieldsData);

        return repo.statementExecute(sqlStatement);
    }

    /**
     * Build update statement with multiple conditions result set.
     *
     * @param tableName                 the table name of the table that is intended to be updated
     * @param fieldsData                the fields data that is to be updated
     * @param updateConditionFieldNames the update conditional  ColumnFieldType(s) that hold information about the field that is the condition(s)
     * @return the result set of any generated keys from the execution of the statement
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
    public ResultSet buildUpdateStatementWithMultipleConditions(String tableName,ColumnFieldType[] fieldsData,ColumnFieldType... updateConditionFieldNames) throws SQLException {
        StringBuilder sql = new StringBuilder().append("update ").append(tableName).append(" set ");
        if(updateConditionFieldNames.length==1){
            return buildUpdateStatement(fieldsData,updateConditionFieldNames[0]);
        }
        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName()).append(" = ?, ");
        }
        sql.replace(sql.length()-1,sql.length(),"");

        sql.append(" where ");
        for(ColumnFieldType fieldType:updateConditionFieldNames){
            sql.append(fieldType.getColumnName())
                    .append(" = ")
                    .append(fieldType.getDefaultValue().toString())
                    .append(" and ");
        }
        sql.replace(sql.length()-5,sql.length(),"");
        sqlStatement = conn.prepareStatement(sql.toString(),keysToReturn);
        sqlStatement = parseTypeData(sqlStatement,fieldsData);
        return repo.statementExecute(sqlStatement);
    }

    @Override
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        List<ColumnFieldType> conditionalFieldTypes = new ArrayList<>();
        keysToReturn = tableConfig.getAllFieldNames().toArray(new String[0]);
        processConditionStatements(tableConfig,conditionalFieldTypes,conditionalFieldNames);
        return buildUpdateStatementWithMultipleConditions(tableConfig.getTableName(), tableConfig.getFieldTypes().toArray(new ColumnFieldType[0]),conditionalFieldTypes.toArray(new ColumnFieldType[0]));
    }
}
