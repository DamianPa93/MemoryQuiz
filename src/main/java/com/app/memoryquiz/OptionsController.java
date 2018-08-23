package com.app.memoryquiz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OptionsController implements Initializable{

    @FXML
    Slider slider;
    @FXML
    TextField field;
    @FXML
    Slider slider1;
    @FXML
    TextField field1;
    @FXML 
    Slider slider2;
    @FXML
    TextField field2;
    
    FXMLController parentController = new FXMLController();
    
    private static final int SLIDER_INIT_VALUE = 2;
    private static final int SLIDER_1_INIT_VALUE = 5;
    private static final int SLIDER_2_INIT_VALUE = 5;
    private int paramAnswers;// = Integer.parseInt(field.toString());
    private int paramNotificationTime;// = Integer.parseInt(field1.toString());
    private int paramNotificationDelay;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //=====NUMBER-OF-ANSWERS-VIEW=====
       //slider.setSnapToTicks(false);
       slider.setValue(SLIDER_INIT_VALUE);
       field.setText(Integer.toString(SLIDER_INIT_VALUE));
       field.textProperty().bind(slider.valueProperty().asString("%.0f")); // slider integer values only
       //=NUMBER-OF-ANSWERS-BEFORE-NOTIF=
       slider1.setValue(SLIDER_1_INIT_VALUE);
       field1.setText(Integer.toString(SLIDER_1_INIT_VALUE));
       field1.textProperty().bind(slider1.valueProperty().asString("%.0f")); // slider integer values only
       //=NUMBER-OF-ANSWERS-BEFORE-NOTIF=
       slider2.setValue(SLIDER_2_INIT_VALUE);
       field2.setText(Integer.toString(SLIDER_2_INIT_VALUE));
       field2.textProperty().bind(slider2.valueProperty().asString("%.0f")); // slider integer values only
    }
    
    @FXML
    private void handleButtonSaveAction(ActionEvent event) throws IOException{
        System.out.println("Accept");
        
        //((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    
    } 
}
