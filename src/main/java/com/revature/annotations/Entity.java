/*
 * File: Entity
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */
package com.revature.annotations;

import java.lang.annotation.*;

/**
 * Specifies an entity to be saved within a table, though not necessarily a table object itself dependent on normalization
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {

}
