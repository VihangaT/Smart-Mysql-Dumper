package com.tech.vihanga.db.dumper;

import com.tech.vihanga.db.dumper.utils.propertyreader;
//import jdk.internal.org.jline.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class Executer {
    private static final Logger LOG= LoggerFactory.getLogger(Executer.class);
    public static void main(String[] args) throws InterruptedException, IOException{

        LOG.info("Application is starting.....");
        LOG.info("invoking the property reader....");
        propertyreader reader = new propertyreader();
        Properties prop=reader.readPropertyFiles("config/app.properties");
        int timePeriod=Integer.parseInt(prop.getProperty("timePeriod"));

        try {
            LOG.info("Schedular Declaration");
            Timer timer=new Timer();
            TimerTask DumpTask=new dbdumper();
            /*DIR CLEARNER CALLS SHOULD BE ADDED*/
            LOG.info("Executing dbdumper");
            timer.schedule(DumpTask,0,timePeriod);

        }catch (Exception ex){
            LOG.warn("Error"+ex);
            LOG.warn("Error:" +ex.getMessage());
        }
    }
}
