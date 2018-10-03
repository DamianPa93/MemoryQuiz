package com.app.memoryquiz;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CrunchifyUpdateConfig {
    public void update(int paramAnswers, int paramNotificationTime, int paramNotificationDelay) throws ConfigurationException{
        PropertiesConfiguration config = new PropertiesConfiguration("/Users/asus/Documents/NetBeansProjects/MemoryQuiz/src/main/resources/res/config.properties");
        config.setProperty("paramAnswers", paramAnswers);
        config.setProperty("paramNotificationTime", paramNotificationTime);
        config.setProperty("paramNotificationDelay", paramNotificationDelay);
        config.save();
        
        System.out.println("Config Property Successfully Updated..");
    }
}