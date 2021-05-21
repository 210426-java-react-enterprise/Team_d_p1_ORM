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

        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString){
            return defaultString;
        }


        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return results.getString(columnPosition);
        }
    },
    BOOLEAN(PostgreSQLType.BOOLEAN, new Class<?>[]{boolean.class}){

        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return results.getBoolean(columnPosition);
        }
        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultStr) {
            return Boolean.parseBoolean(defaultStr);
        }
    },
    DATE(PostgreSQLType.TIMESTAMP, new Class<?>[] { Date.class }) {

        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            Timestamp timeStamp = results.getTimestamp(columnPosition);
            if (timeStamp == null) {
                return null;
            } else {
                return new Date(timeStamp.getTime());
            }
        }
        //TODO figure out how to parse a date object from a string
        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultStr) throws SQLException {
            return OffsetDateTime.parse(defaultStr);
        }
        @Override
        public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject) {
            return OffsetDateTime.parse(javaObject.toString());
        }

    },
    CHAR(PostgreSQLType.CHAR, new Class<?>[] { char.class }) {

        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return results.getString(columnPosition).charAt(0);
        }

        @Override
        public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object javaObject) {
            Character character = (Character) javaObject;
            if (character == null || character == 0) {
                return null;
            } else {
                return character;
            }
        }

        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString) throws SQLException {
            if (defaultString.length() != 1) {
                throw new SQLException();
            }
            return defaultString.charAt(0);
        }
    },
    INTEGER(PostgreSQLType.INT4, new Class<?>[]{int.class}){

        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return  results.getInt(columnPosition);
        }
        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString) {
            return Integer.parseInt(defaultString);
        }
    },
    LONG(PostgreSQLType.INT8, new Class<?>[] { long.class }) {

        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return results.getLong(columnPosition);
        }

        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString) {
            return Long.parseLong(defaultString);
        }
    },
    FLOAT(PostgreSQLType.FLOAT8, new Class<?>[]{float.class}){

        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet resultSet, int columnPosition)throws SQLException{
            return resultSet.getFloat(columnPosition);
        }

        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString){
            return Float.parseFloat(defaultString);
        }

    },
    DOUBLE(PostgreSQLType.DECIMAL, new Class<?>[] { double.class }) {
        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return results.getDouble(columnPosition);
        }

        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString) {
            return Double.parseDouble(defaultString);
        }
    },
    SERIAL(PostgreSQLType.SERIAL4,new Class<?>[]{Long.class}){
        @Override
        public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException {
            return ((long) results.getInt(columnPosition));
        }

        @Override
        public Object parseDefaultString(ColumnFieldType fieldType, String defaultString){
            return (Long.parseLong(defaultString));
        }
    };

    private final PostgreSQLType postgreSQLType;
    private final Class<?>[] classes;

    // Default Implementations, if not implemented above, falls through to here TODO todo tags need to be added throughout for further compliance.
    // Implicitly private constructor for enum types
    DataType(PostgreSQLType postgreSQLType, Class<?>[] classes) {
        this.postgreSQLType = postgreSQLType;
        this.classes = classes;
    }

    public abstract Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition)throws SQLException;

    public abstract Object parseDefaultString(ColumnFieldType fieldType, String defaultString) throws SQLException;

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
