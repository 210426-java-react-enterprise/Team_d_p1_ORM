/*
 * File: ColumnFieldType
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:43 PM
 * Last Modified: 5/14/21, 7:22 PM
 * Created by: Nicholas Recino
 */
package com.revature.types;

import com.revature.configurations.ColumnFieldConfig;
import java.lang.reflect.Field;

/**
 * The type Column field type.
 */
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

    /**
     * Instantiates a new Column field type.
     *
     * @param config the config
     */
    public ColumnFieldType(ColumnFieldConfig config){
        implementConfig(config);
        fieldConfig = config;
    }

    /**
     * Instantiates a new Column field type.
     */
    public ColumnFieldType(){

    }

    /**
     * Gets column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Set column name.
     *
     * @param columnName the column name
     */
    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

    /**
     * Gets table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets table name.
     *
     * @param tableName the table name
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * Sets field.
     *
     * @param field the field
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * Gets data type.
     *
     * @return the data type
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Sets data type.
     *
     * @param dataType the data type
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets default value.
     *
     * @return the default value
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets default value.
     *
     * @param defaultValue the default value
     */
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets field config.
     *
     * @return the field config
     */
    public ColumnFieldConfig getFieldConfig() {
        return fieldConfig;
    }

    /**
     * Sets field config.
     *
     * @param fieldConfig the field config
     */
    public void setFieldConfig(ColumnFieldConfig fieldConfig) {
        this.fieldConfig = fieldConfig;
        implementConfig(this.fieldConfig);
    }

    /**
     *  Implements a new config from another source into this FieldType
     * @param config The field config to implement
     */
    private void implementConfig(ColumnFieldConfig config){
        this.dataType = config.getDataType();
        this.columnName = config.getColumnName();
    }

    @Override
    public String toString() {
        return "ColumnFieldType{\n" +
                "tableName='" + tableName + "\n" +
                ", columnName='" + columnName + '\n' +
                ", dataType=" + dataType + '\n' +
                ", dataValue="+defaultValue+'\n'+
                ", fieldConfig=" + fieldConfig +
                '}'+"\n\n";
    }
}
