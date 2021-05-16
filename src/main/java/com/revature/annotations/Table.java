/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/16/21, 4:03 PM
 * Last Modified: 5/15/21, 1:18 AM
 * Created by: Nicholas Recino
 */

/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/16/21, 4:03 PM
 * Last Modified: 5/15/21, 1:18 AM
 * Created by: Nicholas Recino
 */

/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */
package com.revature.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "";
}
