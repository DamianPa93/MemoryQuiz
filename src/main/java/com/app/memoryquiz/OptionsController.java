package com.app.memoryquiz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.configuration.ConfigurationException;

public class OptionsController implements Initializable{

    @FXML
    Slider slider;
    @FXML
    TextField field; //NUMBER-OF-ANSWERS-VIEW
    @FXML
    Slider slider1;
    @FXML
    TextField field1; //NUMBER-OF-ANSWERS-BEFORE-NOTIF
    @FXML 
    Slider slider2;
    @FXML
    TextField field2; //NOTIF-DELAY
    
    private FXMLController parentController;
    
    CrunchifyUpdateConfig upConfig = new CrunchifyUpdateConfig();
    CrunchifyGetPropertyValue properties = new CrunchifyGetPropertyValue();
    private static int SLIDER_INIT_VALUE;// = 3;
    private static int SLIDER_1_INIT_VALUE;// = 5;
    private static int SLIDER_2_INIT_VALUE;// = 5;
    public int paramAnswers;
    public int paramNotificationTime;
    public int paramNotificationDelay;
    
    public void setParentController(FXMLController parentController){ //set options params to parent controller
        this.parentController = parentController;
    }
    
    /*@Override
    public void initialize(URL location, ResourceBundle resources) { 
        upConfig = new CrunchifyUpdateConfig();
        properties = new CrunchifyGetPropertyValue();
        try {
        //=====NUMBER-OF-ANSWERS-VIEW=====
        SLIDER_INIT_VALUE = paramAnswers;//properties.getParamAnswersValue();//properties.getParamAnswersValue(); //properties.getParamAnswersValue();
        slider.setValue(SLIDER_INIT_VALUE);
        field.setText(Integer.toString(SLIDER_INIT_VALUE));
        field.textProperty().bind(slider.valueProperty().asString("%.0f")); // slider integer values only
        //=NUMBER-OF-ANSWERS-BEFORE-NOTIF=
        SLIDER_1_INIT_VALUE = properties.getParamNotificationTimeValue();
        slider1.setValue(SLIDER_1_INIT_VALUE);
        field1.setText(Integer.toString(SLIDER_1_INIT_VALUE));
        field1.textProperty().bind(slider1.valueProperty().asString("%.0f")); // slider integer values only
        //===========NOTIF-DELAY==========
        SLIDER_2_INIT_VALUE = properties.getParamNotificationDelayValue();
        slider2.setValue(SLIDER_2_INIT_VALUE);
        field2.setText(Integer.toString(SLIDER_2_INIT_VALUE));
        field2.textProperty().bind(slider2.valueProperty().asString("%.0f")); // slider integer values only
        } catch (IOException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        
        try {
            //=====NUMBER-OF-ANSWERS-VIEW=====
            SLIDER_INIT_VALUE = paramAnswers;//properties.getParamAnswersValue();//properties.getParamAnswersValue(); //properties.getParamAnswersValue();
            slider.setValue(SLIDER_INIT_VALUE);
            field.setText(Integer.toString(SLIDER_INIT_VALUE));
            field.textProperty().bind(slider.valueProperty().asString("%.0f")); // slider integer values only
            //=NUMBER-OF-ANSWERS-BEFORE-NOTIF=
            SLIDER_1_INIT_VALUE = properties.getParamNotificationTimeValue();
            slider1.setValue(SLIDER_1_INIT_VALUE);
            field1.setText(Integer.toString(SLIDER_1_INIT_VALUE));
            field1.textProperty().bind(slider1.valueProperty().asString("%.0f")); // slider integer values only
            //===========NOTIF-DELAY==========
            SLIDER_2_INIT_VALUE = properties.getParamNotificationDelayValue();
            slider2.setValue(SLIDER_2_INIT_VALUE);
            field2.setText(Integer.toString(SLIDER_2_INIT_VALUE));
            field2.textProperty().bind(slider2.valueProperty().asString("%.0f")); // slider integer values only
        } catch (IOException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    private void handleButtonCancelAction(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    /*@FXML
    private void handleButtonSaveAction(ActionEvent event) throws IOException{
        paramAnswers = Integer.parseInt(field.getText());
        paramNotificationTime = Integer.parseInt(field1.getText());
        paramNotificationDelay = Integer.parseInt(field2.getText());
        
        System.out.println(paramAnswers + "::" + paramNotificationTime + "::" + paramNotificationDelay);
        
        parentController.setOptionsParams(paramAnswers, paramNotificationTime, paramNotificationDelay);
        parentController.buttonsOptionsRefresh();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    } */
    
    @FXML
    private void handleButtonSaveAction(ActionEvent event) throws IOException, ConfigurationException{
        paramAnswers = Integer.parseInt(field.getText());
        paramNotificationTime = Integer.parseInt(field1.getText());
        paramNotificationDelay = Integer.parseInt(field2.getText());
        
        System.out.println(paramAnswers + "::" + paramNotificationTime + "::" + paramNotificationDelay);
        
        parentController.setOptionsParams(paramAnswers, paramNotificationTime, paramNotificationDelay);
        
        upConfig.update(paramAnswers, paramNotificationTime, paramNotificationDelay);
        parentController.buttonsOptionsRefresh();
        properties = new CrunchifyGetPropertyValue();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    public void setOptionsParams(int paramAnswers,int paramNotificationTime, int paramNotificationDelay){
        this.paramAnswers = paramAnswers; //set how many buttons with answer should be shown
        this.paramNotificationTime = paramNotificationTime; //how many questions must by answered before result notification
        this.paramNotificationDelay = paramNotificationDelay; //how much does notification hang before disaper
    }
}
