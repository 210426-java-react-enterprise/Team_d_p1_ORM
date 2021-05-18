/*
 * File: InsertBuilder
 * Team_d_p1_ORM
 * Date created: 5/17/21, 10:28 PM
 * Last Modified: 5/17/21, 10:28 PM
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

public class InsertBuilder extends StatementBuilder{

    public InsertBuilder(){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.INSERT;
    }

    public ResultSet buildInsertStatement(ColumnFieldType[] fieldsData,String tableName) throws SQLException {
        StringBuilder sql = new StringBuilder().append("insert into ").append(tableName).append("(");
        StringBuilder values = new StringBuilder().append("values(");
        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName()).append(",");
            values.append("?,");
        }
        sql.replace(sql.length(),sql.length(),")");
        values.replace(values.length(),values.length(),")");
        sqlStatement = conn.prepareStatement(sql.toString() + values);
        for(int i =1; i<fieldsData.length;i++){
            ColumnFieldType fieldData = fieldsData[i];
            DataType dataType = fieldData.getDataType();
            switch (dataType){
                case STRING:
                case CHAR:
                    sqlStatement.setString(i, (String) fieldData.getDefaultValue());
                    break;
                case INTEGER:
                case SERIAL:
                    sqlStatement.setInt(i, (Integer) fieldData.getDefaultValue());
                    break;
                case BOOLEAN:
                    sqlStatement.setBoolean(i, (Boolean) fieldData.getDefaultValue());
                    break;
                case DOUBLE:
                    sqlStatement.setDouble(i, (Double) fieldData.getDefaultValue());
                    break;
                case LONG:
                    sqlStatement.setLong(i, (Long) fieldData.getDefaultValue());
                    break;
                case FLOAT:
                    sqlStatement.setFloat(i, (Float) fieldData.getDefaultValue());
                    break;
                case DATE:
                    sqlStatement.setDate(i, (Date) fieldData.getDefaultValue());
                    break;
                default:
                    dataType.javaToPostgreSQLArguments(fieldData,fieldData.getDefaultValue());

            }
        }
        Repo repo = new Repo();
//        TODO call to repo, not in this branch itself, need to refactor to include it.
        return null;
    }
}
