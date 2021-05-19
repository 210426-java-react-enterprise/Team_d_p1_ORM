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
        public Object createStatement(Object o) {
            return null;
        }
        //TODO change to InsertBuilder Not implemented in dev branch yet
    },
    /**
     * PostgreSQL statement in the form of SELECT ... FROM table ...
     */
    SELECT(new QueryBuilder()) {
        @Override
        public Object createStatement(Object o) {
            return null;
        }
    },
    /**
     * PostgreSQL statement in the form of DELETE FROM table ...
     */
    DELETE(new DeleteBuilder()) {
        @Override
        public Object createStatement(Object o) {
            return null;
        }
    },
    UPDATE(new UpdateBuilder()){
        @Override
        public Object createStatement(Object o) {
            return null;
        }
    },
    /**
     * PostgreSQL statement in the form of CREATE TABLE, ALTER TABLE, or something returning the number of rows affected
     */
    EXECUTE(new QueryBuilder()) {
        @Override
        public Object createStatement(Object o) {
            return null;
        }
    };

    private StatementBuilder o;


    StatementType(StatementBuilder o) {
        this.o = o;
    }

    public abstract Object createStatement(Object o);



}
