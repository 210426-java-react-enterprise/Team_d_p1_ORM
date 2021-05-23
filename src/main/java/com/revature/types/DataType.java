/*
 * File: DataTypes
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:43 PM
 * Last Modified: 5/14/21, 6:50 PM
 * Created by: Nicholas Recino
 */
package com.revature.types;

import com.revature.util.DataFieldConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * Java Data Types Equivalent to the PostgreSQL Types
 */
//TODO Determine what types correspond to which PSQL types Still need
//    -- JSON
public enum DataType implements DataFieldConverter {

    STRING(PostgreSQLType.VARCHAR, new Class<?>[] {String.class}){

    },
    BOOLEAN(PostgreSQLType.BOOLEAN, new Class<?>[]{boolean.class}){

    },
    DATE(PostgreSQLType.TIMESTAMP, new Class<?>[] { Date.class }) {

        @Override
        public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject) {
            return OffsetDateTime.parse(javaObject.toString());
        }

    },
    CHAR(PostgreSQLType.CHAR, new Class<?>[] { char.class }) {

        @Override
        public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject) {
            Character character = (Character) javaObject;
            if (character == null || character == 0) {
                return null;
            } else {
                return character;
            }
        }

    },
    INTEGER(PostgreSQLType.INT4, new Class<?>[]{int.class}){

    },
    LONG(PostgreSQLType.INT8, new Class<?>[] { long.class }) {

    },
    FLOAT(PostgreSQLType.FLOAT8, new Class<?>[]{float.class}){

    },
    DOUBLE(PostgreSQLType.DECIMAL, new Class<?>[] { double.class }) {

    },
    SERIAL(PostgreSQLType.SERIAL4,new Class<?>[]{Long.class}){

    };

    private final PostgreSQLType postgreSQLType;
    private final Class<?>[] classes;

    // Default Implementations, if not implemented above, falls through to here TODO todo tags need to be added throughout for further compliance.
    // Implicitly private constructor for enum types
    DataType(PostgreSQLType postgreSQLType, Class<?>[] classes) {
        this.postgreSQLType = postgreSQLType;
        this.classes = classes;
    }

    public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject){
        return javaObject;
    }

    @Override
    public PostgreSQLType getPostgreSQLType() {
        return postgreSQLType;
    }

    public static DataType getDataType(Class<?> clazz){
        for(DataType element:values()){
            if(element.classes[0] == clazz)
                return element;
        }
        return null;
    }



}
