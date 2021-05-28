/*
 * File: StatementType
 * Team_d_p1_ORM
 * Date created: 5/15/21, 12:39 AM
 * Last Modified: 5/15/21, 12:39 AM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

import com.revature.exception.ImproperConfigurationException;
import com.revature.util.ORMState;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The enum Statement type.
 */
public enum StatementType {

    /**
     * PostgreSQL statement in the form of INSERT ... FROM table ...
     */
    INSERT{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            return getStatementBuilderFromORM(objectToPersist, "insert");
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            return getStatementBuilderFromORMWithCondition(objectToPersist, conditionalParam, "insert");
        }
    },
    /**
     * PostgreSQL statement in the form of SELECT ... FROM table ...
     */
    SELECT{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            return getStatementBuilderFromORM(objectToPersist, "query");
        }
        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            return getStatementBuilderFromORMWithCondition(objectToPersist, conditionalParam, "query");
        }
    },
    /**
     * PostgreSQL statement in the form of DELETE FROM table ...
     */
    DELETE{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            return getStatementBuilderFromORM(objectToPersist, "delete");
        }
//      (objectPassthrough, "username", "password")
        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            return getStatementBuilderFromORMWithCondition(objectToPersist, conditionalParam, "delete");
        }
    },
    /**
     * PostgreSQL statement in the form of UPDATE ... FROM table
     */
    UPDATE{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            return getStatementBuilderFromORM(objectToPersist, "update");
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            return getStatementBuilderFromORMWithCondition(objectToPersist, conditionalParam, "update");
        }
    },
    /**
     * PostgreSQL statement in the form of CREATE TABLE, ALTER TABLE, or something returning the number of rows affected
     */
    EXECUTE{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
            return null;
        }
    };

    private static ResultSet getStatementBuilderFromORMWithCondition(Object objectToPersist, String[] conditionalParam, String builderName) throws ImproperConfigurationException {
        try {
            return ORMState.getStatementBuilder(builderName).buildStatement(objectToPersist, conditionalParam);
        } catch (SQLException e) {
            throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
        }
    }

    private static ResultSet getStatementBuilderFromORM(Object objectToPersist, String builderName) throws ImproperConfigurationException {
        try {
            return ORMState.getStatementBuilder(builderName).buildStatement(objectToPersist);
        } catch (SQLException e) {
            throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
        }
    }


    /**
     * Executes a statement built around the mapping of a specific object
     *
     * @param <T>             the type parameter of the object to be persisted
     * @param objectToPersist the object to persist
     * @return the result set generated from the execution of a Postgresql statement
     * @throws ImproperConfigurationException when the shape of the table and constraints do not match the object passed through
     */
    public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
        return null;
    }

    /**
     * Executes a statement built around the mapping of a specific object with specific conditions
     *
     * @param <T>             the type parameter of the object to be persisted
     * @param objectToPersist the object to persist
     * @param conditionalParam the string names of the conditional parameters that alter the statement creation process
     * @return the result set generated from the execution of a Postgresql statement
     * @throws ImproperConfigurationException when the shape of the table and constraints do not match the object passed through
     */
    public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
        return null;
    }

}
