package com.company.external.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogConfig {
    static {
        PropertyConfigurator.configure("D:/dev/old/DevLibrary/JAVA/MAIN/bank/src/main/resources/log4j.properties");
    }

    public static Logger getLogger(Class getClass) {
        return Logger.getLogger(getClass);
    }
}