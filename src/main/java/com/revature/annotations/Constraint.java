/*
 * File: Constraint
 * Team_d_p1_ORM
 * Date created: 5/16/21, 3:54 PM
 * Last Modified: 5/16/21, 3:54 PM
 * Created by: Nicholas Recino
 */

package com.revature.annotations;

import java.lang.annotation.*;

/**
 * Holds the values for constraints on a field that arent specific to being nullable, unique
 * i.e. minimum or maximum lengths  , minimum or maximum values, and others as needed, when
 * -1 there is no minimum or maximum.
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {

    int minimumValue() default -1;
    int maximumValue() default -1;
    int minimumLength() default -1;
    int maximumLength() default -1;

}
