
package com.app.memoryquiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;


public class CrunchifyGetPropertyValue {
    String result = "";
    int value = 0;
    InputStream inputStream;
    
    public String getPropValues() throws IOException{
        
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            //inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            inputStream = this.getClass().getResourceAsStream("/res/config.properties");
            
            if(inputStream != null){
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");               
            }
            
            Date time = new Date(System.currentTimeMillis());
            
            //get the property value and print it out
            String paramAnswers = prop.getProperty("paramAnswers");
            String paramNotificationDelay = prop.getProperty("paramNotificationDelay");
            String paramNotificationTime = prop.getProperty("paramNotificationTime");
            
            result = "paramAnswers = " + paramAnswers + "\n" 
                    + "paramNotificationDelay = " + paramNotificationDelay + "\n"
                    + "paramNotificationTime = " + paramNotificationTime;
            
            System.out.println(result + "\nRuntime " + time);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
    
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
            //get the property value and print it out
            //result = prop.getProperty("paramAnswers");
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
            //get the property value and print it out
            //result = prop.getProperty("paramNotificationDelay");
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
            //get the property value and print it out
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
