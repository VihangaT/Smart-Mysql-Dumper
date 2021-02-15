package com.tech.vihanga.db.dumper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBarrayMaker {
    private static final Logger LOG= LoggerFactory.getLogger(DBarrayMaker.class);

    public static String[] arrayMaker(String databasesString){
        LOG.info("Executing the array maker");
        String[] DatabasesArray=databasesString.split(",");
        LOG.info("Returning the databases array");
        return  DatabasesArray;
    }
}
