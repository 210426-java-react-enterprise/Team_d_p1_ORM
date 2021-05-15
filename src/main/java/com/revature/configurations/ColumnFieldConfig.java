/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:48 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:48 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:44 PM
 * Last Modified: 5/14/21, 6:57 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:40 PM
 * Last Modified: 5/14/21, 6:57 PM
 * Created by: Nicholas Recino
 */

/*
 * File: ColumnConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 6:47 PM
 * Last Modified: 5/14/21, 6:47 PM
 * Created by: Nicholas Recino
 */

package com.revature.configurations;

import com.revature.types.DataType;

public class ColumnFieldConfig {
    private String fieldName;
    private String columnName;
    private DataType dataType;
    private boolean notNull;
    private boolean serialID;
    private boolean primaryKey;
    private boolean foreignKey;
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

    public boolean isSerialID() {
        return serialID;
    }

    public void setSerialID(boolean serialID) {
        this.serialID = serialID;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
