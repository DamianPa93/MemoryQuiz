package com.app.memoryquiz;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Lexicon;
import model.LexiconLogic;

public class FXMLController implements Initializable {
    @FXML
    private Label label;  
    @FXML
    private HBox hbboxx;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    
    LexiconLogic lexiconLogic = new LexiconLogic();
    Lexicon labelLexicon;
    List<Lexicon> buttonLexicon;
    List<Button> buttonList = new ArrayList<Button>();
    int wordCounter = 2;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        if(buttonLexicon.get(0).id == labelLexicon.id){
            System.out.println("CORRECT");
            initializeQuiz();
        }
    }
    
    @FXML
    private void handleButtonAction1(ActionEvent event) {
        System.out.println("You clicked me! --1");
        if(buttonLexicon.get(1).id == labelLexicon.id){
            System.out.println("CORRECT");
            initializeQuiz();
        }
    }
    
    @FXML
    private void handleButtonAction2(ActionEvent event) {
        System.out.println("You clicked me! --2");
        if(buttonLexicon.get(2).id == labelLexicon.id){
            System.out.println("CORRECT");
            initializeQuiz();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lexiconLogic.fillLexicon();
        //initializeButtons();
        buttonTest(wordCounter);
        initializeQuiz();
    }
    
    private void initializeQuiz(){
        randomization();
        
        label.setText(labelLexicon.word);
        
        int n = 0;
        for(Button x: buttonList){
            x.setText(buttonLexicon.get(n).translation);
            n++;
        }   
    }
    
    private void randomization(){
        List<Lexicon> tempList;
        tempList = lexiconLogic.rollQuiz(wordCounter);  // 3
        Random rand = new Random();
        labelLexicon = tempList.get(rand.nextInt(tempList.size()));
        buttonLexicon = tempList;
    }
    
    /*private void initializeButtons(){
        /*buttonList.add(button);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonTest(wordCounter); //3
    } */
    
    
    
    private void buttonTest(int n){
        for(int i =0; i<n; i++){
            Button button = new Button();
            
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle (ActionEvent e){
                    System.out.println(labelLexicon.id);
                }
            });
            buttonList.add(button);
            hbboxx.getChildren().clear();
            hbboxx.getChildren().addAll(buttonList);
        }
    }
}
