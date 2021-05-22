/*
 * File: TableConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */
package com.revature.configurations;

import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;
import com.revature.annotations.Column;
import com.revature.exception.ImproperConfigurationException;
import com.revature.services.ExtractionService;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableConfig {

    private Class<?> dataClass;
    private String tableName;
    private List<ColumnFieldType> fieldTypes;


    /**
     * Setup a table config associated with the dataClass and field configurations. The table-name will be extracted
     * from the dataClass.
     */


    public TableConfig(Object objectToBePersisted) throws ImproperConfigurationException {
        this.dataClass = objectToBePersisted.getClass();
        this.tableName = ExtractionService.extractTableName(dataClass);
        extractData(objectToBePersisted);
    }

    /**
     * Setup a table config associated with the dataClass, table-name, and field configurations.
     */
    public TableConfig(Class<?> dataClass, String tableName) {
        this.dataClass = dataClass;
        this.tableName = tableName;
    }

    private TableConfig(Class<?> dataClass, String tableName, List<ColumnFieldType> fieldTypes) {
        this.dataClass = dataClass;
        this.tableName = tableName;
        this.fieldTypes = fieldTypes;
    }



    public Class<?> getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class<?> dataClass) {
        this.dataClass = dataClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnFieldType> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(List<ColumnFieldType> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    /**
     * Extract the TableConfig for a particular class by looking for class and field annotations. This is used
     *  internally by other classes to configure a class.
     */
    public static TableConfig fromClass(Class<?> clazz) throws SQLException {
        String tableName = ExtractionService.extractTableName(clazz);
        return new TableConfig(clazz, tableName);
    }

    public void extractData(Object objectToPersist) throws ImproperConfigurationException {
        this.fieldTypes = ExtractionService.extractData(objectToPersist,tableName);
    }

    @Override
    public String toString() {
        return "TableConfig{" +
                "dataClass=" + dataClass +
                ", tableName='" + tableName + '\'' +
                ", fieldTypes=" + fieldTypes +
                '}';
    }
}
