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


    public <T> ResultSet createStatement(T objectToPersist) throws ImproperConfigurationException {
        return null;
    }
    public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam) throws ImproperConfigurationException {
        return null;
    }



}
