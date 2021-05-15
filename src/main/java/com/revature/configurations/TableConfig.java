/*
 * File: TableConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */
package com.revature.configurations;

import com.revature.types.ColumnFieldType;

import java.util.List;

public class TableConfig<T> {

    private Class<T> dataClass;
    private String tableName;
    private List<ColumnFieldConfig> fieldConfigs;
    private ColumnFieldType[] fieldTypes;


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
}
