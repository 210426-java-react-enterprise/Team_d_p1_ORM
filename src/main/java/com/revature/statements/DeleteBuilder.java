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

    private final Repo repo;

    /**
     *  Creates a SQL script builder that creates a DELETE oriented sql statement
     * @param repo Object that actually makes the call to execute the sql statement through JDBC
     */
    public DeleteBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.DELETE;
        this.repo = repo;
    }

    /**
     *  Build a delete statement with one conditional field
     * @param deleteConditionFieldName The ColumnFieldType which represents the column that is utilized as a condition for what rows to delete
     * @return a ResultSet if any keys are generated from this execution, may be null
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
    public ResultSet buildDeleteStatement(ColumnFieldType deleteConditionFieldName) throws SQLException {
        String sql = "delete from " +
                deleteConditionFieldName.getTableName() +
                " where " +
                deleteConditionFieldName.getColumnName() +
                " = ?";
        sqlStatement = conn.prepareStatement(sql);
        sqlStatement = parseTypeData(sqlStatement,new ColumnFieldType[]{deleteConditionFieldName});
        System.out.println(sqlStatement);
        return repo.statementExecute(sqlStatement);
    }

    /**
     * Build a delete statement with one or more  conditional fields using the string representation of the column name
     * @param tableName name of the table that is being acted upon
     * @param conditionFieldName The ColumnFieldType(s) which represents the column(s) that is/are utilized as a condition for what rows to delete
     * @return a ResultSet if any keys are generated from this execution, may be null
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
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
        return repo.statementExecute(sqlStatement);
    }

    /**
     *  Builds  Statement and then executes the statement in accordance to which portion of CRUD is desired over-ridden from StatementBuilder
     *  to allow polymorphic calls in StatementType enum
     * @param objectToBePersisted Object to extract data and ColumnFieldTypes from
     * @param conditionalFieldNames The column names which represents the column(s) that is/are utilized as a condition for what rows to delete,
     *                              used to generate the ColumnFieldType(s) to pass through  the CRUD specific builder
     * @return a ResultSet if any keys are generated from this execution, may be null
     * @throws ImproperConfigurationException If there is no data present in a field while NotNull condition is present
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
    @Override
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws ImproperConfigurationException, SQLException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        List<ColumnFieldType> conditionalFieldTypes = new ArrayList<>();
        processConditionStatements(tableConfig,conditionalFieldTypes,conditionalFieldNames);
        return buildDeleteStatementWithMultipleConditions(tableConfig.getTableName(),conditionalFieldTypes.toArray(new ColumnFieldType[0]));
    }
}
