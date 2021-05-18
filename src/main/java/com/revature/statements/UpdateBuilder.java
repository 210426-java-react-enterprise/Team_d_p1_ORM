/*
 * File: UpdateBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:23 PM
 * Last Modified: 5/14/21, 11:23 PM
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateBuilder extends StatementBuilder{

    public UpdateBuilder(){
        conn = ConnectionFactory.getInstance().getConnection(); // TODO This has a more dependency style intention with another branch, refactor when merged
        type = StatementType.UPDATE;
    }

    public ResultSet buildInsertStatement(ColumnFieldType[] fieldsData, String tableName,String updateConditionFieldNames) throws SQLException {
        StringBuilder sql = new StringBuilder().append("update ").append(tableName).append(" set");

        for(ColumnFieldType fieldName:fieldsData){
            sql.append(fieldName.getColumnName()).append(" = ?,");
        }
        sql.replace(sql.length()-1,sql.length()," where");

        ColumnFieldType conditionField = Arrays.stream(fieldsData)
                                                        .filter((element)-> element.getColumnName().equals(updateConditionFieldNames))
                                                        .collect(Collectors.toList())
                                                        .get(0);
        sql.append(conditionField.getColumnName())
                .append("=")
                .append(conditionField.getDefaultValue().toString());

        sqlStatement = conn.prepareStatement(sql.toString());
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
