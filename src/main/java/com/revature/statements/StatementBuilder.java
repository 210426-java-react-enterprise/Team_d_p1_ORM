/*
 * File: StatementBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:54 PM
 * Last Modified: 5/14/21, 7:54 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

// Builds Statements based upon table info and field types, extended for specific statement types, ALA the Sub languages of SQL

import com.revature.configurations.TableConfig;
import com.revature.exception.ImproperConfigurationException;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;

import java.sql.*;
import java.util.Arrays;
import java.util.List;


/**
 * The type Statement builder.
 */
public abstract class StatementBuilder{

    /**
     * The StatementType of the builder in question.
     */
    protected StatementType type;
    /**
     * The Sql statement to be prepared and executed.
     */
    protected PreparedStatement sqlStatement;
    /**
     * The Connection used to facilitate the execution of a sql statement.
     */
    protected Connection conn;
    /**
     * The Sql string to be parsed and passed into the sql statment.
     */
    protected StringBuilder sql;

    protected String[] keysToReturn;

    /**
     * Parse type data  into a prepared statement after a string has been loaded into the statement.
     *
     * @param statement  the statement to be processed and have data types inserted into
     * @param fieldsData the fields data containing the data to be inserted into a statement
     * @return the prepared statement that has been processed
     * @throws SQLException when the data type does not match a valid Postgresql datatype or conversion
     * */
    protected PreparedStatement parseTypeData(PreparedStatement statement, ColumnFieldType[] fieldsData) throws SQLException {

        for(int i =1; i<fieldsData.length+1;i++){
            ColumnFieldType fieldData = fieldsData[i-1];
            DataType dataType = fieldData.getDataType();
            switch (dataType){
                case STRING:
                case CHAR:
                    statement.setString(i, (String) fieldData.getDefaultValue());
                    break;
                case INTEGER:
                case SERIAL:
                    statement.setInt(i, (Integer) fieldData.getDefaultValue());
                    break;
                case BOOLEAN:
                    statement.setBoolean(i, (Boolean) fieldData.getDefaultValue());
                    break;
                case DOUBLE:
                    statement.setDouble(i, (Double) fieldData.getDefaultValue());
                    break;
                case LONG:
                    statement.setLong(i, (Long) fieldData.getDefaultValue());
                    break;
                case FLOAT:
                    statement.setFloat(i, (Float) fieldData.getDefaultValue());
                    break;
                default:
                    dataType.javaToPostgreSQLArguments(fieldData,fieldData.getDefaultValue());

            }
        }
        return statement;
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
    public abstract ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException;

    /**
     * Process a list of conditions from a table config and return the ones matching specified conditions field names.
     *
     * @param tableConfig           the table config from which the ColumnFields could be pulled from
     * @param conditionalFieldTypes the conditional field types  that are needing to be parsed
     * @param conditionalFieldNames the conditional field names of the conditional field types that are specified
     */
    protected void processConditionStatements(TableConfig tableConfig,List<ColumnFieldType> conditionalFieldTypes, String... conditionalFieldNames){
        List<String> conditionalFields = Arrays.asList(conditionalFieldNames);
        for(ColumnFieldType type:tableConfig.getFieldTypes()){
            if(conditionalFields.contains(type.getColumnName())){
                conditionalFieldTypes.add(type);
            }
        }
    }

    public PreparedStatement multipleConditionSqlBuilder(StringBuilder sql, ColumnFieldType[] conditionFieldNames, String... keysToReturn) throws SQLException {
        sql.append(" where ");
        for(ColumnFieldType columnFieldType: conditionFieldNames) {
            sql.append(columnFieldType.getColumnName())
                    .append(" = ?")
                    .append(" and ");
        }
        sql.replace(sql.length()-5, sql.length(), "");
        sqlStatement =  (keysToReturn.length==0) ? conn.prepareStatement(sql.toString()) : conn.prepareStatement(sql.toString(),keysToReturn);
        sqlStatement = parseTypeData(sqlStatement, conditionFieldNames);
        return sqlStatement;
    }
}
