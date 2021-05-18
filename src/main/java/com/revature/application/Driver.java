package com.revature.application;

import com.revature.configurations.TableConfig;

public class Driver {
    public static void main(String[] args) {

        System.out.println(TableConfig.extractTableName(Integer.class));
    }
}
