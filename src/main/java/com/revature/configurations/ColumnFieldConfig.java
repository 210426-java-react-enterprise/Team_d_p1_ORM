/*
 * File: ColumnFieldConfig
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:32 AM
 * Last Modified: 5/14/21, 11:33 PM
 * Created by: Nicholas Recino
 */
package com.revature.configurations;

import com.revature.types.DataType;

/**
 * Configuration Class for Column Annotations
 */
public class ColumnFieldConfig {
    private String fieldName;
    private String columnName;
    private DataType dataType;
    private boolean notNull;
    private boolean unique;

    /**
     *
     * @return the name of the field inside of an annotated column field
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     *
     * @param fieldName Sets the name of the field itself for a column
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     *
     * @return The name of the column that is associated to the database
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     *
     * @param columnName Sets the name of the column that is associated to the database
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     *
     * @return the Java DataType that is used to correspond to the Postgresql datatype of a Column
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     *
     * @param dataType Sets the Java DataType that is used to correspond to the Postgresql datatype of a Column
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /**
     *
     * @return whether a column is optional or not, ie is allowed to contain null values
     */
    public boolean isNotNull() {
        return notNull;
    }

    /**
     *
     * @param notNull Sets whether a column is optional or not, ie is allowed to contain null values
     */
    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    /**
     *
     * @return whether the data within a column must be unique
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     *
     * @param unique Sets whether the data within a column must be unique
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     *
     * @return the representation of a field config as a string taking into account whether it is null and unique
     */
    @Override
    public String toString() {
        return "ColumnFieldConfig{ " +
                " notNull = " + notNull +
                ", unique = " + unique +
                " }";
    }
}
