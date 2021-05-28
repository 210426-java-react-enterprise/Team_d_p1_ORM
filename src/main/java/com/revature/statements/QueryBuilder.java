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
import java.util.List;

/**
 * The type Query builder.
 */
public class QueryBuilder extends StatementBuilder{

    private final Repo repo;

    /**
     * Instantiates a new Query builder.
     *
     * @param repo the repo used to execute the statements
     */
    public QueryBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection();
        type = StatementType.SELECT;
        this.repo = repo;
        keysToReturn = new String[1];
    }

    /**
     *  Build a select statement that grabs all information from a table or where a singular row is equal to a specified condition
     * @param conditionFieldName The ColumnFieldType which represents the column that is utilized as a condition for what rows to select,
     *                          may be null null is utilized for grabbing all information from a table
     * @return a ResultSet of all the rows that match the specified column condition, may be null if nothing is present
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
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

    /**
     * Build a select statement with one or more conditional fields
     * @param tableName name of the table that is being acted upon
     * @param conditionFieldName The ColumnFieldType(s) which represents the column(s) that is/are utilized as a condition for what rows to select
     * @return a ResultSet if any keys are generated from this execution, may be null if nothing is present
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
    public ResultSet buildSelectStatementWithMultipleConditions(String tableName, ColumnFieldType... conditionFieldName) throws SQLException {
        if(conditionFieldName.length==1) {
            return buildSelectStatement(conditionFieldName[0]);
        }else {
            StringBuilder sql = new StringBuilder().append("select * from ").append(tableName);
            sqlStatement = multipleConditionSqlBuilder(sql,conditionFieldName,new String[1]);
        }
        System.out.println(sqlStatement);
        return repo.queryExecute(sqlStatement);
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
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        List<ColumnFieldType> conditionalFieldTypes = new ArrayList<>();
        processConditionStatements(tableConfig,conditionalFieldTypes,conditionalFieldNames);
        return buildSelectStatementWithMultipleConditions(tableConfig.getTableName(),conditionalFieldTypes.toArray(new ColumnFieldType[0]));
    }
}





