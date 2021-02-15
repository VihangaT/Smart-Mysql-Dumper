package com.tech.vihanga.db.dumper.utils;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyreader {
    private static final Logger LOG= LoggerFactory.getLogger(propertyreader.class);

    public static Properties readPropertyFiles(String filename) throws IOException{
        LOG.info("Executing Property Reader");
        FileInputStream fis =null;
        Properties prop=null;
        try{
            LOG.info("InputStream");
            fis =new FileInputStream(filename);
            prop =new Properties();
            prop.load(fis);

        }
        catch(FileNotFoundException fnfe){
            LOG.warn("FileNotFound"+fnfe);
            fnfe.printStackTrace();
        }catch (IOException ioe){
            LOG.warn("FileNotFound"+ioe);
            ioe.printStackTrace();
        }finally {
            fis.close();
            LOG.info("Closing the reader");
        }
        LOG.info("Returning the props"+prop);
        return prop;
    }
}
