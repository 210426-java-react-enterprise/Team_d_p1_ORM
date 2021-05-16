/*
 * File: StatementType
 * Team_d_p1_ORM
 * Date created: 5/15/21, 12:39 AM
 * Last Modified: 5/15/21, 12:39 AM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

public enum StatementType {

    /** PostgreSQL statement in the form of INSERT ... FROM table ... */
    INSERT,
    /** PostgreSQL statement in the form of SELECT ... FROM table ... */
    SELECT,
    /** PostgreSQL statement in the form of UPDATE table SET ... */
    UPDATE,
    /** PostgreSQL statement in the form of DELETE FROM table ... */
    DELETE,
    /** PostgreSQL statement in the form of CREATE TABLE, ALTER TABLE, or something returning the number of rows affected */
    EXECUTE

}
