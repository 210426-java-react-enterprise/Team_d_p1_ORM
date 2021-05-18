/*
 * File: StatementBuilder
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:54 PM
 * Last Modified: 5/14/21, 7:54 PM
 * Created by: Nicholas Recino
 */

package com.revature.statements;

// Builds Statements based upon table info and field types, extended for specific statement types, ALA the Sub languages of SQL

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 */
public abstract class StatementBuilder{

    protected StatementType type;
    protected PreparedStatement sqlStatement;
    protected Connection conn;
}
