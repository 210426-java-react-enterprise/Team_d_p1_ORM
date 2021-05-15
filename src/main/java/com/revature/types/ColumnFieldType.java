/*
 * File: ColumnFieldType
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:43 PM
 * Last Modified: 5/14/21, 7:22 PM
 * Created by: Nicholas Recino
 */

/*
 * File: FieldType
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:09 PM
 * Last Modified: 5/14/21, 7:09 PM
 * Created by: Nicholas Recino
 */

package com.revature.types;

import com.revature.configurations.ColumnFieldConfig;
import com.revature.services.DataFieldConverter;

import java.lang.reflect.Field;

public class ColumnFieldType {

    // Default Values that will persist if no value is given for the respective field data type
    private static boolean DEFAULT_VALUE_BOOLEAN;
    private static byte DEFAULT_VALUE_BYTE;
    private static char DEFAULT_VALUE_CHAR;
    private static short DEFAULT_VALUE_SHORT;
    private static int DEFAULT_VALUE_INT;
    private static long DEFAULT_VALUE_LONG;
    private static float DEFAULT_VALUE_FLOAT;
    private static double DEFAULT_VALUE_DOUBLE;
    // Useful pieces of information that would be needed to define the mapping and the information for a Field in a database;
    private  String tableName;
    private Field field;
    private  String fieldName;
    private  DataTypes dataType;
    private Object defaultValue;
    private ColumnFieldConfig fieldConfig;
    private boolean isPrimaryKey;
    private boolean isSerial;
    private boolean isForeignKey;
    private DataFieldConverter fieldConverter;
}
