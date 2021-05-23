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
import com.revature.types.DataType;
import com.revature.util.datasource.ConnectionFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertBuilder extends StatementBuilder{

    private Repo repo;

    public InsertBuilder(Repo repo){
        conn = ConnectionFactory.getInstance().getConnection();
        type = StatementType.INSERT;
        this.repo = repo;
    }

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
        sqlStatement = conn.prepareStatement(sql.toString() + values);
        sqlStatement = parseTypeData(sqlStatement,fieldsData);
        System.out.println(sqlStatement);
        return repo.queryExecute(sqlStatement);
    }

    @Override
    protected ResultSet buildStatement(Object objectToBePersisted, String... conditionalFieldNames) throws SQLException, ImproperConfigurationException {
        TableConfig tableConfig = new TableConfig(objectToBePersisted);
        return buildInsertStatement(tableConfig.getFieldTypes().toArray(new ColumnFieldType[0]), tableConfig.getTableName());
    }
}
