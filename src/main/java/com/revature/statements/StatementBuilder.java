/*
 * File: StatementBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:54 PM
 * Last Modified: 5/14/21, 7:54 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

// Builds Statements based upon table info and field types, extended for specific statement types, ALA the Sub languages of SQL

/**
 *
 * @param <T> Class that the Statements Will be acted upon
 * @param <ID_CLASS> Class of the primary key / foreign key ( ID ) not needed Object can satisfy this reference
 */
public abstract class StatementBuilder<T, ID_CLASS> {

    protected StatementType type;
}
