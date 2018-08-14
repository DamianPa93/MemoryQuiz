package com.app.memoryquiz;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import model.Lexicon;
import model.LexiconLogic;

public class FXMLController implements Initializable {
    
    LexiconLogic lexiconLogic = new LexiconLogic();
    
    @FXML
    private Label label;
    
    @FXML
    private Button button2;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    private void handleButtonAction1(ActionEvent event) {
        List<Lexicon> temp = new ArrayList<Lexicon>();
        temp = lexiconLogic.rollQuiz();
        label.setText(temp.get(0).translation);
        System.out.println("----");
        for(Lexicon x: temp){
            System.out.println(x.translation);
        }
    }
    
    @FXML
    private void handleButtonAction2(ActionEvent event) {
        System.out.println("You clicked me! --2");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lexiconLogic.fillLexicon();
        button2.setText(lexiconLogic.rollValue(lexiconLogic.lexiconList).word);
    }    
}
