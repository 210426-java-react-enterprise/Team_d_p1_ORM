/*
 * File: StatementType
 * Team_d_p1_ORM
 * Date created: 5/15/21, 12:39 AM
 * Last Modified: 5/15/21, 12:39 AM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

public enum StatementType {

    /**
     * PostgreSQL statement in the form of INSERT ... FROM table ...
     */
    INSERT(new InsertBuilder()){

        @Override
        public <T> T createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> T createStatementWithCondition(T objectToPersist, String conditionalParam){
            return objectToPersist;
        }
        //TODO change to InsertBuilder Not implemented in dev branch yet
    },
    /**
     * PostgreSQL statement in the form of SELECT ... FROM table ...
     */
    SELECT(new QueryBuilder()) {

        @Override
        public <T> T createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> T createStatementWithCondition(T objectToPersist, String conditionalParam){
            return objectToPersist;
        }
    },
    /**
     * PostgreSQL statement in the form of DELETE FROM table ...
     */
    DELETE(new DeleteBuilder()) {

        @Override
        public <T> T createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> T createStatementWithCondition(T objectToPersist, String conditionalParam){
            return objectToPersist;
        }
    },
    UPDATE(new UpdateBuilder()){

        @Override
        public <T> T createStatement(T objectToPersist) {
            return null;
        }

        @Override
        public <T> T createStatementWithCondition(T objectToPersist, String conditionalParam){
            return objectToPersist;
        }
    },
    /**
     * PostgreSQL statement in the form of CREATE TABLE, ALTER TABLE, or something returning the number of rows affected
     */
    EXECUTE(new QueryBuilder()) {

        @Override
        public <T> T createStatement(T objectToPersist) {
            return objectToPersist;
        }

        @Override
        public <T> T createStatementWithCondition(T objectToPersist, String conditionalParam){
            return objectToPersist;
        }
    };

    private StatementBuilder statementBuilder;

    StatementType(StatementBuilder statementBuilder) {
        this.statementBuilder = statementBuilder;
    }

    public <T> T createStatement(T objectToPersist){
        return objectToPersist;
    }
    public <T> T createStatementWithCondition(T objectToPersist, String conditionalParam){
        return objectToPersist;
    }



}
