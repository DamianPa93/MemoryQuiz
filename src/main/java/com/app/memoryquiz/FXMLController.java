package com.app.memoryquiz;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Lexicon;
import model.LexiconLogic;
import org.controlsfx.control.Notifications;



public class FXMLController implements Initializable {
    //============================BUTTONS-LABEL-LOGIC===========================
    @FXML
    private Label label;//Question text label for buttons answers
    @FXML
    private HBox hbboxx;//Keep together generated buttons
    
    CrunchifyGetPropertyValue properties = new CrunchifyGetPropertyValue();
    LexiconLogic lexiconLogic = new LexiconLogic(); //Main controller logic, mostly responsible for random answers generation
    Lexicon labelLexicon; //Lexicon which value is added to label.setText()
    List<Lexicon> buttonLexicon; //list of rnd Lexicons which are added to button (at least one is equal to labelLexicon)
    List<Lexicon> wrongAnswers = new ArrayList<Lexicon>(); //list of wrong answers per run
    List<Lexicon> correctAnswers = new ArrayList<Lexicon>(); //list of corrected answers per run
    public List<Button> buttonList = new ArrayList<Button>(); //list of button spawned
    
    //public int paramAnswers = 3; //button spawn count
    public int paramAnswers;
    public int paramNotificationTime; //how many spawns
    public int paramNotificationDelay; //notifitcation delay
    int questionCounterInit = 0;//how many questions arleady loead
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //
            paramAnswers = properties.getParamAnswersValue();
            paramNotificationTime = properties.getParamNotificationTimeValue();
            paramNotificationDelay = properties.getParamNotificationDelayValue();
            //
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            lexiconLogic.fillLexicon(); //generate sample "database"
            initializeButtonLogic(); //simple: roll answers,spawn buttons and set buttons text to randoms from lexiconLogic
            showCurrentStep();
        
    }
    
    public void initializeButtonLogic(){
        randomization();
        initializeButtons();
        initializeQuiz();
    }
    
    private void randomization(){
        List<Lexicon> tempList;
        tempList = lexiconLogic.rollQuiz(paramAnswers); //get random Lexicon objects depends on paramAnswers value
        Random rand = new Random();
        labelLexicon = tempList.get(rand.nextInt(tempList.size())); //set labelLexicon as one from randoms ones futher will exist as label.setText()
        buttonLexicon = tempList; //set buttonLexicon list as possible answers from all initialized random list
    }
    
    private void initializeButtons(){
        for(final Lexicon x: buttonLexicon){
            Button button = new Button();
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle (ActionEvent e){
                   if(x.id==labelLexicon.id){
                       System.out.println(initializeButtonsNotification("Correct"));
                       showCurrentStep();
                   } else {
                       wrongAnswers.add(x); //add Lexicon which didnt match labelLexicon
                       correctAnswers.add(labelLexicon);  //add actual labelLexicon to list so we can compare them in notification
                       System.out.println(initializeButtonsNotification("Fail"));
                       showCurrentStep();
                       System.out.println(wrongAnswers.size() + " " + correctAnswers.size());
                   }
                }
            });
            button.prefWidthProperty().bind(hbboxx.widthProperty());
            button.prefHeightProperty().bind(hbboxx.heightProperty());
            buttonList.add(button);
            hbboxx.getChildren().clear(); //clear buttons so we can spawn another ones with new values
            hbboxx.getChildren().addAll(buttonList); //add new created buttons to view
        }
    }
    
    private String initializeButtonsNotification(String message){
        questionCounterInit++; 
        buttonList.clear();
        initializeButtonLogic();
        resultNotification();
        return message;
    }
    
    private void initializeQuiz(){ //set labelText and buttonsText
        label.setText(labelLexicon.word);
        label.setAlignment(Pos.CENTER);
        
        int n = 0;
        for(Button x: buttonList){
            x.setText(buttonLexicon.get(n).translation);
            n++;
        }   
    }
    
    private void resultNotification(){  //show notification based on answers and above logic
        Image img = new Image("/icons/ok_img1.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Result")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(paramNotificationDelay))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        System.out.println("Clicked on notification");
                    }
                });
        notificationBuilder.darkStyle();
        if(questionCounterInit==paramNotificationTime && wrongAnswers.size()==0){
            notificationBuilder.text("Success!");
            notificationBuilder.show();
            questionCounterInit=0;
        }
        else if(questionCounterInit==paramNotificationTime){
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
    
    public void buttonsOptionsRefresh(){
        buttonList.clear();
        initializeButtonLogic();
        showCurrentStep();
    }
    //=================================MENU-BAR=================================
    @FXML
    private Label labelScore;//Keep actual number of question answered in cycle
    
    public void setOptionsParams(int paramAnswers,int paramNotificationTime, int paramNotificationDelay){
        this.paramAnswers = paramAnswers; //set how many buttons with answer should be shown
        this.paramNotificationTime = paramNotificationTime; //how many questions must by answered before result notification
        this.paramNotificationDelay = paramNotificationDelay; //how much does notification hang before disaper
    }
    
    @FXML //temporary just for test
    private void handleMenuItemParams(ActionEvent event){
        //System.out.println("FXMLController:" + paramAnswers + "::" + paramNotificationTime + "::" + paramNotificationDelay);
        try {
            //CrunchifyGetPropertyValue properties = new CrunchifyGetPropertyValue();
            //properties.getPropValues();
            properties.getParamAnswersValue();
            properties.getParamNotificationDelayValue();
            properties.getParamNotificationTimeValue();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleMenuItemCloseAction(ActionEvent event){
        Platform.exit();
    }
    
    @FXML
    private void handleMenuItemOptionsAction(ActionEvent event) throws IOException{
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLController.class.getResource("/fxml/Options.fxml"));
        
        Parent root = loader.load();
        
        OptionsController controller = loader.getController(); //passing values through controllers
        controller.setParentController(this);
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
        //OptionsController controller = loader.getController(); //passing values through controllers
        //controller.setParentController(this);
    }
    
    private void showCurrentStep(){
        labelScore.setText(questionCounterInit + "/" + paramNotificationTime);
    }
    
    //============================SAVE&LOAD-OPTIONS=============================
}

