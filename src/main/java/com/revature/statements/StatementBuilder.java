/*
 * File: StatementBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:54 PM
 * Last Modified: 5/14/21, 7:54 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

// Builds Statements based upon table info and field types, extended for specific statement types, ALA the Sub languages of SQL

import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class StatementBuilder{

    protected StatementType type;
    protected PreparedStatement sqlStatement;
    protected Connection conn;
    protected StringBuilder sql;

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
                case DATE:
                    statement.setDate(i, (Date) fieldData.getDefaultValue());
                    break;
                default:
                    dataType.javaToPostgreSQLArguments(fieldData,fieldData.getDefaultValue());

            }
        }
        return statement;
    }
}
