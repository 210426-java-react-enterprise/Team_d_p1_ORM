/*
 * File: PostgreSQLType
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:31 AM
 * Last Modified: 5/15/21, 12:26 AM
 * Created by: Nicholas Recino
 */

/*
 * File: PostgreSQLType
 * Team_d_p1_ORM
 * Date created: 5/16/21, 1:31 AM
 * Last Modified: 5/15/21, 12:26 AM
 * Created by: Nicholas Recino
 */

/*
 * File: PostgreSQLTypes
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:43 PM
 * Last Modified: 5/14/21, 6:30 PM
 * Created by: Nicholas Recino
 */
package com.revature.types;

/**
 * Enumerator for all the Postgres data-types that will be allowed to be utilized within the ORM
 *
 * @author Nicholas Recino
 */
public enum PostgreSQLType {
    /**
     * Boolean postgre sql type.
     */
    BOOLEAN,
    /**
     * Char postgre sql type.
     */
    CHAR,
    /**
     * Varchar postgre sql type.
     */
    VARCHAR,
    /**
     * Float 8 postgre sql type.
     */
    FLOAT8,
    /**
     * Int 4 postgre sql type.
     */
    INT4,
    /**
     * Int 8 postgre sql type.
     */
    INT8,
    /**
     * Json postgre sql type.
     */
    JSON,
    /**
     * Decimal postgre sql type.
     */
    DECIMAL,
    /**
     * Timestamp postgre sql type.
     */
    TIMESTAMP,
    /**
     * Serial 4 postgre sql type.
     */
    SERIAL4
}
