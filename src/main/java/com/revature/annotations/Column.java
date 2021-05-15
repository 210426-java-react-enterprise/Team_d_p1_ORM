/*
 * File: Column
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */

/*
 * File: Column
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:44 PM
 * Last Modified: 5/14/21, 6:52 PM
 * Created by: Nicholas Recino
 */

/*
 * File: Column
 * Team_d_p1_ORM
 * Date created: 5/14/21, 4:30 PM
 * Last Modified: 5/14/21, 4:21 PM
 * Created by: Nicholas Recino
 */

package com.revature.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String columnName() default "";
    boolean notNull() default false;
    boolean primaryKey() default false;
    boolean foreignKey() default false;
    boolean unique() default false;
    boolean serialID() default false;

}
