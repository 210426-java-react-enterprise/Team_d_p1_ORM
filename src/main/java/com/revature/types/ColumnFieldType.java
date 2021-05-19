/*
 * File: ColumnFieldType
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:43 PM
 * Last Modified: 5/14/21, 7:22 PM
 * Created by: Nicholas Recino
 */
package com.revature.types;

import com.revature.configurations.ColumnFieldConfig;
import com.revature.util.DataFieldConverter;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;

public class ColumnFieldType {

    // Default Values that will persist if no value is given for the respective field data type
    private static boolean DEFAULT_VALUE_BOOLEAN;
    private static byte DEFAULT_VALUE_BYTE;
    private static char DEFAULT_VALUE_CHAR;
    private static short DEFAULT_VALUE_SHORT;
    private static int DEFAULT_VALUE_INT;
    private static long DEFAULT_VALUE_LONG;
    private static float DEFAULT_VALUE_FLOAT;
    private static double DEFAULT_VALUE_DOUBLE;
    // Useful pieces of information that would be needed to define the mapping and the information for a Field in a database;
    private  String tableName;
    private Field field;
    private String columnName;
    private DataType dataType;
    private Object defaultValue;
    private ColumnFieldConfig fieldConfig;
    private boolean isPrimaryKey;
    private boolean isSerial;
    private boolean isForeignKey;
    private boolean notNull;
    private DataFieldConverter fieldConverter;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public ColumnFieldConfig getFieldConfig() {
        return fieldConfig;
    }

    public void setFieldConfig(ColumnFieldConfig fieldConfig) {
        this.fieldConfig = fieldConfig;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isSerial() {
        return isSerial;
    }

    public void setSerial(boolean serial) {
        isSerial = serial;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

}
