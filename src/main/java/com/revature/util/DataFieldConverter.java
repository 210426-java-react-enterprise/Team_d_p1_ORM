/*
 * File: DataFieldConverter
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:32 AM
 * Last Modified: 5/15/21, 7:53 PM
 * Created by: Nicholas Recino
 */

/*
 * File: DataFieldConverter
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:31 AM
 * Last Modified: 5/15/21, 7:53 PM
 * Created by: Nicholas Recino
 */

/*
 * File: DataFieldConverter
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:31 AM
 * Last Modified: 5/15/21, 7:53 PM
 * Created by: Nicholas Recino
 */

/*
 * File: DataFieldConverter
 * Team_d_p1_ORM
 * Date created: 5/14/21, 9:34 PM
 * Last Modified: 5/14/21, 7:49 PM
 * Created by: Nicholas Recino
 */
package com.revature.util;

import com.revature.types.ColumnFieldType;
import com.revature.types.PostgreSQLType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Converts The  Data Type From @Column to its proper PostgreSQL data type
 */
public interface DataFieldConverter{

    /**
     *  Converts a default String using a specified column field type, and returns the appropriate argument to a PSQL insert or update statement.
     * @param fieldType
     * @param defaultString
     * @return Returns the appropriate argument to a PSQL insert or update statement.
     * @throws SQLException
     */
    public Object parseDefaultString(ColumnFieldType fieldType, String defaultString)throws SQLException;

    /**
     * Converts a Java Object(Reflection) and returns the appropriate argument to a PSQL insert or update statement.
     * @param fieldType
     * @param obj
     * @return
     * @throws SQLException
     */
    public Object javaToPostgreSQLArguments(ColumnFieldType fieldType, Object obj)throws SQLException;

    /**
     *  Returns the object extracted from the results associated with column specified in position columnPosition
     * @param fieldType Associated ColumnFieldType potentially might be null.
     * @param results
     * @param columnPosition
     * @return
     * @throws SQLException
     */
    public Object resultToJava(ColumnFieldType fieldType, ResultSet results, int columnPosition) throws SQLException;

    /**
     * Returns the Postgres data type that is stored in the database for specified argument.
     * @return
     */
    public PostgreSQLType getPostgreSQLType();
}
