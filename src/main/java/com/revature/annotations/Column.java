/*
 * File: Column
 * Team_d_p1_ORM
 * Date created: 5/16/21, 4:06 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */

package com.revature.annotations;

import java.lang.annotation.*;

/**
 * columnName is the name of specified column corresponding in the database
 * notNull specifies whether the specified value is allowed to be null or not
 * unique specifies whether the specified value is unique in the database
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String columnName() default "";
    boolean notNull() default false;
    boolean unique() default false;
}
