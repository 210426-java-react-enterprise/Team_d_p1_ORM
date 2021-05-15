/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */

/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:44 PM
 * Last Modified: 5/14/21, 4:31 PM
 * Created by: Nicholas Recino
 */

/*
 * File: Table
 * Team_d_p1_ORM
 * Date created: 5/14/21, 4:31 PM
 * Last Modified: 5/14/21, 4:12 PM
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
