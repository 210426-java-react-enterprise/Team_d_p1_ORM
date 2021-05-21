/*
 * File: StatementType
 * Team_d_p1_ORM
 * Date created: 5/15/21, 12:39 AM
 * Last Modified: 5/15/21, 12:39 AM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

import java.sql.ResultSet;

public enum StatementType {

    /**
     * PostgreSQL statement in the form of INSERT ... FROM table ...
     */
    INSERT(new InsertBuilder()){

        @Override
        public <T> ResultSet createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
            return null;
        }
        //TODO change to InsertBuilder Not implemented in dev branch yet
    },
    /**
     * PostgreSQL statement in the form of SELECT ... FROM table ...
     */
    SELECT(new QueryBuilder()) {

        @Override
        public <T> ResultSet createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
            return null;
        }
    },
    /**
     * PostgreSQL statement in the form of DELETE FROM table ...
     */
    DELETE(new DeleteBuilder()) {

        @Override
        public <T> ResultSet createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
            return null;
        }
    },
    UPDATE(new UpdateBuilder()){

        @Override
        public <T> ResultSet createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
            return null;
        }
    },
    /**
     * PostgreSQL statement in the form of CREATE TABLE, ALTER TABLE, or something returning the number of rows affected
     */
    EXECUTE(new QueryBuilder()) {

        @Override
        public <T> ResultSet createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
            return null;
        }
    };

    private StatementBuilder statementBuilder;

    StatementType(StatementBuilder statementBuilder) {
        this.statementBuilder = statementBuilder;
    }

    public <T> ResultSet createStatement(T objectToPersist){
        return null;
    }
    public <T> ResultSet createStatementWithCondition(T objectToPersist, String... conditionalParam){
        return null;
    }



}
