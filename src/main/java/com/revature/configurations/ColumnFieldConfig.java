/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:32 AM
 * Last Modified: 5/14/21, 11:33 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:32 AM
 * Last Modified: 5/14/21, 11:33 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:48 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */
package com.revature.configurations;

import com.revature.types.DataType;

public class ColumnFieldConfig {
    private String fieldName;
    private String columnName;
    private DataType dataType;
    private boolean notNull;
    private boolean unique;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    @Override
    public String toString() {
        return "ColumnFieldConfig{ " +
                " notNull = " + notNull +
                ", unique = " + unique +
                " }";
    }
}
