package com.app.memoryquiz;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Lexicon;
import model.LexiconLogic;
import org.controlsfx.control.Notifications;

public class FXMLController implements Initializable {
    //============================BUTTONS-LABEL-LOGIC===========================
    @FXML
    private Label label;  
    @FXML
    private HBox hbboxx;
    
    LexiconLogic lexiconLogic = new LexiconLogic();
    Lexicon labelLexicon;
    List<Lexicon> buttonLexicon;
    List<Lexicon> wrongAnswers = new ArrayList<Lexicon>();
    List<Lexicon> correctAnswers = new ArrayList<Lexicon>();
    List<Button> buttonList = new ArrayList<Button>();
    int wordCounter = 3; //button spawn count
    int questionCounter = 5; //how many spawns
    int questionCounterInit = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lexiconLogic.fillLexicon();
        initializeButtonLogic();
    }
    
    private void initializeButtonLogic(){
        randomization();
        initializeButtons();
        initializeQuiz();
    }
    
    private void randomization(){
        List<Lexicon> tempList;
        tempList = lexiconLogic.rollQuiz(wordCounter);
        Random rand = new Random();
        labelLexicon = tempList.get(rand.nextInt(tempList.size()));
        buttonLexicon = tempList;
    }
    
    private void initializeButtons(){
        for(final Lexicon x: buttonLexicon){
            Button button = new Button();
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle (ActionEvent e){
                   if(x.id==labelLexicon.id){
                       System.out.println(initializeButtonsNotification("Correct"));
                   } else {
                       wrongAnswers.add(x);
                       correctAnswers.add(labelLexicon);
                       System.out.println(initializeButtonsNotification("Fail"));
                       System.out.println(wrongAnswers.size() + " " + correctAnswers.size());
                   }
                }
            });
            button.prefWidthProperty().bind(hbboxx.widthProperty());
            button.prefHeightProperty().bind(hbboxx.heightProperty());
            buttonList.add(button);
            hbboxx.getChildren().clear();
            hbboxx.getChildren().addAll(buttonList);
        }
    }
    
    private String initializeButtonsNotification(String message){
        questionCounterInit++;
        buttonList.clear();
        initializeButtonLogic();
        resultNotification();
        return message;
    }
    
    private void initializeQuiz(){
        label.setText(labelLexicon.word);
        label.setAlignment(Pos.CENTER);
        
        int n = 0;
        for(Button x: buttonList){
            x.setText(buttonLexicon.get(n).translation);
            n++;
        }   
    }
    
    private void resultNotification(){
        Image img = new Image("/icons/ok_img1.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Result")
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
        if(questionCounterInit==questionCounter && wrongAnswers.size()==0){
            notificationBuilder.text("Success!");
            notificationBuilder.show();
            questionCounterInit=0;
        }
        else if(questionCounterInit==questionCounter){
            StringBuilder sb = new StringBuilder();
            sb.append("Mistakes:");
            for(int i=0; i<wrongAnswers.size(); i++){
                sb.append("\n" + correctAnswers.get(i).word + " to: " 
                        + correctAnswers.get(i).translation
                        + ", a nie : " + wrongAnswers.get(i).translation 
                        + "( " + wrongAnswers.get(i).word.toUpperCase() + " )");
            }
            notificationBuilder.text(sb.toString());
            notificationBuilder.show();
            questionCounterInit=0;
            wrongAnswers.clear();
            correctAnswers.clear();
        }           
    }
    //=================================MENU-BAR=================================
    @FXML
    private MenuItem menuItemClose;
    
    @FXML
    private void handleMenuItemCloseAction(ActionEvent event){
        Platform.exit();
    }
}
