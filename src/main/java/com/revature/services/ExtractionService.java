/*
 * File: ExtractionService
 * Team_d_p1_ORM
 * Date created: 5/22/21, 5:48 PM
 * Last Modified: 5/22/21, 5:48 PM
 * Created by: Nicholas Recino
 */

package com.revature.services;

import com.revature.annotations.Column;
import com.revature.annotations.Table;
import com.revature.configurations.ColumnFieldConfig;
import com.revature.exception.ImproperConfigurationException;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExtractionService {
    /**
     *
     * @param objectToPersist
     * @param tableName
     * @return
     * @throws ImproperConfigurationException
     */
    public static List<ColumnFieldType> extractData(Object objectToPersist,String tableName) throws ImproperConfigurationException {
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
        return columnFieldTypes;
    }

    /**
     *
     * @param fieldConfig
     * @param column
     * @param field
     * @return
     */
    private static ColumnFieldConfig columnFieldConfigSetup(ColumnFieldConfig fieldConfig, Column column, Field field){
        String columnName = column.columnName();
        fieldConfig.setColumnName((columnName.equals("") ? field.getName().toLowerCase() : columnName));
        fieldConfig.setNotNull(column.notNull());
        fieldConfig.setUnique(column.unique());
        fieldConfig.setDataType(DataType.getDataType(field.getType()));
        fieldConfig.setFieldName(field.getName());
        return fieldConfig;
    }


    /**
     * Extracts and return the table name for a class seeing first if the name specified in the annotation is present and if not,
     * then simply making the class name lowercase the table name.
     * @param clazz Class name to extract string from
     * @return returns a table name from a class object that has been annotated
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
}
