/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/15/21, 1:05 AM
 * Last Modified: 5/15/21, 1:05 AM
 * Created by: Nicholas Recino
 */

package com.revature.models;

import com.revature.configurations.TableConfig;
import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTable<T> {

    private final Repo repo;
    private final Class<?> dataClass;
    private final String tableName;
    private final List<ColumnFieldType> fieldTypes;
    private final ColumnFieldType idField;
    private Constructor<T> constructor;
    private Map<String, ColumnFieldType> fieldTypeNameMap;

    public DatabaseTable(Repo repo, Class<?> dataClass)throws SQLException{
        this(repo,TableConfig.fromClass(dataClass));

    }

    public DatabaseTable(Repo repo, TableConfig tableConfig )throws SQLException{
        this.repo = repo;
        this.dataClass = tableConfig.getDataClass();
        this.tableName = tableConfig.getTableName();
        this.fieldTypes = tableConfig.getFieldTypes();
        // TODO determine best way to get ID field PK/FK
        this.idField = null;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public String getTableName() {
        return tableName;
    }

    public List<ColumnFieldType> getFieldTypes() {
        return fieldTypes;
    }

    public ColumnFieldType getIdField() {
        return idField;
    }

    public Constructor<T> getConstructor() {
        return constructor;
    }

    public ColumnFieldType getFieldTypeByColumnName(String columnName) {
        if (fieldTypeNameMap == null) {
            // build our alias map if we need it
            Map<String, ColumnFieldType> map = new HashMap<>();
            for (ColumnFieldType fieldType : fieldTypes) {
                map.put(fieldType.getColumnName(), fieldType);
            }
            fieldTypeNameMap = map;
        }
        return fieldTypeNameMap.get(columnName);
    }

    /**
     * Return a string representation of the fields of an object of type T from the Class that the table represents.
     */
    public String objectToString(T object) {
        StringBuilder sb = new StringBuilder(64);
        sb.append(object.getClass().getSimpleName());
        for (ColumnFieldType fieldType : fieldTypes) {
            sb.append(' ').append(fieldType.getColumnName()).append("=");
        }
        return sb.toString();
    }
}
