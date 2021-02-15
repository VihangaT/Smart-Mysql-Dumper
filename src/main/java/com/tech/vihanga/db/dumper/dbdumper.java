package com.tech.vihanga.db.dumper;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.File;
import java.io.IOException;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.TimerTask;

import com.tech.vihanga.db.dumper.utils.DBarrayMaker;
import com.tech.vihanga.db.dumper.utils.propertyreader;
import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;


public class dbdumper extends TimerTask {
    private  static final Logger LOG= LoggerFactory.getLogger(dbdumper.class);

    public  static void dumper() throws InterruptedException, IOException {

        try {
            LOG.info("Reading the configurations");
            propertyreader reader=new propertyreader();
            Properties prop=reader.readPropertyFiles("config/app.properties");

            LOG.info("Assigning the values..");
            String DBurl= prop.getProperty("database.url");
            String username=prop.getProperty("username");
            String password=prop.getProperty("password");
            String mysqldumpPath=prop.getProperty("mysqldumpPath");
            String backupPath= prop.getProperty("backupPath");
            String databases= prop.getProperty("databases");

            LOG.info("Creating the database array");
            DBarrayMaker DBM=new DBarrayMaker();
            String[] databasesArray=DBM.arrayMaker(databases);
            LOG.info("Creating the timestamps...");
            long date =System.currentTimeMillis();
            LocalDateTime Datesimple=LocalDateTime.now();
            LOG.info("Creating the Saving Path");
            String savePath =backupPath+"/"+date;
            LOG.info("Creating the saving dirrectory");
            File f1=new File(savePath);
            f1.mkdir();
            LOG.info("Iterating the databases array");
                for (String db:databasesArray){
                    LOG.info("Taking the backup of "+db);
                    String execute=mysqldumpPath+"/mysqldump -h"+ DBurl +"-u" +username +" -p" +password +" " + db + " -r " + savePath+"/"+db+".sql";
                    LOG.info("To be executed>>>>"+execute);
                    LOG.info("Executing the process");
                    Process runtimeprocess =Runtime.getRuntime().exec(execute);
                    int processComplete = runtimeprocess.waitFor();
                    LOG.info("Process Execution Completed");
                    /*It will return 0 if the process in completed,otherwise it'll send some other value*/

                    if (processComplete==0){
                        LOG.info(db+ " Backup Completed");
                    }else {
                        LOG.warn("Backup Failed");
                    }
                }
        LOG.info("");

        }catch (Exception ex){
            LOG.warn("Error"+ex.getMessage());
            LOG.warn("Error"+ex);
        }
    }
    @Override
    public void run() {
        dbdumper runner =new dbdumper();
        try{
            runner.dumper();
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
