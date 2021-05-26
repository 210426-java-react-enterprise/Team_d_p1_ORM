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
            try{
                StatementBuilder insertStatementBuilder = ORMState.getStatementBuilder("insert");
                return insertStatementBuilder.buildStatement(objectToPersist);
            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            try{
                StatementBuilder insertStatementBuilder = ORMState.getStatementBuilder("insert");
                return insertStatementBuilder.buildStatement(objectToPersist,conditionalParam);
            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

        }
        //TODO change to InsertBuilder Not implemented in dev branch yet
    },
    /**
     * PostgreSQL statement in the form of SELECT ... FROM table ...
     */
    SELECT{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            try{
                StatementBuilder insertQueryBuilder = ORMState.getStatementBuilder("query");
                return insertQueryBuilder.buildStatement(objectToPersist);

            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            try{
                StatementBuilder insertQueryBuilder = ORMState.getStatementBuilder("query");
                return insertQueryBuilder.buildStatement(objectToPersist,conditionalParam);

            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

        }
    },
    /**
     * PostgreSQL statement in the form of DELETE FROM table ...
     */
    DELETE{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            try{
                StatementBuilder insertDeleteBuilder = ORMState.getStatementBuilder("delete");
                return insertDeleteBuilder.buildStatement(objectToPersist);

            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

        }
//      (objectPassthrough, "username", "password")
        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            try{
                StatementBuilder insertDeleteBuilder = ORMState.getStatementBuilder("delete");
                return insertDeleteBuilder.buildStatement(objectToPersist,conditionalParam);

            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

        }
    },
    /**
     * PostgreSQL statement in the form of UPDATE ... FROM table
     */
    UPDATE{

        @Override
        public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
            try{
                StatementBuilder insertUpdateBuilder = ORMState.getStatementBuilder("update");
                return insertUpdateBuilder.buildStatement(objectToPersist);

            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
            try{
                StatementBuilder insertUpdateBuilder = ORMState.getStatementBuilder("update");
                return insertUpdateBuilder.buildStatement(objectToPersist,conditionalParam);

            }catch (SQLException e){
                e.printStackTrace();
                throw new ImproperConfigurationException("The configuration of the Object is invalid when compared to the database");
            }

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
