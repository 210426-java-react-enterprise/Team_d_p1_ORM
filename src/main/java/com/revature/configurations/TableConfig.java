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
import com.revature.types.ColumnFieldType;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableConfig<T> {

    private Class<T> dataClass;
    private String tableName;
    private List<ColumnFieldConfig> fieldConfigs;
    private ColumnFieldType[] fieldTypes;


    /**
     * Setup a table config associated with the dataClass and field configurations. The table-name will be extracted
     * from the dataClass.
     */
    public TableConfig(Class<T> dataClass, List<ColumnFieldConfig> fieldConfigs) {
        this(dataClass, extractTableName(dataClass), fieldConfigs);
    }

    /**
     * Setup a table config associated with the dataClass, table-name, and field configurations.
     */
    public TableConfig(Class<T> dataClass, String tableName, List<ColumnFieldConfig> fieldConfigs) {
        this.dataClass = dataClass;
        this.tableName = tableName;
        this.fieldConfigs = fieldConfigs;
    }

    private TableConfig(Class<T> dataClass, String tableName, ColumnFieldType[] fieldTypes) {
        this.dataClass = dataClass;
        this.tableName = tableName;
        this.fieldTypes = fieldTypes;
    }


    public Class<T> getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnFieldConfig> getFieldConfigs() {
        return fieldConfigs;
    }

    public void setFieldConfigs(List<ColumnFieldConfig> fieldConfigs) {
        this.fieldConfigs = fieldConfigs;
    }

    public ColumnFieldType[] getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(ColumnFieldType[] fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    /**
     * Extract the TableConfig for a particular class by looking for class and field annotations. This is used
     *  internally by other classes to configure a class.
     */
    public static <T> TableConfig<T> fromClass(Class<T> clazz) throws SQLException {
        String tableName = extractTableName(clazz);
        return new TableConfig<>(clazz, tableName, extractFieldTypes(clazz));
    }

// TODO might need to put these features in their own behaviour/service layer unless configs count as that
    /**
     * Extracts and return the table name for a class seeing first if the name specified in the annotation is present and if not,
     * then simply making the class name lowercase the table name.
     */
    public static <T> String extractTableName(Class<T> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        String name = null;
        if (table != null && table.name().length() > 0) {
            name = table.name();
        } else {
                name = clazz.getSimpleName().toLowerCase();
            }
        return name;
    }

    public static <T> ColumnFieldType[] extractFieldTypes(Class<T> clazz){
        List<ColumnFieldType> columnFieldTypes = new ArrayList<>();
        for(Class<?> classParse = clazz; clazz!=null; classParse = classParse.getSuperclass()){
            for(Field field: classParse.getDeclaredFields()){
                //TODO Need to define a ColumnFieldType Properly, above logic will grab all fields associated with a class object
                ColumnFieldType fieldInfo = new ColumnFieldType();
                if(fieldInfo!=null)
                    columnFieldTypes.add(fieldInfo);
            }
        }
        return columnFieldTypes.toArray(new ColumnFieldType[columnFieldTypes.size()]);
    }

}
