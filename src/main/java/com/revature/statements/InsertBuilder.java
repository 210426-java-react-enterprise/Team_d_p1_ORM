/*
 * File: InsertBuilder
 * Team_d_p1_ORM
 * Date created: 5/17/21, 10:28 PM
 * Last Modified: 5/17/21, 10:28 PM
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

/**
 * The type Insert builder.
 */
public class InsertBuilder extends StatementBuilder{

    private final Repo repo;
    private String [] keysToReturn;

    /**
     * Instantiates a new Insert builder.
     *
     * @param repo the repo used to execute the statements
     */
    public InsertBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection();
        type = StatementType.INSERT;
        this.repo = repo;
        keysToReturn = new String[0];
    }

    /**
     * Builds an insert statement with a singular conditional field
     *
     * @param fieldsData    the fields data that is to be inserted
     * @param tableName the name of the table that is being inserted into
     * @return the result set of any generated keys from the execution of the statement
     * @throws SQLException If there is an issue with connection to the database, or the models provided do not match what is present on the database utilized
     */
    public ResultSet buildInsertStatement(ColumnFieldType[] fieldsData,String tableName) throws SQLException {
        StringBuilder sql = new StringBuilder().append("insert into ").append(tableName).append(" (");
        StringBuilder values = new StringBuilder().append(" values(");
        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName())
                    .append(",");
            values.append("?,");
        }
        sql.replace(sql.length()-1,sql.length(),")");
        values.replace(values.length()-1,values.length(),")");
        sqlStatement = conn.prepareStatement(sql.toString() + values,keysToReturn);
        sqlStatement = parseTypeData(sqlStatement,fieldsData);
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
    public ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted,true);
        keysToReturn = tableConfig.getAllFieldNames().toArray(new String[0]);
        return buildInsertStatement(tableConfig.getFieldTypes().toArray(new ColumnFieldType[0]), tableConfig.getTableName());
    }
}
