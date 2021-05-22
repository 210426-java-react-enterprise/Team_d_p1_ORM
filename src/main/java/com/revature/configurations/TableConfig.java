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
    public TableConfig(Class<?> dataClass){
        this.dataClass = dataClass;
        this.tableName = extractTableName(dataClass);
        this.fieldTypes = extractFieldTypes(dataClass);
    }

    public TableConfig(Object objectToBePersisted) throws ImproperConfigurationException {
        this.dataClass = objectToBePersisted.getClass();
        this.tableName = extractTableName(dataClass);
        extractData(objectToBePersisted);
    }

    /**
     * Setup a table config associated with the dataClass, table-name, and field configurations.
     */
    public TableConfig(Class<?> dataClass, String tableName) {
        this.dataClass = dataClass;
        this.tableName = tableName;
        this.fieldTypes = extractFieldTypes(dataClass);

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
        String tableName = extractTableName(clazz);
        return new TableConfig(clazz, tableName);
    }

// TODO might need to put these features in their own behaviour/service layer unless configs count as that
    /**
     * Extracts and return the table name for a class seeing first if the name specified in the annotation is present and if not,
     * then simply making the class name lowercase the table name.
     */
    public static  String extractTableName(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        String name = null;
        if (table != null && table.name().length() > 0) {
            name = table.name();
        } else {
                name = clazz.getSimpleName().toLowerCase();
            }
        return name;
    }

    private List<ColumnFieldType> extractFieldTypes(Class<?> clazz){
        List<ColumnFieldType> columnFieldTypes = new ArrayList<>();
        for(Class<?> classParse = clazz; classParse!=null; classParse = classParse.getSuperclass()){
            for(Field field: classParse.getDeclaredFields()){
               Column column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                ColumnFieldConfig fieldConfig = new ColumnFieldConfig();

                ColumnFieldType fieldInfo = new ColumnFieldType(columnFieldConfigSetup(fieldConfig,column,field));
                fieldInfo.setTableName(tableName);
                fieldInfo.setField(field);
                columnFieldTypes.add(fieldInfo);
            }
        }
        return  columnFieldTypes;
    }
    private ColumnFieldConfig columnFieldConfigSetup(ColumnFieldConfig fieldConfig,Column column,Field field){
        String columnName = column.columnName();
        fieldConfig.setColumnName((columnName.equals("") ? field.getName().toLowerCase() : columnName));
        fieldConfig.setNotNull(column.notNull());
        fieldConfig.setUnique(column.unique());
        fieldConfig.setDataType(DataType.getDataType(field.getType()));
        fieldConfig.setFieldName(field.getName());
        return fieldConfig;
    }

    public void extractData(Object objectToPersist) throws ImproperConfigurationException {
        Class<?> clazz = objectToPersist.getClass();
        List<ColumnFieldType> columnFieldTypes = new ArrayList<>();
        for(Class<?> classParse = clazz; classParse!=null; classParse = classParse.getSuperclass()){
            for(Field field: classParse.getDeclaredFields()){
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                ColumnFieldConfig fieldConfig = new ColumnFieldConfig();
                ColumnFieldType fieldInfo = new ColumnFieldType(columnFieldConfigSetup(fieldConfig,column,field));
                try {
                    fieldInfo.setDefaultValue(field.get(objectToPersist));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }catch(NullPointerException e){
                    if(column.notNull()){
                        throw new ImproperConfigurationException("Value that is classified as not null, is infact null");
                    }
                }
                fieldInfo.setTableName(tableName);
                fieldInfo.setField(field);
                columnFieldTypes.add(fieldInfo);
            }
        }
        this.fieldTypes = columnFieldTypes;
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
