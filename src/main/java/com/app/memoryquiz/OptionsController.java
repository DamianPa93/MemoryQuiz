package com.app.memoryquiz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    private static int SLIDER_INIT_VALUE = 3;
    private static int SLIDER_1_INIT_VALUE = 5;
    private static int SLIDER_2_INIT_VALUE = 5;
    private int paramAnswers;
    private int paramNotificationTime;
    private int paramNotificationDelay;
    
    public void setParentController(FXMLController parentController){ //set options params to parent controller
        this.parentController = parentController;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { //URL location, ResourceBundle resources
       //=====NUMBER-OF-ANSWERS-VIEW=====
       slider.setValue(SLIDER_INIT_VALUE);
       field.setText(Integer.toString(SLIDER_INIT_VALUE));
       field.textProperty().bind(slider.valueProperty().asString("%.0f")); // slider integer values only
       //=NUMBER-OF-ANSWERS-BEFORE-NOTIF=
       slider1.setValue(SLIDER_1_INIT_VALUE);
       field1.setText(Integer.toString(SLIDER_1_INIT_VALUE));
       field1.textProperty().bind(slider1.valueProperty().asString("%.0f")); // slider integer values only
       //===========NOTIF-DELAY==========
       slider2.setValue(SLIDER_2_INIT_VALUE);
       field2.setText(Integer.toString(SLIDER_2_INIT_VALUE));
       field2.textProperty().bind(slider2.valueProperty().asString("%.0f")); // slider integer values only
    } 
    
    @FXML
    private void handleButtonCancelAction(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    private void handleButtonSaveAction(ActionEvent event) throws IOException{
        paramAnswers = Integer.parseInt(field.getText());
        paramNotificationTime = Integer.parseInt(field1.getText());
        paramNotificationDelay = Integer.parseInt(field2.getText());
        
        System.out.println(paramAnswers + "::" + paramNotificationTime + "::" + paramNotificationDelay);
        
        parentController.setOptionsParams(paramAnswers, paramNotificationTime, paramNotificationDelay);
        parentController.buttonsOptionsRefresh();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    } 
}
