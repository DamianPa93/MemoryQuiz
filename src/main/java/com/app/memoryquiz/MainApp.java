package com.app.memoryquiz;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        
        root.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                stage.setX(event.getScreenX()-xOffset);
                stage.setY(event.getScreenY()-yOffset);
            }
        }); 
        stage.setTitle("Memory Quiz");
        stage.setScene(scene);
        //stage.setResizable(true);
        stage.show(); 
        
        //====================
        /*Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/dark-theme.css"); //Styles
        
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Memory Quiz");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();*/
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
