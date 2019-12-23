package com.luxoft.analyzer.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private String classType;

    public Log(Class aClass) {
        this.classType=aClass.getTypeName();
    }

    public void error(String message){
        printMessage("error", message);
    }
    public void info(String message){
        printMessage("info", message);
    }

    public void debug(String message){
        printMessage("debug", message);
    }

    private void printMessage(String type, String message){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String printMessage = String.format("| %s | %s | %s | %s", simpleDateFormat.format(date), type, classType, message);
        System.out.println(printMessage);
    }
}
