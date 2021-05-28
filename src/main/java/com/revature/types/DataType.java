/*
 * File: DataTypes
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:43 PM
 * Last Modified: 5/14/21, 6:50 PM
 * Created by: Nicholas Recino
 */
package com.revature.types;

import com.revature.util.DataFieldConverter;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * Java Data Types Equivalent to the PostgreSQL Types
 */
public enum DataType implements DataFieldConverter {

    /**
     * The String.
     */
    STRING(PostgreSQLType.VARCHAR, new Class<?>[] {String.class}){

    },
    /**
     * The Boolean.
     */
    BOOLEAN(PostgreSQLType.BOOLEAN, new Class<?>[]{boolean.class}){

    },
    /**
     * The Date.
     */
    DATE(PostgreSQLType.TIMESTAMP, new Class<?>[] { Date.class }) {

        @Override
        public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject) {
            return OffsetDateTime.parse(javaObject.toString());
        }

    },
    /**
     * The Char.
     */
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
    /**
     * The Integer.
     */
    INTEGER(PostgreSQLType.INT4, new Class<?>[]{int.class}){

    },
    /**
     * The Long.
     */
    LONG(PostgreSQLType.INT8, new Class<?>[] { long.class }) {

    },
    /**
     * The Float.
     */
    FLOAT(PostgreSQLType.FLOAT8, new Class<?>[]{float.class}){

    },
    /**
     * The Double.
     */
    DOUBLE(PostgreSQLType.DECIMAL, new Class<?>[] { double.class }) {

    },
    /**
     * The Serial.
     */
    SERIAL(PostgreSQLType.SERIAL4,new Class<?>[]{Long.class}){

    };

    private final PostgreSQLType postgreSQLType;
    private final Class<?>[] classes;

    // Default Implementations, if not implemented above, falls through to here
    // Implicitly private constructor for enum types
    DataType(PostgreSQLType postgreSQLType, Class<?>[] classes) {
        this.postgreSQLType = postgreSQLType;
        this.classes = classes;
    }

    public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject){
        return javaObject;
    }

    /**
     * Returns the Postgres data type that is stored in the database for specified argument.
     * @return the PostgreSQL data type
     */
    @Override
    public PostgreSQLType getPostgreSQLType() {
        return postgreSQLType;
    }

    /**
     * Get the DataType of a specific class.
     *
     * @param clazz the Class whose DataType is being modeled
     * @return the data type that is modeled after a Class
     */
    public static DataType getDataType(Class<?> clazz){
        for(DataType element:values()){
            if(element.classes[0] == clazz)
                return element;
        }
        return null;
    }



}
