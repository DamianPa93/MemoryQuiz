
package com.app.memoryquiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CrunchifyGetPropertyValue {
    String result = "";
    int value = 0;
    InputStream inputStream;
    
    public int getParamAnswersValue() throws IOException{
        
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            inputStream = this.getClass().getResourceAsStream("/res/config.properties");
            
            if(inputStream != null){
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");               
            }
            value = Integer.parseInt(prop.getProperty("paramAnswers"));
            
            System.out.println(value);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return value;
    }
    
    public int getParamNotificationDelayValue() throws IOException{
        
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            inputStream = this.getClass().getResourceAsStream("/res/config.properties");
            
            if(inputStream != null){
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");               
            }
            value = Integer.parseInt(prop.getProperty("paramNotificationDelay"));
            
            System.out.println(value);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return value;
    }
    
    public int getParamNotificationTimeValue() throws IOException{
        
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            inputStream = this.getClass().getResourceAsStream("/res/config.properties");
            
            if(inputStream != null){
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");               
            }
            value = Integer.parseInt(prop.getProperty("paramNotificationTime"));
            
            System.out.println(value);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return value;
    }
}
