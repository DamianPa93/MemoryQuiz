package com.app.memoryquiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CrunchifyGetPropertyValue {
    String result = "";
    int value = 0;
    InputStream inputStream;
    
    public int getConfigProperties(String map) throws IOException{
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            inputStream = this.getClass().getResourceAsStream("/res/config.properties");
            
            if(inputStream != null){
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");               
            }
            value = Integer.parseInt(prop.getProperty(map.toString()));
            System.out.println(value);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return value;
    }
}