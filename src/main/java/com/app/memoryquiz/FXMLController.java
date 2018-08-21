package com.app.memoryquiz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.Lexicon;
import model.LexiconLogic;
import org.controlsfx.control.Notifications;

public class FXMLController implements Initializable {
    @FXML
    private Label label;  
    @FXML
    private HBox hbboxx;
    
    LexiconLogic lexiconLogic = new LexiconLogic();
    Lexicon labelLexicon;
    List<Lexicon> buttonLexicon;
    List<Button> buttonList = new ArrayList<Button>();
    int wordCounter = 4; //button spawn count
    int questionCounter = 10; //how many spawns
    int questionCounterInit = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lexiconLogic.fillLexicon();
        initializeButtonLogic();
    }
    
    private void initializeButtonLogic(){
        randomization();
        initializeButtons(wordCounter);
        initializeQuiz();
    }
    
    private void randomization(){
        List<Lexicon> tempList;
        tempList = lexiconLogic.rollQuiz(wordCounter);
        Random rand = new Random();
        labelLexicon = tempList.get(rand.nextInt(tempList.size()));
        buttonLexicon = tempList;
    }
    
    private void initializeButtons(int n){
        for(final Lexicon x: buttonLexicon){
            Button button = new Button();
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle (ActionEvent e){
                   if(x.id==labelLexicon.id){
                       questionCounterInit++;
                       System.out.println(questionCounterInit);
                       System.out.println("Correct");
                       buttonList.clear();
                       initializeButtonLogic();
                       result();
                   } else {
                       questionCounterInit++;
                       System.out.println(questionCounterInit);
                       System.out.println("Fail");
                       buttonList.clear();
                       initializeButtonLogic();
                       result();
                   }
                }
            });
            buttonList.add(button);
            hbboxx.getChildren().clear();
            hbboxx.getChildren().addAll(buttonList);
        }
    }
    
    private void initializeQuiz(){
        label.setText(labelLexicon.word);
        
        int n = 0;
        for(Button x: buttonList){
            x.setText(buttonLexicon.get(n).translation);
            n++;
        }   
    }
    
    private void result(){
        Image img = new Image("/icons/ok_img1.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Result")
                .text("Mistakes:")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        System.out.println("Clicked on notification");
                    }
                });
        notificationBuilder.darkStyle();
        if(questionCounterInit==questionCounter){
            notificationBuilder.show();
            questionCounterInit=0;
        }           
    }
}
