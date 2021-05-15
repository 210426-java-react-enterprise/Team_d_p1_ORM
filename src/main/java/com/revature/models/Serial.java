/*
 * File: Serial
 * Team_d_p1_ORM
 * Date created: 5/14/21, 11:44 PM
 * Last Modified: 5/14/21, 11:44 PM
 * Created by: Nicholas Recino
 */

package com.revature.models;

public class Serial extends Number {

    private static long serialClassValue;

    static{
       serialClassValue = 1;
    }

    private  long serialValue;

    public Serial(){
         serialValue = Serial.increment();
    }

    public Serial(long specifiedLast){
        serialValue = setSerialsValue(specifiedLast);
        Serial.increment();
    }

    private static long setSerialsValue(long value){
        return serialClassValue = value;
    }

    private static long increment(){
        return serialClassValue++;
    }

    public long getSerialValue() {
        return serialValue;
    }

    @Override
    public int intValue() {
        return Integer.max(Integer.MAX_VALUE,(int)(serialValue));
    }

    @Override
    public long longValue() {
        return serialValue;
    }

    @Override
    public float floatValue() {
        return (float)(serialValue);
    }

    @Override
    public double doubleValue() {
        return (double)serialValue;
    }

    public void setSerialValue(long serialValue) {
        this.serialValue = serialValue;
    }


}
