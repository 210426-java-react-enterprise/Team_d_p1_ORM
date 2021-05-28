/*
 * File: ImproperConfigurationException
 * Team_d_p1_ORM
 * Date created: 5/21/21, 11:13 PM
 * Last Modified: 5/21/21, 11:13 PM
 * Created by: Nicholas Recino
 */

package com.revature.exception;

public class ImproperConfigurationException extends  Exception{

    /**
     *  Thrown when the expected configuration of an object does not meet what an object that is passed through to be modeled after
     * @param message to pass-through to the method handling this exception
     */
    public ImproperConfigurationException(String message){
        super(message);
    }

}
